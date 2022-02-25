package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    static int BLOCK_SIZE = 60;
    // bullet velocity when fired in millis
    static int BULLET_SPEED = 1000;
    // bullet reload time
    static int BULLET_RELOAD = 1500;
    static int CHARACTER_SIZE = 50;
    public static Bullet bullet;
    public static ArrayList<Block> blocks = new ArrayList<>();
    static Pane root = new Pane();
    static Image textures = new Image("sample/tank_sprite.png");
    static ImageView imageViewP = new ImageView(textures);
    public static Tank player = new Tank(imageViewP, 0, 0, 50, 50);
    char lastPressedKeyP = 'w';
    public static boolean counterP1 = true;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        initContent();
        Scene scene = new Scene(root, Color.BLACK);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
        primaryStage.setTitle("Battle City");
        primaryStage.getIcons().add(new Image("sample/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        timer.start();
    }
    public void update() {
        if (isPressed(KeyCode.W)) {
            lastPressedKeyP = 'W';
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(-2);
        } else if (isPressed(KeyCode.S)) {
            lastPressedKeyP = 'S';
            player.animation.play();
            player.animation.setOffsetY(50);
            player.moveY(2);
        } else if (isPressed(KeyCode.A)) {
            lastPressedKeyP = 'A';
            player.animation.play();
            player.animation.setOffsetY(100);
            player.moveX(-2);
        } else if (isPressed(KeyCode.D)) {
            lastPressedKeyP = 'D';
            player.animation.play();
            player.animation.setOffsetY(150);
            player.moveX(2);
        }
        if (isPressed(KeyCode.SPACE) && counterP1) {
            counterP1 = false;
            if (lastPressedKeyP == 'W') {
                bullet = new Bullet(player.getTranslateX() + 18, player.getTranslateY() - 25, 0, -5000);
            }
            if (lastPressedKeyP == 'S') {
                bullet = new Bullet(player.getTranslateX() + 18, player.getTranslateY() + 60, 0, 5000);
            }
            if (lastPressedKeyP == 'A') {
                bullet = new Bullet(player.getTranslateX() - 25, player.getTranslateY() + 18, -5000, 0);
            }
            if (lastPressedKeyP == 'D') {
                bullet = new Bullet(player.getTranslateX() + 60, player.getTranslateY() + 18, 5000, 0);
            }
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(BULLET_SPEED));
            translateTransition.setNode(imageViewP);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                counterP1 = true;
            });
        }
    }
    public void initContent() throws FileNotFoundException {
        Map map = new Map();
        root.getChildren().addAll(player);
        map.addTrees();
    }
    AnimationTimer timer = new AnimationTimer() {
        public void handle(long now) {
            update();
        }
    };
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
