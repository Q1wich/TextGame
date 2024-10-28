package Droids;

import Droids.Special.Effects;
import Droids.Special.SkillParameters;
import Logger.Logger;

import java.util.*;

public class BaseDroid {
    int rarity;
    String name;
    int BaseHealth;
    int BaseDamage;
    int BaseArmor;
    int BaseResist;
    int BaseAccuracy;
    int BaseCritChance;
    int BaseCritDamage;
    boolean isAlive;
    int health;
    int damage;
    int armor;
    int resist;
    int accuracy;
    int critChance;
    int critDamage;
    Effects effects;

    public BaseDroid(String name, int BaseHealth, int BaseDamage, int BaseArmor, int BaseResist, int BaseAccuracy, int BaseCritChance, int BaseCritDamage) {
        this.rarity = 1;
        this.isAlive = true;
        this.name = name;
        this.BaseHealth = BaseHealth;
        this.BaseDamage = BaseDamage;
        this.BaseArmor = BaseArmor;
        this.BaseResist = BaseResist;
        this.BaseAccuracy = BaseAccuracy;
        this.BaseCritChance = BaseCritChance;
        this.BaseCritDamage = BaseCritDamage;
        this.health = BaseHealth;
        this.damage = BaseDamage;
        this.armor = BaseArmor;
        this.resist = BaseResist;
        this.accuracy = BaseAccuracy;
        this.critChance = BaseCritChance;
        this.critDamage = BaseCritDamage;
        this.effects = new Effects();
    }

    public void takeDamage(BaseDroid caster, int damage, String damageType) {
        int res = 0;
        Logger logger = Logger.getInstance();
        String command;
        if(!effects.isUnderEffect("Invincibility")){
            if (damageType.equals("pure")) {
                res = damage;
                this.changeHealth(res);
            } else if (damageType.equals("physical")) {
                double damageReduction = (0.052 * armor) / (1 + 0.052 * armor);
                double effectiveDamage = damage - (damage * damageReduction);
                res = (int) effectiveDamage;
                this.changeHealth(res);
            } else {
                res = (int)(damage - damage * resist);
                this.changeHealth(res);
            }
        }
        if(health<= 0){
            isAlive = false;
        }
        command = (caster.getName()+",dealt "+res+" "+damageType+" to "+name);
        logger.logWithPrint(command);
    }

    public void changeHealth(int amount){
        this.setHealth(this.health-amount);
    }

    public void takeEffect(String effect, int time) {
        effects.applyEffect(this,effect,time);
    }

    public void takeHeal(int amount) {
        health += amount;
        Logger logger = Logger.getInstance();
        String command = (name+", get healed by "+amount);
        logger.logWithPrint(command);
    }

    public void purifyNegative() {
        effects.purifyNegative(this);
    }

    public void dispelPositive(){
        effects.dispelPositive(this);
    }

    public void updateEffects(){
        effects.updateEffects(this);
    }
    public void restoreEnergy(int amount) {}

    public void setBasicCharge(int amount){}

    public void useAbility(SkillParameters parameters){}

    public void useUltimate(SkillParameters parameters){}

    public boolean canCast(){
        return false;
    }
    public int getBasicCharge(){
        return 0;
    }

    public int getNeededCharge(){
        return 0;
    }


    public void showCurrentInfo() {
        System.out.println("\nisAlive: "+isAlive);
        System.out.println("\nName: "+name);
        System.out.println("Rarity: " + rarity+"★");
        System.out.println("Health: " + health);
        System.out.println("Damage: " + damage);
        System.out.println("Armor: " + armor);
        System.out.println("Resist: " + resist + "%");
        System.out.println("accuracy: " + accuracy + "%");
        System.out.println("Crit chance: " + critChance + "%");
        System.out.println("Crit damage: " + critDamage + "%");
        effects.showEffects(this);

    }

    public void displayInfo() {
        System.out.println("\nName: "+name);
        System.out.println("Rarity: " + rarity+"★");
        System.out.println("Health: " + BaseHealth);
        System.out.println("Damage: " + BaseDamage);
        System.out.println("Armor: " + BaseArmor);
        System.out.println("Resist: " + BaseResist + "%");
        System.out.println("accuracy: " + BaseAccuracy + "%");
        System.out.println("Crit chance: " + BaseCritChance + "%");
        System.out.println("Crit damage: " + BaseCritDamage + "%");
    }

    public void setToDefault() {
        health = BaseHealth;
        damage = BaseDamage;
        armor = BaseArmor;
        resist = BaseResist;
        accuracy = BaseAccuracy;
        critDamage = BaseDamage;
        critChance = BaseCritChance;
        isAlive = true;
        effects.setToDefault(this);
    }


    public boolean isAlive() {
        return isAlive;
    }

    public int getRarity(){
        return rarity;
    }

    public String getName(){
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getResist() {
        return resist;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public int getBaseHealth() {
        return BaseHealth;
    }

    public int getBaseDamage() {
        return BaseDamage;
    }

    public int getBaseArmor() {
        return BaseArmor;
    }

    public int getBaseResist() {
        return BaseResist;
    }

    public int getBaseAccuracy() {
        return BaseAccuracy;
    }

    public int getBaseCritChance() {
        return BaseCritChance;
    }

    public int getBaseCritDamage() {
        return BaseCritDamage;
    }

    public HashMap<String, Integer> getPositiveEffects() {
        return effects.getPositiveEffects();
    }

    public HashMap<String, Integer> getNegativeEffects() {
        return effects.getNegativeEffects();
    }

    public Effects getEffect(){
        return effects;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            isAlive = false;
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setResist(int resist) {
        this.resist = resist;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
