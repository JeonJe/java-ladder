package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lines {

    private static final int MIN_HEIGHT_OF_LADDER = 1;

    private final List<Line> values;

    public Lines(List<Line> values) {
        validateLines(values);

        this.values = values;
    }

    private void validateLines(List<Line> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("최소 높이는 최소 " + MIN_HEIGHT_OF_LADDER + "이상이어야 합니다.");
        }

        Line firstLine = values.get(0);
        boolean invalid = values.stream()
                .anyMatch(value -> !value.isSamePointsSize(firstLine));
        if (invalid) {
            throw new IllegalArgumentException("모든 라인의 포지션 수는 동일해야합니다.");
        }
    }

    public static Lines of(int countOfPlayer, int maxHeightOfLadder) {
        List<Line> lines = IntStream.range(0, maxHeightOfLadder)
                .mapToObj(idx -> Line.from(countOfPlayer))
                .collect(Collectors.toList());

        return new Lines(lines);
    }

    public List<Line> values() {
        return new ArrayList<>(values);
    }

    public int finalPosition(final int startPosition) {
        int position = startPosition;

        for (Line line: values) {
            position = line.nextPosition(position);
        }

        return position;
    }
}