package bullscows;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int lenOfSymbols = 0;
        try {
             lenOfSymbols = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Error: " + scanner.next() + " isn`t a valid number.");
            System.exit(-1);
        }
        if (lenOfSymbols==0) {
            System.out.println("error");
            System.exit(-1);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int allowSybols = 0;
        try {
            allowSybols = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Error: " + scanner.next() + " isn`t a valid number.");
            System.exit(-1);
        }
        if (lenOfSymbols>allowSybols) {
            System.out.println("Error: it's not possible to generate a code with a length of" +
                    " "+lenOfSymbols+ " with " + allowSybols+ " unique symbols.");
            System.exit(-1);
        }
        char lastChar = (char) (allowSybols+86);
        String secretNumber = randomSymbolsCode(lenOfSymbols,allowSybols);
        StringBuilder cipher = new StringBuilder();
        cipher.append("*".repeat(Math.max(0, lenOfSymbols)));
        if (allowSybols>9) {
            System.out.println("The secret is prepared: " + cipher.toString() + " (0-9, a-" + lastChar + ").");
        }
        else {
            System.out.println("The secret is prepared: " + cipher.toString() + " (0-"+allowSybols+").");
        }
        System.out.println("Okay, let's start a game!");
        //System.out.println(secretNumber.contains("z"));
        String[] secretNumberArray = secretNumber.split("");
        boolean match = false;
        int turn = 1;
        int x = 0, y = 0;// x  - bulls , y - cows
        int kio=0;
        while (!match) {
            String userNumber = scanner.next();
            if (userNumber.length()!=lenOfSymbols) {
                System.out.println("error");
                System.exit(-1);
            }
            String[] userNumberArray = userNumber.split("");
            if (userNumber.equals(secretNumber)) {
                match = true;
                // Проверяет одинковы ли наши строки
            }
            System.out.println("Turn " + turn+":");
            for ( int i = 0; i <userNumber.length(); i++) {
                //System.out.println(userNumberArray[i]+ " "+ secretNumberArray[i]);
                if (userNumberArray[i].equals(secretNumberArray[i]))  {
                    x += 1;

                    //System.out.println(x);
                }
            }
            for ( int k = 0; k<userNumber.length(); k ++){
                if (secretNumber.contains(userNumberArray[k])) {
                    y += 1;
                }
            }
            y= y-x;
            System.out.print("Grade: ");
            if (x!=0 && y== 0) {
                System.out.println(x + " bull(s).");
            }
            if (x==0 && y!= 0 ) {
                System.out.println( y + " cow(s).");
            }
            if (x==0 && y==0) {
                System.out.println("None.");
            }
            if (x!=0 && y!=0) {
                System.out.println(x + " bull(s)" + " and "+ y + " cow(s)." );
            }
            x=0;
            y=0;
            turn+=1;

        }

        System.out.println("Congratulations! You guessed the secret code.");
    }
    public static String randomSecretCode1(int lenSecret){
        if (lenSecret>10) {
            return "Error: can't generate a secret number with a length of "+lenSecret+ " because there aren't enough unique digits.";
        }
        int i = 0;
        StringBuilder unique = new StringBuilder();
        while (i<lenSecret) {
            long pseudoRandomNumber = System.nanoTime();
            String lalala = new String(String.valueOf(pseudoRandomNumber / 1000));
            StringBuilder extract = new StringBuilder(lalala);
            extract.reverse();
            String[] extract_list = extract.toString().split("");
            if (unique.indexOf(extract_list[0]) == -1) {
                unique.append(extract_list[0]);
                i++;
            }
        }
        return unique.toString();
    }
    public static String randomSecretCode(int lenSecret) {

        StringBuilder unique = new StringBuilder();
        Random random = new Random();
        if (lenSecret>10) {
            System.out.println("\"Error: can't generate a secret number with a length of"+lenSecret+
                    "because there aren't enough unique digits.");
            System.exit(-1);
        }
        else {
            int i = 0;
            while (i < lenSecret) {
                int randomNumber = random.nextInt(10);
                if (!unique.toString().contains(randomNumber + "")) {
                    unique.append(randomNumber);
                    i++;
                }
            }
        }
        return unique.toString();
    }
    public static String randomSymbolsCode(int lenOfSymbols,int allowSybols){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        StringBuilder unique = new StringBuilder();
        boolean triger = true;
        int counter = 0;


        if (allowSybols>36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(-1);
        }
        else {
            while(counter<lenOfSymbols) {
                int randomix = random.nextInt(allowSybols);//+97;
                if (randomix>9) {
                    char randomChar = (char) (randomix+87);
                    if (!unique.toString().contains(randomChar+"")) {
                        unique.append(randomChar);
                        counter++;
                    }
                }
                else {
                    if (!unique.toString().contains(randomix + "")) {
                        unique.append(randomix);
                        counter++;
                    }
                }
                //System.out.println(unique);
            }


        }
        return unique.toString();

    }

}



