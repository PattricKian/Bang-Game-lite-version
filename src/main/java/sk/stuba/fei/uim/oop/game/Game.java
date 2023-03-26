package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    public static final String RESET = "\033[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    private final Player[] players;
    private int currentPlayer;
    private Board board;
    private ArrayList<Card> usedCards = new ArrayList<>();



    public Game() {
        System.out.println("--- Welcome to BANG Game ---");
        int numberPlayers = 0;

        while (numberPlayers < 2 || numberPlayers > 6) {
            numberPlayers = ZKlavesnice.readInt("*** Enter number of players (2-6): ***");
            if (numberPlayers < 2 || numberPlayers > 6) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        this.players = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("*** Enter name for PLAYER " + (i + 1) + " : ***"));
        }

        this.board = new Board(this.players);
        this.startGame();
    }

    private void startGame() {
        System.out.println(RED + "--- GAME STARTED ---" + RESET);

        while (this.getNumberOfActivePlayers() > 1) {

            Player activePlayer = this.players[this.currentPlayer];

            if(board.getActionCardsSize() < 3){
                board.setActionCards(usedCards);
            }

            if (!activePlayer.isActive()) {
                usedCards = activePlayer.removeCardsFromHand();
                for (Card card : usedCards) {
                    this.board.addActionCard(card);
                    Collections.shuffle(this.usedCards);

                }
                this.incrementCounter();
                continue;
            }
            System.out.println(RED + "==============================================================================================================================" + RESET);
            System.out.println(RED + "--- PLAYER " + activePlayer.getName() + " STARTS TURN ---" + RESET);
            System.out.println("--- " + activePlayer.getName() + "'s lives: " + RED + activePlayer.getLives() + RESET + " ---");


            this.makeTurn(activePlayer);
            this.incrementCounter();
        }
        System.out.println(RED + "--- GAME FINISHED ---");
        System.out.println("*** And the WINNER is " + getWinner().getName() + " ***" + RESET);
    }

    private void makeTurn(Player activePlayer) {

        this.board.printBoard();
        activePlayer.addTwoPlayableCards();
        ArrayList<Card> playableCards = activePlayer.getPlayableCards();

        int maxCards = activePlayer.getLives();
        int numCardsToPlay = 0;


        if (playableCards.size() != 0) {

            while (true) {
                System.out.println(GREEN + "--- Your Cards ---" + RESET);
                this.printCards(playableCards);
                numCardsToPlay = ZKlavesnice.readInt(GREEN + "*** Enter how many cards you want to play (0-" + playableCards.size() + "): ***" + RESET);
                if (numCardsToPlay >= 0 && numCardsToPlay <= playableCards.size()) {
                    break;
                } else {
                    System.out.println(RED + " !!! You entered a wrong number of cards. Try again! !!!" + RESET);
                }
            }
            if (numCardsToPlay > activePlayer.getNumCards()) {
                System.out.println(RED + " !!! You cannot play more cards than you have. Try again! !!!" + RESET);
                this.makeTurn(activePlayer);
            } else {
                for (int i = 0; i < numCardsToPlay; i++) {
                    this.playCard(playableCards, activePlayer);
                }
            }
        } else {
            this.removeCard(activePlayer);
        }
        while (activePlayer.getNumCards() > maxCards) {
            System.out.println(RED + "--- You have too many cards in your hand. Which card do you want to throw away? ---" + RESET);
            ArrayList<Card> cards = activePlayer.getAllCards();
            int numberCard = choosingCard(cards, "REMOVE");
            Card removedCard = cards.get(numberCard);
            activePlayer.removeCard(removedCard);
            this.usedCards.add(removedCard);
        }
    }

    private void printCards(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + 1 + ": " + cards.get(i).getName());
        }
    }

    private void removeCard(Player activePlayer) {
        ArrayList<Card> cards = activePlayer.getAllCards();
        int numberCard = choosingCard(cards, "REMOVE");
        cards.get(numberCard).removeCard(activePlayer);
    }


    private void playCard(ArrayList<Card> playableCards, Player activePlayer) {
        System.out.println(GREEN + "--- Cards on hand ---" + RESET);
        int numberCard = choosingCard(playableCards, "PLAY");
        Card selectedCard = playableCards.get(numberCard);
        selectedCard.playCard(activePlayer);
        this.board.addActionCard(selectedCard);
        activePlayer.removeCard(selectedCard);
        playableCards.remove(selectedCard);
        usedCards.add(selectedCard);
        Collections.shuffle(this.usedCards);
    }

    private int choosingCard(ArrayList<Card> cards, String verb) {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card " + (i + 1) + ":" + cards.get(i).getName());
        }
        int numberCard = 0;
        while (true) {
            numberCard = ZKlavesnice.readInt(GREEN + "*** Enter number of card you want to " + verb + ": ***" + RESET) - 1;
            if (numberCard < 0 || numberCard > cards.size() - 1) {
                System.out.println(RED + " !!! You enter wrong number of card. Try Again! !!! " + RESET);
            } else {
                break;
            }
        }
        return numberCard;
    }

    private void incrementCounter() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
    }

    private int getNumberOfActivePlayers() {
        int count = 0;
        for (Player player : this.players) {
            if (player.isActive()) {
                count++;
            }
        }
        return count;
    }

    private Player getWinner() {
        for (Player player : this.players) {
            if (player.isActive()) {
                return player;
            }
        }
        return null;
    }


}


