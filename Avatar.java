public class Avatar {
    protected double health;
    private int strength;
    private Stuff hand = null;
    private int capacity;
    private int wealth;
    private final Stuff[] backpack = new Stuff[4];
    private boolean hasDoneCriticalAttack  = false;
    private boolean hasRoboticArms = false;
    private boolean hasSustainingSystems = false;
    protected double maxHealth;
    protected boolean healedRecently = false;
    protected int criticalAttackChance = 10;

    public Avatar (){
        setHealth();
        setStrength();
        setCapacity();
        setWealth();
    }

    private void setHealth(){                //random health from 80 to 100
        double temp = (Math.random()*21)+80;
        if (temp > 100) {
            this.health = 100;
            this.maxHealth = 100;
        }
        else {
            this.health = temp;
            this.maxHealth = temp;
        }
    }

    private void setStrength(){
        this.strength = (int)((Math.random()*4)+3);       //random strength from 3 to 6
    }

    public String upgradeStrength(){
        this.strength = 8;
        this.capacity = 40;
        return "Strength upgraded to 8\n";
    }

    public String upgradeHealth(int health){
        this.maxHealth = health;
        this.health = health;
       return "Health upgraded to" +health + "HP\n";
    }

    private void setCapacity(){
        this.capacity = 10 + (this.strength*5);     //capacity from 20 to 40
    }

    private void setWealth(){           //wealth from 10 to 100 (smaller health == bigger wealth, for balance
        this.wealth = 12000 - (int)this.health;
    }

    public void setWealth(int expense){
        this.wealth -= expense;
    }

    public void setHand(Stuff stuff) {
        this.hand = stuff;
    }

    public int getWealth(){
        return this.wealth;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public Stuff getHand(){
        return hand;
    }

    public double getHealth(){
        return this.health;
    }

    public int getStrength(){
        return this.strength;
    }

    public boolean getHasDoneCriticalAttack(){
        return this.hasDoneCriticalAttack;
    }

    public int getMaxHealth(){
        return (int)this.maxHealth;
    }

    public boolean hasRoboticArms(){return this.hasRoboticArms;}

    public boolean hasSustainingSystems(){return this.hasSustainingSystems;}

    public Stuff[] getBackpack(){
        return this.backpack;
    }

    public String showBackpack(){
        int num = 0;
        String inventory = "";
        for (Stuff stuff : backpack) {
            if (stuff != null) {
                num++;
                inventory += (num + ". " + stuff.name + "\n");
            }
        }
        return inventory;
    }

    public String drinkPotion(){
        Stuff potion = null;
        for (int i=0; i<backpack.length; i++)
            if (backpack[i] instanceof Potion) {
                potion = backpack[i];
                backpack[i] = null;
                break;
            }
        assert potion != null;
        double healed = this.health + ((Potion)potion).getHealAmount();
        this.health = Math.min(healed, this.maxHealth);

        return "You now have " + (int)getHealth() +" HP.\n";
    }

    public String addToBackpack(Stuff stuff){
        int totalWeight = 0;
        for (Stuff value : backpack) {
            if (value != null)
                totalWeight += value.weight;
        }
        if ((totalWeight + stuff.weight) <= this.capacity) {
            for (int i = 0; i < backpack.length; i++) {
                if (backpack[i] == null) {
                    backpack[i] = stuff;
                    return "You added " + stuff.name + " to backpack.\n";
                }
            }
        }
        return "";
    }

    public void changeWeapon(Stuff weapon){
        this.backpack[0] = weapon;
    }

    public boolean isAlive(){
        return this.health >= 1;
    }

    public int attack() {
        int damage;
        int baseAttack = ((Weapon)getHand()).baseAttack;
        int criticalAttackValue = (int)((Math.random()*6)+25);     //random critical hit from 25 to 30
        int chance = (int)(Math.random()*101);          //10% chance for critical strike
        if (getHand() instanceof Weapon && chance < this.criticalAttackChance) {
            this.hasDoneCriticalAttack = true;
            return criticalAttackValue;
        }
        else if (getHand() instanceof Weapon) {
            this.hasDoneCriticalAttack = false;
            damage = (int)((Math.random()*baseAttack)+(getStrength()));    //random damage form strength to base attack + strength
            return damage;
        }
        else if (getHand() instanceof Potion){
            this.hasDoneCriticalAttack = false;
            this.health += ((Potion) getHand()).getHealAmount();
            return 0;
        }
        else {
            this.hasDoneCriticalAttack = false;
            return 0;
        }
    }

    public void hurt(double damage) {
        this.health -= damage;
    }

    @Override
    public String toString(){
        return "Space Trooper: [Health: " + (int)getHealth() + ", Strength: " + getStrength() +
                ", Galactic credits: " + getWealth() + ", Capacity: " + getCapacity() + ", Weapon: " + getHand().toString() + "]";
    }
}
