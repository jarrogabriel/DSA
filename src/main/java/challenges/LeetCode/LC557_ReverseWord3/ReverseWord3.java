package challenges.LeetCode.LC557_ReverseWord3;

public class ReverseWord3 {

    public static void main(String[] args) {
        String input = "Let's take LeetCode contest";
        String resultado = reverseWords(input);
        System.out.println("Resultado: " + resultado);
    }


    public static String reverseWords(String s) {
        // implementação a ser feita
        String[] palavras = s.split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String string : palavras) {
            StringBuilder palavra = new StringBuilder().append(string).reverse();
            resultado.append(palavra).append(" ");
        }
        return resultado.deleteCharAt(resultado.length() - 1).toString();
    }
}
