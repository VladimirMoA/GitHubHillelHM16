import org.w3c.dom.ls.LSOutput;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BaseGame {

    public static PossibleChoice printChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число (0 - камень, 1 - ножницы, 2 - бумага)");
        PossibleChoice choice = null;
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            switch (number) {
                case 0 : choice = PossibleChoice.rock;
                    System.out.println("Выбран камень");
                    return choice;
                case 1 : choice = PossibleChoice.scissors;
                    System.out.println("Выбраны ножницы");
                    return choice;
                case 2 : choice = PossibleChoice.paper;
                    System.out.println("Выбрана бумага");
                    return choice;
                default:
                    System.out.println("Выбран неправильный номер");
            }

        } else {
            System.out.println("Извините, но это явно не число. Перезапустите программу и попробуйте снова!");
        }
        return choice;
    }

    public static PossibleChoice printRandomChoice() {

        int number = (int) (Math.random() * 3);
        PossibleChoice choice = null;
        switch (number) {
            case 0:
                choice = PossibleChoice.rock;
                System.out.println("Машина выбрала камень");
                return choice;
            case 1:
                choice = PossibleChoice.scissors;
                System.out.println("Машина выбрала ножницы");
                return choice;
            case 2:
                choice = PossibleChoice.paper;
                System.out.println("Машина выбрала бумагу");
                return choice;
        } return choice;
    }

    public static int temporaryResult(PossibleChoice machine, PossibleChoice player) {
        int playerPos = -1;
        int machinePos = -1;
        int [][] result = new int [3][3];
        result [0][0] = 0;
        result [1][1] = 0;
        result [2][2] = 0;
        result [0][1] = 1;
        result [1][2] = 1;
        result [2][0] = 1;
        result [0][2] = 2;
        result [1][0] = 2;
        result [2][1] = 2;
        switch (player) {
            case rock: playerPos = 0;
            break;
            case scissors: playerPos = 1;
            break;
            case paper: playerPos = 2;
            break;
        }
        switch (machine) {
            case rock: machinePos = 0;
            break;
            case scissors: machinePos = 1;
            break;
            case paper: machinePos = 2;
            break;
        }
        if (result[machinePos][playerPos] == 0) {
            System.out.println("У вас ничья");
            return 0;
        } else if (result[machinePos][playerPos] == 1) {
            System.out.println("Вы проиграли");
            return 1;
        } else if (result[machinePos][playerPos] == 2) {
            System.out.println("Вы выйграли");
            return 2;
        }
        return -1;
    }

    public static void writeToFile(String s)
            throws IOException {
        FileWriter fileWriter = new FileWriter("Результат");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(s);
        printWriter.close();
    }

    public static void main(String[] args) throws IOException {
        int playerWinCount = 0;
        int machineWinCount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько вы хотите сыграть игр?");
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            System.out.println("Приступим");
            for (int i = 1; i <= number; i++) {
                switch (temporaryResult(printChoice(),printRandomChoice())) {
                    case 0 : playerWinCount++;
                    machineWinCount++;
                    break;
                    case 1 : machineWinCount++;
                    break;
                    case 2 : playerWinCount++;
                    break;
                }
                Scanner esc = new Scanner(System.in);
                System.out.println("Если надоело напиши 'конец' в ином случае можешь пропустить нажав Enter");
                if (esc.hasNextLine()) {
                    String escape = esc.nextLine();
                    System.out.println(escape);
                    if (escape.equals("конец")) break;
                }
            }
            String result;
            if (playerWinCount > machineWinCount) {
                result = "Вы выйграли машину со счётом " + playerWinCount + ":" + machineWinCount;
                System.out.println(result);
            } else if (playerWinCount < machineWinCount) {
                result = "Машина выйграла вас со счётом " + machineWinCount + ":" + playerWinCount;
                System.out.println(result);
            } else {
                result = "Вы сражались на равных, финальный счёт " + machineWinCount + ":" + playerWinCount;
                System.out.println(result);
            }
            writeToFile(result);
        } else {
                System.out.println("Извините, но это явно не число. Перезапустите программу и попробуйте снова!");
        }
    }
}
