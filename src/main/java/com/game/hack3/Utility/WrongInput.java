package com.game.hack3.Utility;

public class WrongInput {

    /**
     * Used to keep user input short
     * @param Input Takes user input
     * @param <T> of any type
     * @throws CustomException thrown if the input is over 10 characters.
     */
    public static <T> void GetLength(T Input) throws CustomException {
        int Length = ((String) Input).length();
        if (Length > 10) {
            throw new CustomException("Your username cannot be over 10 characters. Please try again.");
        }
    }
}
