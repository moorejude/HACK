package com.game.hack3.Enemies;

import com.game.hack3.PlayableCharacter.Inventory;

import java.io.Serializable;

public class Troll extends EnemyCharacter implements Serializable {

    public static int TimesPrevious = 0;
    public int OriginalHealth = 0;
    public static int DamageDone = 0;

    public Troll(int health) {
        super(health);
        this.health = health;
    }

    @Override
    public String EnemyName() {
        return "Troll";
    }

    @Override
    public String Summon() {
        TimesFaced();
        Original();
        return "You have summoned a Troll!";
    }

    @Override
    public int GetHealth() {
        return this.health;
    }

    /**
     * This is an internal method that occurs when the enemy "Troll" is summoned.
     */
    public void TimesFaced() {
        TimesPrevious += 1;
    }

    /**
     * @return the amount of times the "Troll" was summoned.
     */
    public static int GetAmount() {
        return TimesPrevious;
    }

    public static int GetDamage(){
        return DamageDone;
    }

    public void DamageAmount(int number) {
        DamageDone += number;
    }

    public String EnemyAttack() {
        if (!Inventory.InInventory("Shield")){
            DamageAmount(25);
        }else{
            DamageAmount(20);
        }
        return "The Troll attacks you with club!";
    }

    @Override
    public String toString(){
        return EnemyName() + " with Health: " + OriginalHealth;
    }

    public void Original(){
        OriginalHealth = this.health;
    }

    @Override
    public int OriginalHealth(){
        return OriginalHealth;
    }


}
