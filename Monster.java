public class Monster {
    String name;
    double health;
    int attackPower;
    int attackChance;
    int resistance;

    public Monster(String name) {
        this.name = name;
        setStats();
    }

    private void setStats(){
        if (this.name.equals("Void Parasite")){
            this.health = 50;
            this.attackPower = 26;
            this.attackChance = 70;
        }
        if (this.name.equals("Mind Eater")){
            this.health = 80;
            this.attackPower = 34;
            this.attackChance = 60;
            this.resistance = 5;
        }
        if (this.name.equals("Xenomorph")){
            this.health = 150;
            this.attackPower = 32;
            this.attackChance = 80;
            this.resistance = 10;
        }
    }

    public int attack(){
        int damage = (int)((Math.random()*(this.attackPower-(this.attackPower/2)))+(this.attackPower/2));
        int chance = (int)(Math.random()*101);
        if (chance < this.attackChance)
            return damage;
        else
            return 0;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public void hurt(int damage){
        this.health -= damage;
    }

    public String phrase(int damage){
        if (this.name.equals("Void Parasite")){
            if (damage <= 17)
                return (this.name + " bites you, with its sharp teeth! It inflicts " + damage + " damage!");
            else if (damage <= 22)
                return (this.name + " strikes you, with deadly claws! It inflicts " + damage + " damage!");
            else
                return (this.name + " stabs you, with pointy sting! It inflicts " + damage + " damage!");
        }
        if (this.name.equals("Mind Eater")){
            if (damage <= 20)
                return (this.name + " slaps you with its biotic tail! It inflicts " + damage + " damage!");
            else if (damage <= 30)
                return (this.name + " strikes you with psionic wave! It inflicts " + damage + " damage!");
            else
                return (this.name + " attacks you with it's piercing scream! It inflicts " + damage + " damage!");
        }
        if (this.name.equals("Xenomorph")) {
            if (damage <= 18)
                return (this.name + " bites with many of his deadly fangs. He tears your flesh and tissues. He inflicts " + damage + " damage!");
            else if (damage <= 27)
                return (this.name + " spits on you, with his venomous saliva. It smells like rotten meat. He inflicts " + damage + " damage!");
            else
                return (this.name + " lift you with his long tail. He takes a swing, and throws you at the wall. He inflicts " + damage + " damage!");
        }
        return "";
    }

    @Override
    public String toString(){
        return "Monster: [Name: " + this.name + " Health: " + this.health + " Attack chance: " + this.attackChance + " Attack power: " + this.attackPower + "]";
    }
}
