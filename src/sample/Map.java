package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static sample.Main.BLOCK_SIZE;

public class Map {
    private String[][] array;
    public static int width;
    public static int height;
    public Map() throws FileNotFoundException {
        FileReader reader = new FileReader("src/sample/level1.txt");
        Scanner scanner = new Scanner(reader);
        String temp = scanner.nextLine();
        width = Integer.parseInt(temp.split(" ")[0]);
        height = Integer.parseInt(temp.split(" ")[1]);
        array = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = scanner.next();
                switch (array[i][j].charAt(0)) {
                    case '0':
                        break;
                    case 'B':
                        Block brick = new Block(Block.BlockType.BRICK, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case 'S':
                        Block steel = new Block(Block.BlockType.STEEL, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case 'W':
                        Block water = new Block(Block.BlockType.WATER, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case '*':
                        Block wall = new Block(Block.BlockType.WALL, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                    case 'P':
                        int[] cor = {j * BLOCK_SIZE + 5, i * BLOCK_SIZE + 5};
                        Position.setCoordinates(cor);
                        break;
                    case 'E':
                        Block eagle = new Block(Block.BlockType.EAGLE, j * BLOCK_SIZE, i * BLOCK_SIZE);
                        break;
                }
            }
        }
        Main.root.setPrefSize(width*BLOCK_SIZE,height*BLOCK_SIZE);
    }
    public void addTrees() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (array[i][j].charAt(0)) {
                    case ('T'):
                        Image grassImage = new Image("sample/block_sprite.png");
                        ImageView trees = new ImageView(grassImage);
                        trees.setViewport(new Rectangle2D(300,0,60,60));
                        trees.setTranslateX(j * BLOCK_SIZE);
                        trees.setTranslateY(i * BLOCK_SIZE);
                        trees.toFront();
                        Main.root.getChildren().add(trees);
                        break;
                }
            }
        }
    }
}
