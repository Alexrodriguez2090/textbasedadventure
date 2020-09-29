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
                //roomToAppend.add(Arrays.asList(room));
                rooms.add(roomToAppend);
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return rooms;
    }
}
