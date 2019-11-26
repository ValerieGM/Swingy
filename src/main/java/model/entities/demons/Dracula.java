package model.entities.demons;

public class Dracula extends Demon {
    public Dracula(int level) {
        super(level);
        this.name = "Dracula";
        this.type = "Incubus";
        this.attack = 3 * this.level;
        this.defense = 2 * this.level;
        this.hp = 10 * this.level;
    }
}
