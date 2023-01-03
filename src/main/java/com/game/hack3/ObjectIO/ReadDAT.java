package com.game.hack3.ObjectIO;

import com.game.hack3.Enemies.Skeleton;
import com.game.hack3.Enemies.Slime;
import com.game.hack3.Enemies.Troll;
import com.game.hack3.PlayableCharacter.Inventory;
import com.game.hack3.Game;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ReadDAT {

    /**
     * Load data saved to a file and use it to load a previous game.
     */
    public static void LoadFromFile(){
        try{
            FileInputStream fis = new FileInputStream("save.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataStorage dataStorage = (DataStorage)ois.readObject();
            Game.PlayerHP = dataStorage.CharacterHealth;
            Game.EnemiesKilled = dataStorage.CharacterKills;
            Game.PlayerAttacked = dataStorage.CharacterAttacked;
            Game.PotionsDrank = dataStorage.DrankPotions;
            Game.theEnemy = dataStorage.CurrentEnemy;
            Slime.TimesPrevious = dataStorage.slimesfaced;
            Troll.TimesPrevious = dataStorage.trollsfaced;
            Skeleton.TimesPrevious = dataStorage.skeletonsfaced;
            Inventory.ItemInventory = (ArrayList<String>) dataStorage.InventoryList;
            Game.Shield.Get().LoadDurability(dataStorage.ShieldDurability);
            Game.EnemiesFought = dataStorage.EnemiesList;
            Game.ShieldsBroke = dataStorage.ShieldsBroke;
            Game.DamageToEnemies = dataStorage.DamagetoEnemies;
            Game.DamageShielded = dataStorage.DamageShielded;
            Game.username = dataStorage.CharacterName;
            ois.close();
        } catch (IOException| ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Reads a file.
     * @return username written to that file.
     */
    public static String LoadName(){
        try{
            FileInputStream fis = new FileInputStream("username.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            String PlayerName;
            PlayerName = (String) ois.readObject();
            ois.close();
            return PlayerName;
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
