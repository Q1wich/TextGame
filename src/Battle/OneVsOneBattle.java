package Battle;

import Droids.BaseDroid;
import java.util.List;
import java.util.Scanner;
import Logger.Logger;

public class OneVsOneBattle extends Battle {

    public OneVsOneBattle(List<BaseDroid> teamA, List<BaseDroid> teamB) {
        super(teamA, teamB);
    }
    @Override
    public void startBattle(Scanner scanner){
        super.startBattle(scanner);
        List<BaseDroid> cycleA = List.copyOf(teamA);
        List<BaseDroid> cycleB = List.copyOf(teamB);
        int turn = 0;
        while (cycleA.getFirst().isAlive() && cycleB.getFirst().isAlive()) {
            if (turn == 0) {
                teamASkillPoints += executeDroidAction(cycleA, cycleB, 0, teamASkillPoints);
            } else {
                teamBSkillPoints += executeDroidAction(cycleB, cycleA, 0, teamBSkillPoints);
            }
            turn = (turn + 1) % 2;
        }

        String message = cycleB.getFirst().isAlive() ? "Team B has won the battle" : "Team A has won the battle";
        logger.logWithPrint(message);

        resetTeam(teamA);
        resetTeam(teamB);
    }
}
