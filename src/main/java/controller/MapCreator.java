package controller;

import model.helpers.View;
import model.entities.Entity;
import view.SquareMap;

public class MapCreator {
    public static SquareMap generateMap(Entity angel) {
        int mapSize = (angel.getLevel() -1) * 5 + 10 - (angel.getLevel() % 2);

        if (mapSize > 19)
            mapSize = 19;
        SquareMap squareMap = new SquareMap(mapSize);
        squareMap.registerAngel(angel);
        View.print(angel.getName() + " is here ");
        squareMap.spawnDemons();
        return (squareMap);
    }
}
