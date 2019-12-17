package model.entities.demons;

import model.Print;
import model.entities.Entity;

import java.util.Random;

public abstract class Demon extends Entity {
    Demon(int level) {
        this.level = level;
    }

    public void attack(Entity entity) {
        Print.print(this.getName() + "is attacking");
        int highLevel = 0;
        if (this.getType().equals("Lilith")){
            int rand = new Random().nextInt(4);
            if (rand == 3){
                Print.print("High Level Hit!!!");
                highLevel = this.level * 2;
            }
        }

        entity.defend(this, this.attack);
        if (entity.getHp() <= 0) {
            if (entity.getType().equals("Archangel"))
                Print.print("I am the hell and the high water");
            else if (entity.getType().equals("Seraph"))
                Print.print("Kneel, peasant");
            else if (entity.getType().equals("Cherubim"))
                Print.print("If it's loving that want");
        }
    }

    public void defend(Entity entity, int damage) {
        int finalDamage = damage - this.defense;

        if (finalDamage <= 0)
            finalDamage = 1;
        this.hp -= finalDamage;
        Print.print(entity.getName() + " inflicted " + finalDamage + " harm to " + this.name);
        if (hp <= 0)
            Print.print(this.name + " has died!!!");
    }
}