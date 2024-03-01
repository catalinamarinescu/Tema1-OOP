package cardtypes;

import fileio.ActionsInput;

import gametools.CreateGame;

import java.util.ArrayList;

public abstract class Hero extends Card {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private final int health;

    /** constructor ce initializeaza fiecare camp**/
    public Hero(final int mana, final String description,
                final ArrayList<String> colors, final String name) {
        this.mana = mana;
        this.description = description;
        this.colors = colors;
        this.name = name;
        this.health = 30;

    }

    /** returneaza viata**/
    public int getHealth() {
        return health;
    }
    /** returneaza viata**/
    public int getMana() {
        return mana;
    }
    /** seteaza mana **/
    public void setMana(final int mana) {
        this.mana = mana;
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
    /**seteaza culorile **/
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }
    /** returneaza numele**/
    public String getName() {
        return name;
    }
    /** seteaza numele**/
    public void setName(final String name) {
        this.name = name;
    }

    /** metoda de atac a cartii ce va fi suprascrisa**/
    void usePower(final CreateGame createGame, final ActionsInput actionsInput) { }
}
