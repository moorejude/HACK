package com.game.hack3.Utility;

public class CustomException extends Exception{

    /**
     * A custom exception
     * @param message that is getting thrown with the exception. Usually what the User did wrong.
     */
    public CustomException(String message){
        super(message);
    }

}

