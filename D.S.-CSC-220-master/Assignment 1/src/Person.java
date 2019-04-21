public class Person {

    private String name;
    private double money;

    public Person(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    private void setMoney(double money) {
        this.money = money;
    }

    private int cpbbHelper(Bread object) {
        try {
            return (int) Math.floor(money / object.calculateTypePrice());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int calculatePossibleBreadBought(String breadName) {
        try {
            if (breadName.equals("BrownBread") || breadName.equals("bb")) {
                BrownBread b = new BrownBread("bb", 1);
                return cpbbHelper(b);
            } else if (breadName.equals("RyeBread") || breadName.equals("rb")) {
                RyeBread b = new RyeBread("rb", 1);
                return cpbbHelper(b);
            } else if (breadName.equals("MultiSeedBread") || breadName.equals("mb")) {
                MultiSeedsBread b = new MultiSeedsBread("mb", 1);
                return cpbbHelper(b);
            } else {
                return 0;
            }

            //return possibleBuyings;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Uh oh, something went wrong. Keep eating your bread and we will fix it");
            return -1;
        }
    }
}
