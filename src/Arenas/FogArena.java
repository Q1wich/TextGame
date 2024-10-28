package Arenas;

public class FogArena extends Arena{
    @Override
    public  double accuracyMultiplier(){
        return 0.7;
    }
    @Override
    public String toString(){
        return "Fog arena.Accuracy of all droids is reduced by 30%";
    }
}
