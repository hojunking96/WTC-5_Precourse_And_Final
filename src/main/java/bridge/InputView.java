package bridge;

import bridge.exceptions.InputException;
import camp.nextstep.edu.missionutils.Console;

import static bridge.exceptions.InputException.*;

public class InputView {

    public static int readBridgeSize() throws IllegalArgumentException {
        try {
            String bridgeSizeInput = Console.readLine();
            return InputException.bridgeSizeInputError(bridgeSizeInput);
        } catch (IllegalArgumentException e) {
            return readBridgeSize();
        }
    }

    public static String readMoving() throws IllegalArgumentException {
        try {
            String movingInput = Console.readLine();
            movingInputError(movingInput);
            return movingInput;
        } catch (IllegalArgumentException e) {
            return readMoving();
        }
    }

    public static String readGameCommand() {
        try {
            String gameCommandInput = Console.readLine();
            gameCommandInputError(gameCommandInput);
            return gameCommandInput;
        } catch (IllegalArgumentException e) {
            return readGameCommand();
        }
    }
}
