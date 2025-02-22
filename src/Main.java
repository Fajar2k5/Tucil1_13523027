import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("====================");
        System.out.println("IQ PUZZLER PRO!!! :D");
        System.out.println("====================");

        Scanner scan = new Scanner(System.in);

        while (true) {
            String input;
            System.out.println("\nKetik salah satu nomor di bawah untuk:");
            System.out.println("1. Mulai\n2. Exit\n");

            input = scan.nextLine();

            if (input.equals("1")) {
                Game game = new Game(scan);
                game.runGame();
            } else if (input.equals("2")) {
                System.out.println("Anda keluar.\n");
                break;
            } else {
                System.out.println("Input tidak valid\n");
            }
        }
    }
}