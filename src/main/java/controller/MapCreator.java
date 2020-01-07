package controller;

import model.helpers.View;
import model.entities.Entity;
import view.Map;

public class MapCreator {
    public static Map generateMap(Entity angel) {
        int mapSize = (angel.getLevel() -1) * 5 + 10 - (angel.getLevel() % 2);

        if (mapSize > 19)
            mapSize = 19;
        Map map = new Map(mapSize);
        map.registerAngel(angel);
        View.print(angel.getName() + " is here ");
        map.spawnDemons();
        return (map);
    }
}
