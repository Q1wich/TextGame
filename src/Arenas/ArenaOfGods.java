package Arenas;

public class ArenaOfGods extends Arena {
    @Override
    public double critDamageMultiplier(){
        return 1.35;
    }
    @Override
    public double damageMultiplier(){
        return 1.35;
    }
    @Override
    public String toString(){
        return "Arena of Gods.Damage and crit damage of all droids is increased by 35%";
    }
}
