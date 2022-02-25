package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static sample.Main.blocks;

public class Tank extends Pane {
    ImageView imageView;
    SpriteAnimation animation;
    public Tank(ImageView imageView, int offsetX, int offsetY, int width, int height) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(200), offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }
    public void moveX(int x) {
        boolean movingRight = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            for (Node platform : blocks) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent()) && !platform.getId().equals("EAGLE")) {
                    if (movingRight) {
                        if (this.getTranslateX() + Main.CHARACTER_SIZE == platform.getTranslateX()) {
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + Main.BLOCK_SIZE) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int y) {
        boolean movingDown = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            for (Node platform : blocks) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent()) && !platform.getId().equals("EAGLE")) {
                    if (movingDown) {
                        if (this.getTranslateY() + Main.CHARACTER_SIZE == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + Main.BLOCK_SIZE) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

}
