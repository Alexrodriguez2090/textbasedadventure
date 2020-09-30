import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Building {
    public static ArrayList<ArrayList<String>> buildingMap() {
        BufferedReader reader;
        ArrayList<ArrayList<String>> rooms = new ArrayList<ArrayList<String>>();
        try {
            reader = new BufferedReader(new FileReader("map.txt"));

            String line = reader.readLine();
            while (line != null) {
                String room[] = line.split("\t");
                ArrayList<String> roomToAppend = new ArrayList<String>(Arrays.asList(room));
                rooms.add(roomToAppend);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return rooms;
    }

    public static ArrayList<ArrayList<String>> buildingMap2() {
        BufferedReader reader;
        ArrayList<ArrayList<String>> rooms = new ArrayList<ArrayList<String>>();
        try {
            reader = new BufferedReader(new FileReader("map2.txt"));

            String line = reader.readLine();
            while (line != null) {
                String room[] = line.split("\t");
                ArrayList<String> roomToAppend = new ArrayList<String>(Arrays.asList(room));
                rooms.add(roomToAppend);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return rooms;
    }
}
