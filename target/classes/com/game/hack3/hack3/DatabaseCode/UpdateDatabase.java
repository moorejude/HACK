package com.game.hack3.DatabaseCode;

import com.game.hack3.Enemies.Skeleton;
import com.game.hack3.Enemies.Slime;
import com.game.hack3.Enemies.Troll;
import com.game.hack3.ObjectIO.ReadDAT;
import com.game.hack3.Game;
import java.sql.*;

public class UpdateDatabase {
    static String url = "jdbc:sqlite:HACKDatabase.sqlite";

    /**
     * This method uploads Player stats to the Player_Stats table in the HACKDatabase.sqlite. Things that are kept
     * track of include:
     * -Username of the Player
     * -Amount of enemies the Player killed
     * -Amount of damage the Player did to enemies
     * -Amount of damage the Player tanked with shields
     * -The enemy that finally killed the player.
     */

    public static void PlayerTableData(){
        String player_stats_sql ="INSERT INTO Player_Stats (username, player_kills, player_damage, player_defended," +
                "player_defeatedBy) Values (?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(url);
                PreparedStatement playerStatsStatement = connection.prepareStatement(player_stats_sql)){
            playerStatsStatement.setString(1, ReadDAT.LoadName());
            playerStatsStatement.setInt(2, Game.EnemiesKilled);
            playerStatsStatement.setInt(3, Game.DamageToEnemies);
            playerStatsStatement.setInt(4, Game.DamageShielded);
            playerStatsStatement.setString(5, Game.theEnemy.EnemyName());
            playerStatsStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This method uploads Enemy stats to the Enemy_Stats table in the HACKDatabase.sqlite. Things that are kept track
     * of include:
     * - Skeleton damage to Player
     * - Amount of times Skeletons were summoned in one game
     * - Slime damage to Player
     * - Amount of times Slimes were summoned in one game
     * - Troll damage to Player
     * - Amount of times Trolls were summoned in one game
     */

    public static void EnemyTableData(){
        String enemy_stats_sql = "INSERT INTO Enemy_Stats (skeleton_damage, skeleton_summon, slime_damage, " +
                "slime_summon, troll_damage, troll_summon) Values (?, ?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(url);
            PreparedStatement enemyStatsStatement = connection.prepareStatement(enemy_stats_sql)){
            enemyStatsStatement.setInt(1, Skeleton.GetDamage());
            enemyStatsStatement.setInt(2, Skeleton.GetAmount());
            enemyStatsStatement.setInt(3, Slime.GetDamage());
            enemyStatsStatement.setInt(4, Slime.GetAmount());
            enemyStatsStatement.setInt(5, Troll.GetDamage());
            enemyStatsStatement.setInt(6, Troll.GetAmount());
            enemyStatsStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
