package model.entities.angels;

import lombok.Getter;
import lombok.Setter;
import model.helpers.View;
import model.entities.Entity;
import view.SquareMap;

import java.util.Random;

@Getter
@Setter
public abstract class Angel extends Entity {

    private int xp;
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

    private void updateMap() {
        seer.updateAngelPosition();
    }

    public void setPosition(int x, int y) {
        this.x += x;
        this.y += y;
        updateMap();
    }

    public void riseUp() {
        this.level += 1;
        int current = this.level;
        this.attack += current;
        this.defense += current;
        this.hp += current;

        View.print(this.name + " has risen. Current circle:: " + this.level);
        View.print(" ::Attack: " + this.attack);
        View.print(" ::Defense: " + this.defense);
        View.print(" ::Hp: " + this.hp);
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
            int xpEarned = 0;

            String str = this.getType();
            if (str.equals("Archangel"))
                View.print(this.name + ":: Such a rush!!");
            else if (str.equals("Seraph"))
                View.print(this.name + ":: Back from hence you came!!");
            else if (str.equals("Cherub"))
                View.print(this.name + ":: Oh dear me!!");

            //just to see how it works
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

            if (str.equals("Lilith")) {
                xpEarned = 750;
                this.xp += xpEarned;
            } else if (str.equals("Dracula")) {
                xpEarned = 500;
                this.xp += xpEarned;
            }

            View.print("You earned " + xpEarned + "xp");
            if (this.xp >= (this.level * 1000 + Math.pow(this.level - 1, 2) * 450))
                riseUp();
        }
    }

    public void defend (Entity entity, int damage) {
        int finalDamage = damage - this.defense;

        if (finalDamage <= 0)
            finalDamage = 1;
        this.hp -= finalDamage;
        View.print(entity.getName() + " inflicted " + finalDamage + "harm to " + this.name);
        if (this.hp <= 0)
            View.print(this.name + " has been smote!!!");
    }
}
