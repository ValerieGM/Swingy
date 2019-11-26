package model.entities.angels;

public class Archangel extends Angel{
    public Archangel() {
        super();
    }

    public Archangel(String name) {
        super(name);
        this.type = "Archangel";
        this.attack = 20;
        this.defense = 15;
        this.hp = 200;
    }
}
