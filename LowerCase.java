public class LowerCase {
    public static void main(String[] args) {
        String input=args[0];
        int length=input.length();
        String str="";
        for (int i = 0; i <length; i++) {
            if(input.charAt(i)>='A' && input.charAt(i)<='Z'){
                char chr= (char) (input.charAt(i)+32);
                str=str+chr;
            } else{
                str=str+input.charAt(i);
            }
        }
        System.out.println(str);
    }
}
