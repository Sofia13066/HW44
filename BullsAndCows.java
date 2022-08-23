import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

class BullsAndCows{

    public static void main(String[] args) {
        
        int maxRandom = 9;
        int cows = 0;
        int bulls = 0;
        String randomizedNum = rnd(maxRandom);
        System.out.println("Randomized " + randomizedNum);
        System.out.println("Enter number containing 4 digits");
        Scanner scanner = new Scanner(System.in);
        String guessedNum = scanner.nextLine();
        check(randomizedNum, guessedNum, cows, bulls);
        
    }

    private static String rnd(int maxRandom) {
        HashSet<Integer> set = new HashSet<>();
            while (set.size() < 4){
                int varRand = new Random().nextInt(maxRandom)+1;
                set.add(varRand);
            }

        return set
        .stream()
        .map(n->String.valueOf(n))
        .collect(Collectors.joining(""));
    }

    static void check(String randomizedNum,String guessedNum,int cows,int bulls) {
        for (int i = 0; i < guessedNum.length(); i++) {
            char c1 = randomizedNum.charAt(i);
            char c2 = guessedNum.charAt(i);
                if(c1==c2){
                    bulls++;
                }else{
                    for(int j = 0; j < randomizedNum.length(); j++) {
                        if(c2 == randomizedNum.charAt(j)){
                            cows++;
                        }
                    }
                }
            
            // int ind = randomizedNum.indexOf(guessedNum.charAt(i));
            // if (ind == i) bulls++;
        
        }
        System.out.println("Cows = " + cows + ", bulls = " + bulls);
    }




}