package com.game.hack3.PlayableCharacter;

public class Character {
    public int health;

    public Character(int health) {
        this.health = health;
    }

    public int GetHealth(){
        return this.health;
    }

    public void SetHealth(int health) {
        this.health = health;
    }

    /**
     * Updates Character health after being healed for a certain amount.
     * @param Healing the amount of healing from an item
     */
    public int Heal(int Healing) {
        Inventory.RemoveItem("Health Potion");
        return this.health += Healing;
    }
}
