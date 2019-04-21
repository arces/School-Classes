/**
 * Created by Dan on 10/18/2015.
 */
public class test {
    public static void main(String[]  args){
        String str = "abc456";
        int m =0;
        while (m<6){
            if(!Character.isLetter(str.charAt(m))){
                System.out.println(Character.toUpperCase(str.charAt(m)));
            }
            m++;
        }

        String strr = "red$green&blue#orange";
        String[] tokens = strr.split("[$&#]");
        System.out.println(tokens.toString());
    }
}
