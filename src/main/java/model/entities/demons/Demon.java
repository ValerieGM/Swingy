package model.entities.demons;

import model.entities.Entity;

public abstract class Demon extends Entity {
    Demon(int level) {
        this.level = level;
    }

    public void attack(Entity entity) {
        System.out.println(this.getName() + "is attacking");
        entity.defend(this, this.attack);
        if (entity.getHp() <= 0) {
            if (entity.getType().equals("Archangel"))
                System.out.println("I am the hell and the high water");
            else if (entity.getType().equals("Seraph"))
                System.out.println("Kneel, peasant");
            else if (entity.getType().equals("Cherubim"))
                System.out.println("If it's loving that want");
        }
    }

    public void defend(Entity entity, int damage) {
        int realDamage = damage - this.defense;

        if (realDamage <= 0)
            realDamage = 1;
        this.hp -= realDamage;
        System.out.println(entity.getName() + " inflicted " + realDamage + " harm to " + this.name);
        if (hp <= 0)
            System.out.println(this.name + " has died!!!");
    }
}