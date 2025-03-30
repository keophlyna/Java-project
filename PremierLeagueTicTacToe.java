import java.util.*;

class TeamPlayers {
    private String teamName;
    private Map<String, ArrayList<String>> positionPlayers;

    public TeamPlayers(String teamName) {
        this.teamName = teamName;
        this.positionPlayers = new HashMap<>();
        initializePlayers();
    }

    private void initializePlayers() {
        positionPlayers.put("Def", new ArrayList<>());
        positionPlayers.put("Mid", new ArrayList<>());
        positionPlayers.put("Fwd", new ArrayList<>());
    }

    public void addPlayer(String position, String playerName) {
        if (positionPlayers.containsKey(position)) {
            positionPlayers.get(position).add(playerName);
        }
    }

    public ArrayList<String> getPlayersForPosition(String position) {
        return positionPlayers.getOrDefault(position, new ArrayList<>());
    }

    public String getTeamName() {
        return teamName;
    }

    // Added method to validate if a player exists
    public boolean isValidPlayer(String position, String playerName) {
        if (!positionPlayers.containsKey(position)) {
            return false;
        }

        String playerNameLower = playerName.toLowerCase().trim();
        for (String player : positionPlayers.get(position)) {
            if (player.toLowerCase().equals(playerNameLower)) {
                return true;
            }
        }
        return false;
    }
}

public class PremierLeagueTicTacToe {
    private static final List<String> PREMIER_LEAGUE_TEAMS = Arrays.asList(
            "Liverpool", "Chelsea", "Man City", "Man Utd",
            "Spurs", "Arsenal", "Aston Villa", "Newcastle"
    );

    private static final String[] POSITIONS = {"Fwd", "Mid", "Def"};
    private static final int CELL_WIDTH = 8;
    private static final int TEAM_WIDTH = 12;

    private String[][] board;
    private String[][] correctPlayers;
    private String[] selectedTeams;
    private Random random;
    private String currentPlayer;
    private String currentSymbol;
    private ArrayList<TeamPlayers> allTeams;

    public PremierLeagueTicTacToe() {
        board = new String[3][3];
        correctPlayers = new String[3][3];
        selectedTeams = new String[3];
        random = new Random();
        currentPlayer = "Player 1";
        currentSymbol = "X";
        allTeams = createTeamPlayersList();
        initializeGame();
    }

    private ArrayList<TeamPlayers> createTeamPlayersList() {
        ArrayList<TeamPlayers> teams = new ArrayList<>();

        // Liverpool Players
        TeamPlayers liverpool = new TeamPlayers("Liverpool");
        liverpool.addPlayer("Def", "Van Dijk");
        liverpool.addPlayer("Def", "Konate");
        liverpool.addPlayer("Def", "Gomez");
        liverpool.addPlayer("Def", "Alexander-Arnold");
        liverpool.addPlayer("Mid", "Henderson");
        liverpool.addPlayer("Mid", "Thiago");
        liverpool.addPlayer("Mid", "Curtis Jones");
        liverpool.addPlayer("Mid", "Elliott");
        liverpool.addPlayer("Fwd", "Salah");
        liverpool.addPlayer("Fwd", "Nunez");
        liverpool.addPlayer("Fwd", "Diaz");
        liverpool.addPlayer("Fwd", "Jota");
        teams.add(liverpool);

        // Chelsea Players
        TeamPlayers chelsea = new TeamPlayers("Chelsea");
        chelsea.addPlayer("Def", "Silva");
        chelsea.addPlayer("Def", "Disasi");
        chelsea.addPlayer("Def", "Colwill");
        chelsea.addPlayer("Def", "Chalobah");
        chelsea.addPlayer("Mid", "Fernandez");
        chelsea.addPlayer("Mid", "Kovacic");
        chelsea.addPlayer("Mid", "Gallagher");
        chelsea.addPlayer("Mid", "Mudryk");
        chelsea.addPlayer("Fwd", "Neto");
        chelsea.addPlayer("Fwd", "Palmer");
        chelsea.addPlayer("Fwd", "Jackson");
        chelsea.addPlayer("Fwd", "Sterling");
        teams.add(chelsea);

        // Manchester City Players
        TeamPlayers manCity = new TeamPlayers("Man City");
        manCity.addPlayer("Def", "Dias");
        manCity.addPlayer("Def", "Stones");
        manCity.addPlayer("Def", "Akanji");
        manCity.addPlayer("Def", "Ake");
        manCity.addPlayer("Mid", "De Bruyne");
        manCity.addPlayer("Mid", "Rodri");
        manCity.addPlayer("Mid", "Bernardo Silva");
        manCity.addPlayer("Mid", "Gundogan");
        manCity.addPlayer("Fwd", "Haaland");
        manCity.addPlayer("Fwd", "Alvarez");
        manCity.addPlayer("Fwd", "Foden");
        manCity.addPlayer("Fwd", "Grealish");
        teams.add(manCity);

        // Manchester United Players
        TeamPlayers manUtd = new TeamPlayers("Man Utd");
        manUtd.addPlayer("Def", "Varane");
        manUtd.addPlayer("Def", "Maguire");
        manUtd.addPlayer("Def", "Shaw");
        manUtd.addPlayer("Def", "Martinez");
        manUtd.addPlayer("Mid", "Fernandes");
        manUtd.addPlayer("Mid", "Eriksen");
        manUtd.addPlayer("Mid", "Fred");
        manUtd.addPlayer("Mid", "McTominay");
        manUtd.addPlayer("Fwd", "Rashford");
        manUtd.addPlayer("Fwd", "Antony");
        manUtd.addPlayer("Fwd", "Garnacho");
        manUtd.addPlayer("Fwd", "Martial");
        teams.add(manUtd);

        // Tottenham Players
        TeamPlayers spurs = new TeamPlayers("Spurs");
        spurs.addPlayer("Def", "Romero");
        spurs.addPlayer("Def", "Dier");
        spurs.addPlayer("Def", "Porro");
        spurs.addPlayer("Def", "Davies");
        spurs.addPlayer("Mid", "Maddison");
        spurs.addPlayer("Mid", "Hojbjerg");
        spurs.addPlayer("Mid", "Bissouma");
        spurs.addPlayer("Mid", "Sarr");
        spurs.addPlayer("Fwd", "Son");
        spurs.addPlayer("Fwd", "Johnson");
        spurs.addPlayer("Fwd", "Richarlison");
        spurs.addPlayer("Fwd", "Kulusevski");
        teams.add(spurs);

        // Arsenal Players
        TeamPlayers arsenal = new TeamPlayers("Arsenal");
        arsenal.addPlayer("Def", "Saliba");
        arsenal.addPlayer("Def", "Gabriel");
        arsenal.addPlayer("Def", "White");
        arsenal.addPlayer("Def", "Zinchenko");
        arsenal.addPlayer("Mid", "Odegaard");
        arsenal.addPlayer("Mid", "Rice");
        arsenal.addPlayer("Mid", "Havertz");
        arsenal.addPlayer("Mid", "Jorginho");
        arsenal.addPlayer("Fwd", "Saka");
        arsenal.addPlayer("Fwd", "Jesus");
        arsenal.addPlayer("Fwd", "Martinelli");
        arsenal.addPlayer("Fwd", "Trossard");
        teams.add(arsenal);

        // Aston Villa Players
        TeamPlayers astonVilla = new TeamPlayers("Aston Villa");
        astonVilla.addPlayer("Def", "Mings");
        astonVilla.addPlayer("Def", "Konsa");
        astonVilla.addPlayer("Def", "Cash");
        astonVilla.addPlayer("Def", "Moreno");
        astonVilla.addPlayer("Mid", "McGinn");
        astonVilla.addPlayer("Mid", "Douglas Luiz");
        astonVilla.addPlayer("Mid", "Kamara");
        astonVilla.addPlayer("Mid", "Zaniolo");
        astonVilla.addPlayer("Fwd", "Bailey");
        astonVilla.addPlayer("Fwd", "Watkins");
        astonVilla.addPlayer("Fwd", "Diaby");
        astonVilla.addPlayer("Fwd", "Tielemans");
        teams.add(astonVilla);

        // Newcastle Players
        TeamPlayers newcastle = new TeamPlayers("Newcastle");
        newcastle.addPlayer("Def", "Botman");
        newcastle.addPlayer("Def", "Schar");
        newcastle.addPlayer("Def", "Trippier");
        newcastle.addPlayer("Def", "Burn");
        newcastle.addPlayer("Mid", "Guimaraes");
        newcastle.addPlayer("Mid", "Willock");
        newcastle.addPlayer("Mid", "Longstaff");
        newcastle.addPlayer("Mid", "Almiron");
        newcastle.addPlayer("Fwd", "Isak");
        newcastle.addPlayer("Fwd", "Gordon");
        newcastle.addPlayer("Fwd", "Joelinton");
        newcastle.addPlayer("Fwd", "Wilson");
        teams.add(newcastle);

        return teams;
    }

    private void initializeGame() {
        Collections.shuffle(allTeams);

        // Assign teams to ROWS (not columns)
        for (int i = 0; i < 3; i++) {
            selectedTeams[i] = allTeams.get(i).getTeamName();
        }

        // Initialize the board with numbers 1-9
        int cellNumber = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = String.valueOf(cellNumber++);
            }
        }

        // Assign correct players based on team (row) and position (column)
        for (int row = 0; row < 3; row++) {
            TeamPlayers team = null;
            for (TeamPlayers t : allTeams) {
                if (t.getTeamName().equals(selectedTeams[row])) {
                    team = t;
                    break;
                }
            }

            for (int col = 0; col < 3; col++) {
                String position = POSITIONS[col];  // Positions are COLUMNS now
                ArrayList<String> players = team.getPlayersForPosition(position);
                correctPlayers[row][col] = players.get(random.nextInt(players.size()));
            }
        }
    }

    public void displayBoard() {
        // Print position headers
        System.out.printf("%-" + (TEAM_WIDTH-1) + "s", "");
        for (int i = 0; i < 3; i++) {
            System.out.printf("|   %-"+ (CELL_WIDTH-2) + "s", POSITIONS[i]);
        }
        System.out.println("|");

        // Print top border
        System.out.printf("%" + (TEAM_WIDTH-1) + "s", "");
        for (int i = 0; i < 3; i++) {
            System.out.print("-".repeat(CELL_WIDTH));
        }
        System.out.println("-");

        // Print each row with team name and cells
        for (int row = 0; row < 3; row++) {
            // Team name (left column)
            String teamName = selectedTeams[row];
            if (teamName.length() > TEAM_WIDTH-1) {
                teamName = teamName.substring(0, TEAM_WIDTH-1);
            }
            System.out.printf("%-" + (TEAM_WIDTH-1) + "s|", teamName);

            // Cells
            for (int col = 0; col < 3; col++) {
                String cellContent = board[row][col];
                // Calculate padding to center the content
                int totalPadding = CELL_WIDTH - cellContent.length();
                int leftPadding = totalPadding / 2;
                int rightPadding = totalPadding - leftPadding;

                System.out.printf(" %" + (leftPadding + cellContent.length()) + "s%" + rightPadding + "s|",
                        cellContent, "");
            }
            System.out.println();

            // Print horizontal borders
            if (row < 2) {
                System.out.printf("%" + (TEAM_WIDTH-1) + "s", "");
                for (int i = 0; i < 3; i++) {
                    System.out.print("-".repeat(CELL_WIDTH));
                }
                System.out.println("-");
            }
        }
    }

    public boolean makeMove(int cellNumber, String playerGuess) {
        int row = (cellNumber - 1) / 3;
        int col = (cellNumber - 1) % 3;

        if (!board[row][col].matches("\\d+")) {
            System.out.println("This cell is already occupied!");
            return false;
        }

        String team = selectedTeams[row];        // Team comes from ROW
        String position = POSITIONS[col];        // Position comes from COLUMN

        // Find the team object
        TeamPlayers teamObj = null;
        for (TeamPlayers t : allTeams) {
            if (t.getTeamName().equals(team)) {
                teamObj = t;
                break;
            }
        }

        if (teamObj != null && teamObj.isValidPlayer(position, playerGuess)) {
            board[row][col] = currentSymbol;
            return true;
        }

        System.out.println("Incorrect player! Switching turns.");
        switchPlayer();
        return false;
    }

    // Get the TeamPlayers object for a team name
    public TeamPlayers getTeamByName(String teamName) {
        for (TeamPlayers team : allTeams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("Player 1") ? "Player 2" : "Player 1";
        currentSymbol = currentSymbol.equals("X") ? "O" : "X";
    }

    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].matches("\\d+") &&
                    board[i][0].equals(board[i][1]) &&
                    board[i][0].equals(board[i][2])) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (!board[0][j].matches("\\d+") &&
                    board[0][j].equals(board[1][j]) &&
                    board[0][j].equals(board[2][j])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].matches("\\d+") &&
                board[0][0].equals(board[1][1]) &&
                board[0][0].equals(board[2][2])) {
            return true;
        }

        if (!board[0][2].matches("\\d+") &&
                board[0][2].equals(board[1][1]) &&
                board[0][2].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].matches("\\d+")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PremierLeagueTicTacToe game = new PremierLeagueTicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Premier League Tic Tac Toe!");
        System.out.println("Player 1 (X) will start first.");

        while (!game.isBoardFull()) {
            game.displayBoard();
            System.out.println(game.currentPlayer + "'s turn (" + game.currentSymbol + ")");

            System.out.print("Choose a cell (1-9): ");
            int cellNumber;
            try {
                cellNumber = scanner.nextInt();
                if (cellNumber < 1 || cellNumber > 9) {
                    System.out.println("Invalid cell number! Please choose between 1 and 9.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int row = (cellNumber - 1) / 3;
            int col = (cellNumber - 1) % 3;

            // Skip if cell already taken
            if (!game.board[row][col].matches("\\d+")) {
                System.out.println("This cell is already occupied! Choose another cell.");
                continue;
            }

            String team = game.selectedTeams[row];
            String position = game.POSITIONS[col];

            // Get valid players for hint (optional)
            TeamPlayers teamObj = game.getTeamByName(team);

            scanner.nextLine(); // Clear buffer

            System.out.print("Guess a " + team + " " + position + ": ");
            String playerGuess = scanner.nextLine().trim();

            // Check if input is empty or contains only numbers
            if (playerGuess.isEmpty() || playerGuess.matches("\\d+")) {
                System.out.println("Invalid player name! Please enter a valid player name.");
                System.out.println("Switching to " + (game.currentPlayer.equals("Player 1") ? "Player 2" : "Player 1") + "'s turn.");
                game.switchPlayer();
                continue;
            }

            // Check if player exists in the team and position
            if (teamObj != null && teamObj.isValidPlayer(position, playerGuess)) {
                boolean moveResult = game.makeMove(cellNumber, playerGuess);

                if (moveResult) {
                    if (game.checkWin()) {
                        game.displayBoard();
                        System.out.println("Congratulations! " + game.currentPlayer + " wins!");
                        scanner.close();
                        return;
                    }
                    game.switchPlayer();
                }
            } else {
                System.out.println("Player not found in " + team + " " + position + " position.");

                // Optional: Give a hint
                System.out.print("Would you like a hint? (y/n): ");
                String wantHint = scanner.nextLine().trim().toLowerCase();

                if (wantHint.equals("y") || wantHint.equals("yes")) {
                    List<String> players = teamObj.getPlayersForPosition(position);
                    System.out.println("Available " + team + " " + position + " players:");
                    for (int i = 0; i < players.size(); i++) {
                        System.out.print(players.get(i));
                        if (i < players.size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                    // Player gets another chance after seeing the hint
                    continue;
                } else {
                    // If player declines hint or enters anything other than 'y' or 'yes'
                    System.out.println("Switching to " + (game.currentPlayer.equals("Player 1") ? "Player 2" : "Player 1") + "'s turn.");
                    game.switchPlayer();
                }
            }
        }

        if (game.isBoardFull() && !game.checkWin()) {
            game.displayBoard();
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}