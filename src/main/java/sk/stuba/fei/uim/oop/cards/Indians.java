package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(Board board) {
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
        boolean indiansUsed = false;
        for (Player chosenPlayer : this.board.getPlayers()) {
            if (chosenPlayer == player) {
                continue;
            }
            for (Card card : chosenPlayer.getPlayableCards()) {
                if (card instanceof Bang) {
                    chosenPlayer.removeCard(card);
                    indiansUsed = true;
                    System.out.println(BLUE + chosenPlayer.getName() + " used BANG card and avoided life point loss!" + RESET);
                    break;
                }
            }
            if (!indiansUsed) {
                chosenPlayer.removeLife();
                System.out.println(RED + chosenPlayer.getName() + " was raided by Indians and lost a life!" + RESET);
            }
        }
    }




}
