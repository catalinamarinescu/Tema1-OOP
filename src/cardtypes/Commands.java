package cardtypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Input;
import gametools.CreateGame;

import java.util.ArrayList;


public class Commands {
    private CreateGame createGame;

    /** se returneaza jocul in sine**/
    public CreateGame getCreateGame() {
        return createGame;
    }

    /** se seteaza jocul **/
    public void setCreateGame(final CreateGame createGame) {
        this.createGame = createGame;
    }

    public Commands(final CreateGame createGame) {
        this.createGame = createGame;
    }

    /**metoda unde se realizeaza rezolvarea actiunilor**/
    public void resolveCommands(final Input inputData, final ArrayNode outputData) throws
            JsonProcessingException {
        for (ActionsInput actionsInput : createGame.getActions()) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();

            node.put("command", actionsInput.getCommand());

            if (actionsInput.getCommand().equals("getCardsInHand")) {
                node.put("playerIdx", actionsInput.getPlayerIdx());
                ArrayList<Card> copyDeck = new ArrayList<>();

                if (actionsInput.getPlayerIdx() == 1) {
                    copyDeck.addAll(createGame.getFirstsHand());
                } else if (actionsInput.getPlayerIdx() == 2) {
                    copyDeck.addAll(createGame.getSecondsHand());
                }

                node.putPOJO("output", copyDeck);
                outputData.add(node);

            } else if (actionsInput.getCommand().equals("getPlayerDeck")) {
                node.put("playerIdx", actionsInput.getPlayerIdx());
                ArrayList<Card> copyDeck = new ArrayList<>();
                if (actionsInput.getPlayerIdx() == 1) {
                    copyDeck.addAll(createGame.getFirstPlayersdeck());
                } else if (actionsInput.getPlayerIdx() == 2) {
                    copyDeck.addAll(createGame.getSecondPlayersdeck());
                }
                node.putPOJO("output", copyDeck);
                outputData.add(node);

            } else if (actionsInput.getCommand().equals("getPlayerHero")) {
                node.put("playerIdx", actionsInput.getPlayerIdx());
                Hero copyHero;
                if (actionsInput.getPlayerIdx() == 1) {
                    copyHero = createGame.getFirstPlayersHero();
                    node.putPOJO("output", copyHero);
                    outputData.add(node);
                } else if (actionsInput.getPlayerIdx() == 2) {
                    copyHero = createGame.getSecondPlayershero();
                    node.putPOJO("output", copyHero);
                    outputData.add(node);
                }
            } else if (actionsInput.getCommand().equals("getPlayerTurn")) {
                node.putPOJO("output", createGame.getWhosTurn());
                outputData.add(node);
            } else if (actionsInput.getCommand().equals("endPlayerTurn")) {

                //atunci cand se da comanda aceasta se schimba
                //al carui jucator ii este randul
                if (createGame.getWhosTurn() == 1) {
                    createGame.setWhosTurn(2);
                } else if (createGame.getWhosTurn() == 2) {
                    createGame.setWhosTurn(1);
                }

                createGame.setNumberTurn(createGame.getNumberTurn() + 1);
                createGame.startGame();

            } else if (actionsInput.getCommand().equals("getPlayerMana")) {
                node.put("playerIdx", actionsInput.getPlayerIdx());
                if (actionsInput.getPlayerIdx() == 1) {
                    node.put("output", createGame.getFirstPlayerMana());
                } else if (actionsInput.getPlayerIdx() == 2) {
                    node.put("output", createGame.getSecondPlayerMana());
                }
                outputData.add(node);
            } else if (actionsInput.getCommand().equals("placeCard")) {
                createGame.placeCard(actionsInput, node, outputData);
            } else if (actionsInput.getCommand().equals("getCardsOnTable")) {
                ArrayList<ArrayList<Minion>> copyTable = new ArrayList<>();
                copyTable.addAll(createGame.getBoard());
                node.putPOJO("output", copyTable);
                outputData.add(node);
            } else if (actionsInput.getCommand().equals("getEnvironmentCardsInHand")) {
                node.put("playerIdx", actionsInput.getPlayerIdx());
                ArrayList<Card> copyEnv = new ArrayList<>();
                if (actionsInput.getPlayerIdx() == 1) {
                    for(int i = 0; i < createGame.getFirstPlayersdeck().size(); i++) {
                        if (createGame.getFirstPlayersdeck().get(i).equals("Winterfell")
                        || createGame.getFirstPlayersdeck().get(i).equals("Firestorm")
                        || createGame.getFirstPlayersdeck().get(i).equals("Heart Hound")) {
                            copyEnv.add(createGame.getFirstPlayersdeck().get(i));
                        }
                    }
                    node.putPOJO("output", copyEnv);
                } else if (actionsInput.getPlayerIdx() == 2) {
                    for (int i = 0; i < createGame.getSecondPlayersdeck().size(); i++) {
                        if (createGame.getSecondPlayersdeck().get(i).equals("Winterfell")
                                || createGame.getSecondPlayersdeck().get(i).equals("Firestorm")
                                || createGame.getSecondPlayersdeck().get(i)
                                .equals("Heart Hound")) {
                            copyEnv.add(createGame.getSecondPlayersdeck().get(i));
                        }
                    }
                    node.putPOJO("output", copyEnv);
                }
                outputData.add(node);
            } else if (actionsInput.getCommand().equals("useEnvironmentCard")) {
                if (createGame.getWhosTurn() == 1) {
                    ArrayList<Environment> env = new ArrayList<>();
                   for (int i = 0; i < createGame.getFirstsHand().size(); i++) {
                       if (createGame.getFirstsHand().get(i).equals("Winterfell")
                               || createGame.getFirstsHand().get(i).equals("Firestorm")
                               || createGame.getFirstsHand().get(i).equals("Heart Hound")) {
                            env.add((Environment) createGame.getFirstsHand().get(i));
                       }
                   }

                   for (int i = 0; i < env.size(); i++) {
                       if (createGame.getFirstsHand().get(actionsInput.getHandIdx())
                               .equals(env.get(i))) {
                           env.get(i).usePower(createGame, actionsInput);
                       }
                   }

                } else if (createGame.getWhosTurn() == 2) {
                    ArrayList<Environment> env = new ArrayList<>();
                    for (int i = 0; i < createGame.getSecondsHand().size(); i++) {
                        if (createGame.getSecondsHand().get(i).equals("Winterfell")
                                || createGame.getSecondsHand().get(i).equals("Firestorm")
                                || createGame.getSecondsHand().get(i).equals("Heart Hound")) {
                            env.add((Environment) createGame.getSecondsHand().get(i));
                        }
                    }

                    for (int i = 0; i < env.size(); i++) {
                        if (createGame.getSecondsHand().get(actionsInput.getHandIdx())
                                .equals(env.get(i))) {
                            env.get(i).usePower(createGame, actionsInput);
                        }
                    }
                }
            }
        }
    }
}
