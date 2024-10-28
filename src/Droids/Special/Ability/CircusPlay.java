package Droids.Special.Ability;

import Droids.Special.SkillParameters;
import java.util.Random;

public class CircusPlay extends Ability{
    public CircusPlay(String name, String effect) {
        super(name, effect);
    }
    @Override
    public void applyAbility(SkillParameters parameters){
        Random random = new Random();
        int choice = random.nextInt(100)%5;
        Ability ability = switch (choice) {
            case 0 -> new ANewStar("A New Star", "-");
            case 1 -> new TitanPower("Titan Power", "-");
            case 2 -> new AtYourService("At Your service", "-");
            case 3 -> new PunchOfAnger("Punch of Anger", "-");
            case 4 -> new ProofOfHonor("Proof Of Honor", "-");
            default -> null;
        };
        System.out.println(name+"Show us"+ability.name);
        ability.applyAbility(parameters);
    }
}
