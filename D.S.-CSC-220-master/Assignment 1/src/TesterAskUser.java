import java.util.Scanner;

public class TesterAskUser {

    //Makes program wide vars
    private String name = "";
    private int money = 0;
    private int shopMoney = 0;

    //Default empty constructor
    public TesterAskUser() {
    }

    //main function that calls mainRunFunction()
    public static void main(String[] args) {
        TesterAskUser testAskUser = new TesterAskUser();
        testAskUser.mainRunFunction();
    }

    //The actual main function that is called
    public void mainRunFunction() {
        try {
            //sets up some vars for looping and calls a couple first time functions
            boolean running = true;
            String temp = "";
            firstTimeRun();
            printMenu();

            //starts the main loop that will run until the user enters exit
            while (running) {
                temp = userInput(0);
                //check the user input and matches the input with commands
                if (temp.equals("1") || temp.equals("bread list")) {
                    breadList();

                    //Calls the buy command to buy bread
                } else if (temp.equals("2") || temp.equals("buy")) {
                    breadList();
                    System.out.println("Which bread would you like to buy?");


                    //sets up some vars for use later on
                    double breadPrice = 0.0;
                    boolean breadLoop = true;

                    //loops to find out what kind of bread the user wants to buy
                    while (breadLoop) {
                        String userIn = userInput(0);
                        if (userIn.equals("brown bread") || userIn.equals("1")) {
                            breadPrice = breadPrice("bb");
                            breadLoop = false;
                        } else if (userIn.equals("multi seed bread") || userIn.equals("2")) {
                            breadPrice = breadPrice("mb");
                            breadLoop = false;
                        } else if (userIn.equals("rye bread") || userIn.equals("3")) {
                            breadPrice = breadPrice("rb");
                            breadLoop = false;
                        } else {
                            System.out.println("That is not a bread we sell. Please pick again");
                        }
                    }

                    // loops to find out the number of pieces of bread the user wants to buy
                    System.out.println("How many would you like of that bread?");
                    int maxBread = (int) (money / breadPrice);
                    breadLoop = true;

                    while (breadLoop) {
                        System.out.println("Please enter a number between 0-" + maxBread);
                        temp = userInput(0);
                        if (Integer.parseInt(temp) <= maxBread && Integer.parseInt(temp) >= 0) {
                            double totalBreadPrice = Math.round((double) Integer.parseInt(temp) * breadPrice);
                            System.out.println("You just bought " + Integer.parseInt(temp) + " pieces of bread for $" + totalBreadPrice);
                            money -= totalBreadPrice;
                            shopMoney += totalBreadPrice;
                            breadLoop = false;
                        } else {
                            System.out.println("Sorry that number is to big or to small");
                        }
                    }

                    //more main user commands
                } else if (temp.equals("3") || temp.equals("about")) {
                    System.out.println("Written by Dan Janes for Bread International Inc.");
                } else if (temp.equals("4") || temp.equals("help")) {
                    System.out.println("1. Bread List: Prints out the list of breads for purchase\n2. Buy: Lets you buy bread\n3. About: Prints out the about page\n4. Help: Prints out this page\n5. Print Info: Prints the $ you have and the $ the owner has\n6. Exit: Exits the program");
                } else if (temp.equals("5") || temp.equals("print info")) {
                    System.out.println(name + " has $" + money + " and the owner has $" + shopMoney);
                } else if (temp.equals("6") || temp.equals("exit")) {
                    running = false;
                } else {
                    System.out.println("Sorry that is not a command. Type help if you need help");

                }
                //if the loop is still running then it will print this out
                if (running) {
                    System.out.println("Please enter a command");
                }

            }
            //catches any exception
        } catch (Exception e) {
            System.out.println("Something bad happened. Sorry about that");
        }

    }

    //prints out the breads for sale
    private void breadList() {
        System.out.println("\nToday the bread list is the following:\n(Price is per loaf)\nBrown Bread: $" + breadPrice("bb") + "\nMulti Seed Bread: $" + breadPrice("mb") + "\nRye Bread: $" + breadPrice("rb") + "\n");
    }

    //Prints out the price of the bread. It is dynamic to the current price of the bread
    private double breadPrice(String breadName) {
        BrownBread bb = new BrownBread("", 1);
        MultiSeedsBread mb = new MultiSeedsBread("", 1);
        RyeBread rb = new RyeBread("", 1);
        try {
            if (breadName.equals("bb")) {
                return bb.calculateTypePrice();
            } else if (breadName.equals("mb")) {
                return mb.calculateTypePrice();
            } else if (breadName.equals("rb")) {
                return rb.calculateTypePrice();
            } else {
                return -1.0;
            }
            //Catches any exception
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    // gets user input and puts it to lower case if a 1 in passed threw as the var
    public String userInput(int i) {
        try {
            Scanner scan = new Scanner(System.in);
            if (i == 1) {
                return scan.nextLine();
            } else {
                return scan.nextLine().toLowerCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    //Runs the first time set up of asking what your name is and how much $ do you have
    public void firstTimeRun() {
        try {
            System.out.println("Hello, what is your name?");
            name = userInput(1);
            System.out.println("How much money do you have to the nearest dollar?");
            money = Integer.parseInt(userInput(0));
        } catch (Exception e) {
            e.printStackTrace();
            name = "John";
            money = 10;
        }
    }

    //Prints the menu
    public void printMenu() {
        System.out.println("****************************************\nWelcome " + name + " to the Bread Program\n****************************************\n\nType the name of the command or the number to run it\n\nCommands:\n\t1. Bread List\n\t2. Buy\n\t3. About\n\t4. Help\n\t5. Print Info\n\t6. Exit\n");

    }
}