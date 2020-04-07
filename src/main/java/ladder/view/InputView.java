package ladder.view;

import java.util.Scanner;

public class InputView {

    private static final String NAME_INPUT_MSG =
            "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String HEIGHT_INPUT_MSG =
            "최대 사다리 높이는 몇 개인가요?";

    public static String inputNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(NAME_INPUT_MSG);
        String names = scanner.nextLine();
        return names;
    }

    public static String inputHeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(HEIGHT_INPUT_MSG);
        String height = scanner.nextLine();
        return height;
    }
}