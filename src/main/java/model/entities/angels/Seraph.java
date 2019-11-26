package model.entities.angels;

public class Seraph extends Angel{
    public Seraph() {
        super();
    }

    public Seraph(String name) {
        super(name);
        this.type = "Seraph";
        this.attack = 15;
        this.defense = 10;
        this.hp = 150;
    }
}
