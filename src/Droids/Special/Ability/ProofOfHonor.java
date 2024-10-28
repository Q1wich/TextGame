package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

public class ProofOfHonor extends Ability{

    public ProofOfHonor(String name, String effect) {
        super(name, effect);
    }
    @Override
    public void applyAbility(SkillParameters parameters){
        BaseDroid caster = parameters.getAllyTarget(0);
        System.out.println(caster.getName() + " uses Proof of Honor.");
        caster.takeEffect("Strength Of Honor", 2);
        caster.restoreEnergy(10);
        caster.takeEffect("Cost Of Honor", 1);
    }
}
