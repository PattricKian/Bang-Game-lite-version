package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Barrell extends Card {
    private static final String CARD_NAME = "Barrell";


    public Barrell(Board board) {
        super(CARD_NAME, board);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public boolean canPlay(int index) {
        return true;
    }

    @Override
    public void playCard(Player player){
        player.setBarrellUsed(true);
        super.playCard(player);
    }
}