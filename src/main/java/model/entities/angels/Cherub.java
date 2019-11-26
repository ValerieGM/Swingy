package model.entities.angels;

public class Cherub extends Angel{
    public Cherub() {
        super();
    }

    public Cherub(String name) {
        super(name);
        this.type = "Archangel";
        this.attack = 10;
        this.defense = 5;
        this.hp = 100;
    }
}
