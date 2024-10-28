package Droids.Special.Ability;

import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;
import java.util.Scanner;
public class Ability {
    String name;
    String effect;
    Scanner scanner = new Scanner(System.in);

    public Ability(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public void applyAbility(SkillParameters parameters) {}

    public void displayAbility() {
        System.out.println("Ability: " + name);
        System.out.println("Effect: " + effect);
    }
    public BaseDroid chooseTarget(List<BaseDroid> targets) {
        if(targets.size() == 1){
            return targets.getFirst();
        }
        System.out.println("Choose target: ");
        int i = 1;
        for (BaseDroid target : targets) {
            System.out.println(i + ". " + target.getName());
            i++;
        }
        int choice;
        choice = scanner.nextInt();
        while (choice < 1 || choice > targets.size()) {
            System.out.println("Wrong input, please choose again.");
            choice = scanner.nextInt();
        }
        System.out.println("------------------------------------------------------------");
        return targets.get(choice - 1);
    }
}
