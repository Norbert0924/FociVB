public class WorldCupResult {

    //year;stage;date;team_a;team_b;goals_a;goals_b;penalties_a;penalties_b
    //2010;Group A;2010.06.11;South Africa;Mexico;1;1
    //2014;Quarter-finals;2014.07.05;Argentina;Belgium;1;0
    //2014;Quarter-finals;2014.07.05;Netherlands;Costa Rica;0;0;4;3

    private WorldCup worldCup;

    private String stage;
    private String date;
    private String team_a;
    private String team_b;
    private int goals_a;
    private int goals_b;
    private int penalties_a;
    private int penalties_b;

    private boolean penalties;



    public WorldCupResult(WorldCup wc, String line) {

        this.worldCup = wc;
        String[] parts = line.split(";");
        this.stage = parts[0];
        this.date = parts[1];
        this.team_a = parts[2];
        this.team_b = parts[3];
        this.goals_a = Integer.parseInt(parts[4]);
        this.goals_b = Integer.parseInt(parts[5]);

        if (parts.length > 6) {
            penalties_a = Integer.parseInt(parts[6]);
            penalties_b = Integer.parseInt(parts[7]);
            penalties = true;
        }
        else {
            penalties = false;

            penalties_a = penalties_b = 0;
        }


    }
    public boolean getPenalty() { return penalties; }


    public String getStage() { return stage; }
    public String setStage() { return stage; }

    public String getDate() { return date; }
    public String setDate() { return date; }

    public String getTeamA() { return team_a; }
    public String setTeamA() { return team_a; }

    public String getTeamB() { return team_b; }
    public String setTeamB() { return team_b; }

    public int getGoalsA() { return goals_a; }
    public int setGoalsA() { return goals_a; }

    public int getGoalsB() { return goals_b; }
    public int setGoalsB() { return goals_b; }

    public int getPenaltiesA() { return penalties_a; }
    public int setPenaltiesA() { return penalties_a; }

    public int getpenaltiesB() { return penalties_b; }
    public int setpenaltiesB() { return penalties_b; }


    public void getWorldCup(WorldCup wc) {
        worldCup = wc;
    }
    public void setWorldCup(WorldCup wc) {
        worldCup = wc;
    }


    public int goalSum() {

        return goals_a + goals_b;
    }

    public int goalDifference() {
        int diff = Math.abs(goals_a - goals_b);

        if (diff == 0)

            return Math.abs(penalties_a - penalties_b);

        else return diff;
    }

    public boolean wonA() {
        return goals_a > goals_b || (goals_a - goals_b == 0 && penalties_a > penalties_b);
    }


}