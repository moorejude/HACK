package com.game.hack3.TextIO;

import com.game.hack3.PlayableCharacter.Inventory;

import java.io.PrintWriter;

public class DisplayStats {
    /**
     * Method that takes a list of stats and then writes it to a .txt document.
     * @param EnemiesKilled amount of enemies killed by the user
     * @param SlimesFaced amount of times a Slime was summoned
     * @param SkeletonsFaced amount of times a Skeleton was summoned
     * @param TrollsFaced amount of times a Troll was summoned
     * @param PlayerAttacked amount of times the user chose to attack an EnemyCharacter.
     * @param DefeatedBy the EnemyCharacter that dealt the final blow on the Character.
     * @param PotionsDrank amount of times the user chose to drink a potion.
     */
    public static <T> void PrintStats(T EnemiesKilled, T SlimesFaced, T SkeletonsFaced, T TrollsFaced,
                                      T PlayerAttacked, T DefeatedBy, T PotionsDrank, T ShieldsBroke){
        try {
            PrintWriter HACKStats = new PrintWriter("HACK_Stats.txt");
            HACKStats.write("-----------------------------------   Your HACK Stats!   ---" +
                    "--------------------------------\n");
            HACKStats.write("You were defeated by a " + DefeatedBy + ".\n\n");
            HACKStats.write("You slayed " + EnemiesKilled + " enemies.\n");
            HACKStats.write("\tSlime's Fought......." + SlimesFaced + "\n");
            HACKStats.write("\tSkeleton's Fought...." + SkeletonsFaced + "\n");
            HACKStats.write("\tTroll's Fought......." + TrollsFaced + "\n\n");
            HACKStats.write("You were attacked " + PlayerAttacked + " times!\n");
            HACKStats.write("You broke " + ShieldsBroke + " shield(s)!\n");
            HACKStats.write("You drank " + PotionsDrank + " health potion(s)!\n\n");
            HACKStats.write("Your Inventory:\n");
            HACKStats.write(String.valueOf(Inventory.ItemInventory));

            HACKStats.close();
        }catch(Exception ex) {
            System.out.println("Writing to File could not be completed.");
        }
    }
}
