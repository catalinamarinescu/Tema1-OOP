package cardtypes;
import java.util.ArrayList;

public abstract class Card {
   private int mana;
   private String description;
   private ArrayList<String> colors;
   private  String name;

   /** **/
    public String getName() {
        return name;
    }
    /** **/
    public void setName(final String name) {
        this.name = name;
    }
    /** **/
    public int getMana() {
        return mana;
    }
    /** **/
    public void setMana(final int mana) {
        this.mana = mana;
    }
    /** **/
    public String getDescription() {
        return description;
    }
    /** **/
    public void setDescription(final String description) {
        this.description = description;
    }
    /** **/
    public ArrayList<String> getColors() {
        return colors;
    }
    /** **/
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    public Card() { }

    /** **/
    public Card(final int mana, final  String description,
                final ArrayList<String> colors, final String name) {
        this.mana = mana;
        this.colors = colors;
        this.description = description;
        this.name = name;
    }
}
