package ladder;

import ladder.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LineTest {

    @DisplayName("Line 객체 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 15})
    public void makeLineObject(int playerCounts) {
        assertThatCode(() -> {
            Line.drawLine(playerCounts, new RandomDrawingLineStrategy());
        }).doesNotThrowAnyException();
    }

    @DisplayName("PlayerCounts가 2 미만인 경우 객체 생성 에러 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void throwExceptionOnMakingLineObject(int playerCounts) {
        assertThatThrownBy(() -> {
            Line.drawLine(playerCounts, new RandomDrawingLineStrategy());
        }).isInstanceOf(LadderBuildingException.class)
                .hasMessageContaining(LadderBuildingException.INVALID_PLAYER_COUNTS);
    }

    @DisplayName("Line 객체를 통해 그린 Line의 길이는 playerCounts과 동일함")
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 15})
    public void lineSizeTest(int playerCounts) {
        Line line = Line.drawLine(playerCounts, new RandomDrawingLineStrategy());

        List<Direction> pointDirections = line.getPointDirections();

        assertThat(pointDirections.size()).isEqualTo(playerCounts);
    }

    /*
    |----|    |
    형태의 1개 라인으로 테스트
     */

    @DisplayName("Line 객체의 한 Point의 인덱스 좌표를 받으면 방향에 따라 move시킨 뒤 인덱스 반환")
    @Test
    public void movePointOnLine() {
        DrawingLineStrategy drawingLineStrategy = new DrawingLineStrategy() {
            @Override
            public List<Point> drawLine(int playerCounts) {
                List<Point> points = Arrays.asList(new Point(0, Direction.RIGHT),
                        new Point(1, Direction.LEFT),
                        new Point(2, Direction.DOWN));
                return points;
            }
        };

        Line line = Line.drawLine(3, drawingLineStrategy);

        assertThat(line.movePointOnLine(0)).isEqualTo(1);
        assertThat(line.movePointOnLine(1)).isEqualTo(0);
        assertThat(line.movePointOnLine(2)).isEqualTo(2);
    }
}