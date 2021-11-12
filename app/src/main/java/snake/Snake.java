package snake;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    public static final Color COLOR = Color.DEEPSKYBLUE;
    public static final Color DEAD = Color.RED;
    private final Grid grid;
    private int length;
    private boolean safe;
    private final List<Point> points;
    private Point head;
    private int xVelocity;
    private int yVelocity;

    /**
     * @param initialPoint The {@link Point} to the put the snake's head on.
     */
    public Snake(Grid grid, Point initialPoint) {
        length = 1;
        points = new LinkedList<>();
        points.add(initialPoint);
        head = initialPoint;
        safe = true;
        this.grid = grid;
        xVelocity = 0;
        yVelocity = 0;
    }

    /**
     * This method is called after food was eating.
     *
     * @param point The {@link Point} where the food was.
     */
    private void growTo(Point point) {
        length++;
        checkAndAdd(point);
    }

    /**
     * Called every update. Remove last old point and add new one.
     *
     * @param point The new Point to add.
     */
    private void shiftTo(Point point) {
        // The head goes to the new location
        checkAndAdd(point);
        // The last/oldest position remove
        points.remove(0);
    }

    /**
     * Checks for collision and marks the "safe" flag when it has no collide.
     *
     * @param point The new Point to move to.
     */
    private void checkAndAdd(Point point) {
        point = grid.wrap(point);
        safe &= !points.contains(point);
        points.add(point);
        head = point;
    }

    /**
     * @return The points occupied by snake.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * @return {@code true} check if snake not collide yourself.
     */
    public boolean isSafe() {
        return safe || length == 1;
    }

    /**
     * @return The snake head location.
     */
    public Point getHead() {
        return head;
    }

    private boolean isStill() {
        return xVelocity == 0 & yVelocity == 0;
    }

    /**
     * Make the snake move one grid in current direction.
     */
    public void move() {
        if (!isStill()) {
            shiftTo(head.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Make the snake grow to the grid where its headed.
     */
    public void extend() {
        if (!isStill()) {
            growTo(head.translate(xVelocity, yVelocity));
        }
    }

    public void setUp() {
        if (yVelocity == 1 && length > 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }

    public void setDown() {
        if (yVelocity == -1 && length > 1) return;
        xVelocity = 0;
        yVelocity = 1;
    }

    public void setLeft() {
        if (xVelocity == 1 && length > 1) return;
        xVelocity = -1;
        yVelocity = 0;
    }

    public void setRight() {
        if (xVelocity == -1 && length > 1) return;
        xVelocity = 1;
        yVelocity = 0;
    }
}
