package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JLabel implements MouseListener {
    private String number;
    private String color;
    private int sizes;
    private boolean canClick;
    private boolean up;
    private boolean isClick;
    public void turnFront(){
        this.setIcon(new ImageIcon("D:\\游戏素材\\Game\\Resources\\Pokers\\"+number+"-"+color+".png"));
        this.up=true;
    }
    public void turnBack(){
        this.setIcon(new ImageIcon("D:\\游戏素材\\Game\\Resources\\Pokers\\CardBack.png"));
        this.up=false;
    }
    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", color='" + color + '\'' +
                ", size=" + sizes +
                '}';
    }

    public Card(String number, String color, int size,boolean up) {
        this.number = number;
        this.color = color;
        this.sizes = size;
        this.up=up;
        if(this.up){
            turnFront();
        }else{
            turnBack();
        }
        this.setSize(90,108);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSizes() {
        return sizes;
    }

    public void setSizes(int size) {
        this.sizes = size;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(canClick){
            int step=0;
            if(isClick){
                step=20;
            }else{
                step=-20;
            }
            isClick=!isClick;
            Point from=this.getLocation();
            Point to=new Point(from.x,from.y+step);
            this.setLocation(to);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
