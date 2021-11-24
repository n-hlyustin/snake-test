package snake;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent Event) {
        Snake snake = Main.getGrid().getSnake();
        GameLoop loop = Main.getLoop();
        if (loop.isKeyPressed()) {
            return;
        }
        loop.setKeyPressed();
        switch (Event.getCode()) {
            case UP -> snake.setUp();
            case DOWN -> snake.setDown();
            case LEFT -> snake.setLeft();
            case RIGHT -> snake.setRight();
            case ENTER -> {
                if (loop.isPaused()) {
                    Main.reset();
                    (new Thread(loop)).start();
                }
            }
        }
    }
}
