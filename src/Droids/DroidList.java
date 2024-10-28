package Droids;

import Droids.Special.Ability.*;
import Droids.Special.Ultimate.*;


public class DroidList {
    BaseDroid x1;
    BaseDroid y1;
    RareDroid Cora;
    RareDroid Oscar;
    UltraDroid Aurora;
    UltraDroid Atlas;
    UltraDroid Arthur;
    UltraDroid MrMysterious;
    public DroidList() {
        this.x1 = new BaseDroid("x1",700,50,10,25,70,5,150);
        this.y1 = new BaseDroid("y1",500,75,5,15,100,15,200);
        this.Cora = new RareDroid("Cora",1700,40,5,10,33,5,150,new AtYourService("At your service","Purify all negative effects and heal for 71% of current to one ally"));
        this.Oscar = new RareDroid("Oscar",960,140,10,25,85,1,350,new PunchOfAnger("Punch Of Anger","Deals furious hit to his enemy with 100% crit rate"));
        this.Aurora = new UltraDroid("Aurora",1300,80,10,30,65,10,120, new ANewStar("A New Star","Restore 25 energy and give Invincibility for 1 move to an ally"), new StarFall("Star fall","Dispel all enemy buffs and make them lose 15% of current hp",0,40));
        this.Atlas = new UltraDroid("Atlas",4000,210,0,0,60,0,100, new TitanPower("Titan Power","Unleash god's power on his enemies and deal 140% to main target and 60% to others"),new SkyHood("Sky hood","Covers team with sky cape and give them Shelter effect for 3 turn",0,80));
        this.Arthur = new UltraDroid("Arthur",1600,160,25,50,100,60,150,new ProofOfHonor("Proof of Honor","Increase Artur damage by 40 and critDamage by 250% for 2 turns and give him 10 energy but make him lose 30 armor and 60 magic resist for 1 turn"), new TruePower("True power","Launches 7 critical slashes into chosen enemy. Every slash deals 60% damage",0,40));
        this.MrMysterious = new UltraDroid("Mr.Mysterious",1700,170,17,17,17,17,170,new CircusPlay("Circus play","Use random ability"),new Phantasmagoria("Phantasmagoria","Use random ultimate",0,50));
    }
    public void printDroidsExtended(){
        System.out.println("Droid list:");
        x1.displayInfo();
        y1.displayInfo();
        Cora.displayInfo();
        Oscar.displayInfo();
        Aurora.displayInfo();
        Atlas.displayInfo();
        Arthur.displayInfo();
        MrMysterious.displayInfo();
    }

    public BaseDroid getX1() {
        return x1;
    }

    public BaseDroid getY1() {
        return y1;
    }

    public RareDroid getCora() {
        return Cora;
    }

    public RareDroid getOscar() {
        return Oscar;
    }

    public UltraDroid getAurora() {
        return Aurora;
    }

    public UltraDroid getAtlas() {
        return Atlas;
    }

    public UltraDroid getArthur() {
        return Arthur;
    }

    public UltraDroid getMrMysterious(){
        return MrMysterious;
    }

}

