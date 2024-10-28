package Battle;

import Droids.BaseDroid;
import Logger.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ThreeVsThreeBattle extends Battle{
    public ThreeVsThreeBattle(List<BaseDroid> teamA, List<BaseDroid> teamB) {
        super(teamA, teamB);
    }
    @Override
    public void startBattle(Scanner scanner) {
        super.startBattle(scanner);
        List<BaseDroid> cycleA = new ArrayList<>(teamA);
        List<BaseDroid> cycleB = new ArrayList<>(teamB);
        int quierA = 0;
        int quierB = 0;
        int turn = 0;
        while (!cycleA.isEmpty() && !cycleB.isEmpty()) {
            if (turn == 0) {
                teamASkillPoints += executeDroidAction(cycleA, cycleB, quierA, teamASkillPoints);
            } else {
                teamBSkillPoints += executeDroidAction(cycleB, cycleA, quierB, teamBSkillPoints);
            }
            removeDefeatedDroids(cycleA, logger);
            removeDefeatedDroids(cycleB, logger);
            if(cycleA.isEmpty()){
                break;
            }
            if(cycleB.isEmpty()){
                break;
            }
            if(turn == 0) {
                quierA = (quierA + 1) % cycleA.size();
            }
            else {
                quierB = (quierB + 1) % cycleB.size();
            }
            turn = (turn + 1) % 2;
        }

        String message = cycleA.isEmpty() ? "Team B has won the battle" : "Team A has won the battle";
        logger.logWithPrint(message);

        resetTeam(teamA);
        resetTeam(teamB);
    }

    private void removeDefeatedDroids(List<BaseDroid> team, Logger logger) {
        Iterator<BaseDroid> iterator = team.iterator();
        while (iterator.hasNext()) {
            BaseDroid droid = iterator.next();
            if (!droid.isAlive()) {
                String command = droid.getName() + " has been defeated";
                logger.logWithPrint(command);
                iterator.remove();
            }
        }
    }
}