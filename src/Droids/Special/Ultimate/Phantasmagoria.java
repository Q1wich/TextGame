package Droids.Special.Ultimate;
import Droids.Special.SkillParameters;
import java.util.Random;

public class Phantasmagoria extends Ultimate{
    public Phantasmagoria(String name, String effect, int basicCharge, int neededCharge) {
        super(name, effect, basicCharge, neededCharge);
    }
    @Override
    public void applyUltimate(SkillParameters parameters) {
        Random random = new Random();
        int choice = random.nextInt(100)%3;
        Ultimate ultimate = switch (choice) {
            case 0 -> new SkyHood("Sky hood", "-", 0, 80);
            case 1 -> new StarFall("Star fall", "-", 0, 40);
            case 2 -> new TruePower("True power", "-", 0, 40);
            default -> null;
        };
        System.out.println(name+" Show us "+ultimate.name);
        ultimate.applyUltimate(parameters);
    }
}
