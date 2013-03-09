import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Election{
    JFrame frame;
    ArrayList<Ballot> ballots;
    JButton vote;
    JButton login;
    String voterId, voterName;
    public Election(String ballotFile) throws IOException{
        ballots = new ArrayList<Ballot>();
        frame = new JFrame("Ballot");
        JPanel containers = new JPanel();
        login = new JButton("Login");
        vote = new JButton("Cast your vote");
        login.addActionListener(new VoterLoginListener());
        vote.addActionListener(new VoteListener());
        vote.setEnabled(false);
        frame.add(containers);
        Scanner s = new Scanner(new File(ballotFile));
        int ballots = Integer.parseInt(s.nextLine());
        for(int i = 0; i < ballots; i++){
            Ballot b = new Ballot(s.nextLine());
            containers.add(b);
        }
        containers.add(login);
        containers.add(vote);
        frame.setVisible(true);
        GridLayout g = new GridLayout(1, ballots+2);
        frame.pack();
    }
    public static void main(String[] args) throws IOException{
        new Election(args[0]);
    }

    class VoterLoginListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String getVoter = JOptionPane.showInputDialog("What's your user login id?");
            try{

                Scanner s = new Scanner(new File("voters.txt"));
                while(s.hasNext()){
                    String currentVoter = s.nextLine();
                    String[] voterInfo = currentVoter.split(":");
                    if(voterInfo[0].equals(getVoter)){
                        if(voterInfo[2].equals("true")){
                            JOptionPane.showMessageDialog(null, "You already voted");
                            return;
                        }
                        else{
                            voterId = voterInfo[0];
                            voterName = voterInfo[1];
                            vote.setEnabled(true);
                            login.setEnabled(false);
                            return;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "You aren't in our database");
            }
            catch(IOException p){

            }
        }
    }
    class VoteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to vote?", "Just in case..", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                for(Ballot b: ballots){

                }
            }
        }
    }
}

