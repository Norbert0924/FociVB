import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<Integer, WorldCup> wcresult = new TreeMap();

        List<WorldCupResult> results = new ArrayList();

        try {
            //beolvas
            wcresult = loadWorldCupsByYear("worldcups.csv");

            results.addAll(loadWorldCupResult("matches_2018.csv", wcresult.get(2018)));
            results.addAll(loadWorldCupResult("matches_2014.csv", wcresult.get(2014)));
            results.addAll(loadWorldCupResult("matches_2010.csv", wcresult.get(2010)));


            List<WorldCupResult> towrite = new ArrayList<>();

           for (WorldCup wc : wcresult.values())
               if (wc.getHost().equals("Brazil"))
                   towrite.addAll(wc.getResults());

           saveWorldCupResults("selected.txt", towrite);

            System.out.println("All macthes "+results.size());
           System.out.println("2. Maximum goal difference: " + largestDifference(wcresult));
           System.out.println("3. The player has won: " + teamAWins(wcresult)+" bets");

           Map<String, Integer> res = goalsPerStage(wcresult);

            for (Map.Entry<String, Integer> entry : res.entrySet())
                System.out.println(entry.getKey() + " : " + entry.getValue());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, Integer> goalsPerStage(Map<Integer, WorldCup> worldCupResult) throws IOException {

        Map<String, Integer> totalGoalsByStage = new LinkedHashMap();

        for (WorldCup wc : worldCupResult.values())
            for(WorldCupResult cupResult : wc.getResults())
                if (wc.getHost().equals("Brazil"))

                    totalGoalsByStage.put(cupResult.getStage(), totalGoalsByStage.getOrDefault(cupResult.getStage(), 0) + cupResult.goalSum());


        return totalGoalsByStage;

    }

    private static int teamAWins(Map<Integer, WorldCup> worldCupResult) throws IOException {

        // sima sum prog tetel
        int wins = 0;

        for (WorldCup wc : worldCupResult.values())
            for(WorldCupResult cupResult : wc.getResults())
                if (wc.getHost().equals("Brazil"))
                    if (cupResult.wonA())
                        wins++;

        return wins;
    }

    private static int largestDifference(Map<Integer, WorldCup> worldCupResult) throws IOException {
        // max kereses

        int max=Integer.MIN_VALUE;

        for (WorldCup wc : worldCupResult.values())
            for(WorldCupResult cupResult : wc.getResults())
                if (wc.getHost().equals("Brazil"))
                    if (cupResult.goalDifference() > max)
                        max = cupResult.goalDifference();

        return max;
    }

    private static Map<Integer, WorldCup> loadWorldCupsByYear(String path) throws IOException {
        Map<Integer, WorldCup> worldCups = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            reader.readLine(); // fejléc

            while ((line = reader.readLine()) != null) {

                WorldCup worldcup = new WorldCup(line);
                worldCups.put(worldcup.getYear(), worldcup);
            }
        }

        return worldCups;
    }

    private static List<WorldCupResult> loadWorldCupResult(String path, WorldCup worldcup) throws IOException {

        List<WorldCupResult> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            reader.readLine(); // fejléc

            while ((line = reader.readLine()) != null) {
                WorldCupResult result = new WorldCupResult(worldcup, line);
                worldcup.getResults().add(result);
                results.add(result);
            }
        }
        return results;
    }

    private static void saveWorldCupResults(String path, Collection<WorldCupResult> results) throws IOException {

        try (PrintWriter writer = new PrintWriter(path)) {
            writer.println("stage;date;team_a;team_b;goals_a;goals_b;penalties_a;penalties_b");

            for (WorldCupResult result : results) {

                String line = result.getStage()
                        + ";" + result.getDate()
                        + ";" + result.getTeamA()
                        + ";" + result.getTeamB()
                        + ";" + result.getGoalsA()
                        + ";" + result.getGoalsB()
                        + (result.getPenalty() ?
                        (";" + result.getPenaltiesA() + ";" + result.getpenaltiesB()) : "");
                writer.println(line);
            }
        }
    }
}
