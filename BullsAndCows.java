import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

class BullsAndCows {

    public static void main(String[] args) throws FileNotFoundException {

        int maxRandom = 9;
        int cows = 0;
        int bulls = 0;
        boolean winner;

        StringBuilder allRes = new StringBuilder();
        String randomizedNum = rnd(maxRandom);
        System.out.println("Randomized " + randomizedNum);
        int tries = 0;
        do {
            System.out.println("Enter number containing 4 digits");
            Scanner scanner = new Scanner(System.in);
            String guessedNum = scanner.nextLine();
            winner = check(randomizedNum, guessedNum, cows, bulls, allRes, ++tries);
        } while (winner != true);
        System.out.println("You are a winner");

    }

    private static String rnd(int maxRandom) {
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int varRand = new Random().nextInt(maxRandom) + 1;
            set.add(varRand);
        }

        return set
                .stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(""));
    }

    private static boolean check(String randomizedNum, String guessedNum, int cows, int bulls, StringBuilder allRes, int tries)
            throws FileNotFoundException {

        boolean winner = false;
        for (int i = 0; i < 4 && i < guessedNum.length(); i++) {
            char c1 = randomizedNum.charAt(i);
            char c2 = guessedNum.charAt(i);
            if (c1 == c2) {
                bulls++;
            } else {
                for (int j = 0; j < randomizedNum.length(); j++) {
                    if (c2 == randomizedNum.charAt(j)) {
                        cows++;
                    }
                }
            }
        }
        String res = "Guessed number " + guessedNum + ", cows = " + cows + ", bulls = " + bulls;
        allRes.append(res + "\n");
        System.out.println(allRes);
        
        if (bulls == 4) {
            winner = true;
            recordAllResult(allRes.toString(), tries);
        }
        cows = 0;
        bulls = 0;
        return winner;

    }

    private static void recordAllResult(String allRes, int tries) throws FileNotFoundException {
        StringBuilder dataFileName = new StringBuilder(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_kk_mm")))
                .append("_" + tries + ".txt");
        PrintStream out = new PrintStream(new File(dataFileName.toString()));
        out.println(allRes.toString());
        out.close();

    }

}