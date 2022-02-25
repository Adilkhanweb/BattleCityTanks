package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ConcurrentModificationException;
public class Bullet extends Pane {
    static int temp = 0;
    Image bulletImage = new Image("sample/bullet_sprite.png");
    ImageView imageView = new ImageView(bulletImage);
    Tank bullet;
    public Bullet(double translateX, double translateY, int movebyX, int moveByY) {
        if (movebyX == 0 && moveByY < 0) {
            bullet = new Tank(imageView, 0, 0, 15, 15);
        } else if (movebyX == 0 && moveByY > 0) {
            bullet = new Tank(imageView, 0, 15, 15, 15);
        } else if (moveByY == 0 && movebyX < 0) {
            bullet = new Tank(imageView, 0, 30, 15, 15);
        } else if (moveByY == 0 && movebyX > 0) {
            bullet = new Tank(imageView, 0, 45, 15, 15);
        } else {
            bullet = new Tank(imageView, 0, 15, 15, 15);
        }
        bullet.setTranslateY(translateY);
        bullet.setTranslateX(translateX);
        Main.root.getChildren().add(bullet);
        transition(bullet, movebyX, moveByY);
    }
    TranslateTransition translateTransition = new TranslateTransition();
    public AnimationTimer timer = new AnimationTimer() {
        public void handle(long now) {
            bulletIntersect();
        }
    };

    public void transition(Tank bullet, int x, int y) {

        translateTransition.setDuration(Duration.millis(Main.BULLET_RELOAD));
        translateTransition.setNode(bullet);
        translateTransition.setByX(x);
        translateTransition.setByY(y);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
        timer.start();
        translateTransition.setOnFinished(event -> {
            comparison(bullet);
        });
    }
    public static void comparison(Tank bullet) {
        Image image = new Image("sample/explosion.gif");
        ImageView gifOfExplosion = new ImageView(image);
        Main.root.getChildren().add(gifOfExplosion);
        Main.root.getChildren().remove(bullet);
        gifOfExplosion.setTranslateX(bullet.getTranslateX() - 10);
        gifOfExplosion.setTranslateY(bullet.getTranslateY() - 10);
        time(gifOfExplosion, 630);
    }

    public static void time(ImageView gif, int duration) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.setNode(gif);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            Main.root.getChildren().remove(gif);
        });
    }
    public void bulletIntersect() {
        try {
            for (Node node : Main.blocks) {
                if (bullet.getBoundsInParent().intersects(node.getBoundsInParent()) && !(node.getId() == "WATER")) {
                    translateTransition.stop();
                    comparison(bullet);
                    if (node.getId() == "BRICK") {
                        this.temp++;
                            if (this.temp == 1){
                                Main.blocks.remove(node);
                                Main.root.getChildren().remove(node);
                                this.temp = 0;
                            }
                    }
                    timer.stop();
                }
            }
        } catch (ConcurrentModificationException e) {
        }
    }
}
