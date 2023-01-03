package com.game.hack3.Items;

public class Armor{
    public int Stat;
    public String Type;
    int Durability;
    public static int TrueDurability;

    public Armor(int stat, String type, int durability){
        Stat = stat;
        Type = type;
        this.Durability = durability;
        SetTrueDurability();
    }

    public void Durability(){
        Durability -= 1;
    }

    public int GetDurability(){
        return Durability;
    }

    public void SetTrueDurability(){
        TrueDurability = this.Durability;
    }

    public void ResetDurability(){
        this.Durability = TrueDurability;
    }

    public void LoadDurability(int aDurability){
        this.Durability = aDurability;
    }

}
