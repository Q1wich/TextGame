package Battle;

import Logger.Logger;
import Arenas.Arena;
import Arenas.ArenaOfDeath;
import Arenas.ArenaOfGods;
import Arenas.FogArena;
import Droids.BaseDroid;
import Droids.Special.SkillParameters;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    Arena arena;
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    int teamASkillPoints = 3;
    int teamBSkillPoints = 3;
    List<BaseDroid> teamA;
    List<BaseDroid> teamB;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Logger logger = Logger.getInstance();

    public Battle(List<BaseDroid> teamA, List<BaseDroid> teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }
    public void startBattle(Scanner scanner){
        arena = chooseArena(scanner);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<String> commands = new ArrayList<>();
        commands.add("Battle date: "+ timestamp);
        commands.add("Start Battle in " + arena);
        commands.add("Team A: ");
        for (BaseDroid droid : teamA) {
            commands.add("-" + droid.getName());
        }
        commands.add("Team B: ");
        for (BaseDroid droid : teamB) {
            commands.add("-" + droid.getName());
        }
        logger.logList(commands);

    }
    public Arena chooseArena(Scanner scanner) {
        showListOfArenas();
        System.out.print("Choose an arena (1-4): ");

        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> new Arena();
            case 2 -> new ArenaOfDeath();
            case 3 -> new ArenaOfGods();
            case 4 -> new FogArena();
            default -> {
                System.out.println("Invalid choice. Please select a number between 1 and 4.");
                yield chooseArena(scanner);
            }
        };
    }

    public void showListOfArenas(){
        Arena arena = new Arena();
        Arena arenaOfDeath = new ArenaOfDeath();
        Arena arenaOfGods = new ArenaOfGods();
        Arena fogArena = new FogArena();
        System.out.println("1."+arena);
        System.out.println("2."+arenaOfDeath);
        System.out.println("3."+arenaOfGods);
        System.out.println("4."+fogArena);
    }

    public int executeDroidAction(List<BaseDroid> currentTeam, List<BaseDroid> enemyTeam, int currentIndex, int points) {
        System.out.println("------------------------------------------------------------");
        BaseDroid unit = currentTeam.get(currentIndex);
        SkillParameters parameters = new SkillParameters();
        parameters.setSkillPoints(points);
        parameters.addAllyTarget(unit);
        parameters.setArena(arena);
        for (BaseDroid enemy : enemyTeam) {
            parameters.addEnemyTarget(enemy);
        }
        for (BaseDroid ally : currentTeam) {
            if (ally != unit) {
                parameters.addAllyTarget(ally);
            }
        }
        int res = action(parameters);
        int amount = (int)(unit.getHealth()*arena.healthReduction());
        if(amount>0) {
            System.out.println(unit.getName() + ", has lost " + amount + " health");
            unit.setHealth(unit.getHealth() - amount);
            if (!unit.isAlive()) {
                return 0;
            }
        }
        unit.updateEffects();
        return res;
    }

    public void resetTeam(List<BaseDroid> team) {
        for (BaseDroid unit : team) {
            unit.setToDefault();
        }
    }

    public int action(SkillParameters parameters){
        return switch (parameters.getAllyTarget(0).getRarity()) {
            case 1 -> actionB(parameters);
            case 2 -> actionRare(parameters);
            case 3 -> actionUltra(parameters);
            default -> 0;
        };
    }

    public void attack(BaseDroid caster, List<BaseDroid> enemies){
        if(enemies.size()==1) {
            enemies.getFirst().takeDamage(caster, hit(caster), "physical");
        }
        else {
            int choice;
            System.out.println("Choose target:");
            for (int i = 0; i < enemies.size(); i++) {
                System.out.println(i + 1 + "." + enemies.get(i).getName());
            }
            choice = scanner.nextInt();
            while (choice < 1 || choice > enemies.size()) {
                System.out.println("Wrong input");
                choice = scanner.nextInt();
            }
            BaseDroid enemy = enemies.get(choice - 1);
            int res = hit(caster);
            if (res != 0) {
                System.out.println("------------------------------------------------------------");
                enemy.takeDamage(caster, res, "physical");
            }
        }
    }

    public int hit(BaseDroid caster){
        int proc = random.nextInt(100);
        if((caster.getAccuracy()*arena.accuracyMultiplier())-proc>0){
            proc = random.nextInt(100);
            int damage;
            if(caster.getCritChance()-proc>0){
                damage = (int)((caster.getDamage()*arena.damageMultiplier())*(caster.getCritDamage()*arena.critDamageMultiplier())/100);
            }
            else{
                damage = (int)(caster.getDamage()*arena.damageMultiplier());
            }
            return damage;
        }
        else{
            String command = (caster.getName()+",has missed");
            logger.logWithPrint(command);
        }
        return 0;
    }

    public int actionB(SkillParameters parameters){
        if(!(parameters.getEnemyTargets().isEmpty() || parameters.getAllyTargets().isEmpty())) {
            int choice = 2;
            while (choice != 1) {
                System.out.println("\n" + parameters.getAllyTarget(0).getName() + ",turn:");
                System.out.println("Choose action:");
                System.out.println("0.Show status");
                System.out.println("1.Attack");
                choice = scanner.nextInt();
                if (choice == 0) {
                    parameters.getAllyTarget(0).showCurrentInfo();
                } else if(choice!= 1) {
                    System.out.println("Wrong input");
                }
            }
            attack(parameters.getAllyTarget(0), parameters.getEnemyTargets());
            parameters.setSkillPoints(parameters.getSkillPoints() + 1);
        }
        return 1;
    }

    public int actionRare(SkillParameters parameters) {
        BaseDroid unit = parameters.getAllyTarget(0);
        int choice = 5;
        boolean isChosen = true;
        while(isChosen){
            System.out.println(unit.getName()+",turn:");
            System.out.println("Choose action:");
            System.out.println("0.Show status");
            System.out.println("1.Attack");
            if(parameters.getSkillPoints()>0) {
                System.out.println("2.Ability.Skill points: " + parameters.getSkillPoints());
            }
            else{
                System.out.println(RED+"2.Ability.Skill points: " + parameters.getSkillPoints()+RESET);
            }
            choice = scanner.nextInt();
            isChosen = switch (choice){
                case 0 -> {
                    unit.showCurrentInfo();
                    yield true;
                }
                case 1 -> {
                    attack(unit, parameters.getEnemyTargets());
                    yield false;
                }
                case 2 -> {
                    if (parameters.getSkillPoints() < 1) {
                        choice = 0;
                        System.out.println("Not enough skill point to cast ability");
                        yield true;
                    } else {
                        unit.useAbility(parameters);
                        yield false;
                    }
                }
                default -> {
                    System.out.println("Wrong input");
                    yield true;
                }
            };
        }
        if(choice == 1){
            return 1;
        }
        else{
            return -1;
        }

    }
    public int actionUltra(SkillParameters parameters) {
        BaseDroid unit = parameters.getAllyTarget(0);
        int choice = 0;
        boolean isChosen = true;
        while(isChosen){
            System.out.println(unit.getName()+",turn:");
            System.out.println("Choose action:");
            System.out.println("0.Show status");
            System.out.println("1.Attack");
                if(parameters.getSkillPoints()>0) {
                    System.out.println("2.Ability.Skill points: " + parameters.getSkillPoints());
                }
                else{
                    System.out.println(RED+"2.Ability.Skill points: " + parameters.getSkillPoints()+RESET);
                }
                if(parameters.getAllyTarget(0).canCast()) {
                    System.out.println("3.Ultimate.Charge: " + unit.getBasicCharge() + "/" + unit.getNeededCharge());
                }
                else{
                    System.out.println(RED+"3.Ultimate.Charge: " + unit.getBasicCharge() + "/" + unit.getNeededCharge()+RESET);
                }
            choice = scanner.nextInt();
            isChosen = switch (choice){
                case 0 -> {
                    unit.showCurrentInfo();
                    yield true;
                }
                case 1 -> {
                    attack(unit, parameters.getEnemyTargets());
                    parameters.setSkillPoints(parameters.getSkillPoints()+1);
                    unit.restoreEnergy(10);
                    yield false;
                }
                case 2 -> {
                    if (parameters.getSkillPoints() < 1) {
                        System.out.println("Not enough skill point to cast ability");
                        yield true;
                    } else {
                        unit.useAbility(parameters);
                        parameters.setSkillPoints(parameters.getSkillPoints()-1);
                        unit.restoreEnergy(15);
                        yield false;
                    }
                }
                case 3 ->{
                    if(unit.canCast()){
                        unit.useUltimate(parameters);
                        unit.setBasicCharge(0);
                        yield false;
                    }
                    else{
                        System.out.println("Not enough charge");
                        yield true;
                    }
                }
                default -> {
                    System.out.println("Wrong input");
                    yield true;
                }
            };
        }
        if(choice == 1){
            return 1;
        }
        else if(choice == 2){
            return -1;
        }
        else{
            return 0;
        }

    }
}
