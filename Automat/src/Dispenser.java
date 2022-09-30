import java.util.*;

public class Dispenser {
    // Necessary statements
    private ArrayList<Integer> inserted_coins = new ArrayList<Integer>();
    private Scanner scanner = new Scanner(System.in);
    private int input;



    // Predefined dispenser settings
    private final int[] allowed_coins = {1, 2, 5};
    private final int can_price = 1;



    // Dispenser initialization
    public void init() {

        // Display coins in dispenser
        //System.out.print("Zawartość: ");
        //for(int coin :inserted_coins){
        //    System.out.print(coin);
        //    System.out.print(" ");
        //}

        Arrays.sort(allowed_coins);

        System.out.println("Cena jednej puszki: " + can_price);
        displayCoins();
        System.out.println("Ile puszek napoju chcesz kupić?");

        input = scanner.nextInt();

        paymentProcess(input);

        System.out.println("-------------------------------");
    }

    // Whole payment process (returns change)
    private void paymentProcess(int amount_of_cans) {

        int price = calculatePrice(amount_of_cans);

        while(price > 0) {
            System.out.println("Wrzuć monetę! Pozostało do zapłaty: " + price);

            input = scanner.nextInt();

            if(!checkCoin(input)) {
                System.out.println("Wrzuć prawidłową monetę!");
                displayCoins();
                continue;
            }

            inserted_coins.add(input);
            price -= input;

            if(price < 0) {

                int change = Math.abs(price);

                changeProcessing(change);
            }
        }
    }

    // Display allowed coins
    private void displayCoins() {
        String result = "";

        for(int i = 0; i < allowed_coins.length; i++) {
            result += allowed_coins[i] + " ";
        }

        System.out.println("Dozwolone monety: " + result);

    }

    // Check if coin is allowed
    private boolean checkCoin(int coin) {
        for (int allowed_coin : allowed_coins) {
            if (coin == allowed_coin) {
                return true;
            }
        }
        return false;
    }

    // Calculate whole price of all cans
    private int calculatePrice(int amount) {
        int price = 0;

        price = amount * can_price;

        return price;
    }

    // Calculate and give change
    private void changeProcessing(int change) {

        ArrayList<Integer> changeToRemove = new ArrayList<Integer>();

        int change_sum = change;

        Collections.sort(inserted_coins);
        Collections.reverse(inserted_coins);

        for(int inserted_coin: inserted_coins) {

            if(inserted_coin <= change) {

                changeToRemove.add(inserted_coin);

                change -= inserted_coin;
            }
        }

        if(change == 0) {
            System.out.print("Reszta: " + change_sum + " ( ");

            for(int coin_to_remove: changeToRemove) {
                System.out.print(coin_to_remove + " ");
                inserted_coins.remove(coin_to_remove);
            }
            System.out.println(")");

        } else {
            System.out.println("Automat nie ma jak wydać reszty!");
        }
    }
}