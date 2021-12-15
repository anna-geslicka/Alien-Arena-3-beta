public class Stuff {
    String name;
    int weight;

    public Stuff() {}       //For potions and weapons setting

    public Stuff(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
