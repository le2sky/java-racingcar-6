package racingcar;

import racingcar.entity.Cars;
import racingcar.entity.MovePolicy;
import racingcar.mapper.Delimiter;
import racingcar.mapper.InputMapper;

import java.util.List;

import static racingcar.view.InputView.readNames;
import static racingcar.view.InputView.readTimes;
import static racingcar.view.OutputView.*;

class Controller {

    private final MovePolicy policy;

    public Controller(MovePolicy policy) {
        this.policy = policy;
    }

    public void run() {
        start(createCars());
    }

    private Cars createCars() {
        return Cars.from(getCarNames());
    }

    private List<String> getCarNames() {
        Delimiter delimiter = InputMapper.getNameListDelimiter();

        return InputMapper.mapToNameList(readNames(delimiter.getName(), delimiter.getShape()));
    }

    private void start(Cars cars) {
        int maxTimes = getRaceTimes();

        race(cars, maxTimes);
    }

    private int getRaceTimes() {
        return InputMapper.mapToPositiveNumber(readTimes());
    }

    private void race(Cars cars, int maxTimes) {
        printResultHeader();

        for (int times = 0; times < maxTimes; times++) {
            raceOneTimes(cars);
        }

        printWinner(cars.findFrontCarsName());
    }

    private void raceOneTimes(Cars cars) {
        cars.moveAll(policy);

        printResult(cars.describeAll());
    }
}
