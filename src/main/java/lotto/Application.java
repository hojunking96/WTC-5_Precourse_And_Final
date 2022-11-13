package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int money = moneyInput();
        List<Lotto> lotto = new ArrayList<>();
        buyLotto(money, lotto);
        printLotto(lotto);
        List<Integer> winNums = winNumInput();
        int bonusNum = bonusNumInput(winNums);
        List<Result> results = new ArrayList<>();
/*
        int first = 0, second = 0, third = 0, fourth = 0, fifth = 0;
        for (Result e : results) {
            if (e.getMatchCnt() == 6)
                first++;
            if (e.getMatchCnt() == 5)
                if (e.isBonusMatch())
                    second++;
                else third++;
            if (e.getMatchCnt() == 4)
                fourth++;
            if (e.getMatchCnt() == 3)
                fifth++;
            PrintEnd(first, second, third, fourth, fifth, money);
        }
        */
    }

    public static int moneyInput() {
        String input = Console.readLine();
        inputTypeError(input);
        int money = Integer.parseInt(input);
        moneyInputError(money);
        return money;
    }

    public static void moneyInputError(int money) {
        if (money % 1000 != 0) {
            System.out.println("[ERROR] 로또 구입 금액은 1000 단위여야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static List<Integer> winNumInput() {
        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> winNums = new ArrayList<>();
        String input = Console.readLine();
        winNumInputError(input);
        String[] tmpNums = input.split(",");
        for (String e : tmpNums) {
            inputTypeError(e);
            int num = Integer.parseInt(e);
            rangeError(num);
            duplicate(winNums, num);
            winNums.add(num);
        }
        return winNums;
    }

    public static void winNumInputError(String input) {
        if (!input.contains(",")) {
            System.out.println("[ERROR] 잘못된 입력입니다.");
            throw new IllegalArgumentException();
        }
    }

    public static Integer bonusNumInput(List<Integer> winNums) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String tmp = Console.readLine();
        //예외 확인
        inputTypeError(tmp);
        int bonus = Integer.parseInt(tmp);
        rangeError(bonus);
        duplicate(winNums, bonus);
        return bonus;
    }

    public static void inputTypeError(String input) {
        for (int i = 0; i < input.length(); i++)
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("[ERROR] 숫자를 입력해야 합니다.");
                throw new IllegalArgumentException();
            }
    }

    public static void rangeError(int number) {
        if (number < 0 || number > 45) {
            System.out.println("[ERROR] 1~45의 숫자를 입력해야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static void duplicate(List<Integer> winNums, int number) {
        if (winNums.contains(number)) {
            System.out.println("[ERROR] 중복된 수를 입력하였습니다.");
            throw new IllegalArgumentException();
        }
    }

    public static void buyLotto(int money, List<Lotto> lotto) {
        int lottoQTY = money / 1000;
        for (int i = 0; i < lottoQTY; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lotto.add(new Lotto(numbers));
        }
    }

    public static void printLotto(List<Lotto> lotto) {
        System.out.println(lotto.size() + "개를 구매했습니다.");
        for (Lotto e : lotto)
            e.printNumbers();
    }
/*
    public static void PrintEnd(int first, int second, int third, int fourth, int fifth, int money) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000)원 - " + fifth + "개");
        System.out.println("4개 일치 (50,000)원 - " + fourth + "개");
        System.out.println("5개 일치 (1,500,000)원 - " + third + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000)원 - " + second + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + first + "개");
        int total = fifth * 5000 + fourth * 50000 + third * 1500000 + second * 30000000 + first * 2000000000;
        float rev = total / money;
        System.out.println("총 수익률은 " + Math.round(rev * 10) / 10.0 + "입니다.");
    }
*/
}
