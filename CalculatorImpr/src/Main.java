import com.frequal.romannumerals.Converter;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, MyCustomException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например 5 + 4)");
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws ParseException, MyCustomException {
        String[] pattern = input.split(" ");
        if (pattern.length != 3) {
            throw new MyCustomException("Ошибка, некорректный формат ввода");
        }

        Converter converter = new Converter();
        int result;
        int a;
        int b;

        if (IsRoman(pattern[0], pattern[2])) {
            a = converter.toNumber(pattern[0]);
            b = converter.toNumber(pattern[2]);
        } else {
            try {
                a = Integer.parseInt(pattern[0]);
                b = Integer.parseInt(pattern[2]);
            } catch (NumberFormatException e) {
                return ("Ошибка, Введенные числа не удовлетворяют условию: " +e.getClass().getSimpleName());
            }
        }

        if (a > 10 || b > 10) {
            throw new MyCustomException("Ошибка, веденные числа не удовлетворяют условию");
        }

        switch (pattern[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                try {
                    result = a / b;
                } catch (ArithmeticException e) {
                    return ("Ошибка, деление на ноль: " +e.getClass().getSimpleName());
                }
                break;
            default:
                throw new MyCustomException("Ошибка, неверная операция");
        }

        if (IsRoman(pattern[0], pattern[2])) {
            if (result <= 0) {
                throw new MyCustomException("Ошибка, результат не может быть меньше нуля");
            }
            return converter.toRomanNumerals(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static boolean IsRoman(String num1, String num2) {
        if (num1.matches("[IVXLCDMivxlcdm]+") && num2.matches("[IVXLCDMivxlcdm]+")) {
            return true;
        } else return false;
    }
}

class MyCustomException extends Exception {
    public MyCustomException(String message) {
        super(message);
    }
}

