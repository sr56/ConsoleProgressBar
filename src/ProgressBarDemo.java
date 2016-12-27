/**
 * Created by suraj on 12/27/16.
 */


public class ProgressBarDemo {
    /*ANSI color code Escape sequences. */
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";

    public static void startDemo() {
        int completed = 0;
        char over = '▓', pending = '░', working = '▒';
        String print, output;
        String[] wheel = {"◜", "◝", "◞", "◟", "◯"};

        while (completed++ <= 100) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //Do Nothing
            }

            output = "Working.";
            for (int i = 0; i <= 5; i++) {
                print = ((completed % 5) <= i) ? " " : ".";
                output += print;
            }
            output += " " + PURPLE + wheel[(completed % wheel.length)] + RESET + " [";

            for (int i = 0; i <= 100; i++) {
                if (i < completed) {
                    print = GREEN + over + RESET;
                } else if (i == completed) {
                    print = BLUE + working + RESET;
                } else {
                    print = RED + pending + RESET;
                }
                output += print;
            }
            output += " ] " + (completed - 1) + "%   ";

            System.out.print("\r");
            System.out.print(output);
        }
        System.out.println(" Done!");
    }

    public static void main(String[] args) {
        startDemo();
    }

}