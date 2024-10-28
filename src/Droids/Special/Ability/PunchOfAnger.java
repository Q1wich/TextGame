package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;

public class PunchOfAnger extends Ability {

    public PunchOfAnger(String name, String effect) {
        super(name, effect);
    }

    @Override
    public void applyAbility(SkillParameters parameters) {
        List<BaseDroid> enemies = parameters.getEnemyTargets();
        BaseDroid caster = parameters.getAllyTarget(0);
        BaseDroid target = chooseTarget(enemies);
        System.out.println(caster.getName() + " uses Punch Of Anger on " + target.getName());
        int damage = (int) ((caster.getDamage()*parameters.getArena().damageMultiplier()) * ((caster.getCritDamage()*parameters.getArena().critDamageMultiplier()) / 100));
        target.takeDamage(caster,damage, "physical");
    }

}
