package challenges.LeetCodeTests.LC20_ValidBrackets;


import challenges.LeetCode.LC20_ValidBrackets.ValidBrackets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidBracketsTest {

    @Test
    void testValidBrackets() {
        // Test cases for valid brackets
        assertEquals("YES", ValidBrackets.isValid("()"));
        assertEquals("YES", ValidBrackets.isValid("()[]{}"));
        assertEquals("YES", ValidBrackets.isValid("{[()]}"));
    }

    @Test
    void testInvalidBrackets() {
        // Test cases for invalid brackets
        assertEquals("NO", ValidBrackets.isValid("(]"));
        assertEquals("NO", ValidBrackets.isValid("([)]"));
        assertEquals("NO", ValidBrackets.isValid("}[{}])"));
    }

    @Test
    void testEmptyString() {
        // Test case for an empty string
        assertEquals("YES", ValidBrackets.isValid(""));
    }

    @Test
    void testSingleBracket() {
        // Test cases for single unmatched brackets
        assertEquals("NO", ValidBrackets.isValid("("));
        assertEquals("NO", ValidBrackets.isValid(")"));
        assertEquals("NO", ValidBrackets.isValid("{"));
        assertEquals("NO", ValidBrackets.isValid("}"));
    }
}
