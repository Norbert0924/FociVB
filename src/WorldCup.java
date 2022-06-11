import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldCup {


    //year;host;confederation;date_from;date_to

    //2010;South Africa;CAF;2010.06.11;2010.07.11

    private List<WorldCupResult> results=new ArrayList<>();


    private int year;
    private String host;
    private String confederation;
    private String date_from;
    private String date_to;

    public String getHost() { return host; }

    public int getYear() { return year; }

    public List<WorldCupResult> getResults() { return results; }



    public WorldCup(int year) {
        this.year = year;
    }

    public WorldCup(String line) {
        String[] parts = line.split(";");
        this.year = Integer.parseInt(parts[0]);
        this.host = parts[1];
        this.confederation = parts[2];
        this.date_from = parts[3];
        this.date_to = parts[4];

    }
}
