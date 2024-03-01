package cardtypes;

import fileio.ActionsInput;
import gametools.CreateGame;

import java.util.ArrayList;

public class LordRoyce extends Hero {
    public LordRoyce(final int mana, final String description, final ArrayList<String> colors,
                     final String name) {
        super(mana, description, colors, name);
    }

    @Override
    void usePower(final CreateGame createGame, final ActionsInput actionsInput) {
        int x = actionsInput.getAffectedRow();
        ArrayList<Minion> row = createGame.getBoard().get(x);

        int max = 0;
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getAttackDamage() > max) {
                max = row.get(i).getAttackDamage();
            }
        }

        for (int i = 0; i < row.size(); i++) {
            if (max == row.get(i).getAttackDamage()) {
                row.get(i).setFrozen(true);
                break;
            }
        }
    }
}
