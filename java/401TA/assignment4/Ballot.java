import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.*;

public class Ballot extends JPanel{
    ArrayList<Choice> buttons;
    String uniqueID, name;
    Choice currentChoice;

    public Ballot(String encodedString){
        try{


            super();
            buttons = new ArrayList<Choice>();
            String[] decoded = encodedString.split(":");
            uniqueID = decoded[0];
            File f = new File(uniqueID);
            name = decoded[1];
            String[] choices = decoded[2].split(",");
            this.add(new JLabel(name));
            BallotListener b = new BallotListener(this);
            for(int i = 0; i < choices.length; i++){
                Choice c = new Choice(choices[i]);
                c.addActionListener(b);
                buttons.add(c);
                this.add(c);
            }
        //BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        }
        catch(IOException p){

        }
        //this.setLayout(box);
    }

    public void selectChoice(Choice choice){
        if(choice != currentChoice){
            if (currentChoice != null)
                currentChoice.toggleSelection();

            currentChoice = choice;
            choice.toggleSelection();
        }
        else{
            currentChoice.toggleSelection();
            currentChoice = null;
        }
    }

    public String getId(){
        return uniqueID;
    }

    public String getSelectedChoice(){
        if(currentChoice != null)
            return currentChoice.getName();
        else
            return null;
    }

}

class BallotListener implements ActionListener{
    Ballot associatedBallot;
    public void actionPerformed(ActionEvent e){
        Choice c = (Choice)e.getSource();
        associatedBallot.selectChoice(c);
    }

    public BallotListener(Ballot b){
        associatedBallot = b;
    }

}
