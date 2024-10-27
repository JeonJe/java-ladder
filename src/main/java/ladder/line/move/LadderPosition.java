package ladder.line.move;

import java.util.Objects;

public class LadderPosition {
    private final Position position;
    private final Point point;

    public LadderPosition(int position, Point point) {
        this(new Position(position), point);
    }

    public LadderPosition(Position poisition, Point point) {
        this.position = poisition;
        this.point = point;
    }

    public Position move() {
        if (point.move() == Direction.RIGHT) {
            return position.right();
        }
        if (point.move() == Direction.LEFT) {
            return position.left();
        }
        return position;
    }

    public Direction getDirection() {
        return point.move();
    }

    public boolean hasRightConnection() {
        return point.move() == Direction.RIGHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LadderPosition ladderPosition = (LadderPosition) o;
        return Objects.equals(position, ladderPosition.position) && Objects.equals(point, ladderPosition.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, point);
    }

}
