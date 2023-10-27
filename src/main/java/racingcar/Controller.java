package racingcar;

import racingcar.entity.MovePolicy;
import racingcar.mapper.Delimiter;
import racingcar.mapper.InputMapper;
import racingcar.view.InputView;

import java.util.List;

class Controller {

    private final MovePolicy policy;

    public Controller(MovePolicy policy) {
        this.policy = policy;
    }

    public void run() {
        List<String> names = readNames();
    }

    private List<String> readNames() {
        String delimiterName = Delimiter.NAME.getName();
        String delimiterShape = Delimiter.NAME.getShape();

        return InputMapper.mapToNameList(InputView.readNames(delimiterName, delimiterShape));
    }
}
