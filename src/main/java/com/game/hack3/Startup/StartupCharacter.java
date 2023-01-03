package com.game.hack3.Startup;

import com.game.hack3.PlayableCharacter.Character;
import com.game.hack3.Game;

public class StartupCharacter extends Thread {

    /**
     * A separate thread to run concurrently with other threads when the game is launched.
     * Initializes the beginning player Character.
     */
    @Override
    public void run() {
        Game.thePlayer = GetACharacter();
    }

    /**
     * Creates a new character for the start-up of the game.
     * @return a new Character with 100 health
     */
    public static Character GetACharacter(){
        return new Character(100);
    }

}
