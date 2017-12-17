
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GameWindow {

    Stage gameWindow;

    GameScene gameScene;
    MediaPlayer player_background = initPlayerBackground();
    boolean clicked = false;

    private MediaPlayer initPlayerBackground() {
        String uriString = new File("resources/MENU_MUSIC.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.setVolume(0.4);
        return player;
    }

    public void init(Stage stage) {

        player_background.play();

        this.gameScene = GameScene.getStartScene();

        gameWindow = stage;
        stage.setTitle("TRAIN#1192");
        stage.setFullScreen(true);
        stage.setScene(gameScene.scene);
        stage.show();

    }

    public void start(){

        int fontSize = (int) (gameWindow.getHeight() / 27);
        gameScene.text.setFont(Font.font("Courier New", FontWeight.LIGHT, fontSize));

        gameScene.text.setText("");
        animateText(gameScene.text, "Каждый охотник желает знать,\n" +
                "где сидит этот ёбаный гук.\n       \n" +
                "Но каждый хороший охотник уже знает,\n" +
                "что гук сидит на ёбаном дереве . . .\n" +
                "                       \n" +
                "               - Безымянный солдат, 1018 год", 12000);


        blackOut(200000, gameScene.rectangle);

    }

    public void animateText(Text text, String string, int time) {

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
                int fontSize = (int) (Math.sqrt(gameWindow.getHeight()*gameWindow.getWidth()) / 30);
                gameScene.text.setFont(Font.font("Courier New", FontWeight.LIGHT, fontSize));

            }
        };

        animation.play();

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
                    int fontSize = (int) (Math.sqrt(gameWindow.getHeight()*gameWindow.getWidth()) / 15);
                    gameScene.text.setFont(Font.font("Courier New", FontWeight.LIGHT, fontSize));
                    gameScene.text.setFill(Color.WHITE);
                    if (!clicked)
                        gameScene.text.setText("НАЧАТЬ ПОГРУЖЕНИЕ");
                    gameScene.text.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameScene.text.setText("НАЧАТЬ ПОГРУЖЕНИЕ\nВ ТВОЮ МАМАШУ");
                            clicked = true;
                        }
                    });
                    gameScene.text.setTranslateX(gameScene.scene.getWidth()/2 - gameScene.text.getLayoutBounds().getWidth()/2);
                    gameScene.text.setTranslateY(gameScene.scene.getHeight()/2 - gameScene.text.getLayoutBounds().getHeight()/2);
                }
            }
        };
        animation.play();
        return animation;
    }

}
