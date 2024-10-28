import Logger.Logger;
import Battle.Battle;
import Battle.OneVsOneBattle;
import Battle.ThreeVsThreeBattle;
import Droids.BaseDroid;
import Droids.DroidList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        int choice = 0;
        BaseDroid droidA = null;
        BaseDroid droidB = null;
        List<BaseDroid> teamA = new ArrayList<>();
        List<BaseDroid> teamB = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (choice != 7) {
            printMenu();
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    startOneVsOneBattle(droidA, droidB,scanner);
                    logger.log("\n");
                    break;
                case 2:
                    startThreeVsThreeBattle(teamA, teamB,scanner);
                    logger.log("\n");
                    break;
                case 3:
                    int newChoice = 0;
                    while (newChoice < 1 || newChoice > 2) {
                        System.out.println("Choose team to build:\n1)1vs1\n2)3vs3");
                        newChoice = scanner.nextInt();
                        switch (newChoice) {
                            case 1:
                                System.out.println("Choose team A droid: ");
                                droidA = pickDroid(scanner);
                                System.out.println("Choose team B droid: ");
                                droidB = pickDroid(scanner);
                                break;
                            case 2:
                                BaseDroid droid;
                                teamA.clear();
                                teamB.clear();
                                System.out.println("Build team A: ");
                                for (int i = 1; i < 4; i++) {
                                    System.out.println("Choose " + i + " droid");
                                    droid = pickDroid(scanner);
                                    if (droid != null) {
                                        teamA.add(droid);
                                    } else {
                                        System.out.println("Something went wrong try again");
                                        i--;
                                    }
                                }
                                System.out.println("Build team B: ");
                                for (int i = 1; i < 4; i++) {
                                    System.out.println("Choose " + i + " droid");
                                    droid = pickDroid(scanner);
                                    if (droid != null) {
                                        teamB.add(droid);
                                    } else {
                                        System.out.println("Something went wrong try again");
                                        i--;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Wrong input");
                        }
                    }
                    break;
                case 4:
                    showTeams(droidA, droidB, teamA, teamB);
                    break;
                case 5:
                    showList();
                    break;
                case 6:
                    showBattleLog();
                    break;
                case 7:
                    System.out.println("Closing game...");
                    break;
                default:
                    System.out.println("Wrong Input");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("1. Fight 1 vs 1");
        System.out.println("2. Fight 3 vs 3");
        System.out.println("3. Make teams");
        System.out.println("4. Check teams");
        System.out.println("5. Droid List");
        System.out.println("6. Show battle logs");
        System.out.println("7. Exit");
    }

    public static void startOneVsOneBattle(BaseDroid droidA, BaseDroid droidB,Scanner scanner) {
        if (droidA == null || droidB == null) {
            System.out.println("Teams doesn't built properly.Can not start battle");
        } else {
            List<BaseDroid> teamOne = new ArrayList<>();
            List<BaseDroid> teamTwo = new ArrayList<>();
            teamOne.add(droidA);
            teamTwo.add(droidB);
            Battle battle = new OneVsOneBattle(teamOne, teamTwo);
            System.out.println("Starting battle...");
            battle.startBattle(scanner);
        }
    }

    public static void startThreeVsThreeBattle(List<BaseDroid> teamA, List<BaseDroid> teamB,Scanner scanner) {
        Battle battle = new ThreeVsThreeBattle(teamA, teamB);
        if (teamA.size() != 3 || teamB.size() != 3) {
            System.out.println("Teams doesn't built properly.Can not start battle");
        } else {
            System.out.println("Starting battle...");
            battle.startBattle(scanner);
        }
    }

    public static BaseDroid pickDroid(Scanner scanner) {
        BaseDroid droid = null;
        DroidList list = new DroidList();
        String choice;
        boolean isNotDone;
        do {
            System.out.println("Enter droid name to pick(enter \"show\" to see list of droids):");
            choice = scanner.next().toLowerCase();

            isNotDone = switch (choice) {
                case "show" -> {
                    showList();
                    yield true;
                }
                case "x1" -> {
                    droid = list.getX1();
                    yield false;
                }
                case "y1" -> {
                    droid = list.getY1();
                    yield false;
                }
                case "cora" -> {
                    droid = list.getCora();
                    yield false;
                }
                case "oscar" -> {
                    droid = list.getOscar();
                    yield false;
                }
                case "aurora" -> {
                    droid = list.getAurora();
                    yield false;
                }
                case "atlas" -> {
                    droid = list.getAtlas();
                    yield false;
                }
                case "arthur" -> {
                    droid = list.getArthur();
                    yield false;
                }
                case "mr.mysterious" ->{
                    droid = list.getMrMysterious();
                    yield false;
                }
                default -> {
                    System.out.println("Wrong input. Please enter a valid droid name.");
                    yield true;
                }
            };
        } while (isNotDone);
        return droid;
    }

    public static void showTeams(BaseDroid droidA, BaseDroid droidB, List<BaseDroid> teamA, List<BaseDroid> teamB) {
        if (droidA != null && droidB != null) {
            System.out.println("1v1 Battle Teams:");
            System.out.println("Team A Droid: " + droidA.getName());
            System.out.println("Team B Droid: " + droidB.getName());
        } else {
            System.out.println("1v1 teams are not yet built.");
        }
        if (teamA.size() == 3 && teamB.size() == 3) {
            System.out.println("\n3v3 Battle Teams:");
            System.out.println("Team A:");
            for (BaseDroid droid : teamA) {
                System.out.println("- " + droid.getName());
            }
            System.out.println("Team B:");
            for (BaseDroid droid : teamB) {
                System.out.println("- " + droid.getName());
            }
        } else {
            System.out.println("\n3v3 teams are not yet built.");
        }
    }

    public static void showList() {
        DroidList everyone = new DroidList();
        everyone.printDroidsExtended();
    }
    public static void showBattleLog() {
        String logFilePath = "D:\\BattleLog\\battle_log.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }
    }
}
