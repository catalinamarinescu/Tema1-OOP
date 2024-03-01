package cardtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.Coordinates;
import gametools.CreateGame;

import java.util.ArrayList;

public abstract class Minion extends Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private boolean frozen;

    /** se returneaza mana**/
    public int getMana() {
        return mana;
    }
    /** se seteaza mana**/
    public void setMana(final int mana) {
        this.mana = mana;
    }
    /** se returneaza viata**/
    public int getHealth() {
        return health;
    }
    /** se seteaza viata**/
    public void setHealth(final int health) {
        this.health = health;
    }
    /** se returneaza damage-ul**/
    public int getAttackDamage() {
        return attackDamage;
    }
    /** se seteaza damage-ul  **/
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }
    /** se seteaza numele**/
    public String getName() {
        return name;
    }
    /** se returneaza numele**/
    public void setName(final String name) {
        this.name = name;
    }

    @JsonIgnore
    /** verifica daca o carte e frozen**/
    public boolean isFrozen() {
        return frozen;
    }

    /** seteaza daca o carte este frozen**/
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }
    /** returneaza descrierea**/
    public String getDescription() {
        return description;
    }
    /** seteaza descrierea**/
    public void setDescription(final String description) {
        this.description = description;
    }
    /** returneaza culorile**/
    public ArrayList<String> getColors() {
        return colors;
    }
    /** seteaza culorile**/
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /** constructorul ce initializeaa fiecare camp**/
    public Minion(final int mana, final int attackDamage, final int health,
                  final String description, final ArrayList<String> colors,
                  final String name) {
        this.mana = mana;
        this.attackDamage = attackDamage;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
    }

    /** metoda de atatc de va fi suprascrisa**/
    void usePower(final CreateGame createGame, final Coordinates attacker,
                  final Coordinates attacked) { }
}
