package snake.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import snake.*;

import static snake.Grid.SIZE;

public class Painter {
    public static void paint(Grid grid, GraphicsContext gc) {
        paintBackground(gc, grid.getWidth(), grid.getHeight());
        paintGridLines(gc, grid.getWidth(), grid.getHeight());
        paintFood(gc, grid.getFood());
        paintSnake(gc, grid.getSnake());
        paintScore(gc, grid.getSnake().getPoints().size());
        Main.getLoop().setKeyReleased();
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("ENTER - начать заново.", 10, 20);
    }

    private static void paintBackground(GraphicsContext gc, double width, double height) {
        gc.setFill(Grid.COLOR);
        gc.clearRect(0, 0, width, height);
        gc.fillRect(0, 0, width, height);
    }

    private static void paintGridLines(GraphicsContext gc, double width, double height) {
        gc.setStroke(Color.WHEAT);
        gc.setLineWidth(0.1);

        // draw vertical lines
        for(int i = 0 ; i < width - 1; i += SIZE){
            gc.strokeLine(i, 0, i, width);
        }

        // draw horizontal lines
        for(int i = 0; i < height; i += SIZE){
            gc.strokeLine(0, i, height, i);
        }
    }

    private static void paintFood(GraphicsContext gc, Food food) {
        gc.setFill(Food.COLOR);
        paintPoint(food.getPoint(), gc);
    }

    private static void paintSnake(GraphicsContext gc, Snake snake) {
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }
    }

    private static void paintScore(GraphicsContext gc, int score) {
        gc.setFill(Color.BEIGE);
        gc.fillText("Счет: " + 100 * score, 10, 490);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }
}