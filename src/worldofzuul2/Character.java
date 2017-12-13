package worldofzuul2;

public abstract class Character {

    private final String name;
    private int hp;
    private int damage;

    /**
     * Constructor for NPC with Item(s).
     *
     * @param name - Name of the NPC.
     * @param hp - hp of the NPC.
     */
    Character(String name, int damage, int hp) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    /**
     * Accessor for damage of Character.
     *
     * @return damage - the current damage the Character deals.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Mutator for hp of the Character.
     *
     * @param damageTaken - the damage taken by the Character.
     */
    public void changeHp(int damageTaken) {
        this.hp = hp - damageTaken;
    }

    /**
     * Mutator that sets the hp of the Character.
     *
     * @param hp - the Health Points of the Character.
     */
    public void setHP(int hp) {
        this.hp = hp;
    }

    /**
     * Accessor for the hp of a Character.
     *
     * @return hp - the current Health Point of the Character.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Accessor for the Name of the Character.
     *
     * @return name - the Name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * This method checks if the Character is alive.
     *
     * @return true if the monster har move than 0 hp.
     */
    public boolean alive() {
        return this.hp > 0;
    }

    /**
     * Mutator of the damage the character deals.
     *
     * @param increase - increases the damage the Character deals
     */
    public void changeDamage(int increase) {
        this.damage += increase;
    }
}