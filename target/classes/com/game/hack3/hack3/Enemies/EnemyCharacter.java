package com.game.hack3.Enemies;

import com.game.hack3.PlayableCharacter.Inventory;
import com.game.hack3.Utility.Randomizer;
import com.game.hack3.Game;

import java.io.Serializable;

public abstract class EnemyCharacter implements Serializable {
    int health;

    public EnemyCharacter(int health){
        this.health = health;
    }

    /**
     * Each enemy will have a different health.
     */
    public abstract int GetHealth();

    /**Returns new EnemyCharacter health after being attacked by Character/user.
     * @param AttackMove integer- Character's attack stat.
     */
    public int Attacked(int AttackMove) {
        return this.health -= AttackMove;
    }

    /** All extensions of the EnemyCharacter abstract class have their own personal attack.*/
    public abstract String EnemyAttack();

    /** Each Enemy has a different name.*/
    public abstract String EnemyName();

    /**All extensions of the EnemyCharacter abstract class will be summoned differently, and this will also initialize
     * the TimesPrevious integer in each class.
     */
    public abstract String Summon();

    /**Gets a random boolean, and if True, drops a Health Potion in the Character Inventory.*/
    public static boolean PotionDrop() {
        boolean GetBoolean = Randomizer.RandomBoolean();
        if (GetBoolean) {
            Inventory.AddItem("Health Potion");
            return true;
        }
        return false;
    }

    /**Gets a random boolean, and if True, drops a Shield into the Character Inventory.*/
    public static boolean ShieldDrop(){
        boolean GetBoolean = Randomizer.RandomBoolean();
        if (GetBoolean) {
            Inventory.AddItem(Game.Shield.GetName());
            return true;
        }
        return false;
    }

    /**This method keeps track of each enemy original health once summoned, since it is randomized, so that the Player
     * can see a list of all enemies with their original health.
     */
    public abstract int OriginalHealth();

}
