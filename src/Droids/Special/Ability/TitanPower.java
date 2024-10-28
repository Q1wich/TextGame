package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;

public class TitanPower extends Ability{
    public TitanPower(String name, String effect) {
        super(name, effect);
    }
    @Override
    public void applyAbility(SkillParameters parameters){
        List<BaseDroid> enemies = parameters.getEnemyTargets();
        BaseDroid caster = parameters.getAllyTarget(0);
        BaseDroid target = chooseTarget(enemies);
        int firstEnemyDamage = (int) (caster.getDamage() * 1.4 * parameters.getArena().damageMultiplier());
        target.takeDamage(caster,firstEnemyDamage, "physical");
        int splashDamage = (int) (caster.getDamage() * 0.6 * parameters.getArena().damageMultiplier());
        for (BaseDroid enemy: enemies) {
            if(enemy!=target) {
                enemy.takeDamage(caster,splashDamage, "physical");
            }
        }
    }
}
