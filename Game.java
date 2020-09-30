import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game {
    /**
    * Just the main menu of the game
    * @param sc - Takes in the scanner from the main
    */
    public static int mainMenu(Scanner sc) {
        System.out.println("  The Mansion: Prologue");
        System.out.println("-------------------------");
        System.out.println("     1. Start Game");
        System.out.println("     2. Quit");

        int input = sc.nextInt();
        if (input == 1) {
            return 2;
        } else if (input == 2) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
    * This function runs the game, everything that will happen ingame has to come from here.
    * @param sc - Scanner from the main
    * @param rooms - ArrayList of all rooms in the game
    * @return The return is only for when the user quits, which takes them back to menu, or for when the game is finished.
    */
    public static int ingame(Scanner sc, ArrayList<ArrayList<String>> rooms) {
        //Initializing the active room, rooms with keys, and rooms the player has seen
        ArrayList<String> activeRoom = new ArrayList<String>();
        ArrayList<String> importantRooms = new ArrayList<String>();
        ArrayList<String> seenRooms = new ArrayList<String>();
        ArrayList<String> items = new ArrayList<String>();
        seenRooms.add("default");
        activeRoom = Setup.setUpStart(rooms);
        importantRooms = Setup.importantRooms(rooms);
        items = Setup.setUpItems();

        System.out.println();
        System.out.println("****************");
        System.out.println("  Instructions");
        System.out.println("****************");
        Setup.instructions();
        System.out.println();
        Setup.wakeUp(activeRoom);

        int ingame = 1;
        while (ingame == 1) {
            System.out.println(activeRoom.get(3));
            System.out.println("What would you like to do?");
            String input = sc.next();
            System.out.println();
                if (input.equals("n") || input.equals("s") || input.equals("e") || input.equals("w")) {
                    activeRoom = Character.findRoom(rooms, activeRoom, input, items); //Check if room exists, and output to user the outcome
                    seenRooms = Character.firstTimeRoom(seenRooms, activeRoom); //Check if this room has been seen before for description
                    items = Character.roomCheck(activeRoom, importantRooms, items); //Check if room has keys
                    if (activeRoom.get(0).equals("End")) {
                        ingame = 2;
                    }
                } else if (input.equals("inspect")) {
                    System.out.println(activeRoom.get(1));
                } else if (input.equals("items")) {
                    Character.characterItems(items);
                } else if (input.equals("instructions") || input.equals("help")) {
                    Setup.instructions();
                } else if (input.equals("quit")) {
                    return 1;
                } else {
                    System.out.println("Command not found. Type 'help' for a explanation of commands.");
                }
        }

        if (ingame == 2) {
            rooms = Building.buildingMap2();
            activeRoom = rooms.get(0);
            Spe.sleep(3);
            System.out.println();
            System.out.println("You wake up, but all you see is darkness.");
            System.out.println("Judging by all the glass on the ground, you feel you are in the gallery.");
            System.out.println("You feel like you've been here before.");
        }
        while (ingame == 2) {
            System.out.println("What would you like to do?");
            String input = sc.next();
            System.out.println();
                if (input.equals("n") || input.equals("s") || input.equals("e") || input.equals("w")) {
                    activeRoom = Character.partTwo(rooms, activeRoom, input);
                    if (activeRoom.get(0).equals("Trueend")) {
                        Spe.speech();
                        return 0;
                    }
                } else if (input.equals("inspect")) {
                    System.out.println("You can't see anything.");
                } else if (input.equals("items")) {
                    System.out.println("No items can help you now.");
                } else if (input.equals("instructions") || input.equals("help")) {
                    System.out.println("No one can help you now.");
                } else if (input.equals("quit")) {
                    System.out.println("No quitting now.");
                } else {
                    System.out.println("Command not found. Type 'help' for a explanation of commands.");
                }
        }
        return 1;
    }

    /**
    * The main, which holds whether the user is in the menu, or ingame
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<String>> rooms = new ArrayList<ArrayList<String>>();
        rooms = Building.buildingMap();

        int mode = 1;
        while (mode != 0) {

            while (mode == 1) {
                mode = mainMenu(sc);
            }

            while (mode == 2) {
                mode = ingame(sc, rooms);
            }
        }
    }
}
