package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public class CatBalou extends Card {
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String WHITE_BOLD = "\033[1;37m";

    private static final String CARD_NAME = "Cat balou";

    public CatBalou(Board board) {
        super(CARD_NAME, board);
    }

    @Override
    public boolean canPlay() {
        return this.board.isAimed();
    }

    @Override
    public boolean canPlay(int index) {
        return this.board.getAimTiles()[index];
    }

    @Override
    public void playCard(Player player) {
        super.playCard(player);
        int playerIndex = this.choosingTile("PLAYER");
        Player chosenPlayer = this.board.getPlayers()[playerIndex];
        if (chosenPlayer != player) {
            if (chosenPlayer.getPlayableCards().size() > 0) {
                Random random = new Random();
                Card[] opponentCards = chosenPlayer.getPlayableCards().toArray(new Card[0]);
                Card removedCard = opponentCards[random.nextInt(opponentCards.length)];
                chosenPlayer.removeCard(removedCard);
                System.out.println(BLUE + chosenPlayer.getName() + " lost a card: " + removedCard.getName() + RESET);
            } else {
                System.out.println(YELLOW + chosenPlayer.getName() + " has no cards to remove!" + RESET);

            }
        } else {
            System.out.println(RED+ "You cannot play Cat Balou on yourself!"+ RESET);
        }
    }
}