//package ui;
//
//import model.*;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//import static java.lang.Math.abs;
//
///**
// * Represents the game state and handles user commands
// */
//public class ConsoleGameArea implements Comparator<Zombie> {
//
//    private static final String JSON_STORE = "./data/scoreboard.json";
//    private static final int WIDTH = 80;
//    private static final int POINTS_PER_KILL = 10;
//    private int zombieCount;
//    private List<Zombie> zombies;
//    private Player player;
//    private int currentRound;
//    private int points;
//    private int kills;
//    private boolean isOver;
//    private boolean endGame;
//    private Gun currentGun;
//
//    private JsonReader jsonReader;
//    private JsonWriter jsonWriter;
//
//    private Scanner scanner;
//    private Scoreboard scoreboard;
//
//    // EFFECTS: constructs a new GameArea Object with 1 zombie in the game,
//    // round number at 1, 0 kills, 0 points and is not over
//    public ConsoleGameArea() {
//        this.zombies = new ArrayList<>();
//        this.zombies.add(new Zombie());
//        this.zombieCount = zombies.size();
//        this.currentRound = 1;
//        this.points = 0;
//        this.kills = 0;
//        this.isOver = false;
//        this.endGame = true;
//        this.currentGun = new Gun();
//        this.scanner = new Scanner(System.in);
//        this.player = new Player();
//        this.scoreboard = new Scoreboard();
//        this.jsonReader = new JsonReader(JSON_STORE);
//        this.jsonWriter = new JsonWriter(JSON_STORE);
//        startGame();
//        update();
//    }
//
//    // command processing code using scanner from https://github.students.cs.ubc.ca/CPSC210/TellerApp
//    // MODIFIES: this
//    // EFFECTS: allows user to choose if they want to see the current scoreboard or start a new game
//    private void startGame() {
//        String command;
//        boolean keepGoing = true;
//        while (keepGoing) {
//            System.out.println("Welcome to Apocalypse!");
//            System.out.println("by Brett Capistrano");
//            System.out.println("Type 'start' to start a new game or 'scoreboard' to see scoreboard");
//            command = scanner.next();
//            command = command.toLowerCase();
//            if (command.equals("start")) {
//                endGame = false;
//                keepGoing = false;
//            } else if (command.equals("scoreboard")) {
//                handleScoreboard();
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: prints the scoreboard and allows user to delete an existing record from the scoreboard or load a
//    // scoreboard from a file
//    private void handleScoreboard() {
//        printScoreboard();
//        System.out.println("Would you like to load a scoreboard from a file, save current scoreboard "
//                + "or delete a record from the scoreboard? " + "(type yes or no)");
//        String command = scanner.next();
//        command = command.toLowerCase();
//        if (command.equals("yes")) {
//            System.out.println("Type 'l' to load a scoreboard,
//            'd' to delete a record, or 's' to save the scoreboard");
//            String input = scanner.next();
//            input = input.toLowerCase();
//            if (input.equals("d")) {
//                //deleteRecord();
//            } else if (input.equals("l")) {
//                loadWorkRoom();
//            } else if (input.equals("s")) {
//                saveWorkRoom();
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: allows user to delete record from the scoreboard by typing in the name associated with the record
////    private void deleteRecord() {
////        System.out.println("Enter the name associated with the record "
////                + "you want to delete: (nothing will happen if the name does not exist in the scoreboard)");
////        String input = scanner.next();
////        scoreboard.deleteRecord(input);
////    }
//
//    // EFFECTS: prints scoreboard if an entry exists, otherwise notify user that it is empty
//    private void printScoreboard() {
//        if (scoreboard.scoreboardSize() == 0) {
//            System.out.println("Empty scoreboard.");
//        } else {
//            for (Record r : scoreboard.getScoreboard()) {
//                System.out.println("Name: " + r.getName() + " "
//                        + "Kills: " + r.getTotalKills() + " "
//                        + "Points: " + r.getTotalPoints() + " "
//                        + "Rounds survived: " + r.getTotalRounds());
//            }
//        }
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: updates the game state every time an action is performed by
//    // the player through a keyboard input and carries out various tasks once the game is over
//    private void update() {
//        while (!endGame) {
//            printStats();
//            action();
//            checkGameOver();
//            handleRoundOver();
//            moveZombies();
//            checkGameOver();
//            if (isOver) {
//                System.out.println("Game Over!");
//                updateScoreboard();
//                handleNewGame();
//                startGame();
//            }
//        }
//    }
//
//    // menu options idea from the TellerApp from https://github.students.cs.ubc.ca/CPSC210/TellerApp
//    // EFFECTS: prints the possible actions that the player can perform
//    private void printOptions() {
//        System.out.println("\nActions:");
//        System.out.println("\tm -> move");
//        System.out.println("\ts -> shoot");
//        System.out.println("\tr -> reload");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: resets game state to default state
//    private void handleNewGame() {
//        this.zombies = new ArrayList<>();
//        zombies.add(new Zombie());
//        this.zombieCount = zombies.size();
//        this.currentRound = 1;
//        this.points = 0;
//        this.kills = 0;
//        this.isOver = false;
//        this.endGame = true;
//        this.player = new Player();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: allows user to create a Record with their name
//    private void updateScoreboard() {
//        System.out.println("Enter your name to enter scoreboard:");
//        System.out.println("(if you have an existing record it will be overwritten.)");
//        String command = scanner.next();
//        scoreboard.addRecord(new Record(command, this.kills, this.points, this.currentRound));
//    }
//
//    // command processing using scanner from the TellerApp from https://github.students.cs.ubc.ca/CPSC210/TellerApp
//    // MODIFIES: this
//    // EFFECTS: handle console input from the player for game actions
//    private void action() {
//        String command;
//        boolean keepGoing = true;
//        while (keepGoing) {
//            printOptions();
//            command = scanner.next();
//            command = command.toLowerCase();
//            if (command.equals("m")) {
//                player.move(chooseDir());
//                keepGoing = false;
//            } else if (command.equals("s")) {
//                handleShooting();
//                keepGoing = false;
//            } else if (command.equals("r")) {
//                currentGun.reload();
//                keepGoing = false;
//            }
//        }
//    }
//
//    // EFFECTS: shoots gun and handles whether zombie was hit
//    private void handleShooting() {
//        String dir = chooseDir();
//        if (currentGun.getCurrentAmmo() != 0) {     // don't worry about hitting zombie if gun is empty
//            handleHit(dir);
//        } else {
//            System.out.println("Empty clip, reloading!");
//        }
//        currentGun.shoot();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: check if player hit a zombie in the direction they shot at and remove zombie from game
//    private void handleHit(String dir) {
//        Zombie zombieToRemove = null;
//        if (dir.equals("l")) {
//            zombieToRemove = getClosestZombie("l");
//        } else if (dir.equals("r")) {
//            zombieToRemove = getClosestZombie("r");
//        }
//        if (zombieToRemove != null) {
//            zombies.remove(zombieToRemove);
//            points += POINTS_PER_KILL;
//            kills++;
//            this.zombieCount = zombies.size();
//        }
//    }
//
//    // EFFECTS: returns closest zombie to the player from specified direction (either left or right of player)
//    private Zombie getClosestZombie(String d) {
//        List<Zombie> zombiesOneSide = new ArrayList<>();
//        if (d.equals("l")) {
//            for (Zombie z : zombies) {
//                if (zombieDistance(z) > 0) {
//                    zombiesOneSide.add(z);
//                }
//            }
//        } else if (d.equals("r")) {
//            for (Zombie z : zombies) {
//                if (zombieDistance(z) < 0) {
//                    zombiesOneSide.add(z);
//                }
//            }
//        }
//        sortZombiesByDistance(zombiesOneSide);
//        if (zombiesOneSide.size() != 0) {
//            return zombiesOneSide.get(0);
//        }
//        return null;
//    }
//
//    // EFFECTS: returns a list of zombies sorted by their distance
//    private void sortZombiesByDistance(List<Zombie> zombies) {
//        zombies.sort(this::compare);
//    }
//
//    // EFFECTS: compares distance of two zombies to the player
//    @Override
//    public int compare(Zombie z1, Zombie z2) {
//        return zombieDistance(z1) - zombieDistance(z2);
//    }
//
//
//    // EFFECTS: prompt user to choose whether to move/shoot left or right
//    private String chooseDir() {
//        String command;
//        String choice = null;
//        boolean keepGoing = true;
//        while (keepGoing) {
//            System.out.println("Type 'l' to move/shoot left or 'r' to move/shoot right");
//            command = scanner.next();
//            command = command.toLowerCase();
//            if (command.equals("l")) {
//                choice = "l";
//                keepGoing = false;
//            } else if (command.equals("r")) {
//                choice = "r";
//                keepGoing = false;
//            }
//        }
//        return choice;
//    }
//
//    // EFFECTS: prints number of zombies left and their distance from player, current round, kills, and points
//    private void printStats() {
//        System.out.println("Player Position: " + player.getPosition());
//        System.out.println("Kills: " + kills);
//        System.out.println("Zombies left: " + zombieCount);
//        System.out.println("Points: " + points);
//        System.out.println("Round: " + currentRound);
//        System.out.println("Gun: " + currentGun.getCurrentAmmo() + "/" + currentGun.getReserveAmmo());
//        for (Zombie z : zombies) {
//            if (zombieDistance(z) > 0) {
//                System.out.println("There is a zombie " + zombieDistance(z) + " units left from the player");
//            } else {
//                System.out.println("There is a zombie " + abs(zombieDistance(z)) + " units right from the player");
//            }
//
//        }
//    }
//
//    // EFFECTS: return the distance between the player and a zombie
//    private int zombieDistance(Zombie zb) {
//        return (player.getPosition() - zb.getPosition());
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: if round is over, adds more zombies to game and increase round number
//    private void handleRoundOver() {
//        if (zombieCount == 0) {
//            currentRound++;
//            for (int i = 0; i < currentRound; i++) {
//                zombies.add(new Zombie());
//                zombieCount = zombies.size();
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: moves all zombies currently on screen towards the player
//    private void moveZombies() {
//        for (Zombie zb : zombies) {
//            zb.move(zombieDistance(zb));
//        }
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: checks if a zombie has touched the player then ends the game if this is the case
//    private void checkGameOver() {
//        for (Zombie zb : zombies) {
//            if (zb.getPosition() == player.getPosition()) {
//                isOver = true;
//                break;
//            }
//        }
//    }
//
//    public static int getWidth() {
//        return WIDTH;
//    }
//
//    // from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//    // MODIFIES: this
//    // EFFECTS: loads workroom from file
//    private void loadWorkRoom() {
//        try {
//            scoreboard = jsonReader.read();
//            System.out.println("Loaded scoreboard from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    // from JSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//    // EFFECTS: saves the workroom to file
//    private void saveWorkRoom() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(scoreboard);
//            jsonWriter.close();
//            System.out.println("Saved scoreboard to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//}
//
