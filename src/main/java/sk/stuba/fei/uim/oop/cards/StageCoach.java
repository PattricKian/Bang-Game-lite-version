package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class StageCoach extends Card {
    private static final String CARD_NAME = "StageCoach!";

    public StageCoach(Board board) {
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
    public void playCard(Player player) {
        super.playCard(player);
        player.addTwoPlayableCards();
        System.out.println("You used Stage Coach ! +2 Cards!");


    }
}
