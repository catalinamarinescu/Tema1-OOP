package cardtypes;

import fileio.Coordinates;
import gametools.CreateGame;

import java.util.ArrayList;

public class Miraj extends Minion {
    public Miraj(final int mana, final int attackDamage, final int health,
                 final String description, final ArrayList<String> colors,
                 final String name) {
        super(mana, attackDamage, health, description, colors, name);
    }
    @Override
    void usePower(final CreateGame createGame, final Coordinates attacker, final Coordinates attacked) {
       int xAttacker = attacker.getX();
       int yAttacker = attacker.getY();

       int xAttacked = attacked.getX();
       int yAttacked = attacked.getY();

       int cardAttackerHealth = createGame.getBoard().get(xAttacker).get(yAttacker).getHealth();
       int cardAttackedHealth = createGame.getBoard().get(xAttacked).get(yAttacked).getHealth();

       Minion cardAttacker = createGame.getBoard().get(xAttacker).get(yAttacker);
       Minion cardAttacked = createGame.getBoard().get(xAttacked).get(yAttacked);

       cardAttacker.setHealth(cardAttackedHealth);
       cardAttacked.setHealth(cardAttackerHealth);
    }
}
