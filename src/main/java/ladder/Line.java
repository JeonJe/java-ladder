package ladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Line {
    private List<Boolean> points = new ArrayList<>();
    private LineGenerateStrategy lineGenerateStrategy;

    public Line(List<Boolean> points) {
        this(points, new RandomLineGenerator());
    }

    public Line(List<Boolean> points, LineGenerateStrategy lineGenerateStrategy) {
        this.points = points;
        this.lineGenerateStrategy = lineGenerateStrategy;
    }

    public Line(int countOfPerson) {
        this(countOfPerson, new RandomLineGenerator());
    }

    public Line(int countOfPerson, LineGenerateStrategy lineGenerateStrategy) {
        this.lineGenerateStrategy = lineGenerateStrategy;
        generatePoints(countOfPerson);
    }

    private void generatePoints(int countOfPerson) {

        boolean prevLine = false;
        for (int i = 0; i < countOfPerson - 1; i++) {
            if (prevLine) {
                points.add(false);
                prevLine = false;
                continue;
            }

            Boolean currentLine = lineGenerateStrategy.generate();
            points.add(currentLine);
            prevLine = currentLine;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(points);
    }

    @Override
    public String toString() {
        return "Line{" +
                "points=" + points +
                '}';
    }
}
