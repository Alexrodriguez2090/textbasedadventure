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
        System.out.println(input);
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
        String nameOfSecondKeyRoom = roomsWithSecondKey.get(rndRoomForFirstKey);

        ArrayList<String> importantRooms = new ArrayList<String>();
        importantRooms.add(nameOfFirstKeyRoom);
        importantRooms.add(nameOfSecondKeyRoom);
        return importantRooms;
    }

    //public static ArrayList<String> roomCheck(ArrayList<ArrayList<String>> rooms) {
    //    return 0;
    //}

    public static ArrayList<String> setUpStart(ArrayList<ArrayList<String>> rooms) {
        Random rnd = new Random();
        System.out.println(rooms.size());

        ArrayList<String> startingRooms = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(4).equals("true")) {
                startingRooms.add(rooms.get(i).get(0));
            }
        }
        System.out.println(startingRooms.size());
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

    public static void instructions() {
        System.out.println("1. Move by simply typing the first letter of north, south, east, or west.");
        System.out.println("n, s, e, w");
        System.out.println("2. You will inspect a room the first time you enter, and you can review your inspection again by typing 'inspect'.");
        System.out.println("3. If you need to see these instructions again, simply type 'instructions' or 'help'.");
        System.out.println("4. You can quit at any time by typing 'quit', although your progress will not save.");
        System.out.println("5. Have fun!");
    }

    public static ArrayList<String> findRoom(ArrayList<ArrayList<String>> rooms, ArrayList<String> activeRoom, String compass) {
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

        ArrayList<String> roomToSend = new ArrayList<String>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).get(0).equals(newRoom)) {
                roomToSend = rooms.get(i);
                break;
            }
        }
        return roomToSend;
    }


    public static int ingame(Scanner sc, ArrayList<ArrayList<String>> rooms) {
        ArrayList<String> activeRoom = new ArrayList<String>();
        ArrayList<String> importantRooms = new ArrayList<String>();

        activeRoom = setUpStart(rooms);
        importantRooms = importantRooms(rooms);

        System.out.println("You open your eyes and ");

        int ingame = 1;
        while (ingame == 1) {
            System.out.println("What would you like to do?");
            String input = sc.nextLine();
            System.out.println(input);
                if (input.equals("n") || input.equals("s") || input.equals("e") || input.equals("w")) {
                    findRoom(rooms, activeRoom, input);
                } else if (input.equals("inspect")) {

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
        System.out.println(rooms);

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
