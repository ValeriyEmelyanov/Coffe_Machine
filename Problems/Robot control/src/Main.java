class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        moveToX(robot, toX);
        moveToY(robot, toY);
    }

    private static void moveToX(Robot robot, int toX) {
        if (robot.getX() == toX) {
            return;
        }

        Direction desired = robot.getX() > toX ? Direction.LEFT : Direction.RIGHT;
        setDirection(robot, desired);
        while (robot.getX() != toX) {
            robot.stepForward();
        }
    }

    private static void moveToY(Robot robot, int toY) {
        if (robot.getY() == toY) {
            return;
        }

        Direction desired = robot.getY() > toY ? Direction.DOWN : Direction.UP;
        setDirection(robot, desired);
        while (robot.getY() != toY) {
            robot.stepForward();
        }
    }

    private static void setDirection(Robot robot, Direction desired) {
        while (robot.getDirection() != desired) {
            robot.turnLeft();
        }
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}