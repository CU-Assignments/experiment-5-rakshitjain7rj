import java.util.ArrayList;
import java.util.List;

public class SumCalculator {

    public static int sumList(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // Unboxing: Integer to int
        }
        return sum;
    }

    public static Integer parseInteger(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(parseInteger("10")); // Autoboxing: int to Integer
        numbers.add(20); // Autoboxing
        numbers.add(Integer.valueOf(30));
        numbers.add(parseInteger("40"));
        numbers.add(50);

        int sum = sumList(numbers);
        System.out.println("The sum of the numbers is: " + sum);
    }
}
