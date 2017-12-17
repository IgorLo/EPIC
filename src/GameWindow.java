
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GameWindow {

    GameScene gameScene;
    MediaPlayer player_background = initPlayerBackground();

    private MediaPlayer initPlayerBackground() {
        String uriString = new File("resources/MENU_MUSIC.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.setVolume(0.2);
        return player;
    }

    public void init(Stage stage) {

        player_background.play();

        this.gameScene = GameScene.getStartScene();

        stage.setTitle("TRAIN#1192");
        stage.setScene(gameScene.scene);
        stage.setFullScreen(true);
        stage.show();

    }

    public void start(){

        gameScene.text.setText("");
        Animation animation = animateText(gameScene.text, "Псалмопевец просит избавить его \nот уст лживых," +
                "от языка лукавого. \nМы видим, что лживость, \nкак часто бывает..." +
                "\n              \n      - Библия, песнь 119:2", 12000);

        animation.getOnFinished();
        blackOut(200000, gameScene.rectangle);

    }

    public Animation animateText(Text text, String string, int time) {

        String content = string;

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(time));
            }

            protected void interpolate(double frac) {

                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
                text.setTranslateX(gameScene.scene.getWidth()/2 - text.getLayoutBounds().getWidth()/2);
                text.setTranslateY(gameScene.scene.getHeight()/2 - text.getLayoutBounds().getHeight()/2);
            }
        };

        animation.play();
        return animation;

    }

    public Animation blackOut(int timeMillis, Rectangle rectangle){
        long startTime = System.currentTimeMillis();
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(timeMillis));
            }

            protected void interpolate(double frac) {
                if (rectangle.getOpacity() < 100.0)
                    rectangle.setOpacity(rectangle.getOpacity() + 0.0001 * Math.sqrt(Math.sqrt(System.currentTimeMillis() - startTime)));
                if (gameScene.rectangle.getOpacity() >= 1.0){
                    gameScene.text.setFill(Color.WHITE);
                    gameScene.text.setText("П Е Р В О Е\nП О С Е Щ Е Н И Е");
                    gameScene.text.setTranslateX(gameScene.scene.getWidth()/2 - gameScene.text.getLayoutBounds().getWidth()/2);
                    gameScene.text.setTranslateY(gameScene.scene.getHeight()/2 - gameScene.text.getLayoutBounds().getHeight()/2);
                }
                if (gameScene.rectangle.getOpacity() >= 1.6){
                    gameScene.text.setText("2  0  8  4   год");
                    gameScene.text.setTranslateX(gameScene.scene.getWidth()/2 - gameScene.text.getLayoutBounds().getWidth()/2);
                    gameScene.text.setTranslateY(gameScene.scene.getHeight()/2 - gameScene.text.getLayoutBounds().getHeight()/2);
                }
                if (gameScene.rectangle.getOpacity() >= 1.8){
                    gameScene.text.setText("С   К   О   Р   О");
                    gameScene.text.setTranslateX(gameScene.scene.getWidth()/2 - gameScene.text.getLayoutBounds().getWidth()/2);
                    gameScene.text.setTranslateY(gameScene.scene.getHeight()/2 - gameScene.text.getLayoutBounds().getHeight()/2);
                }
                if (gameScene.rectangle.getOpacity() >= 2.0){
                    gameScene.text.setFill(Color.BLACK);
                }
            }
        };
        animation.play();
        return animation;
    }

}
