package challenges.HackerRank.HR_Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Contacts {


    public static void main(String[] args) {
        // Lista de queries fixa
        List<List<String>> queries = new ArrayList<>();
        queries.add(Stream.of("add", "hack").collect(toList()));
        queries.add(Stream.of("add", "hackerrank").collect(toList()));
        queries.add(Stream.of("find", "hac").collect(toList()));
        queries.add(Stream.of("find", "hak").collect(toList()));

        List<Integer> result = contacts(queries);

        // Imprime o resultado
        result.forEach(System.out::println);
    }

    public static List<Integer> contacts(List<List<String>> queries) {

        Node root = new Node();
        List<Integer> contagemLista = new ArrayList<>();
        for (List<String> comando : queries) {
            if (comando.get(0).equals("add")) {
                root.add(comando.get(1));
            } else if (comando.get(0).equals("find")) {
                contagemLista.add(root.findCount(comando.get(1), 0));
            }
        }
        return contagemLista;
    }

    static class Node {
        Node[] childrens = new Node[26];
        int size = 0;

        void setNode(char current, Node child) {
            childrens[getLetterIndex(current)] = child;
        }

        void add(String word) {
            addLetters(word, 0);
        }

        private void addLetters(String word, int index) {
            size++;
            if (word.length() == index) {
                return;
            }
            char current = word.charAt(index);
            Node child = getChildNode(current);
            if (child == null) {
                child = new Node();
                setNode(current, child);
            }
            child.addLetters(word, index + 1);

        }

        Node getChildNode(char letter) {
            return childrens[getLetterIndex(letter)];
        }

        private int getLetterIndex(char letter) {
            return letter - 'a';
        }

        private int findCount(String word, int index) {
            if (word.length() == index) {
                return size;
            }
            Node childNode = getChildNode(word.charAt(index));
            if (childNode == null) {
                return 0;
            }
            return childNode.findCount(word, index + 1);
        }
    }
}
