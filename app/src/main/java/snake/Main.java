package snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import snake.gui.Painter;

public class Main extends Application {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = FXMLLoader.load(getClass().getClassLoader().getResource("snake.fxml"));

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::keyEventHandler);

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(loop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void reset() {
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
    }

    public void keyEventHandler(KeyEvent e) {
        Snake snake = grid.getSnake();
        if (loop.isKeyPressed()) {
            return;
        }
        switch (e.getCode()) {
            case UP:
                snake.setUp();
                break;
            case DOWN:
                snake.setDown();
                break;
            case LEFT:
                snake.setLeft();
                break;
            case RIGHT:
                snake.setRight();
                break;
            case ENTER:
                if (loop.isPaused()) {
                    reset();
                    (new Thread(loop)).start();
                }
        }
    }
}
