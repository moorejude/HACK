package com.game.hack3.PlayableCharacter;

import java.util.ArrayList;
import java.util.Collections;

public class Inventory {

    public static ArrayList<String> ItemInventory = new ArrayList<>();
    /**
     * Adds an item to Inventory
     * @param item that is to be added to the ItemInventory ArrayList
     */
    public static void AddItem(String item){
        ItemInventory.add(item);
    }

    /**
     * Prints out every item in the Inventory
     */
    public static ArrayList<String> GetInventory(){
        return ItemInventory;
    }

    /**
     * Removes Item from Inventory.
     * @param item that is going to be removed.
     */

    public static void RemoveItem(String item){
        ItemInventory.remove(item);
    }

    /**
     * Finds if item in Inventory
     * @param item to check
     * @return True or False if that item is in the ItemInventory
     */
    public static boolean InInventory(String item) {
        return ItemInventory.contains(item);
    }

    /**
     * Clears entire Inventory. Could be useful in future for more interesting gameplay.
     */
    public static void ClearInventory(){
        ItemInventory.clear();
    }

    /**
     * Counts the amount of items of the same kind and returns the number.
     * @param item to search for
     * @return the amount of that item
     */

    public static int NumberOfItem(String item){
        return Collections.frequency(ItemInventory, item);
    }

}
