package tevalcourse.theory.maxflow.hw;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseballElimination {

    private final HashMap<String, Integer> teams;
    private final HashMap<String, ArrayList<String>> eliminatedTeamsCertificates;

    private final int[] w;
    private final int[] l;
    private final int[] r;
    private final int[][] g;

    private int leadingTeamId;
    private String leadingTeam;


    public BaseballElimination(String filename) {
        In in = new In(filename);
        int numTeams = in.readInt();
        in.readLine();

        this.teams = new HashMap<>();
        this.eliminatedTeamsCertificates = new HashMap<>();

        this.w = new int[numTeams];
        this.l = new int[numTeams];
        this.r = new int[numTeams];
        this.g = new int[numTeams][numTeams];

        createDivision(numTeams, in);
    }

    private void createDivision(int numTeams, In in) {
        int maxWins = 0;
        int lTeam = 0;
        String lTeamName = "";
        for (int i = 0; i < numTeams; i++) {
            String teamName = in.readString();
            teams.put(teamName, i);

            int wins = in.readInt();
            if (wins > maxWins) {
                maxWins = wins;
                lTeam = i;
                lTeamName = teamName;
            }

            w[i] = wins;
            l[i] = in.readInt();
            r[i] = in.readInt();

            for (int j = 0; j < numTeams; j++) {
                g[i][j] = in.readInt();
            }
            in.readLine();
        }
        leadingTeamId = lTeam;
        leadingTeam = lTeamName;
    }

    public int numberOfTeams() {
        return teams.size();
    }

    public Iterable<String> teams() {
        return teams.keySet();
    }

    public int wins(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Unknown team.");
        }
        return w[teams.get(team)];
    }

    public int losses(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Unknown team.");
        }
        return l[teams.get(team)];
    }

    public int remaining(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Unknown team.");
        }
        return r[teams.get(team)];
    }

    public int against(String team1, String team2) {
        if (!teams.containsKey(team1) || !teams.containsKey(team2)) {
            throw new IllegalArgumentException("Unknown team.");
        }
        return g[teams.get(team1)][teams.get(team2)];
    }

    public boolean isEliminated(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Unknown team.");
        }

        boolean triviallyEliminated = isTriviallyEliminated(team);
        if (triviallyEliminated) {
            return true;
        }

        int numGames = numberOfTeams() * (numberOfTeams() - 1) / 2;
        FlowNetwork flowNetwork = createEliminationNetwork(team, numGames);

        int source = 0;
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, source, flowNetwork.V() - 1);

        for (FlowEdge e : flowNetwork.adj(source)) {
            if (e.flow() != e.capacity()) {
                eliminatedTeamsCertificates.put(team, getCertificateOfElimination(fordFulkerson, numGames));
                return true;
            }
        }
        return false;
    }

    private boolean isTriviallyEliminated(String team) {
        Integer teamId = teams.get(team);
        if(w[teamId] + r[teamId] < w[leadingTeamId]) {
            ArrayList<String> certificate = new ArrayList<>();
            certificate.add(leadingTeam);
            eliminatedTeamsCertificates.put(team, certificate);
            return true;
        }
        return false;
    }

    private FlowNetwork createEliminationNetwork(String team, int numGames) {
        int numVertices = numGames + numberOfTeams() + 2;

        int source = 0;
        int target = numVertices - 1;

        FlowNetwork flowNetwork = new FlowNetwork(numVertices);
        int teamId = teams.get(team);

        int gamesVertex = 1;
        for (int gamesRow = 0; gamesRow < g.length; gamesRow++) {
            for (int gamesCol = gamesRow + 1; gamesCol < g[gamesRow].length; gamesCol++) {
                flowNetwork.addEdge(new FlowEdge(source, gamesVertex, g[gamesRow][gamesCol]));
                flowNetwork.addEdge(new FlowEdge(gamesVertex, numGames + gamesRow + 1, Double.POSITIVE_INFINITY));
                flowNetwork.addEdge(new FlowEdge(gamesVertex, numGames + gamesCol + 1, Double.POSITIVE_INFINITY));
                gamesVertex++;
            }
            int teamVertex = numGames + gamesRow + 1;
            flowNetwork.addEdge(new FlowEdge(teamVertex, target, Math.max(0, w[teamId] + r[teamId] - w[gamesRow])));
        }
        return flowNetwork;
    }

    private ArrayList<String> getCertificateOfElimination(FordFulkerson fordFulkerson, int numGames) {
        ArrayList<String> certificate = new ArrayList<>();
        for (String curTeam : teams()) {
            int teamVertexFlowNetwork = numGames + 1 + teams.get(curTeam);
            if (fordFulkerson.inCut(teamVertexFlowNetwork)) {
                certificate.add(curTeam);
            }
        }
        return certificate;
    }

    public Iterable<String> certificateOfElimination(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("Unknown team.");
        }
        ArrayList<String> certificate = eliminatedTeamsCertificates.get(team);
        if (certificate == null) {
            if (isEliminated(team)) {
                return eliminatedTeamsCertificates.get(team);
            } else {
                return null;
            }
        }
        return certificate;
    }


    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                StdOut.println(division.certificateOfElimination(team));
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
