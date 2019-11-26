package controller;

import javax.validation.*;
import java.util.*;

import model.entities.Entity;
import model.entities.angels.*;
import model.entities.demons.*;

import static controller.Universal.factory;

public class EntityFactory {
    private static Entity entity;

    public static Entity newAngel(String name, String type) {
        if (type.equals("Archangel"))
            entity = new Archangel(name);
        else if (type.equals("Seraph"))
            entity = new Seraph(name);
        else if (type.equals("Cherubim"))
            entity = new Cherub(name);

        System.out.println("All created:::::");

//        Validator validator = factory.getValidator();

//        Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
//        if (constraintViolations.size() > 0) {
//            for (ConstraintViolation<Entity> constraint : constraintViolations)
//                System.out.println(constraint.getMessage());
//            return null;
//        }
//        else
            return (entity);
    }

    public static Entity newDemon(Entity angel, String type) {
        if (type.equals("Dracula"))
            entity = new Dracula(angel.getLevel());
        else if (type.equals("Lilith"))
            entity = new Lilith(angel.getLevel());
        return(entity);
    }
}