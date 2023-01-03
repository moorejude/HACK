package Enemies;

import com.game.hack3.Enemies.Skeleton;
import com.game.hack3.Enemies.Slime;
import com.game.hack3.Enemies.Troll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyCharacterTest {

    @Test
    @DisplayName("Subtract a number from the enemy health.")
    void attacked() {
        Slime slime1 = new Slime(60);
        assertEquals(35, slime1.Attacked(25));

        Troll troll1 = new Troll(45);
        assertEquals(40, troll1.Attacked(5));

        Skeleton skeleton1 = new Skeleton(150);
        assertEquals(30, skeleton1.Attacked(120));
    }

    @Test
    @DisplayName("Returns type of enemy character.")
    void enemyname() {
        Skeleton skeleton2 = new Skeleton(60);
        assertEquals("Skeleton", skeleton2.EnemyName());

        Troll troll2 = new Troll(60);
        assertEquals("Troll", troll2.EnemyName());

        Slime slime2 = new Slime(60);
        assertEquals("Slime", slime2.EnemyName());
    }

    @Test
    @DisplayName("Returns health of enemy character.")
    void gethealth(){
        Skeleton skeleton3 = new Skeleton(70);
        assertEquals(70, skeleton3.GetHealth());

        Troll troll3 = new Troll(90);
        assertEquals(90, troll3.GetHealth());

        Slime slime3 = new Slime(80);
        assertEquals(80, slime3.GetHealth());
    }

    @Test
    void originalhealth(){
        Skeleton skeleton5 = new Skeleton(75);
        skeleton5.Summon();
        skeleton5.Attacked(25);
        assertEquals(75, skeleton5.OriginalHealth());

        Troll troll5 = new Troll(60);
        troll5.Summon();
        troll5.Attacked(5);
        assertEquals(60, troll5.OriginalHealth());

        Slime slime5 = new Slime(70);
        slime5.Summon();
        slime5.Attacked(5);
        assertEquals(70, slime5.OriginalHealth());
    }

}