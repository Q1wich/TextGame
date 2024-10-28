package Droids;

import Droids.Special.Ability.Ability;
import Droids.Special.SkillParameters;

public class RareDroid extends BaseDroid {
    Ability ability;

    public RareDroid(String name,int BaseHealth, int BaseDamage, int BaseArmor, int BaseResist, int Baseaccuracy, int BaseCritChance, int BaseCritDamage, Ability ability) {
        super(name,BaseHealth, BaseDamage, BaseArmor, BaseResist, Baseaccuracy, BaseCritChance, BaseCritDamage);
        this.rarity = 2;
        this.ability = ability;
        this.health = BaseHealth;
        this.damage = BaseDamage;
        this.armor = BaseArmor;
        this.resist = BaseResist;
        this.accuracy = Baseaccuracy;
        this.critChance = BaseCritChance;
        this.critDamage = BaseCritDamage;
    }
    @Override
    public void useAbility(SkillParameters parameters) {
        ability.applyAbility(parameters);
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        ability.displayAbility();
    }
}