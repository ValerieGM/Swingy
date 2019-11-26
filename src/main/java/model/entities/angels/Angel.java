package model.entities.angels;

import lombok.Getter;
import lombok.Setter;
import model.entities.Entity;
import view.SquareMap;

@Getter
@Setter
public abstract class Angel extends Entity {

//    private int xp; (check other class if all else fails)
    private SquareMap seer;

    Angel(){
    }

    Angel(String name) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
    }

    public void register(SquareMap squareMap) {
        seer = squareMap;
    }

    public void updateMap() {
        seer.updateAngelPosition();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        updateMap();
    }

    public void riseUp() {
        this.level += 1;
        int current = this.level;
        this.attack += current;
        this.defense += current;
        this.hp += current;

        System.out.println(this.name + " has risen. Current circle:: " + this.level);
        System.out.println(" ::Attack: " + this.attack);
        System.out.println(" ::Defense: " + this.defense);
        System.out.println(" ::Hp: " + this.hp);
    }

    public void attack(Entity entity) {
        System.out.println();

        entity.defend(this, this.attack);
        if (entity.getHp() <= 0) {
            int xpEarned = 0;

            String str = this.getType();
            if (str.equals("Archangel"))
                System.out.println(this.name + ":: Such a rush!!");
            else if (str.equals("Seraph"))
                System.out.println(this.name + ":: Back from hence you came!!");
            else if (str.equals("Cherub"))
                System.out.println(this.name + ":: Oh dear me!!");

            if (str.equals("Archangel")) {
                xpEarned = 750;
                this.xp += xpEarned;
            } else if (str.equals("Seraph")) {
                xpEarned = 500;
                this.xp += xpEarned;
            } else if (str.equals("Cherub")) {
                xpEarned = 250;
                this.xp += xpEarned;
            }

            System.out.println("You earned " + xpEarned + "xp");
            if (this.xp >= (this.level * 1000 + Math.pow(this.level - 1, 2) * 450))
                riseUp();
        }
    }

    public void defend (Entity entity, int damage) {
        int realDamage = damage - this.defense;

        if (realDamage <= 0)
            realDamage = 1;
        this.hp -= realDamage;
        System.out.println(entity.getName() + " inflicted " + realDamage + "harm to " + this.name);
        if (this.hp <= 0)
            System.out.println(this.name + " has been smote!!!");
    }
}
