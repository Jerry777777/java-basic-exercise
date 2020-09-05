import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        String firstWordList = "";
        String secondWordList = "";

        Scanner scanner = new Scanner(System.in);
        firstWordList = scanner.nextLine();
        secondWordList = scanner.nextLine();

        System.out.println(firstWordList);
        System.out.println(secondWordList);
        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);

        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        firstWordList = firstWordList.toLowerCase();
        secondWordList = secondWordList.toLowerCase();

        List<String> firstList = Arrays.asList(firstWordList.split(","));
        List<String> secondList = Arrays.asList(secondWordList.split(","));

        long countOfNoAlphabet1 = firstList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        long countOfNoAlphabet2 = secondList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        if (countOfNoAlphabet1 > 0 || countOfNoAlphabet2 > 0)
            throw new RuntimeException();

        long emptyString1 = firstList.stream()
                .filter(str -> str.equals(""))
                .count();

        long emptyString2 = secondList.stream()
                .filter(str -> str.equals(""))
                .count();

        if (emptyString1 > 0 || emptyString2 > 0)
            throw new RuntimeException();

        List<String> res = firstList.stream()
                .filter(secondList::contains).distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList())
                .stream().map(String::toUpperCase).map(str -> {
                    StringBuilder r = new StringBuilder();
                    for (int i = 0; i < str.length() - 1; i++) {
                        r.append(str.charAt(i)).append(" ");
                    }
                    r.append(str.charAt(str.length() - 1));
                    return r.toString();
                }).distinct().collect(Collectors.toList());

        System.out.println(res);
        return res;
    }
}
