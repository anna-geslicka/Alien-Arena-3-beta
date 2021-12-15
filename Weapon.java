public class Weapon extends Stuff {
    int baseAttack;
    int bonus;

    public Weapon(String name) {
        this.name = name;
        setStats();
    }

    public void setStats(){
        switch (this.name) {
            case "Blaster": {
                this.baseAttack = 12;
                this.weight = 10;
                this.bonus = 3;
            }
            case "Flamethrower": {
                this.baseAttack = 15;
                this.weight = 15;
                this.bonus = 2;
            }
            case "Plasma Gun": {
                this.baseAttack = 18;
                this.weight = 20;
                this.bonus = 1;
            }
        }
    }
}
