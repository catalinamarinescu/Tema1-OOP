package cardtypes;

import fileio.Coordinates;
import gametools.CreateGame;

import java.util.ArrayList;

public class TheCursedOne extends Minion {
    public TheCursedOne(final int mana, final int attackDamage, final int health,
                        final String description, final ArrayList<String> colors,
                        final String name) {
        super(mana, attackDamage, health, description, colors, name);
    }

    @Override
    void usePower(final CreateGame createGame, final Coordinates attacker,
                  final Coordinates attacked) {
        int x = attacked.getX();
        int y = attacked.getY();

        int attackDamage = createGame.getBoard().get(x).get(y).getAttackDamage();
        int health = createGame.getBoard().get(x).get(y).getHealth();

        Minion card = createGame.getBoard().get(x).get(y);

        card.setHealth(attackDamage);
        card.setAttackDamage(health);
    }
}
