package nextstep.ladder.ui;

import nextstep.ladder.domain.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultView {

    private static final String POINT_TRUE = "-----";
    private static final String POINT_FALSE = "     ";
    private static final String LINE_DELIMITER = "|";

    public static void printLadder(Ladder ladder, Map<Participant, String> prizeMap) {
        System.out.println("사다리 결과");
        System.out.println(formatParticipantNames(prizeMap.keySet()));
        System.out.println(formatLadder(ladder));
        System.out.println(formatPrizes(prizeMap.values()));
    }

    private static String formatLadder(Ladder ladder) {
        return ladder.getLines().stream()
                .map(ResultView::formatLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatLine(Line line) {
        return line.get().stream()
                .map(point -> String.format("%5s", formatPoint(point)))
                .collect(Collectors.joining(LINE_DELIMITER, String.format("%5s", LINE_DELIMITER), LINE_DELIMITER));
    }

    private static String formatPoint(boolean point) {
        if (point) {
            return POINT_TRUE;
        }
        return POINT_FALSE;
    }

    private static String formatParticipantNames(Set<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .map(name -> String.format("%-6s", name))
                .collect(Collectors.joining());
    }

    private static String formatPrizes(Collection<String> prizes) {
        return prizes.stream()
                .map(prize -> String.format("%-6s", prize))
                .collect(Collectors.joining());
    }

    public static void printLadderGameResult(Map<Participant, String> prizeMap, String name) {
        System.out.println("실행 결과");
        System.out.println(formatResult(prizeMap, name));
    }

    private static String formatResult(Map<Participant, String> prizeMap, String name) {
        if ("all".equals(name)) {
            return prizeMap.keySet().stream()
                    .map(participant -> formatParticipantPosition(prizeMap, participant))
                    .collect(Collectors.joining());
        }
        return formatParticipantPosition(prizeMap, Participant.of(name));
    }

    private static String formatParticipantPosition(Map<Participant, String> prizeMap, Participant participant) {
        String name = participant.getName();
        return String.format("%s : %s%s", name, prizeMap.get(participant), System.lineSeparator());
    }

    public static void printException(String message, Exception e) {
        printException(message);
        e.printStackTrace();
    }

    public static void printException(String message) {
        System.out.println(message);
    }
}