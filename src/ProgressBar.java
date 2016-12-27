
public class ProgressBar implements Runnable{
    /*ANSI color code Escape sequences. */
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";

    private int refreshIntervalMilliSec = 10;
    private int completedPercent = 0;
    private boolean progressBarRunning = false;

    public void setRefreshIntervalMilliSec(int millisec){
        this.refreshIntervalMilliSec = millisec;
    }

    public int getCompletedPercent() {
        return completedPercent;
    }

    public void setCompletedPercent(int completedPercent) {
        if(completedPercent > 100) completedPercent = 100;
        if(completedPercent < 0) completedPercent = 0;
        this.completedPercent = completedPercent;
    }

    @Override
    public void run() {
        if(progressBarRunning){
            return;
        }
        progressBarRunning = true;
        char donePart = '▓', pendingPart = '░', workingPart = '▒';
        String temp, wheelRoll, progress, completion;
        String working = "Working......";
        int invocation = 0;
        String[] wheel = {"◜", "◝", "◞", "◟", "◯"};


        while (completedPercent < 100) {
            try {
                Thread.sleep(refreshIntervalMilliSec);
            } catch (InterruptedException ie) {
                // Do Nothing
            }

            working = (working.equalsIgnoreCase("Working......"))?"Working.":working.trim() + ".";
            working = String.format("%1$-13s", working);

            wheelRoll = " " + PURPLE + wheel[(invocation++% wheel.length)] + RESET;

            progress = "[";
            for (int i = 0; i <= 100; i++) {
                if (i < completedPercent) {
                    temp = GREEN + donePart + RESET;
                } else if (i == completedPercent) {
                    temp = BLUE + workingPart + RESET;
                } else {
                    temp = RED + pendingPart + RESET;
                }
                progress += temp;
            }
            progress += "] ";

            completion = (completedPercent - 1) + "%   ";

            temp = working + wheelRoll + progress + completion;

            System.out.print("\r");
            System.out.print(temp);
        }
        progressBarRunning = false;
        System.out.println(" Done!");
    }

    public static void main(String[] args) {
        ProgressBar progressBar = new ProgressBar();
        Thread progressBarThread = new Thread(progressBar);
        progressBarThread.start();

        int progress = 0;
        int random;

        while(progress <= 100){
            random = Double.valueOf(Math.random() * 3D).intValue();
            progress += random;
            progressBar.setCompletedPercent(progress);
            random = Double.valueOf(Math.random() * 24D).intValue();
            try {
                Thread.sleep(random * 100);
            } catch (InterruptedException e) {
                // Do Nothing
            }
        }

    }

}
