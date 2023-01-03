package com.game.hack3.ObjectIO;

import com.game.hack3.Enemies.EnemyCharacter;

import java.io.Serializable;
import java.util.List;

public class DataStorage implements Serializable {
    int CharacterHealth, CharacterKills, CharacterAttacked, DrankPotions, slimesfaced, trollsfaced, skeletonsfaced,
    ShieldDurability, ShieldsBroke, DamagetoEnemies, DamageShielded;

    String CharacterName;

    List<String> InventoryList;
    List<EnemyCharacter> EnemiesList;

    EnemyCharacter CurrentEnemy;

}
