package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Barrell;
import sk.stuba.fei.uim.oop.cards.Card;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cards;
    private ArrayList<Card> actionCards;
    private boolean barrellUsed;
    private boolean dynamiteUsed;
    private boolean skipRound;

    public Player(String name) {
        this.cards = new ArrayList<Card>();
        this.actionCards = new ArrayList<Card>();
        this.name = name;
        this.barrellUsed = false;
        this.lives = 4;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }


    public void setSkipRound(boolean skipRound) {
        this.skipRound = skipRound;
    }







    public void setActionCards(ArrayList<Card> actionCards) {
        this.actionCards = actionCards;
    }


    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public void setBarrellUsed(boolean used) {
        barrellUsed = used;
    }

    public boolean hasBarrellUsed() {
        return barrellUsed;
    }

    public void setDynamiteUsed(boolean used) {
        dynamiteUsed = used;
    }

    public boolean hasDynamiteUsed() {
        return dynamiteUsed;
    }


    public ArrayList<Card> removeCardsFromHand() {
        ArrayList<Card> removedCards = new ArrayList<>(this.cards);
        this.cards.clear();
        return removedCards;
    }


    public void addTwoPlayableCards() {
        ArrayList<Card> twoCards = new ArrayList<Card>();
        int count = 0;
        Iterator<Card> it = this.actionCards.iterator();

        while (it.hasNext() && count < 2) {
            Card card = it.next();
            if (card.canPlay()) {
                twoCards.add(card);
                it.remove();
                count++;
            }
        }

        this.cards.addAll(twoCards);
    }


    public ArrayList<Card> getPlayableCards() {
        ArrayList<Card> cards = new ArrayList<Card>();


        for (Card card : this.cards) {
            if (card.canPlay()) {
                cards.add(card);
            }
        }

        return cards;
    }

    public ArrayList<Card> getAllCards() {
        return this.cards;
    }

    public boolean isActive() {
        return this.lives > 0;
    }

    public void removeLife() {
        this.lives--;
    }

    public void addLife() {
        this.lives++;
    }

    public void removeAndTakeNewCard(Card oldCard, Card newCard) {
        for (int i = 0; i < this.cards.size(); i++) {
            if (Objects.equals(this.cards.get(i), oldCard)) {
                this.cards.remove(i);
                this.cards.add(i, newCard);
                break;
            }
        }
    }


    public int getNumCards() {
        return this.cards.size();
    }



}
