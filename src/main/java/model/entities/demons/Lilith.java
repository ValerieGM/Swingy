package model.entities.demons;

public class Lilith extends Demon {
    public Lilith(int level) {
        super(level);
        this.name = "Lilith";
        this.type = "Succubus";
        this.attack = 3 * this.level;
        this.defense = 2 * this.level;
        this.hp = 10 * this.level;
    }
}
