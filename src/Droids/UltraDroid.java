package Droids;

import Droids.Special.Ability.Ability;
import Droids.Special.SkillParameters;
import Droids.Special.Ultimate.Ultimate;

public class UltraDroid extends RareDroid {
    Ultimate ultimate;
    public UltraDroid(String name, int BaseHealth, int BaseDamage, int BaseArmor, int BaseResist, int Baseaccuracy, int BaseCritChance, int BaseCritDamage, Ability ability,Ultimate ultimate) {
        super(name,BaseHealth, BaseDamage, BaseArmor, BaseResist, Baseaccuracy, BaseCritChance, BaseCritDamage,ability);
        this.rarity = 3;
        this.ultimate = ultimate;
        this.health = BaseHealth;
        this.damage = BaseDamage;
        this.armor = BaseArmor;
        this.resist = BaseResist;
        this.accuracy = Baseaccuracy;
        this.critChance = BaseCritChance;
        this.critDamage = BaseCritDamage;
    }
    @Override
    public void useUltimate(SkillParameters parameters) {
        ultimate.applyUltimate(parameters);
    }
    @Override
    public boolean canCast(){
        return ultimate.canCast();
    }

    @Override
    public void setBasicCharge(int amount){
        ultimate.setBasicCharge(amount);
    }

    @Override
    public int getBasicCharge(){
        return ultimate.getBasicCharge();
    }

    @Override
    public int getNeededCharge(){
        return ultimate.getNeededCharge();
    }

    @Override
    public void restoreEnergy(int amount){
        ultimate.changeBasicCharge(amount);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        ultimate.displayUltimate();
    }
    @Override
    public void setToDefault(){
        super.setToDefault();
        ultimate.setBasicCharge(0);
    }
}
