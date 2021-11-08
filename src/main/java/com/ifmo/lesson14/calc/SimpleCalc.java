package com.ifmo.lesson14.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Добавьте поддержку переменных.
 * Синтаксис следующий.
 * Объявление переменной через символ '=':
 * x = 8
 *
 * Имя переменной не может быть числом.
 *
 * Использование в выражении вместо одного или двух аргументов:
 * x + 2
 * Результат: 10.
 *
 * Калькулятор должен выбрасывать соответствующие исключения с
 * подробными описаниями ошибок и как их исправить. Например,
 * если имя переменной не найдено или использовался неверный синтаксис.
 */
public class SimpleCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter expression: ");

            String line = scanner.nextLine();

            if ("exit".equals(line))
                break;

            try {
                System.out.println("Answer is: " + calculate(line));
            }
            catch (CalcException e) {
                System.err.println("Error occurred: ");

                e.printStackTrace();
            }
        }
    }

    static int calculate(String line) throws CalcException {
        if (!line.contains("+") && !line.contains("-") && !line.contains("="))
            throw new CalcException("Expression must contain '+' or '-' or '=': " + line);

        String[] operands = line.split(" ");

        if (operands.length != 3)
            throw new CalcException("Expression must have only 3 operands separated with space (e.g. 2 + 4): " + line);

        OPERATOR operator = OPERATOR.parse(operands[1]);

        return operator.apply(operands[0], operands[2]);
    }

    private static int parseOperand(String string) throws CalcException {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            throw new CalcException("Wrong operand, must be only integer number: " + string, e);
        }
    }

    private enum OPERATOR {
        PLUS, MINUS, EQUALS;

        static Map<String, Integer> map = new HashMap<>();

        int apply(String arg1, String arg2) throws CalcException {
            int result;
            switch (this) {

                case PLUS:
                    try {
                        result = parseOperand(arg1) + parseOperand(arg2);
                    }
                    catch (CalcException e){
                        try {
                            result = map.get(arg1) + parseOperand(arg2);
                        }
                        catch (NullPointerException e1){
                            throw new CalcException("variable not found, set variable", e1);
                        }
                    }
                    return result;

                case MINUS:
                    try {
                        result = parseOperand(arg1) - parseOperand(arg2);
                    }
                    catch (CalcException e){
                        try {
                            result = map.get(arg1) - parseOperand(arg2);
                        }
                        catch (NullPointerException e1){
                            throw new CalcException("variable not found, set variable", e1);
                        }
                    }
                    return result;

                case EQUALS:
                    map.put(arg1, parseOperand(arg2));
                    return 0;
            }

            throw new CalcException("Unsupported operator: " + this);
        }

        static OPERATOR parse(String str) throws CalcException {
            switch (str) {
                case "+":
                    return PLUS;
                case "-":
                    return MINUS;
                case "=":
                    return EQUALS;
            }

            throw new CalcException("Incorrect operator: " + str);
        }
    }
}
