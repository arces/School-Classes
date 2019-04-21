import java.io.*;
import java.util.*;

/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number - A19013
 @version - V2.0
 @prgm.usage - directly from the OS
 @screenprint - <a href='A19013.gif'>Screen Print</a>
 @see <br><a target='_blank' href='http://www.w3schools.com/html/'>W3 HTML Site</a>
 @HTMLPAGE <a href='stations.htm'> Stations HTML page</a>
 */

public class A19013 {

    private DBCreate dbc = new DBCreate();
    public static void main(String[] args){
        A19013 a = new A19013();
        a.main();

    }


    private void main(){
        //turns the logger on
        try {
            dbc.setLogger("data\\logger.txt");
            log("Log started ");
            logEq();

            //will create the weather database and log it
            dbc.openConnection("weather");
            log("Section 1 - Create Database if needed.");
            logEq();

            //Will drop the stations table and log it
            dbc.execute("DROP TABLE stations");
            log("Section 2 - Drop Stations.");
            log("DROP TABLE stations");
            logEq();

            //Will create a table and log it
            dbc.execute(dbc.createTableSQL("data\\schema_stations.txt"));
            log("Section 3 - Creating the Table.");
            log(dbc.createTableSQL("data\\schema_stations.txt"));
            logEq();

            log("Section 4 - Show Field Names");
            log(dbc.getFieldName("STATIONS", 0));

            logEq();
            log("Section 5 - Populate DB with USA Stations");
            USAStationsRead();

            logEq();

            log("Section 6 - Populate DB with FBIN data");
            logSID();

            logEq();
            log("Section 7 - Create a Web Page");


            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("doc\\stations.htm"));
                bw.write(makeHTMLPage());
                bw.newLine();
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logEq();
            log("Done");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logEq(){
        dbc.status("===================================");
    }
    private void log(String s){
        dbc.status(s);
    }
    private void USAStationsRead(){
        try {
            File f = new File("data\\USAStations.txt");
            //makes a scanner
            Scanner scan = new Scanner(f);
            //makes a temp string
            String tempz = "";
            StringBuilder sb = new StringBuilder();
            //while scanner has a next line
            while (scan.hasNext()) {
                //if the scan has a next line, extra line of defense
                if (scan.hasNext()) {
                    //temp now = the next line of scan
                    tempz = scan.nextLine();
                    //If the first 2 numbers are 73 or 74 which is the code for the USA
                    if (tempz.substring(7, 8).equals("K")) {
                        //makes a new int for each ;

                        //PLEASE NOTE there is a better and faster way of using .split(";") but I originally wrote it this way and
                        //if its not broken then don't fix it.

                        int one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;
                        //Will get the index of each ; in the string
                        one = tempz.indexOf(";");
                        two = tempz.indexOf(";", one + 1);
                        three = tempz.indexOf(";", two + 1);
                        four = tempz.indexOf(";", three + 1);
                        five = tempz.indexOf(";", four + 1);
                        six = tempz.indexOf(";", five + 1);
                        seven = tempz.indexOf(";", six + 1);
                        eight = tempz.indexOf(";", seven + 1);
                        nine = tempz.indexOf(";", eight + 1);
                        ten = tempz.indexOf(";", nine + 1);
                        eleven = tempz.indexOf(";", ten + 1);
                        twelve = tempz.indexOf(";", eleven + 1);

                        //will append the base SQL command to the String builder
                        sb.append("INSERT INTO STATIONS (stationid, stationname, state, latitude, longitude, elevation) VALUES (");
                        //Station ID
                        sb.append("'" + tempz.substring(two + 2, three) + "',");
                        //if the name is longer than 50 characters
                        if (tempz.substring(three + 1, four).length() > 50) {
                            //will remove the ' from the name since SQL doesn't like ' in the commands
                            String add = "'" + tempz.substring(three + 1, three + 48).replace("'", "") + "',";
                            //will replace the , in the string with ""
                            add = add.replace(",", "");
                            //adds a , at the end since if you add it before it will get removed my the previs command
                            add = add + ",";
                            //appends the command to the string builder
                            sb.append(add);
                        } else {
                            //will remove the ' from the name since SQL doesn't like ' in the commands
                            String add = "'" + tempz.substring(three + 1, four).replace("'", "") + "', ";
                            //will replace the , in the string with ""
                            add = add.replace(",", "");

                            //adds a , at the end since if you add it before it will get removed my the previs command
                            add = add + ",";
                            //appends the command to the string builder
                            sb.append(add);
                        }
                        //adds the state
                        sb.append("'" + tempz.substring(four + 1, five) + "', ");
                        //adds the lat
                        sb.append("'" + tempz.substring(seven + 1, eight) + "', ");
                        //adds the long
                        sb.append("'" + tempz.substring(eight + 1, nine) + "', ");
                        //adds the elevation AND a ) at the end
                        sb.append("'" + tempz.substring(eleven + 1, twelve) + "')");
                        // will execute the string builder command
                        dbc.execute(sb.toString());
                        log(tempz.substring(two+1,three));


                        //will reset the string builder to nothing so it can start fresh
                        sb.setLength(0);
                    }
                } else {
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void logSID(){
        dbc.query("SELECT * FROM STATIONS");
        while (dbc.moreRecords()) {
            log("K"+dbc.getField("stationid"));
        }

    }

    private String makeHTMLPage(){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<table border='1' cellpadding='5' cellspacing='0'>\n<tr><th>ID</th><th>STATIONID</th><th>STATIONNAME</th><th>STATE</th><th>LATITUDE</th><th>LONGITUDE</th><th>ELEVATION</th><th>WINDSALOFT</th><th>CITY</th><th>TEMPERATURE</th><th>HUMIDITY</th><th>WINDSPEED</th><th>WINDDIRECTION</th><th>PRESSURE</th><th>DEWPOINT</th></tr>");

        int i =0;
        String dewpoint, humidity, city, pressure, temp, windspeed, windirection, windal;
        //will query the database
        dbc.query("SELECT * FROM STATIONS ORDER BY stationid");
        //will keep going if there are anymore records
        XMLRead myXML = new XMLRead();
        while (dbc.moreRecords()) {
            dewpoint="NA";
            humidity="NA";
            city="NA";
            pressure="NA";
            temp="NA";
            windspeed="NA";
            windirection="NA";
            windal="NA";
            try {

                myXML.loadPage("http://w1.weather.gov/xml/current_obs/" +"K"+ dbc.getField("stationid") + ".xml");
                myXML.setTable("current_observation");
                dewpoint=myXML.getField("dewpoint_c");
                humidity=myXML.getField("relative_humidity");
                city=myXML.getField("location").substring(0, myXML.getField("location").indexOf(","));
                pressure= myXML.getField("pressure_in");
                windirection= myXML.getField("wind_degrees");
                windspeed=myXML.getField("wind_mph");
                temp=myXML.getField("temp_f");
                windal=myXML.getField("wind_kt");
            } catch (Exception e) {

            }
            try {
                //will add the stationid and stationname vars to the combo box
                sb.append("<tr>" + "<td> " + String.valueOf(i) + "</td>" + "<td> " + dbc.getField("stationid") + "</td>" + "<td> " + dbc.getField("stationname") + "</td>" + "<td> " + dbc.getField("state") + "</td>" + "<td> " + dbc.getField("latitude") + "</td>" + "<td> " + dbc.getField("longitude") + "</td>" + "<td> " + dbc.getField("elevation") + "</td>" + "<td> " + windal + "</td>" + "<td> " + city + "</td>" + "<td> " + temp + "</td>" + "<td> " + humidity + "</td>" + "<td> " + windspeed + "</td>" + "<td> " + windirection + "</td>" + "<td> " + pressure + "</td>" + "<td> " + dewpoint + "</td>");

            }
            catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }

        sb.append("</table></html>");
        return sb.toString();
    }
}
