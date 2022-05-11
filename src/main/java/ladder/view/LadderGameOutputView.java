package ladder.view;

import ladder.domain.Ladder;
import ladder.domain.LadderGame;
import ladder.domain.LadderLine;
import ladder.domain.UserName;
import ladder.domain.UserNames;

public class LadderGameOutputView {

  public static final String SPACE = " ";
  public static final String DASH = "-";

  public static void printLadderGame(LadderGame ladderGame) {
    printUsers(ladderGame.getUserNames());

    Ladder ladder = ladderGame.getLadder();
    for (int i = 0; i < ladder.getLadderHeight(); i++) {
      printLadderLine(ladder.getLadderLine(i));
    }
  }

  private static void printUsers(UserNames userNames) {
    for (int i = 0; i < userNames.getUserSize(); i++) {
      String userName = userNames.getUserName(i);
      System.out.print(paddingUserName(userName));
    }
    System.out.println();
  }

  private static String paddingUserName(String userName) {
    return SPACE.repeat(UserName.LENGTH_LIMIT - userName.length() + 1) + userName;
  }

  private static void printLadderLine(LadderLine ladderLine) {
    System.out.print(SPACE.repeat(UserName.LENGTH_LIMIT));
    for (int i = 0; i < ladderLine.getLadderLength(); i++) {
      System.out.print("|");
      printConnect(ladderLine.isConnect(i));
    }
    System.out.println();
  }

  private static void printConnect(boolean connect) {
    if (connect) {
      System.out.print(DASH.repeat(UserName.LENGTH_LIMIT));
      return;
    }
    System.out.print(SPACE.repeat(UserName.LENGTH_LIMIT));
  }
}