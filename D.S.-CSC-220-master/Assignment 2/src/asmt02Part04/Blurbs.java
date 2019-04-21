package asmt02Part04;

/**
 *
 * @author JavaF
 */
import java.util.Scanner;

public class Blurbs {

    /**
     * Generates a series of Blurbs (a word in an alien language).
     *
     * @param args
     */
    public static void main(String args[]) {
        BlurbGenerator blurbMaker = new BlurbGenerator();
        Scanner scan = new Scanner(System.in);

        System.out.println("How many blurbs would you like? ");
        int numBlurbs = scan.nextInt();

        for (int i = 1; i <= numBlurbs; i++) {
            System.out.println("Blurb #" + i + ": " + blurbMaker.makeBlurb());
        }
    }
}
