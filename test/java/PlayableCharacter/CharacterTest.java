package PlayableCharacter;

import com.game.hack3.PlayableCharacter.Character;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    @DisplayName("Adds health to Character after healing.")
    void heal() {
        Character Player1 = new Character(60);
        assertEquals(80, Player1.Heal(20));
    }

    @Test
    @DisplayName("Returns health of Character.")
    void gethealth(){
        Character Player1 = new Character(25);
        assertEquals(25, Player1.GetHealth());
    }

    @Test
    @DisplayName("Sets health of a Character.")
    void sethealth(){
        Character Player1 = new Character(50);
        assertEquals(50, Player1.GetHealth());
    }
}