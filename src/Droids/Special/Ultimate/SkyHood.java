package Droids.Special.Ultimate;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;

public class SkyHood extends Ultimate{
    public SkyHood(String name, String effect, int basicCharge, int neededCharge) {
        super(name, effect, basicCharge, neededCharge);
    }

    @Override
    public void applyUltimate(SkillParameters parameters) {
        List<BaseDroid> targets = parameters.getAllyTargets();
        for(BaseDroid target: targets){
            target.takeEffect("Shelter",3);
        }
    }
}
