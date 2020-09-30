import java.util.concurrent.TimeUnit;

public class Spe {
    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    public static void speech() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
        sleep(3);
        System.out.println("Do you think you can get away from here?");
        sleep(2);
        System.out.println("THERE IS NO ESCAPE FROM THIS PLACE");
        sleep(2);
        System.out.println("In fact, the only way you can get away from here...");
        sleep(2);
        System.out.println("Is if I closed your game.");
        sleep(2);
    }
}
