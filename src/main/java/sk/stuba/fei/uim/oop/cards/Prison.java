package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private static final double ESCAPE_CHANCE = 0.25;



    public Prison(Board board) {
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
        int playerIndex = this.choosingTile("PLAYER");
        Player chosenPlayer = this.board.getPlayers()[playerIndex];
        boolean escaped = Math.random() < ESCAPE_CHANCE;
        if (escaped) {
            System.out.println(GREEN + chosenPlayer.getName() + " escaped from prison and continues the round!" + RESET);
        } else {
            System.out.println(RED + chosenPlayer.getName() + " failed to escape from prison and must skip the round!" + RESET);
            chosenPlayer.setSkipRound(true);
        }
        chosenPlayer.setSkipRound(true);

    }




}
