package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class GameJFrame extends JFrame implements ActionListener {
    JButton[] landlord=new JButton[2];
    JButton[] publishCard=new JButton[2];
    JLabel farmer=new JLabel(new ImageIcon("D:\\游戏素材\\Game\\Resources\\Pokers\\Role_Landlord.png"));
    JTextField[] time=new JTextField[3];
    JButton robBut=new JButton("抢地主");
    JButton noBut=new JButton("不抢");
    JButton outCardBut=new JButton("出牌");
    JButton noCardBut=new JButton("不要");
    People player1;
    People player2;
    People player3;
    List<Card> threeCards=new ArrayList<>();
    public void initJFrame(){
        this.setTitle("斗地主");
        this.setSize(830,620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.gray);
    }
    public GameJFrame(){
        setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\游戏素材\\Game\\Resources\\Pokers\\Role_Landlord.png"));
        initJFrame();
        initView();
        this.setVisible(true);
        java.util.List<Card> cards1=new ArrayList<>();
        java.util.List<Card> cards2=new ArrayList<>();
        List<Card> cards3=new ArrayList<>();
        Room c=new Room();
        c.start();
        for(int i=0;i<c.getAllcards().size();i++){
            c.getAllcards().get(i).setLocation(350,150);
            this.getContentPane().add(c.getAllcards().get(i));
        }
        for(int i=c.getAllcards().size()-3;i<c.getAllcards().size();i++){
            Common.move(c.getAllcards().get(i),c.getAllcards().get(i).getLocation(),new Point(270+96*(i-51),10));
            threeCards.add(c.getAllcards().get(i));
            c.getAllcards().get(i).turnFront();

        }
        for(int i=0;i<c.getAllcards().size()-3;i++){
            if(i%3==0){
                Common.move(c.getAllcards().get(i),c.getAllcards().get(i).getLocation(),new Point(50,30+i*7) );
                cards1.add(c.getAllcards().get(i));
            }else if(i%3==1){
                Common.move(c.getAllcards().get(i),c.getAllcards().get(i).getLocation(),new Point(120+i*9,450) );
                cards2.add(c.getAllcards().get(i));
                c.getAllcards().get(i).turnFront();
                c.getAllcards().get(i).setCanClick(true);
            }else{
                Common.move(c.getAllcards().get(i),c.getAllcards().get(i).getLocation(),new Point(700,30+i*7) );
                cards3.add(c.getAllcards().get(i));
            }
        }
        player1=new People(cards1);
        player2=new People(cards2);
        player3=new People(cards3);
        player1.getCards().sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getSizes()-o2.getSizes();
            }
        });
        player2.getCards().sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getSizes()-o2.getSizes();
            }
        });
        player3.getCards().sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getSizes()-o2.getSizes();
            }
        });
        Common.reposition(this,player1,0);
        Common.reposition(this,player2,1);
        Common.reposition(this,player3,2);
        robBut.setVisible(true);
        noBut.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==landlord[0]){
            farmer.setLocation(20,500);
            farmer.setVisible(true);
            int i=12;
            for (Card threeCard : threeCards) {

                threeCard.setCanClick(true);
                Common.move(threeCard,threeCard.getLocation(),new Point(120+i*9,450));
                i++;
            }
            player2.getCards().add(threeCards.getFirst());
            player2.getCards().add(threeCards.get(1));
            player2.getCards().add(threeCards.get(2));
            player2.getCards().sort(new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return o1.getSizes()-o2.getSizes();
                }
            });
            Common.reposition(this,player2,1);
            landlord[0].setVisible(false);
            landlord[1].setVisible(false);
            publishCard[0].setVisible(true);
            publishCard[1].setVisible(true);
        }else if(source==landlord[1]){
            Random x=new Random();
            int y=x.nextInt(1,3);
            for (Card threeCard : threeCards) {
                threeCard.turnBack();
            }
            if(y==1){
                player1.getCards().add(threeCards.getFirst());
                player1.getCards().add(threeCards.get(1));
                player1.getCards().add(threeCards.get(2));
                player1.getCards().sort(new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        return o1.getSizes()-o2.getSizes();
                    }
                });
                Common.reposition(this,player1,0);
                landlord[0].setVisible(false);
                landlord[1].setVisible(false);
            }else{
                player3.getCards().add(threeCards.getFirst());
                player3.getCards().add(threeCards.get(1));
                player3.getCards().add(threeCards.get(2));
                player3.getCards().sort(new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        return o1.getSizes()-o2.getSizes();
                    }
                });
                Common.reposition(this,player3,2);
                landlord[0].setVisible(false);
                landlord[1].setVisible(false);
                publishCard[0].setVisible(true);
                publishCard[1].setVisible(true);
            }
        }else if(source==publishCard[0]){
            List<Card>outCards=new ArrayList<>();
            for (int i = 0; i < player2.getCards().size(); i++) {
                if(player2.getCards().get(i).isClick()){

                }
            }
            player2.getCards().sort(new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return o1.getSizes()-o2.getSizes();
                }
            });
            Common.reposition(this,player2,1);
        }
    }
    public void initView(){
        robBut.setBounds(320,400,75,20);
        robBut.addActionListener(this);
        robBut.setVisible(false);
        landlord[0]=robBut;
        this.getContentPane().add(robBut);
        noBut.setBounds(420,400,75,20);
        noBut.addActionListener(this);
        noBut.setVisible(false);
        landlord[1]=noBut;
        this.getContentPane().add(noBut);
        outCardBut.setBounds(320,400,60,20);
        outCardBut.addActionListener(this);
        outCardBut.setVisible(false);
        publishCard[0]=outCardBut;
        this.getContentPane().add(outCardBut);
        noCardBut.setBounds(420,400,60,20);
        noCardBut.addActionListener(this);
        noCardBut.setVisible(false);
        publishCard[1]=noCardBut;
        this.getContentPane().add(noCardBut);
        for(int i=0;i<3;i++){
            time[i]=new JTextField("倒计时");
            time[i].setEditable(false);
            time[i].setVisible(true);
            this.getContentPane().add(time[i]);
        }
        time[0].setBounds(140,230,60,20);
        time[1].setBounds(374,360,60,20);
        time[2].setBounds(620,230,60,20);
        //地主图片
        farmer.setVisible(false);
        farmer.setSize(40,40);
        this.getContentPane().add(farmer);

    }
}
