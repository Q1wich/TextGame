package Droids.Special;

import Arenas.Arena;
import Droids.BaseDroid;
import java.util.ArrayList;
import java.util.List;

public class SkillParameters {
    List<BaseDroid> enemyTargets;
    List<BaseDroid> allyTargets;
    int amount;
    int skillPoints;
    Arena arena;

    public SkillParameters() {
        this.enemyTargets = new ArrayList<>();
        this.allyTargets = new ArrayList<>();
        this.amount = 0;
        this.skillPoints = 0;
        this.arena = new Arena();
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public List<BaseDroid> getEnemyTargets() {
        return enemyTargets;
    }

    public List<BaseDroid> getAllyTargets() {
        return allyTargets;
    }

    public BaseDroid getEnemyTarget(int index) {
        if (index >= 0 && index < enemyTargets.size()) {
            return enemyTargets.get(index);
        } else {
            return null;
        }
    }

    public BaseDroid getAllyTarget(int index) {
        if (index >= 0 && index < allyTargets.size()) {
            return allyTargets.get(index);
        } else {
            return null;
        }
    }

    public Arena getArena(){
        return arena;
    }

    public void addEnemyTarget(BaseDroid target) {
        enemyTargets.add(target);
    }

    public void addAllyTarget(BaseDroid target){
        allyTargets.add(target);
    }


    public void setSkillPoints(int amount){
        skillPoints = amount;
    }

    public void setArena(Arena arena){
        this.arena = arena;
    }

    public void setToDefault() {
        enemyTargets.clear();
        allyTargets.clear();
        amount = 0;
    }
}
