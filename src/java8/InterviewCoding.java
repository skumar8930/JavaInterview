package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class InterviewCoding {
    public static void main(String ss[]) {
        //findAllEvenNumbers();
        findNumberStartingWith1();

    }

    // 1.Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
    static void findAllEvenNumbers() {
        List<Integer> numbers = Arrays.asList(2, 5, 7, 56, 23);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
    }

    // 2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
    static void findNumberStartingWith1() {
        //List.of(11, 35, 12, 100, 111, 198, 116, 67, 34).stream().filter(predicate).toList().stream().forEach(System.out::println);
        //OR
        List.of(11, 35, 12, 100, 111, 198, 116, 67, 34).stream().map(String::valueOf).filter(n -> n.startsWith("1"))
                .forEach(System.out::println);

    }

    //12.3
    //1.2
    //0.1

    private static Predicate<Integer> predicate = number -> {
        int remainder = 0;
        int firstNumber = 0;
        while (number != 0) {
            remainder = number % 10;//Remainder
            number = number / 10;// changed number
            if (number == 0) {
                firstNumber = remainder;
            }
        }
        if (firstNumber == 1) {
            return true;
        }
        return false;
    };
}

