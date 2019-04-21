public class BrownBread extends Bread {

    protected static final double PRICE_PER_ITEM = 1.1;

    public BrownBread(String name, int amount) {
        super(name, amount);
        //System.out.println(name + " " + amount + " for brownbread");
    }


    public double calculateTypePrice() {
        try {
            return this.getAmount() * PRICE_PER_ITEM;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sorry something went wrong");
            return 0;
        }

    }
}
