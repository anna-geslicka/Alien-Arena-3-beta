import java.util.ArrayList;

public class Arena {
    Avatar character;
    Monster monster;

    public Arena(Avatar character, Monster monster) {
        this.character = character;
        this.monster = monster;
    }


    public ArrayList<String> fight() {
        String monsterName = this.monster.name;
        String weaponName = this.character.getHand().name;                            // Void Parasite has no resistance
        if (monsterName.equals("Mind Eater") && weaponName.equals("Blaster")) {         //Mind Eater is resistant for Blaster attacks
            return fightBase(this.monster.resistance);                           //Xenomorph is resistant for Blaster & Flamethrower attacks
        } else if (monsterName.equals("Xenomorph") && (weaponName.equals("Blaster") || weaponName.equals("Flamethrower"))) {
            return fightBase(this.monster.resistance);
        } else {
            return fightBase(0);
        }
    }

    public ArrayList<String> fightBase(int resistance) {
        ArrayList<String> fight = new ArrayList<>();
        String characterName = "You";
        String monsterName = this.monster.name;
        Stuff[] backpack = character.getBackpack();
        boolean isPotion = false;
        int potionCount = 0;
        int damage;
        for (Stuff stuff : backpack) {
            if (stuff instanceof Potion)
                potionCount++;
        }

        fight.add("Now entering fight:");
        while (character.isAlive() && monster.isAlive()) {
            if (character.isAlive()) {                     //I thought about choosing randomly, but good guy should start :)
                for (Stuff stuff : backpack) {
                    if (stuff instanceof Potion) {
                        isPotion = true;
                        break;
                    }
                }
                int healOption;
                if (character.getHealth() <= 35 && isPotion)
                    healOption = 1;
                else
                    healOption = 0;
                switch (healOption) {
                    case 1: {
                        fight.add("Oh no! Looks like you're wounded badly. Luckily, you still have a First Aid Kit.");
                        character.drinkPotion();
                        fight.add("you have " + (potionCount - 1) + " First Aid Kits left.");
                        potionCount--;
                        isPotion = false;
                        break;
                    }
                    case 0: {
                        damage = character.attack() - resistance;
                        if (damage < 0)
                            damage = 0;
                        monster.hurt(damage);
                        if (character.getHasDoneCriticalAttack() && monster.health <= 0)
                            fight.add(characterName + " performed CRITICAL STRIKE for " + (damage) + " damage! " + monsterName + " is dead!");
                        else if (character.getHasDoneCriticalAttack())
                            fight.add(characterName + " performed CRITICAL STRIKE for " + (damage) + " damage! " + monsterName + " now has " + monster.health + " HP!");
                        else if (monster.health > 0)
                            fight.add(characterName + " attacks for " + (damage) + " damage! " + monsterName + " now has " + monster.health + " HP! ");
                        else
                            fight.add(characterName + " attacks for " + (damage) + " damage! " + monsterName + " is dead!");
                    }
                }
                if (monster.isAlive()) {
                    damage = monster.attack();
                    character.hurt(damage);
                    if (character.getHealth() <= 0 && damage > 0) {
                        fight.add(monster.phrase(damage));
                        fight.add("You now have " + (int) character.getHealth() + " HP! " + characterName + " is now dead!");
                    } else if (damage > 0) {
                        fight.add(monster.phrase(damage));
                        fight.add("You now have " + (int) character.getHealth() + " HP! ");
                    } else
                        fight.add(monsterName + " missed!");
                }
            }
        }
        if (monster.isAlive()) {
            fight.add(monsterName + " wins!");
            fight.add(gameOver());
        } else {
            fight.add("You win!");
            switch (monsterName) {
                case "Void Parasite":
                    character.setWealth(-80);
                    fight.add("You are leaving The Arena with " + (int) character.getHealth() + " HP, and earned " + 80 + " Galactic Credits. \n" +
                           "You now have " + character.getWealth() + " Galactic Credits.\n");
                    break;
                case "Mind Eater":
                    character.setWealth(-150);
                    fight.add("You are leaving The Arena with " + (int) character.getHealth() + "HP, and earned " + 150 + " Galactic Credits. \n" +
                            "You now have " + character.getWealth() + " Galactic Credits.\n");
                    break;
                case "Xenomorph":
                    fight.add(win());
                    break;
            }
        }
        return fight;
    }

    public String gameOver(){
        return ("__________________________________________________________________\n") +
        "You're in tremendous pain. you can feel, how your vitality is leaving your body."+
                "Slowly loosing free will, you're falling into darkness."+
                "Pain is fading away now, along with sense of shameful defeat..."+
                "You are no more."+
                "Hopefully, you will find some peace in the afterlife.";
    }

    public String win() {
        return "After all the struggle, the Xenomorph is dead. You stare off into the starry sky, gleaming above the Arenas field."+
                "All three of VX-7s suns are slowly setting over the horizon. You spit blood onto the plowed sand beneath your legs."+
                "-Shit... - You mumble, and wipe drops of sweat from your forehead."+
                "-Motherfucker almost kiled me... Now, look at him. - Xenomorph now lays beneath you, causing more pity, than fear."+
                "Only now, great noise reaches your ears. Audience is chanting your name!"+
                "You slowly raise your hand in a multidimensional peace sign, and begin to leave the Arena."+
                "There's so much more monsters to defeat, so much more Universe to explore, so much more to do..."+
                "\nFarewell.";
    }
}
