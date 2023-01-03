package com.game.hack3.Utility;

import java.util.List;
import java.util.Random;

public class Randomizer {
    static Random random = new Random();

    /**
     * Randomize EnemyCharacter level one health.
     * @return an randomized integer between 25 and 65.
     */
    public static int LevelOneHealth(){
        return random.nextInt(65 - 25) + 25;
    }

    /**
     * Ability to pull a random item from a List.
     * @param theArray takes an Array
     * @param <T> any type of Array: String, Double, Integer, etc.
     * @return any one item in theArray.
     */
    public static <T> T anyItem(List<T> theArray) {
        return theArray.get(new Random().nextInt(theArray.size()));
    }

    /**
     * A coinflip for game randomization. Can be called multiple times.
     * @return a boolean
     */
    public static boolean RandomBoolean() {
        return random.nextBoolean();
    }

}
