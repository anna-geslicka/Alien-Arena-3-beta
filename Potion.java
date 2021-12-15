public class Potion extends Stuff {
    private final int healAmount;

    public Potion() {
        this.name = "First Aid Kit";
        this.healAmount = 40;
        this.weight = 5;
    }

    public int getHealAmount(){
        return this.healAmount;
    }

    @Override
    public String toString() {
        return this.name;
    }
}