package snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import snake.gui.Painter;

public class Main extends Application {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static Grid grid;
    private static GameLoop loop;
    private static GraphicsContext context;
    private static StackPane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("snake.fxml"));

        initCanvas();
        reset();
        initScene(primaryStage);
        startGameThread();
    }

    public static void main(String[] args) {
        launch(args);
    }

    static public Grid getGrid() {
        return grid;
    }

    static public GameLoop getLoop() {
        return loop;
    }
    static void reset() {
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
    }

    private static void initCanvas() {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(new KeyHandler());
        root.getChildren().add(canvas);
    }

    private static void initScene(Stage primaryStage) {
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void startGameThread() {
        Thread thread = new Thread(loop);
        thread.start();
    }
}
