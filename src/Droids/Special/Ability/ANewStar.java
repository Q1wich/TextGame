package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;

public class ANewStar extends Ability{

    public ANewStar(String name, String effect) {
        super(name, effect);
    }
    @Override
    public void applyAbility(SkillParameters parameters) {
        List<BaseDroid> allies = parameters.getAllyTargets();
        BaseDroid target = chooseTarget(allies);
        target.restoreEnergy(25);
        target.takeEffect("Invincibility", 1);
    }
}
