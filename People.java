package game;

import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Card> cards=new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public People(List<Card> cards) {
        this.cards = cards;
    }
}
