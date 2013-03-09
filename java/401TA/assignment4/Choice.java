import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Choice extends JButton{
    public static final int CHOSEN = 1;
    public static final int NOT_SELECTED = 0;
    private int state;
    private String name;

    public Choice(String name){
        super(name);
        this.name = name;
        state = 0;
    }

    public void toggleSelection(){
        if(state == CHOSEN){
           //Set color to black, already chosen.
           state = NOT_SELECTED;
           this.setForeground(Color.black);
        }
        else{
            state = CHOSEN;
           this.setForeground(Color.red);
        }
    }

    public boolean isSelected(){
        return state == CHOSEN;
    }
    public String getName(){
        return name;
    }


}
