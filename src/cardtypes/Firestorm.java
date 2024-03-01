package cardtypes;

import fileio.ActionsInput;
import gametools.CreateGame;

import java.util.ArrayList;

public class Firestorm extends Environment {

    public Firestorm(final int mana, final String description,
                     final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    @Override
    void usePower(final CreateGame createGame, final ActionsInput actionsInput) {
        int x = actionsInput.getAffectedRow();
        ArrayList<Minion> row = createGame.getBoard().get(x);

        for (int i = 0; i < row.size(); i++) {
            row.get(i).setHealth(row.get(i).getHealth() - 1);
        }
    }
}
