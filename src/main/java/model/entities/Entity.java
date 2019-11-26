package model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public abstract class Entity {
    @NotNull
    @Size(min = 4, max = 15)
    protected String name;
    protected String type;

    protected int attack;
    protected int defense;

    protected int hp;
    protected int xp; //trial -- not meant to be here
    protected int level;

    protected int x;
    protected int y;

    public abstract void attack(Entity entity);
    public abstract void defend(Entity entity, int damage);
}