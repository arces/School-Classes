public class Tester {

    public static void main(String[] args) {
        try {

            // Price for x of BB
            Bread brownBread = new BrownBread("BB", 5);
            System.out.println("BrownBread: $"
                    + brownBread.calculateTypePrice());

            // Price for x of MB
            Bread multiSeeds = new MultiSeedsBread("MB", 10);
            System.out.println("MultiSeedsBread: $"
                    + multiSeeds.calculateTypePrice());

            // Price for x of RB
            Bread ryeBread = new RyeBread("RB", 15);
            System.out.println("Ryebread: $"
                    + ryeBread.calculateTypePrice());

            // Total Earnings
            double totalEarnings
                    = ryeBread.calculateTypePrice()
                    + brownBread.calculateTypePrice()
                    + multiSeeds.calculateTypePrice();

            System.out.println("Total earnings: $"
                    + totalEarnings);

            // Number of loaves for a given bread type and money amount
            Person person = new Person("BB", 25);
            System.out.println("\nYou may buy " + person.calculatePossibleBreadBought("bb") + " of this type of bread.");

        } catch (IllegalArgumentException illArgExc) {
            System.out.println(illArgExc.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sorry an error occurred");
        }
    }
}
