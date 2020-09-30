import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game {
    public static int mainMenu(Scanner sc) {
        System.out.println("  The Mansion");
        System.out.println("-------------");
        System.out.println("1. Start Game");
        System.out.println("2. Quit");

        int input = sc.nextInt();
        if (input == 1) {
            return 2;
        } else if (input == 2) {
            return 0;
        } else {
            return 1;
        }

    }

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

    public static ArrayList<String> roomCheck(ArrayList<String> activeRoom, ArrayList<String> importantRooms, ArrayList<String> items) {
        //Checks if the room we just went into has the first key
        if (activeRoom.get(0).equals(importantRooms.get(0))) {
            items.set(0, "true");
            System.out.println("You see a chest labeled '1', with a key inside. This chest does not look old, like everything else, so you decide to take the key.");
            return items;
        }
        //Checks if the room we just went into has the second key
        if (activeRoom.get(0).equals(importantRooms.get(1))) {
            items.set(1, "true");
            System.out.println("You see a chest labeled '2' in sink. You do not feel as if this chest fits with the decor here. You take the key.");
            return items;
        }
        return items;
    }

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
    * Prints the instructions out to the user whenever called.
    */
    public static void instructions() {
        System.out.println("1. Move by simply typing the first letter of north, south, east, or west.");
        System.out.println("n, s, e, w");
        System.out.println("2. You will inspect a room the first time you enter, and you can review your inspection again by typing 'inspect'.");
        System.out.println("3. You can view your items by typing 'items'.");
        System.out.println("4. If you need to see these instructions again, simply type 'instructions' or 'help'.");
        System.out.println("5. You can quit at any time by typing 'quit', although your progress will not save.");
        System.out.println("6. Have fun!");
    }

    /**
    * Checks if the user has been in this room before, since the first time going into the room triggers its description.
    * @param seenRooms -
    * @param activeRoom -
    * @return - Returns the list of seen rooms, whether it has changed with this new room or not.
    */
    public static ArrayList<String> firstTimeRoom(ArrayList<String> seenRooms, ArrayList<String> activeRoom) {
        if (seenRooms.contains(activeRoom.get(0))) {
            return seenRooms;
        } else {
            seenRooms.add(activeRoom.get(0));
            System.out.println(activeRoom.get(1));
        }
        return seenRooms;
    }

    /**
    * Checks whichever direction the user has input for a room.
    * @param rooms -
    * @param activeRoom -
    * @param compass - The direction the user input
    * @return - Returns either the new room the user has went into, or the same room if the user walked into a wall
    */
    public static ArrayList<String> findRoom(ArrayList<ArrayList<String>> rooms, ArrayList<String> activeRoom, String compass, ArrayList<String> items) {
        int direction = 0;
        switch (compass) {
            case "n":
                direction = 7;
                break;
            case "s":
                direction = 8;
                break;
            case "e":
                direction = 9;
                break;
            case "w":
                direction = 10;
                break;
        }

        String newRoom = activeRoom.get(direction);
        if (newRoom.equals("null")) {
            System.out.println("There is nothing in that direction.");
            return activeRoom;

        } else if (newRoom.equals("Backyard")) {
            //If they have both keys, else they do not
            if (items.get(0).equals("true") && items.get(1).equals("true")) {
                ArrayList<String> roomToSend = new ArrayList<String>();
                for (int i = 0; i < rooms.size(); i++) {
                    if (rooms.get(i).get(0).equals(newRoom)) {
                        roomToSend = rooms.get(i);
                        break;
                    }
                }
                System.out.println(roomToSend.get(2));
                return roomToSend;

            } else {
                System.out.println("You cannot open the door, you need to fill both keyslots.");
                return activeRoom;
            }
        } else {
            ArrayList<String> roomToSend = new ArrayList<String>();
            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i).get(0).equals(newRoom)) {
                    roomToSend = rooms.get(i);
                    break;
                }
            }
            System.out.println(roomToSend.get(2));
            return roomToSend;

        }
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
        seenRooms.add("default");
        activeRoom = setUpStart(rooms);
        importantRooms = importantRooms(rooms);
        System.out.println(importantRooms);

        //Starting up the items variable. The only 2 items are keys, and they are ordered by their position on the list. [key1, key2]
        ArrayList<String> items = new ArrayList<String>();
        items.add("false");
        items.add("false");

        instructions();
        System.out.println();
        wakeUp(activeRoom);

        int ingame = 1;
        while (ingame == 1) {
            System.out.println(activeRoom.get(3));
            System.out.println("What would you like to do?");
            String input = sc.next();
            System.out.println();
                if (input.equals("n") || input.equals("s") || input.equals("e") || input.equals("w")) {
                    activeRoom = findRoom(rooms, activeRoom, input, items); //Check if room exists, and output to user the outcome
                    seenRooms = firstTimeRoom(seenRooms, activeRoom); //Check if this room has been seen before for description
                    items = roomCheck(activeRoom, importantRooms, items); //Check if room has keys
                } else if (input.equals("inspect")) {
                    System.out.println(activeRoom.get(1));
                } else if (input.equals("items")) {
                    if (items.get(0).equals("true") && items.get(1).equals("true")) {
                        System.out.println("You have both keys.");
                    } else if (items.get(0).equals("true") && items.get(1).equals("false")) {
                        System.out.println("You have a key labeled '1'.");
                    } else if (items.get(0).equals("false") && items.get(1).equals("true")) {
                        System.out.println("You have a key labeled '2'.");
                    } else {
                        System.out.println("You have no keys.");
                    }
                } else if (input.equals("instructions") || input.equals("help")) {
                    instructions();
                } else if (input.equals("quit")) {
                    return 1;
                } else {
                    System.out.println("Command not found. Type 'help' for a explanation of commands.");
                }

        }
        return 1;
    }

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
