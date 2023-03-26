package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card {

    public static final String RESET = "\033[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";


    private static final String CARD_NAME = "Bang";

    public Bang(Board board) {
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
        boolean missUsed = false;

        if (chosenPlayer.hasBarrellUsed()) {

            int chance = (int) (Math.random() * 4) + 1;
            if (chance == 1) {
                chosenPlayer.setBarrellUsed(true);
                System.out.println(BLUE + chosenPlayer.getName() + " used BARREL card chance and avoided being shot!" + RESET);
                return;
            }
        }


        for (Card card : chosenPlayer.getPlayableCards()) {
            if (card instanceof Miss) {
                chosenPlayer.removeCard(card);
                missUsed = true;
                System.out.println(BLUE + chosenPlayer.getName() + " used MISS card and avoided being shot!" + RESET);
                break;
            }
        }


        if (!missUsed) {
            if (chosenPlayer != player) {
                chosenPlayer.removeLife();
                System.out.println(RED + chosenPlayer.getName() + " was shot and lost a life!" + RESET);
                if(chosenPlayer.getLives() <= 0){
                    System.out.println(RED + chosenPlayer.getName() + " has lost all his HP, he is out of the game!" + RESET);
                }
            } else {
                System.out.println(RED + "You cannot shoot yourself!" + RESET);
            }
        }
    }

}
