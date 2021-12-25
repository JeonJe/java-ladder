package nextstep.ladder.model;

import nextstep.ladder.controller.UsersLadderResults;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LadderGame {

    private final Ladder ladder;
    private final UsersLadderResults usersLadderResults;

    public LadderGame(Ladder ladder, UsersLadderResults usersLadderResults) {
        validate(ladder, usersLadderResults);
        this.ladder = ladder;
        this.usersLadderResults = usersLadderResults;
    }

    private void validate(Ladder ladder, UsersLadderResults usersLadderResults) {
        if (Objects.isNull(ladder)) {
            throw new IllegalArgumentException("게임 사다리 정보가 null입니다.");
        }
        if (Objects.isNull(usersLadderResults)) {
            throw new IllegalArgumentException("사다리 게임의 결과에 필요한 사용자와 결과 정보가 null입니다.");
        }
    }

    public UserResults userResults() {
        return UserResults.from(processedUserResults());
    }

    private List<UserResult> processedUserResults() {
        return IntStream.range(0, usersLadderResults.size())
                .boxed()
                .map(this::userResult)
                .collect(Collectors.toList());
    }

    private UserResult userResult(int userIndex) {
        return new UserResult(
                usersLadderResults.getUser(userIndex),
                usersLadderResults.getLadderResult(ladder.move(userIndex))
        );
    }
}