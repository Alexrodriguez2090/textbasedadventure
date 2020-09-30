import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Setup {
    /**
    * Sets up the room for the player to spawn in.
    * @param rooms - Takes in all the rooms to check which the user can spawn in
    * @return - Returns the room the user has spawned in
    */
    public static ArrayList<String> setUpStart(ArrayList<ArrayList<String>> rooms) {
        Random rnd = new Random();

        ArrayList<String> startingRooms = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(4).equals("true")) {
                startingRooms.add(rooms.get(i).get(0));
            }
        }
        int rndRoomToStart = rnd.nextInt(startingRooms.size());
        String nameOfRoom = startingRooms.get(rndRoomToStart);

        ArrayList<String> myRoom = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(0).equals(nameOfRoom)) {
                myRoom = rooms.get(i);
                break;
            }
        }
        return myRoom;
    }

    /**
    * Sets up a blank bag of items for the player
    * @return - Returns a listarray of [false, false] since there are only two items
    */
    public static ArrayList<String> setUpItems() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("false");
        items.add("false");
        return items;
    }

    /**
    * Prints the instructions out to the user whenever called.
    */
    public static void instructions() {
        System.out.println("1. Move by simply typing the first letter of north, south, east, or west.");
        System.out.println("n, s, e, w");
        System.out.println("2. You will inspect a room only the first time you enter, and you can review your inspection again by typing 'inspect'.");
        System.out.println("3. You can view your items by typing 'items'.");
        System.out.println("4. If you need to see these instructions again, simply type 'instructions' or 'help'.");
        System.out.println("5. You can quit at any time by typing 'quit', although your progress will not save.");
        System.out.println("6. Have fun!");
    }

    /**
    *Prints the wake up room for the user
    */
    public static void wakeUp(ArrayList<String>activeRoom) {
        System.out.println("You wake up, but all you see is darkness.");
        System.out.println("You don't know where you are, all you remember was being home.");
        System.out.println("The lights flicker, momentarily staying on at times.");
        System.out.printf("On the wall, you see the plaque 'Darkness Manor - %s'.\n", activeRoom.get(0));
        System.out.println("It seems you have woken up in a mansion.");
        System.out.println();
    }

    /**
    * Sets up which two rooms have the keys necessary to progress
    * @param rooms - Takes in all the rooms in the game, and checks for which could hold keys
    */
    public static ArrayList<String> importantRooms(ArrayList<ArrayList<String>> rooms) {
        Random rnd = new Random();
        ArrayList<String> roomsWithFirstKey = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(5).equals("true")) {
                roomsWithFirstKey.add(rooms.get(i).get(0));
            }
        }

        ArrayList<String> roomsWithSecondKey = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(6).equals("true")) {
                roomsWithSecondKey.add(rooms.get(i).get(0));
            }
        }

        int rndRoomForFirstKey = rnd.nextInt(roomsWithFirstKey.size());
        String nameOfFirstKeyRoom = roomsWithFirstKey.get(rndRoomForFirstKey);

        int rndRoomForSecondKey = rnd.nextInt(roomsWithSecondKey.size());
        String nameOfSecondKeyRoom = roomsWithSecondKey.get(rndRoomForSecondKey);

        ArrayList<String> importantRooms = new ArrayList<String>();
        importantRooms.add(nameOfFirstKeyRoom);
        importantRooms.add(nameOfSecondKeyRoom);
        return importantRooms;
    }
}
