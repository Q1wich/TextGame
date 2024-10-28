package Arenas;

public class ArenaOfDeath extends Arena{
    @Override
    public double healthReduction(){
        return 0.1;
    }
    @Override
    public String toString(){
        return "Arena of death. All droids' health is reduced by 10% of their current hp each turn";
    }
}
