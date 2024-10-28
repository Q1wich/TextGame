package Droids.Special.Ultimate;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;

public class StarFall extends Ultimate{

    public StarFall(String name, String effect, int basicCharge, int neededCharge) {
        super(name, effect, basicCharge, neededCharge);
    }
    @Override
    public void applyUltimate(SkillParameters parameters) {
        List<BaseDroid> targets = parameters.getEnemyTargets();
        BaseDroid caster = parameters.getAllyTarget(0);
        for(BaseDroid target: targets){
            target.dispelPositive();
            target.takeDamage(caster,(int)(target.getHealth()*0.15),"pure");
        }
    }
}
