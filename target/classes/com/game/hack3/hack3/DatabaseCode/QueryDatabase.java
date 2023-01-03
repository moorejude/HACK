package com.game.hack3.DatabaseCode;

import com.game.hack3.Game;

import java.sql.*;

public class QueryDatabase {
    static String url = "jdbc:sqlite:HACKDatabase.sqlite";

    /**
     * This SQL Query pulls from the HACKDatabase.sqlite the average amount of times each Enemy was summoned,
     * as well as the average and max an Enemy has done per game and prints it out for the user in the game
     * window.
     */

    public static void AverageEnemyStats(){
        try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()){
            String enemy_damage_sql = "SELECT AVG (skeleton_damage), AVG (slime_damage), " +
                    "AVG (troll_damage), AVG (skeleton_summon), AVG (slime_summon), AVG (troll_summon)," +
                    "MAX(skeleton_damage), MAX(slime_damage), MAX(troll_damage) FROM Enemy_Stats";

            ResultSet results = statement.executeQuery(enemy_damage_sql);

            int skeletonDMGAverage = results.getInt(1);
            int slimeDMGAverage = results.getInt(2);
            int trollDMGAverage = results.getInt(3);
            int skeletonSummonAverage = results.getInt(4);
            int slimeSummonAverage = results.getInt(5);
            int trollSummonAverage = results.getInt(6);
            int skeletonMaxDmg = results.getInt(7);
            int slimeMaxDmg = results.getInt(8);
            int trollMaxDmg = results.getInt(9);

            Game.PrintLine("---------------------------------- Average Enemy Stats ----------------------------------");
            Game.PrintLine("Skeleton: \n\tdoes an average of " + skeletonDMGAverage + " damage\n\t\tmost damage in " +
                    "one game: "+ skeletonMaxDmg + "\n\tsummoned on average "
                    + skeletonSummonAverage + " times per game\n");
            Game.PrintLine("Slime: \n\tdoes an average of " + slimeDMGAverage + " damage\n\t\tmost damage in one " +
                    "game: " + slimeMaxDmg + "\n\tsummoned on average "
                    + slimeSummonAverage + " times per game\n");
            Game.PrintLine("Troll: \n\tdoes an average of " + trollDMGAverage + " damage\n\t\tmost damage in one " +
                    "game: " + trollMaxDmg + "\n\tsummoned on average "
                    + trollSummonAverage + " times per game\n");
            Game.PrintLine(Game.line);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This SQL Query pulls from the HACKDatabase.sqlite, and it counts the amount of times the Players have been
     * defeated by a specific type of Enemy, and prints out those stats on screen.
     */

    public static void PlayerDefeatedBy(){
        try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()){

            String player_defeat_sql= "SELECT player_defeatedBy, COUNT(player_defeatedBy) " +
                    "AS `value_occurrence` FROM Player_Stats GROUP BY player_defeatedBy ORDER BY `value_occurrence` " +
                    "DESC";

            ResultSet resultSet = statement.executeQuery(player_defeat_sql);
            Game.PrintLine("----------------------------------- Player Defeat Stats -------------------------------" +
                    "-----");
            Game.PrintLine("Players have been defeated by: ");

            while (resultSet.next()){
                String enemy_name = resultSet.getString(1);
                int enemy_number = resultSet.getInt(2);

                Game.PrintLine("\t" + enemy_name + "'s - " + enemy_number + " times!");
            }
            Game.PrintLine(Game.line);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This SQL Query pulls both Enemy and Player information to display a Leaderboard on the Game screen.
     */

    public static void LeaderBoard(){
        int number = 1;
        try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()){

            String leader_board_sql = "SELECT username, player_kills, player_damage, player_defended, " +
                    "skeleton_damage+slime_damage+troll_damage AS total " +
                    "FROM Player_Stats JOIN Enemy_Stats ON Player_Stats.game_id = Enemy_Stats.game_id " +
                    "WHERE player_kills >5 AND player_damage >=500 AND player_defended >=40 " +
                    "ORDER BY player_kills DESC limit 3";

            ResultSet resultSet = statement.executeQuery(leader_board_sql);

            Game.PrintLine("------------------------------------ HACK LeaderBoard -----------------------------" +
                    "--------");
            while (resultSet.next()){
                String username = resultSet.getString(1);
                int kills = resultSet.getInt(2);
                int damage = resultSet.getInt(3);
                int defended = resultSet.getInt(4);
                int sum = resultSet.getInt(5);

                Game.PrintLine(number + " - " + username + ": \n\tkilled " + kills + " enemies\n\tdid " + damage
                        + " damage" + "\n\tshielded for " + defended + " damage\n\thit by enemies for " +
                        sum + " damage!");
                Game.PrintLine(Game.line);
                number += 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
