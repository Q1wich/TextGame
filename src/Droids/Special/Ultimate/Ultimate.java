package Droids.Special.Ultimate;
import Droids.BaseDroid;
import Droids.Special.SkillParameters;

import java.util.List;
import java.util.Scanner;

public class Ultimate {
    String name;
    String effect;
    int basicCharge;
    int neededCharge;
    Scanner scanner = new Scanner(System.in);

    public Ultimate(String name, String effect, int basicCharge, int neededCharge) {
        this.name = name;
        this.effect = effect;
        this.basicCharge = basicCharge;
        this.neededCharge = neededCharge;
    }

    public void applyUltimate(SkillParameters parameters) {}

    public void changeBasicCharge(int amount){
        if(basicCharge+amount>neededCharge){
            basicCharge = neededCharge;
        }
        else if(basicCharge+amount<0){
            basicCharge = 0;
        }
        else{
            basicCharge += amount;
        }
    }
    public void setBasicCharge(int amount){
        if(amount>neededCharge){
            basicCharge = neededCharge;
        }
        else {
            basicCharge = Math.max(amount, 0);
        }
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
    public boolean canCast(){
        return basicCharge>=neededCharge;
    }

    public int getBasicCharge(){
        return basicCharge;
    }

    public int getNeededCharge(){
        return neededCharge;
    }

    public void displayUltimate() {
        System.out.println("Ultimate: " + name);
        System.out.println("Effect: " + effect);
        System.out.println("Cost: " + neededCharge);

    }
}
