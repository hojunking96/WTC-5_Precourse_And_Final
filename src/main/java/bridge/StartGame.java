package bridge;

public class StartGame {

    public static boolean startGame() {
        requestMove();
        BridgeGame.move();
        OutputView.printMap();
        if (isWrongWay()) {
            return whenWrong();
        }
        Application.movingTurn++;
        return true;
    }

    public static void requestMove() {
        OutputView.printSelect();
        Application.movingInput = InputView.readMoving();
    }

    public static boolean whenWrong() {
        if (wantRetry()) {
            BridgeGame.retry();
            return true;
        }
        Application.success = false;
        return false;
    }

    public static boolean wantRetry() {
        OutputView.printRetry();
        String retryOrNot = InputView.readGameCommand();
        if (retryOrNot.equals("R")) {
            return true;
        }
        return false;
    }

    public static boolean isRightWay() {
        String rightWay = Application.bridge.get(Application.movingTurn);
        boolean isRightAnswer = rightWay.equals(Application.movingInput);
        return isRightAnswer;
    }

    public static boolean isWrongWay() {
        boolean isWrongAnswer = !isRightWay();
        return isWrongAnswer;
    }
}
