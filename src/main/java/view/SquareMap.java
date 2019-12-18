package view;

import lombok.Getter;
import model.entities.angels.Angel;
import model.entities.Entity;

import java.util.Random;

import static model.helpers.Universal.*;

@Getter
public class SquareMap {
    private int [][]map;
    public int mapSize;
    private int[]oldPos = new int[]{-1, -1};
    private Angel angel;

    public SquareMap(int mapSize){
        this.mapSize = mapSize;
        this.map = new int [mapSize][mapSize];
    }

    public void updateAngelPosition() {
        this.map[oldPos[0]][oldPos[1]] = 0;
        oldPos[0] = angel.getX();
        oldPos[1] = angel.getY();

        if (this.map[angel.getX()][angel.getY()] == 2)
            this.map[angel.getX()][angel.getY()] = 8;
        else
            this.map[angel.getX()][angel.getY()] = 1;
        if (!bIsGUI)
            displayMap();
    }

    private void displayMap() {
        for (int[] line: map) {
            for (int col : line) {
                String box = col + " ";
                System.out.print(box);
            }
            System.out.println();
        }
    }

    public void registerAngel(Entity entity) {
        this.angel = (Angel)entity;
        this.angel.register(this);
        this.angel.setX(mapSize / 2);
        this.angel.setY(mapSize / 2);
        oldPos[0] = this.angel.getX();
        oldPos[1] = this.angel.getY();
        this.map[mapSize / 2][mapSize / 2] = 1;
    }

    public void spawnDemons() {
        for (int v = 0; v < mapSize; v++) {
            for (int t = 0; t < mapSize; t++) {
                if (map[v][t] != 1) {
                    int ran = new Random().nextInt(3);
                    if (ran == 1)
                        map[v][t] = 2;
                }
            }
        }
        displayMap();
    }
}
