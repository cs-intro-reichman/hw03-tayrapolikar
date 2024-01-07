public class UniqueChars {
    public static void main(String[] args) {
        String input = args[0];
        int length = input.length();
        String str = "";

        for (int i = 0; i < length; i++) {
            char currentChar = input.charAt(i);

            if (str.indexOf(currentChar) == -1 || currentChar == ' ') {
                str = str + currentChar;
            }
        }

        System.out.println(str);
    }
}
