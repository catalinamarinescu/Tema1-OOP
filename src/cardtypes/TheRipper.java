package cardtypes;

import fileio.Coordinates;
import gametools.CreateGame;

import java.util.ArrayList;

public class TheRipper extends Minion {

    public TheRipper(final int mana, final int attackDamage, final int health,
                     final String description, final ArrayList<String> colors,
                     final String name) {
        super(mana, attackDamage, health, description, colors, name);
    }

    @Override
    void usePower(final CreateGame createGame, final Coordinates attacker,
                  final Coordinates attacked) {
        int x = attacked.getX();
        int y = attacked.getY();

        Minion card = createGame.getBoard().get(x).get(y);
        card.setAttackDamage(card.getAttackDamage() - 2);
    }
}
