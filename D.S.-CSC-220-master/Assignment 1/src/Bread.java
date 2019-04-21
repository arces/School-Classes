public abstract class Bread {

    private String name;
    private int amount;

    public Bread(String name, int amount) {
        this.setName(name);
        this.setAmount(amount);
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract double calculateTypePrice();
}
