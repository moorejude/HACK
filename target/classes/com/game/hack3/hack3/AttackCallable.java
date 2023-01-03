package com.game.hack3;

import com.game.hack3.PlayableCharacter.Inventory;
import java.util.concurrent.Callable;

public class AttackCallable implements Callable<Integer> {

    /** A future call method to run a separate thread at the same time other game data is being fetched.
     * The Call will first look into the Character inventory, and depending on if the Character has a shield or not
     * will return a number.
     * @return the amount the player Character was attacked for.
     */
    public Integer call(){
        if (!Inventory.InInventory("Shield")){
            return Game.thePlayer.GetHealth() - 25;
        }else{
            return Game.thePlayer.GetHealth() - 20;
        }
    }
}
