import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private Avatar character;

    public Game() {
    }

    public String intro() {
        return "Hello, welcome to Alien Arena 3!   >@_@<\n" +
                "__________________________________________________________________\n" +
                "You and your crew are currently stationing on planet VX-7 in Andromeda System." +
                "This planet is well known for being a birthplace of many great adventurers and warriors." +
                "VX-7 is famous across whole Universe for its prestigious arena fights, organised annually." +
                "As you have been told, arena is dominated by a deadly Xenomorph - unquestioned champion for many years." +
                "Killing this savage creature appears to be a perfect chance to achieve eternal fame and glory." +
                "You are sure, that this fight is your destiny, but for now, you don't seem to be prepared for the deadly struggle." +
                "VX-7 offers some basic services, and opportunity to challenge some weaker opponents.";
    }

    public String classChoice() {
        return "Your standard class is a Space Trooper - great fighter, who explores the farthest boundaries of the Universe." +
                "His special feature is a rapid and deadly Critical Strike, that he performs from time to time." +
                "If you want, you can change your class for an Alien Hunter, who seeks for dangerous space predators." +
                "He is always aware of his opponents greatest weaknesses, and because of this," +
                "he inflicts them a small amount of additional damage, every time he strikes." +
                "You start the game with Galaxy's most common weapon - blaster.\n" +
                "Do you want to change class to Alien Hunter?";
    }

    public String capitalCity() {
        return "You're entering the great capital city - Pandora. What would you like to do?";
    }

    public String tradePlatform() {
        return "While wondering across the city, you're able to see many spectacular monuments, buildings, and Pandora's famous giant trees." +
                "Finally, in the centre of the metropolis, you reach an obsidian platform, designated for trade and entertainment." +
                "-The Mickey's Rifles- sign draws your attention. Seems like this is the place, where you can buy all of the battle necessities." +
                "Another interesting place is the -Robotic Surgery Centre-. The wide board in front of it informs, that they perform cyborgical surgeries." +
                "Where would you like to go?";
    }

    public String arena(){
        return "Finally, you're approaching The Arena. Massive complex casts its shadow over entire surroundings"+
        "Passing through it's Main Hall, you feel intimidated by its massive, black walls reaching out to the sky."+
                "With every step you feel excitement growing in your heart. Now, it's time to prove your worth.\n"+
                "Choose your enemy:";
    }

    public String fightVoidParasite(){
        return "┌∩┐(◣_◢)┌∩┐\nOh no! Its a Void Parasite - savage, mindless beast, that came from the very heart of the Black Hole! Are you sure, you're ready to face it?";
    }

    public String inventoryNoAid() {
        return "Your inventory: \n" + character.showBackpack();
    }

    public String inventoryAid() {
        return inventoryNoAid() + " Do you like to use First Aid Kit?";
    }

    public String mickeysRifles(){
        return "Welcome to the Mickey's Rifles! You have " + character.getWealth() + " Galactic Credits. What would you like to do?";

    }

    public String surgeryCentre(){
        return "Welcome to the Robotic Surgery Centre. You have " + character.getWealth() + " Galactic Credits. What would you like to do?";
    }

    public String setAvatar(int choice) {
        Weapon blaster = new Weapon("Blaster");

        if (choice == 2)
            this.character = new Warrior();
        else
            this.character = new Avatar();

        this.character.setHand(blaster);
        this.character.changeWeapon(blaster);

        return "Your character:\n" + this.character.toString() + "\n";
    }

    public String showStats(){
        return "Your character:\n" + this.character.toString() + "\n";
    }

    public boolean havePotion() {
        boolean isPotion = false;
        for (Stuff stuff : character.getBackpack()) {
            if (stuff instanceof Potion) {
                isPotion = true;
                break;
            }
        }
        return isPotion;
    }

    public String drinkPotion(){
        return character.drinkPotion();
    }

    public String upgradeWeapon(){
        Stuff[] backpack = character.getBackpack();
        int currentWeight = 0;
        int backpackSpace = 4;
        for (Stuff stuff : backpack) {
            if (stuff != null) {
                currentWeight += stuff.weight;
                backpackSpace--;
            }
        }
        Stuff weapon;
        Stuff hand = character.getHand();
        if (character.getWealth() >= 200) {
            switch (hand.name) {
                case "Blaster": {
                    if ((currentWeight + 15) <= character.getCapacity() && backpackSpace > 0) {
                        weapon = new Weapon("Flamethrower");
                        character.setHand(weapon);
                        character.changeWeapon(weapon);
                        character.setWealth(200);
                        return "Weapon upgraded to Flamethrower!";
                    } else
                        return "You can't lift more objects.\n";
                }
                case "Flamethrower": {
                    if ((currentWeight + 20) <= character.getCapacity() && backpackSpace > 0) {
                        weapon = new Weapon("Plasma Gun");
                        character.setHand(weapon);
                        character.changeWeapon(weapon);
                        character.setWealth(200);
                        return "Weapon upgraded to Plasma Gun!";
                    } else
                        return "You can't lift more objects.\n";
                }
                case "Plasma Gun": {
                    return "You already have the best weapon.\n";
                }
            }
        } else {
            return "You don't have enough Galactic Credits :(\n";
        }
        return "";
    }

    public String buyPotion(){
        Stuff[] backpack = character.getBackpack();
        int currentWeight = 0;
        int backpackSpace = 4;
        for (Stuff stuff : backpack) {
            if (stuff != null) {
                currentWeight += stuff.weight;
                backpackSpace--;
            }
        }
        Stuff potion;
        if (character.getWealth() >= 20 && (currentWeight + 5) <= character.getCapacity() && backpackSpace > 0) {
            potion = new Potion();
            character.setWealth(20);
            return character.addToBackpack(potion);
        } else if (character.getWealth() < 20)
           return "You don't have enough Galactic Credits :(\n";
        else
            return "You can't lift more objects.";
    }

    public String upgradeArms() {
        if (character.getWealth() >= 250 && !character.hasRoboticArms()) {
            character.upgradeStrength();
            character.setWealth(250);
            return "After long and painful surgery Robotic Arms are finally attached to your body. \n" +
                    "Now you can lift more weight, and for certain you're better prepared for upcoming fights.\n";
        } else if (character.hasRoboticArms())
            return "You already have this upgrade.\n";
        else
            return "You don't have enough Galactic Credits :(\n";
    }

    public String attachSystems() {
        if (character.getWealth() >= 250 && !character.hasSustainingSystems()) {
            character.upgradeHealth(120);
            character.setWealth(250);
            return "When you wake up from the anesthesia, you can feel, that your body is filled with pipes and wires.\n" +
                    "Your brand new Life Sustaining System is regulating blood pressure and controlling heartbeat.\n";
        } else if (character.hasSustainingSystems())
            return "You already have this upgrade.\n";
        else
            return "You don't have enough Galactic Credits :(\n";
    }

    public ArrayList<String> fight(String monsterName){
        Monster monster = new Monster(monsterName);
        Arena arena = new Arena(character, monster);
        return arena.fight();
    }
}
