package Droids.Special.Ultimate;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

public class TruePower extends Ultimate{
    public TruePower(String name, String effect, int basicCharge, int neededCharge) {
        super(name, effect, basicCharge, neededCharge);
    }

    @Override
    public void applyUltimate(SkillParameters parameters) {
        BaseDroid caster = parameters.getAllyTarget(0);
        BaseDroid target = chooseTarget(parameters.getEnemyTargets());
        for(int i = 0; i<7;i++){
            target.takeDamage(caster,(int)(caster.getDamage()*0.6*parameters.getArena().damageMultiplier()),"physical");
        }
    }
}
