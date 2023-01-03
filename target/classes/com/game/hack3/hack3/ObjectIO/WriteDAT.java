package com.game.hack3.ObjectIO;

import com.game.hack3.Enemies.EnemyCharacter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteDAT {

    /**
     * This method takes a bunch of data that gets written to a .dat file.
     * @param theArray Saves the current Character Inventory
     * @param Durability Saves the durability of the current shield (if the player has one).
     * @param ShieldBroken Saves the number of shield's the Character has broken for end games stats.
     * @param theEnemy Saves the current enemy that the Player is fighting and its stats.
     * @param theArray2 Saves the current list of enemies the Character has fought.
     * @param PlayerName Current username that the system has
     * @param PlayerHealth The health of the user (even after attacks)
     * @param Killed How many enemies the user has killed so far.
     * @param Attacked How many times the user was attacked
     * @param Drank How many health potions the user drank.
     * @param Slimes How many slimes the user has killed
     * @param Trolls How many trolls the user has killed
     * @param Skeletons how many skeletons the user has killed
     * @param <T> Generic, because things other than a String or Int are going to eventually be passed
     *           through this method.
     *
     */
    public static <T> void SaveToFile(T PlayerName, T PlayerHealth, T theEnemy, T Killed, T Attacked, T Drank, T Slimes,
                                      T Trolls, T Skeletons, T Durability, T ShieldBroken, T DamagetoEnemies,
                                      T DamageShielded,
                                      List<String> theArray, List<EnemyCharacter> theArray2) {
        try{
            FileOutputStream fos = new FileOutputStream("save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            DataStorage dataStorage = new DataStorage();
            dataStorage.CharacterName = (String) PlayerName;
            dataStorage.CharacterHealth = (int) PlayerHealth;
            dataStorage.CurrentEnemy = (EnemyCharacter) theEnemy;
            dataStorage.CharacterKills = (int) Killed;
            dataStorage.CharacterAttacked = (int) Attacked;
            dataStorage.DrankPotions = (int) Drank;
            dataStorage.slimesfaced = (int) Slimes;
            dataStorage.trollsfaced = (int) Trolls;
            dataStorage.skeletonsfaced = (int) Skeletons;
            dataStorage.ShieldDurability = (int) Durability;
            dataStorage.ShieldsBroke = (int) ShieldBroken;
            dataStorage.DamagetoEnemies = (int) DamagetoEnemies;
            dataStorage.DamageShielded = (int) DamageShielded;
            dataStorage.InventoryList = theArray;
            dataStorage.EnemiesList = theArray2;
            oos.writeObject(dataStorage);
            oos.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Method will save the username to a file for future games.
     * @param Username The name the user has set.
     * @param <T> Will take a number if the user inputs a number.
     */
    public static <T> void SaveName(T Username) {
        try {
            FileOutputStream fos = new FileOutputStream("username.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Username);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
