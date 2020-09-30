import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Character{
    /**
    * Checks if the room entered by the player holds a key, by checking the name of the room and the name of important rooms
    * @param activeRoom - The room the player just entered
    * @param importantRooms - The rooms that hold keys
    * @param items - The items the player has, needed to add in the keys if the player found one in the room
    * @return - Returns the items, whether they have changed or not
    */
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
    * Checks if the user has been in this room before, since the first time going into the room triggers its description.
    * @param seenRooms - All the rooms the player has been in
    * @param activeRoom - The room the player just entered
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
    * @param activeRoom - The list of the room the player is walking to, necessary to see which direction they are going to
    * @param compass - The direction the player is moving
    * @return - Returns the name of the room the player is walking to
    */
    public static String playerMovedTo(ArrayList<String> activeRoom, String compass) {
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
        return newRoom;
    }

    /**
    * Checks whichever direction the user has input for a room.
    * @param rooms - The list of all rooms, necessary to send the room the player is walking to to the main game
    * @param activeRoom - The list of the room the player is walking to, necessary to see which direction they are going to
    * @param compass - The direction the user input
    * @param items - Only necessary to see if the player has both keys when moving to the backyard
    * @return - Returns either the new room the user has went into, or the same room if the user walked into a wall
    */
    public static ArrayList<String> findRoom(ArrayList<ArrayList<String>> rooms, ArrayList<String> activeRoom, String compass, ArrayList<String> items) {
        String newRoom = playerMovedTo(activeRoom, compass);

        if (newRoom.equals("null")) {
            System.out.println("There is nothing in that direction.");
            return activeRoom;

        } else if (newRoom.equals("Backyard")) {
            //*If* the player has both keys, *else* they do not
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
    * Prints to the user which items they have
    * @param items - The items the player has, necessary to print it out
    */
    public static void characterItems(ArrayList<String> items) {
        if (items.get(0).equals("true") && items.get(1).equals("true")) {
            System.out.println("You have both keys.");
        } else if (items.get(0).equals("true") && items.get(1).equals("false")) {
            System.out.println("You have a key labeled '1'.");
        } else if (items.get(0).equals("false") && items.get(1).equals("true")) {
            System.out.println("You have a key labeled '2'.");
        } else {
            System.out.println("You have no keys.");
        }
    }


    /**
    * Movement function for the second part of the game
    * @param rooms - The list of all rooms, necessary to send the room the player is walking to to the main game
    * @param activeRoom - The list of the room the player is walking to, necessary to see which direction they are going to
    * @param compass - The direction the user input
    * @return - Returns either the new room the user has went into, or the same room if the user walked into a wall
    */
    public static ArrayList<String> partTwo(ArrayList<ArrayList<String>> rooms, ArrayList<String> activeRoom, String compass) {
        String newRoom = playerMovedTo(activeRoom, compass);

        if (newRoom.equals("null")) {
            System.out.println("There is nothing in that direction.");
            return activeRoom;
        } else if (newRoom.equals("noback")) {
            System.out.println("You try and go back to where you came from, but there is nothing there.");
            return activeRoom;
        } else if (newRoom.equals("locked")) {
            System.out.println("You try and open the door, but it is locked.");
            return activeRoom;
        } else if (newRoom.equals("crumbled")) {
            System.out.println("The stairs are crumbled.");
            return activeRoom;
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
}
