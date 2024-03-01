package gametools;

import cardtypes.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import fileio.GameInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CreateGame {

    private ArrayList<Card> firstPlayersdeck = new ArrayList<>();
    private ArrayList<Card> secondPlayersdeck = new ArrayList<>();
    private Hero firstPlayersHero;
    private Hero secondPlayershero;
    private int startingPlayer;
    private ArrayList<Card> firstsHand = new ArrayList<>();
    private ArrayList<Card> secondsHand = new ArrayList<>();
    private ArrayList<ActionsInput> actions;
    private int firstPlayerMana;
    private int secondPlayerMana;
    private int numberTurn;
    private int whosTurn;

    private int nextMana;
    private ArrayList<ArrayList<Minion>> board = new ArrayList<>();


    /** intoarce jucatorul care urmeaza sa dea **/
    public int getWhosTurn() {
        return whosTurn;
    }
    /** seteaza jucatorul care urmeaza sa dea **/
    public void setWhosTurn(final int whosTurn) {
        this.whosTurn = whosTurn;
    }

    /** intoarce pachetul primului jucator **/
    public ArrayList<Card> getFirstPlayersdeck() {
        return firstPlayersdeck;
    }

    /** intoarce pachetul celui de al doilea jucator **/
    public ArrayList<Card> getSecondPlayersdeck() {
        return secondPlayersdeck;
    }

    /** intoarce eroul primului jucator **/
    public Hero getFirstPlayersHero() {
        return firstPlayersHero;
    }

    /** intoarce eroul celui de al doilea jucator **/
    public Hero getSecondPlayershero() {
        return secondPlayershero;
    }
    /** intoarce cartile din mana primului jucator **/
    public ArrayList<Card> getFirstsHand() {
        return firstsHand;
    }
    /** intoarce cartile din mana celui de al doilea jucator **/
    public ArrayList<Card> getSecondsHand() {
        return secondsHand;
    }
    /** intoarce lista de comenzi care se dau **/
    public ArrayList<ActionsInput> getActions() {
        return actions;
    }
    /** intoarce tabla de joc **/
    public ArrayList<ArrayList<Minion>> getBoard() {
        return board;
    }
    /** intoarce jucatorul care incepe **/
    public int getStartingPlayer() {
        return startingPlayer;
    }
    /** seteaza jucatorul care incepe **/
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
    /** intoarce mana primului jucator **/
    public int getFirstPlayerMana() {
        return firstPlayerMana;
    }
    /** intoarce mana celui de al doilea jucator **/
    public int getSecondPlayerMana() {
        return secondPlayerMana;
    }
    /** intoarce numarul turelor **/
    public int getNumberTurn() {
        return numberTurn;
    }
    /** seteaza numarul turelor **/
    public void setNumberTurn(final int numberTurn) {
        this.numberTurn = numberTurn;
    }
    /** intoarce mana care se aduna la fiecare runda **/
    public int getNextMana() {
        return nextMana;
    }

    public void setFirstPlayerMana(final int firstPlayerMana) {
        this.firstPlayerMana = firstPlayerMana;
    }

    public void setSecondPlayerMana(final int secondPlayerMana) {
        this.secondPlayerMana = secondPlayerMana;
    }

    /** se convertesc cartile de tip minion si environment **/
    public Card createCards(final CardInput card) {
        if (card.getName().equals("Sentinel") || card.getName().equals("Berserker")
                || card.getName().equals("Goliath") || card.getName().equals("Warden")) {
            return new SimpleCards(card.getMana(), card.getAttackDamage(), card.getHealth(),
                    card.getDescription(), card.getColors(), card.getName());
        } else if (card.getName().equals("Miraj")) {
            return new Miraj(card.getMana(), card.getAttackDamage(), card.getHealth(),
                    card.getDescription(), card.getColors(), card.getName());
        } else if (card.getName().equals("The Ripper")) {
            return new TheRipper(card.getMana(), card.getAttackDamage(), card.getHealth(),
                    card.getDescription(), card.getColors(), card.getName());
        } else if (card.getName().equals("Disciple")) {
            return new Disciple(card.getMana(),  card.getAttackDamage(), card.getHealth(),
                    card.getDescription(), card.getColors(), card.getName());
        } else if (card.getName().equals("The Cursed One")) {
            return new TheCursedOne(card.getMana(), card.getAttackDamage(), card.getHealth(),
                    card.getDescription(), card.getColors(), card.getName());
        } else if (card.getName().equals("Firestorm")) {
            return new Firestorm(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        } else if (card.getName().equals("Winterfell")) {
            return new Winterfell(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        } else if (card.getName().equals("Heart Hound")) {
            return new HeartHound(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        }
        return null;
    }

    /** se convertesc cartile de tipul erou **/
    public Hero createHero(final CardInput card) {
        if (card.getName().equals("Lord Royce")) {
            return new LordRoyce(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        } else if (card.getName().equals("Empress Thorina")) {
            return new EmpressThorina(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        } else if (card.getName().equals("King Mudface")) {
            return new KingMudface(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        } else if (card.getName().equals("General Kocioraw")) {
            return new GeneralKocioraw(card.getMana(), card.getDescription(), card.getColors(),
                    card.getName());
        }
        return null;
    }

    /** constructorul unde se initializeaza fiecare camp si se creeaza jocul**/
    public CreateGame(final Input inputData) {
        for (GameInput game : inputData.getGames()) {
            int firstIdx = game.getStartGame().getPlayerOneDeckIdx();
            int secondIdx = game.getStartGame().getPlayerTwoDeckIdx();
            ArrayList<CardInput> deckPlayerOne = inputData.getPlayerOneDecks().getDecks().
                    get(firstIdx);
            ArrayList<CardInput> deckPlayerTwo = inputData.getPlayerTwoDecks().getDecks().
                    get(secondIdx);

            for (CardInput card : deckPlayerOne) {
                firstPlayersdeck.add(createCards(card));
            }

            for (CardInput card : deckPlayerTwo) {
                secondPlayersdeck.add(createCards(card));
            }

            Random random = new Random(game.getStartGame().getShuffleSeed());
            Random random2 = new Random(game.getStartGame().getShuffleSeed());

            Collections.shuffle(firstPlayersdeck, random);
            Collections.shuffle(secondPlayersdeck, random2);

            firstsHand.add(firstPlayersdeck.remove(0));
            secondsHand.add(secondPlayersdeck.remove(0));

            firstPlayersHero = createHero(game.getStartGame().getPlayerOneHero());
            secondPlayershero = createHero(game.getStartGame().getPlayerTwoHero());

            startingPlayer = game.getStartGame().getStartingPlayer();

            actions = game.getActions();

            firstPlayerMana = 1;
            secondPlayerMana = 1;
            whosTurn = startingPlayer;
            numberTurn = 0;
            nextMana = 1;

            for (int i = 0; i < 4; i++) {
               board.add(new ArrayList<>());
            }
        }
    }

    /** **/
    public void startGame() {
        if (numberTurn == 2) {
            nextMana++;

                firstPlayerMana += nextMana;
                secondPlayerMana += nextMana;

            if (firstPlayersdeck.size() > 0 && secondPlayersdeck.size() > 0) {
                firstsHand.add(firstPlayersdeck.remove(0));
                secondsHand.add(secondPlayersdeck.remove(0));
                numberTurn = 0;
            }
        }
    }

    /** **/
    public void placeCard(final ActionsInput actionsInput, final ObjectNode node,
                          final ArrayNode outputData) {
        if (whosTurn == 1) {
            if (firstsHand.size() > actionsInput.getHandIdx()) {
                Card firstCard = firstsHand.get(actionsInput.getHandIdx());

                if (firstPlayerMana < firstCard.getMana()) {
                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Not enough mana to place card on table.");
                    outputData.add(node);

                } else if ((firstCard.getName().equals("The Ripper")
                        || firstCard.getName().equals("Miraj")
                        || firstCard.getName().equals("Goliath")
                        || firstCard.getName().equals("Warden"))
                        && firstPlayerMana >= firstCard.getMana() && board.get(2).size() < 5) {

                    board.get(2).add((Minion) firstCard);
                    firstPlayerMana -= firstCard.getMana();
                    firstsHand.remove(actionsInput.getHandIdx());

                } else if ((firstCard.getName().equals("Sentinel")
                        || firstCard.getName().equals("Berserker")
                        || firstCard.getName().equals("The Cursed One")
                        || firstCard.getName().equals("Disciple"))
                        && firstPlayerMana >= firstCard.getMana() && board.get(3).size() < 5) {

                    board.get(3).add((Minion) firstCard);
                    firstPlayerMana -= firstCard.getMana();
                    firstsHand.remove(actionsInput.getHandIdx());
                } else if (firstCard.getName().equals("Firestorm")
                        || firstCard.getName().equals("Winterfell")
                        || firstCard.getName().equals("Heart Hound")) {
                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Cannot place environment card on table.");
                    outputData.add(node);
                } else if (board.get(2).size() >= 5 || board.get(3).size() >= 5) {
                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Cannot place card on table since row is full.");
                    outputData.add(node);
                }
            }
        } else if (whosTurn == 2) {
            if (secondsHand.size() > actionsInput.getHandIdx()) {
                Card secondCard = secondsHand.get(actionsInput.getHandIdx());

                if (secondPlayerMana < secondCard.getMana()) {
                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Not enough mana to place card on table.");
                    outputData.add(node);

                } else if ((secondCard.getName().equals("The Ripper")
                        || secondCard.getName().equals("Miraj")
                        || secondCard.getName().equals("Goliath")
                        || secondCard.getName().equals("Warden"))
                        && secondPlayerMana >= secondCard.getMana() && board.get(1).size() < 5) {

                    board.get(1).add((Minion) secondCard);
                    secondPlayerMana -= secondCard.getMana();
                    secondsHand.remove(actionsInput.getHandIdx());

                } else if ((secondCard.getName().equals("Sentinel")
                        || secondCard.getName().equals("Berserker")
                        || secondCard.getName().equals("The Cursed One")
                        || secondCard.getName().equals("Disciple"))
                        && secondPlayerMana >= secondCard.getMana() && board.get(0).size() < 5) {

                    board.get(0).add((Minion) secondCard);
                    secondPlayerMana -= secondCard.getMana();
                    secondsHand.remove(actionsInput.getHandIdx());

                } else if (secondCard.getName().equals("Firestorm")
                        || secondCard.getName().equals("Winterfell")
                        || secondCard.getName().equals("Heart Hound")) {

                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Cannot place environment card on table.");
                    outputData.add(node);
                } else if (board.get(0).size() >= 5 || board.get(1).size() >= 5) {
                    node.put("handIdx", actionsInput.getHandIdx());
                    node.put("error", "Cannot place card on table since row is full.");
                    outputData.add(node);
                }
            }
        }
    }
}
