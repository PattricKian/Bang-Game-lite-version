package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public abstract class Card {

    public static final String RESET = "\033[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";


    protected String name;
    protected Board board;

    public Card(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public abstract boolean canPlay();

    public abstract boolean canPlay(int index);

    public void playCard(Player player) {
        System.out.println(GREEN + "--- " + player.getName() + " choose " + this.name + " card to play. ---" + RESET);
    }

    public void removeCard(Player player) {
        this.board.addActionCard(this);
        player.removeAndTakeNewCard(this, this.board.getActionCard());
    }

    protected int choosingTile(String type) {
        this.board.printBoard();
        int numberPlayer = 0;
        while (true) {
            numberPlayer = ZKlavesnice.readInt(GREEN + "*** Enter number of " + type + " you want to choose: ***" + RESET) - 1;
            if (numberPlayer < 0 || numberPlayer > 5 || !this.canPlay(numberPlayer)) {
                System.out.println(RED + " !!! You enter wrong number of tile. Try Again! !!!" + RESET);
            } else {
                break;
            }
        }
        return numberPlayer;
    }


    public String getName() {
        return name;
    }


}
