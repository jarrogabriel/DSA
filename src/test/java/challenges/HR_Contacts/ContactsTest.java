package challenges.HR_Contacts;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactsTest {

    @Test
    void testContactsAddAndFind() {
        List<List<String>> queries = new ArrayList<>();
        queries.add(Stream.of("add", "hack").collect(toList()));
        queries.add(Stream.of("add", "hackerrank").collect(toList()));
        queries.add(Stream.of("find", "hac").collect(toList()));
        queries.add(Stream.of("find", "hak").collect(toList()));

        List<Integer> result = Contacts.contacts(queries);

        assertEquals(2, result.get(0)); // "hac" aparece em "hack" e "hackerrank"
        assertEquals(0, result.get(1)); // "hak" não aparece
    }

    @Test
    void testContactsEmptyQuery() {
        List<List<String>> queries = new ArrayList<>();

        List<Integer> result = Contacts.contacts(queries);

        assertEquals(0, result.size()); // Nenhuma query, nenhum resultado
    }

    @Test
    void testContactsSingleAddAndFind() {
        List<List<String>> queries = new ArrayList<>();
        queries.add(Stream.of("add", "hello").collect(toList()));
        queries.add(Stream.of("find", "he").collect(toList()));

        List<Integer> result = Contacts.contacts(queries);

        assertEquals(1, result.get(0)); // "he" aparece em "hello"
    }

    @Test
    void testContactsMultipleFinds() {
        List<List<String>> queries = new ArrayList<>();
        queries.add(Stream.of("add", "test").collect(toList()));
        queries.add(Stream.of("add", "testing").collect(toList()));
        queries.add(Stream.of("find", "test").collect(toList()));
        queries.add(Stream.of("find", "tes").collect(toList()));
        queries.add(Stream.of("find", "toast").collect(toList()));

        List<Integer> result = Contacts.contacts(queries);

        assertEquals(2, result.get(0)); // "test" aparece em "test" e "testing"
        assertEquals(2, result.get(1)); // "tes" aparece em "test" e "testing"
        assertEquals(0, result.get(2)); // "toast" não aparece
    }
}