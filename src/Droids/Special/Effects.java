package Droids.Special;

import Droids.BaseDroid;
import Logger.Logger;
import java.util.HashMap;

public class Effects {
    HashMap<String, Integer> positiveEffects;
    HashMap<String, Integer> negativeEffects;
    Logger logger = Logger.getInstance();
    public Effects() {
        positiveEffects = new HashMap<>();
        negativeEffects = new HashMap<>();
        positiveEffects.put("Invincibility", 0);
        positiveEffects.put("Shelter", 0);
        positiveEffects.put("Strength Of Honor", 0);
        negativeEffects.put("Cost Of Honor", 0);
    }

    public void applyEffect(BaseDroid droid, String effect, int time) {
        if (positiveEffects.containsKey(effect)) {
            if (positiveEffects.get(effect) <= 0) {
                onEffectChange(droid, effect);
            }
            positiveEffects.put(effect, time);
        } else if (negativeEffects.containsKey(effect)) {
            if (negativeEffects.get(effect) <= 0) {
                onEffectChange(droid, effect);
            }
            negativeEffects.put(effect, time);
        }
        String command = (droid.getName() + ", get\"" + effect + "\" for " + time + " turns");
        logger.logWithPrint(command);
    }

    public void onEffectChange(BaseDroid droid, String effect) {
        switch (effect) {
            case "Shelter":
                Shelter(droid);
                break;
            case "Strength Of Honor":
                StrengthOfHonor(droid);
                break;
            case "Cost Of Honor":
                CostOfHonor(droid);
                break;
        }
    }

    public void offEffectChange(BaseDroid droid, String effect) {
        switch (effect) {
            case "Shelter":
                BackShelter(droid);
                break;
            case "Strength Of Honor":
                BackStrengthOfHonor(droid);
                break;
            case "Cost Of Honor":
                BackCostOfHonor(droid);
                break;
        }
    }

    public void updateEffects(BaseDroid droid) {
        String command;
        for (String effect : positiveEffects.keySet()) {
            int currentTime = positiveEffects.get(effect);
            if (currentTime > 0) {
                currentTime--;
                positiveEffects.put(effect, currentTime);
                if (currentTime == 0) {
                    command = effect + "on " + droid.getName() + " ended";
                    logger.logWithPrint(command);
                    offEffectChange(droid, effect);
                }
            }
        }

        for (String effect : negativeEffects.keySet()) {
            int currentTime = negativeEffects.get(effect);
            if (currentTime > 0) {
                currentTime--;
                negativeEffects.put(effect, currentTime);
                if (currentTime == 0) {
                    command = effect + "on " + droid.getName() + " ended";
                    logger.logWithPrint(command);
                    offEffectChange(droid, effect);
                }
            }
        }
    }

    public void purifyNegative(BaseDroid droid) {
        for (String effect : negativeEffects.keySet()) {
            if (negativeEffects.get(effect) > 0) {
                offEffectChange(droid, effect);
            }
            negativeEffects.put(effect, 0);
        }
        String command = (droid.getName() + ", has been purified from all negative effects");
        logger.logWithPrint(command);
    }

    public void dispelPositive(BaseDroid droid) {
        for (String effect : positiveEffects.keySet()) {
            if (positiveEffects.get(effect) > 0) {
                offEffectChange(droid, effect);
            }
            positiveEffects.put(effect, 0);
        }
        String command = (droid.getName() + ", has been cleared from all positive effects");
        logger.logWithPrint(command);
    }


    public void Shelter(BaseDroid droid) {
        droid.setArmor(droid.getArmor() + 30);
        droid.setResist(droid.getResist() + 25);
    }

    public void StrengthOfHonor(BaseDroid droid) {
        droid.setDamage(droid.getDamage() + 40);
        droid.setCritDamage(droid.getCritDamage() + 250);
    }

    public void CostOfHonor(BaseDroid droid) {
        droid.setArmor(droid.getArmor() - 30);
        droid.setResist(droid.getResist() - 60);
    }

    public void BackShelter(BaseDroid droid) {
        droid.setArmor(droid.getBaseArmor() - 30);
        droid.setResist(droid.getBaseResist() - 25);
    }

    public void BackStrengthOfHonor(BaseDroid droid) {
        droid.setDamage(droid.getDamage() - 40);
        droid.setCritDamage(droid.getCritDamage() - 250);
    }

    public void BackCostOfHonor(BaseDroid droid) {
        droid.setArmor(droid.getArmor() + 30);
        droid.setResist(droid.getResist() + 60);
    }

    public HashMap<String, Integer> getPositiveEffects() {
        return positiveEffects;
    }

    public HashMap<String, Integer> getNegativeEffects() {
        return negativeEffects;
    }

    public boolean isUnderEffect(String effect) {
        return positiveEffects.get(effect) > 0;
    }

    public void setToDefault(BaseDroid droid) {
        purifyNegative(droid);
        dispelPositive(droid);
    }

    public void showEffects(BaseDroid droid) {
        HashMap<String, Integer> list = new HashMap<>();
        for (String effect : positiveEffects.keySet()) {
            int duration = positiveEffects.get(effect);
            if (duration > 0) {
                list.put(effect, duration);
            }
        }
        for (String effect : negativeEffects.keySet()) {
            int duration = negativeEffects.get(effect);
            if (duration > 0) {
                list.put(effect, duration);
            }
        }
        if (!list.isEmpty()) {
            System.out.println(droid.getName() + " has the following effects:");
            for (String effect : list.keySet()) {
                int duration = list.get(effect);
                System.out.println(effect + " (remaining turns: " + duration + ")");
            }
        } else {
            System.out.println(droid.getName() + " has no active effects.");
        }
    }
}