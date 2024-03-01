package cardtypes;

import java.util.ArrayList;

public class SimpleCards extends Minion {
    public SimpleCards(final int mana, final int attackDamage, final int health,
                       final String description, final ArrayList<String> colors,
                       final String name) {
        super(mana, attackDamage, health, description, colors, name);
    }
}
