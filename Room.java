package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Room {
    private List<Card> allcards=new ArrayList<>();

    public List<Card> getAllcards() {
        return allcards;
    }

    public void setAllcards(List<Card> allcards) {
        this.allcards = allcards;
    }

    public Room() {
        String[] number={"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        String[] color={"1","2","3","4"};
        int size=0;
        for(int i=0;i<number.length;i++){
            size++;
            for(int j=0;j<color.length;j++){
                Card c=new Card(number[i],color[j],size,false);
                allcards.add(c);
            }
        }
        Card c1=new Card("小王","1",++size,false);
        Card c2=new Card("大王","2",++size,false);
        allcards.add(c1);
        allcards.add(c2);
    }
    public void start(){
        Collections.shuffle(allcards);
    }
}
