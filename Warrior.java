public class Warrior extends Avatar {
    public Warrior(){}

    @Override
    public int attack() {
        int bonus = ((Weapon)getHand()).bonus;
        int damage;
        int baseAttack = ((Weapon)getHand()).baseAttack;
        if (getHand() instanceof Weapon) {
            damage = (int)((Math.random()*baseAttack)+(getStrength()+bonus));    //random damage form strength + bonus to base attack + strength + bonus
            return damage;
        }
        else if (getHand() instanceof Potion){
            this.health += ((Potion) getHand()).getHealAmount();
            System.out.print("You healed for " + ((Potion) getHand()).getHealAmount() + " HP." + "you now have " + getHealth() + " HP!");
            return 0;
        }
        else {
            return 0;
        }
    }


    @Override
    public String toString(){
        return "Alien Hunter: [Health: " + (int)getHealth() + ", Strength: " + getStrength() +
                ", Galactic credits: " + getWealth() + ", Capacity: " + getCapacity() + ", Weapon: " + getHand().toString() + "]";    }
}

