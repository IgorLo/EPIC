import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Screen;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;


public class GameScene {

    public Scene scene;
    public Text text;
    public Rectangle rectangle;

    public static GameScene getStartScene() {




        String uriString = new File("resources/GLITCH.gif").toURI().toString();
        Image image = new Image(uriString);

        BorderPane pane = new BorderPane();


        Rectangle2D r = Screen.getPrimary().getBounds();

        Rectangle rect = new Rectangle(0,0,r.getWidth(),r.getHeight());
        rect.setFill(Color.BLACK);

        Text title = new Text("");
        int fontSize = (int) (r.getHeight() / 27);
        title.setFont(Font.font("Courier New", FontWeight.LIGHT, fontSize));
        title.setFontSmoothingType(FontSmoothingType.GRAY);

        ImageView imageView = new ImageView(image);
        double coef = r.getHeight()/image.getHeight() * 1.5;
        imageView.setScaleX(coef);
        imageView.setScaleY(coef);
        imageView.setTranslateX(r.getWidth()/2 - image.getWidth()/2);
        imageView.setTranslateY(r.getHeight()/2 - image.getHeight()/2);
        imageView.setOpacity(0.7);




        pane.getChildren().add(imageView);
        pane.getChildren().add(rect);
        pane.getChildren().add(title);

        rect.setOpacity(0.0);

        title.setTranslateX(r.getWidth()/2 - title.getLayoutBounds().getWidth()/2);
        title.setTranslateY(r.getHeight()/2 - title.getLayoutBounds().getHeight()/2);
        title.setTextAlignment(TextAlignment.CENTER);

        GameScene gameScene = new GameScene();
        gameScene.scene = new Scene(pane, r.getWidth(), r.getHeight());
        gameScene.text = title;
        gameScene.rectangle = rect;

        return gameScene;

    }
}
