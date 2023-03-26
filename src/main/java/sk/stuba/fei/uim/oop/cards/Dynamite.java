package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite!";
    private boolean exploded;

    public Dynamite(Board board) {
        super(CARD_NAME, board);
        this.exploded = false;
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
        player.setDynamiteUsed(true);
        super.playCard(player);
        double random = Math.random();
        if (random < 1.0 / 8) {
            player.removeLife();
            player.removeLife();
            player.removeLife();
            System.out.println(RED + "The dynamite exploded and you lost 3 life points!" + RESET);
            exploded = true;
            player.setDynamiteUsed(false);
        } else {
            System.out.println(GREEN + "The dynamite didn't explode!" + RESET);

            exploded = false;
        }
    }


}