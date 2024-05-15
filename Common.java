package game;

import java.awt.*;

public class Common {
    public static void move(Card card, Point from,Point to){
        if(to.x!=from.x){
            double k=(1.0)*(to.y-from.y)/(to.x- from.x);
            double b=to.y-to.x*k;
            int flag=0;
            if(from.x<to.x){
                flag=20;
            }else{
                flag=-20;
            }
            for(int i=from.x;Math.abs(i- to.x)>20;i+=flag){
                double y=k*i+b;
                card.setLocation(i,(int)y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        card.setLocation(to);
    }
    public static void reposition(GameJFrame m,People people,int flag){
        Point p=new Point();
        if(flag==0){
            p.x=50;
            p.y=30;
        }
        if(flag==1){
            p.y=450;
            p.x=130;
        }
        if(flag==2){
            p.x=700;
            p.y=30;
        }
        int len=people.getCards().size();
        for(int i=0;i<len;i++){
            Card card=people.getCards().get(i);
            move(card,card.getLocation(),p);
            m.getContentPane().setComponentZOrder(card,0);
            if(flag==1){
                p.x+=27;
            }else{
                p.y+=21;
            }
        }
    }
}
