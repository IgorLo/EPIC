import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private GameWindow gameWindow = new GameWindow();

    @Override
    public void start(Stage primaryStage) throws Exception {

        gameWindow.init(primaryStage);
        gameWindow.start();

    }
}
