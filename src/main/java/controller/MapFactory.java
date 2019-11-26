package controller;

import model.entities.Entity;
import view.SquareMap;

public class MapFactory {
    public static SquareMap generateMap(Entity angel) {
        int mapSize = (angel.getLevel() -1) * 5 + 10 - (angel.getLevel() % 2);
        SquareMap squareMap = new SquareMap(mapSize);
        squareMap.registerAngel(angel);
        System.out.println(angel.getName() + " is here ");
        squareMap.spawnDemons();
        return (squareMap);
    }
}
