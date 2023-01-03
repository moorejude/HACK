package PlayableCharacter;

import com.game.hack3.PlayableCharacter.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test
    @DisplayName("Adds String to an Array.")
    void addItem() {
        Inventory.ClearInventory();
        Inventory.AddItem("Sword");
        String[] expectedOutput = {"Sword"};
        assertArrayEquals(expectedOutput, Inventory.ItemInventory.toArray(new String[0]));
    }

    @Test
    @DisplayName("Removes String from an Array.")
    void removeItem() {
        Inventory.ClearInventory();
        Inventory.AddItem("Sword");
        Inventory.AddItem("Shield");
        Inventory.AddItem("Potion");
        Inventory.RemoveItem("Potion");
        String[] expectedOutput = {"Sword", "Shield"};
        assertArrayEquals(expectedOutput, Inventory.ItemInventory.toArray(new String[0]));
    }

    @Test
    @DisplayName("Counts amount of same String in Array.")
    void numberOfItem() {
        Inventory.ClearInventory();
        Inventory.AddItem("Sword");
        Inventory.AddItem("Sword");
        assertEquals(2, Inventory.NumberOfItem("Sword"));
    }
}