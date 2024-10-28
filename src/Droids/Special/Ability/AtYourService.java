package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;
import java.util.List;

public class AtYourService extends Ability {
    public AtYourService(String name, String effect) {
        super(name, effect);
    }

    @Override
    public void applyAbility(SkillParameters parameters) {
        List<BaseDroid> allies = parameters.getAllyTargets();
        BaseDroid target = chooseTarget(allies);
        BaseDroid caster = allies.getFirst();
        target.purifyNegative();
        int healAmount = (int) (caster.getHealth() * 0.71);
        target.takeHeal(healAmount);
    }
}
