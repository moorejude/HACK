package com.game.hack3.Startup;

import com.game.hack3.Game;

public class StartupEnemy extends Thread {

    /**
     * Initializes the first enemy at the beginning of the game. Created in a separate thread.
     */
    @Override
    public void run() {
        Game.GetAnEnemy();
    }

}
