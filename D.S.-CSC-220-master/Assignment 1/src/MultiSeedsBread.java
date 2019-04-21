public class MultiSeedsBread extends Bread {

    protected static final double PRICE_PER_ITEM = 1.2;

    public MultiSeedsBread(String name, int amount) {
        super(name, amount);
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
