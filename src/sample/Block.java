package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Block extends Pane {
    public enum BlockType {
        WALL,STEEL,BRICK,WATER,EAGLE
    }
    Image blocksImg = new Image("sample/block_sprite.png");
    ImageView block;
    public Block(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(Main.BLOCK_SIZE);
        block.setFitHeight(Main.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);
        switch (blockType){
            case WALL:
                block.setViewport(new Rectangle2D(0,0,60,60));
                this.setId("WALL");
            break;
            case STEEL:
                block.setViewport(new Rectangle2D(60,0,60,60));
                this.setId("STEEL");
            break;
            case BRICK:
                block.setViewport(new Rectangle2D(120,0,60,60));
                this.setId("BRICK");
            break;
            case WATER:
                block.setViewport(new Rectangle2D(180,0,60,60));
                this.setId("WATER");
            break;
            case EAGLE:
                block.setViewport(new Rectangle2D(360,0,60,60));
                this.setId("EAGLE");
                break;
        }
        getChildren().add(block);
        Main.blocks.add(this);
        Main.root.getChildren().add(this);
    }
}
