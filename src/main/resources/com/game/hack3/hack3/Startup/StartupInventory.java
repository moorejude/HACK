package com.game.hack3.Startup;

import com.game.hack3.PlayableCharacter.Inventory;
import com.game.hack3.Game;

public class StartupInventory extends Thread {

    /**
     * A thread that is run at the startup of the game. Initializes the Inventory for the player Character.
     */
    @Override
    public void run() {
       SetInventory();
    }

    /**
     * Character inventory at the beginning starts out with: A Sword, a Shield, and 2 Health Potions.
     */
    public static void SetInventory(){
        Inventory.AddItem(Game.Sword.GetName());
        Inventory.AddItem(Game.Shield.GetName());
        Inventory.AddItem(Game.HealthPotion.GetName());
        Inventory.AddItem(Game.HealthPotion.GetName());
    }
}


