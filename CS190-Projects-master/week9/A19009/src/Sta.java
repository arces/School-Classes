/**
 * Created by Dan on 10/25/2015.
 */

/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number -
 @version - V 2.0
 @prgm.usage - Called From the jar file
 @screenprint - <a href='A19009.gif'>Screen Print</a>
 @SubClass <br><a target='_blank' href='https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html'>Combo Box</a>
 */

public enum Sta {
    //sets up the names and corresponding values
    NAME(3), Latitude(7), Longitude(8), Altitude(12), State(5), County(6);
    //temp var
    private int num;

    //constructor
    Sta(int i) {
        num = i;
    }

    //will return the num value
    public int returnNum() {
        return num;
    }
}
