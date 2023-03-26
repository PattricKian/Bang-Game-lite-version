package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.tiles.PlayerObject;
import sk.stuba.fei.uim.oop.tiles.Tile;
import sk.stuba.fei.uim.oop.cards.Barrell;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    public static final String RESET = "\033[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED_BOLD = "\033[1;31m";

    private boolean[] aimTiles;
    private Tile[] pond;
    private ArrayList<Tile> deck;
    private ArrayList<Card> actionCards;
    private Player[] players;


    public Board(Player[] players) {
        ArrayList<Tile> cards = new ArrayList<Tile>();


        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                cards.add(new PlayerObject(player));
            }
        }

        this.pond = new Tile[players.length];
        for (int i = 0; i < players.length; i++) {
            this.pond[i] = cards.remove(cards.size() - 1);
        }

        this.deck = new ArrayList<Tile>();
        this.deck.addAll(cards);
        this.aimTiles = new boolean[players.length];
        for (int i = 0; i < players.length; i++) {
            this.aimTiles[i] = true;
        }

        this.actionCards = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            actionCards.add(new Bang(this));
        }

        for (int i = 0; i < 15; i++) {
            actionCards.add(new Miss(this));
        }

        for (int i = 0; i < 8; i++) {
            actionCards.add(new Beer(this));
        }

        for (int i = 0; i < 4; i++) {
            actionCards.add(new StageCoach(this));
        }
        for (int i = 0; i < 2; i++) {
            actionCards.add(new Indians(this));
        }

        for (int i = 0; i < 6; i++) {
            actionCards.add(new CatBalou(this));
        }

        for (int i = 0; i < 2; i++) {
            actionCards.add(new Barrell(this));
        }

        for (int i = 0; i < 1; i++) {
            actionCards.add(new Dynamite(this));
        }


        for (int i = 0; i < 3; i++) {
            actionCards.add(new Prison(this));
        }

        Collections.shuffle(this.actionCards);


        for (Player player : players) {
            ArrayList<Card> newCards = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                newCards.add(actionCards.remove(0));
            }
            player.setCards(newCards);
            player.setActionCards(actionCards);
        }

        this.players = players;

    }


    public void printBoard() {
        System.out.println("---- PLAYERS ----");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.getLives() > 0) {
                String hpText = "[HP=" + player.getLives() + "]";
                if (player.hasBarrellUsed()) {
                    hpText += RESET + YELLOW + " [BARREL]";
                }
                if (player.hasDynamiteUsed()) {
                    hpText += RESET + RED + " [DYNAMITE]";
                }

                System.out.println((i + 1) + ". " + player.getName() + RED + "    " + hpText + RESET);
            }
        }

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.getLives() <= 0) {
                System.out.println(RED_BOLD + (i + 1) + ". " + player.getName() + "    [DEAD]" + RESET);
            }
        }
    }


    public boolean isAimed() {
        return this.aimContains(true);
    }

    private boolean aimContains(boolean query) {
        for (boolean aim : this.aimTiles) {
            if (aim == query) {
                return true;
            }
        }
        return false;
    }

    public boolean[] getAimTiles() {
        return this.aimTiles;
    }


    public Card getActionCard() {
        return this.actionCards.remove(0);
    }

    public void addActionCard(Card card) {
        this.actionCards.add(card);
    }


    public Player[] getPlayers() {
        return this.players;
    }

    public int getActionCardsSize() {
        return this.actionCards.size();
    }

    public void setActionCards(ArrayList<Card> actionCards) {
        this.actionCards = actionCards;
    }
}
