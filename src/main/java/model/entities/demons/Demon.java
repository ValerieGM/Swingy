package model.entities.demons;

import model.helpers.View;
import model.entities.Entity;

import java.util.Random;

public abstract class Demon extends Entity {
    Demon(int level) {
        this.level = level;
    }

    public void attack(Entity entity) {
        View.print(this.getName() + "is attacking");
        int highLevel = 0;
        if (this.getType().equals("Lilith")){
            int rand = new Random().nextInt(4);
            if (rand == 3){
                View.print("High Level Hit!!!");
                highLevel = this.level * 2;
            }
        }

        entity.defend(this, this.attack);
        if (entity.getHp() <= 0) {
            if (entity.getType().equals("Archangel"))
                View.print("I am the hell and the high water");
            else if (entity.getType().equals("Seraph"))
                View.print("Kneel, peasant");
            else if (entity.getType().equals("Cherubim"))
                View.print("If it's loving that want");
        }
    }

    public void defend(Entity entity, int damage) {
        int finalDamage = damage - this.defense;

        if (finalDamage <= 0)
            finalDamage = 1;
        this.hp -= finalDamage;
        View.print(entity.getName() + " inflicted " + finalDamage + " harm to " + this.name);
        if (hp <= 0)
            View.print(this.name + " has died!!!");
    }
}