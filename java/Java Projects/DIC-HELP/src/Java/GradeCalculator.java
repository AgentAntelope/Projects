package Java;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.FileReader;
import java.util.Scanner;

 public class GradeCalculator extends JFrame 
{ 
   private static final int WIDTH = 550; 
   private static final int HEIGHT = 430; 
      
   private static final int MAX_NUMBER_OF_STUDENTS = 20; 
      
             // instance variables 
   private Student[] studentList = new Student[MAX_NUMBER_OF_STUDENTS]; 
   private int noOfStudents; 
   private double score, tst1, tst2, tst3; 
   private double  classAvg, stAvg, totalScore; 
   private int displayedStudentIndex = 0; 
   private char ltrGrade; 
   private String stName; 
      
             // This area is for the GUI components 
             // Each item that will be displayed will 
             // have a label and a textfield, (L) and (TF),  
             // respectively. 
   private JLabel stNameL, tst1L, tst2L, tst3L, 
                                     classAvgL, stAvgL, headingL; 
   private JTextField stNameTF, tst1TF, tst2TF, tst3TF; 
   private JTextArea classAvgTA, stAvgTA; 
   private JButton exitB, nextB, prevB, calcGrade; 
      
   private ButtonHandler bHandler; 
      
    public GradeCalculator() 
   { 
      setTitle("Grade Calculator");           // set's the title 
      setSize(WIDTH, HEIGHT);                 // set the window size 
      Container pane = getContentPane();      // get the container 
      pane.setLayout(null);                   // set the container's layout to null 
                      
      bHandler = new ButtonHandler();         // instantiate the button event handler 
   
             // instantiate the labels 
      headingL = new JLabel("STUDENT RECORD"); 
      stNameL = new JLabel("Studen Name", SwingConstants.RIGHT); 
      tst1L = new JLabel("Test 1", SwingConstants.LEFT); 
      tst2L = new JLabel("Test 2", SwingConstants.LEFT); 
      tst3L = new JLabel("Test 3", SwingConstants.LEFT); 
      stAvgL = new JLabel("Student Average     " 
                                             + "\n"   + "Class Average"); 
             //instantiate the text fields 
      stNameTF = new JTextField(65); 
      tst1TF = new JTextField(10); 
      tst2TF = new JTextField(10); 
      tst3TF = new JTextField(10); 
               
             // instantiate the text area 
      classAvgTA = new JTextArea(6, 20); 
      classAvgTA.setAutoscrolls(true); 
   
             // instantiate the buttons and register the listener 
      exitB = new JButton("Exit"); 
      exitB.addActionListener(bHandler); 
   
      nextB = new JButton("Next"); 
      nextB.addActionListener(bHandler); 
   
      prevB = new JButton("Previous"); 
      prevB.addActionListener(bHandler); 
   
      calcGrade = new JButton("Calc Grade"); 
      calcGrade.addActionListener(bHandler); 
   
             // set the size of the labels, text fields, and buttons 
             
      headingL.setSize(200, 30); 
      stNameL.setSize(100, 30); 
      stNameTF.setSize(100, 30); 
      tst1L.setSize(100, 30); 
      tst1TF.setSize(100, 30); 
      tst2L.setSize(120, 30); 
      tst2TF.setSize(100, 30); 
      tst3L.setSize(100, 30); 
      tst3TF.setSize(100, 30); 
      classAvgTA.setSize(370, 120); 
      calcGrade.setSize(100, 30); 
      prevB.setSize(100, 30);
      nextB.setSize(100, 30); 
      exitB.setSize(100, 30); 
                
             //set the location of the labels, text fields, 
             //and buttons 
      headingL.setLocation(220, 10); 
      stNameL.setLocation(20, 50); 
      stNameTF.setLocation(120, 50); 
      tst1L.setLocation(20, 100); 
      tst1TF.setLocation(120, 100); 
      tst2L.setLocation(300, 50); 
      tst2TF.setLocation(420, 50); 
      tst3L.setLocation(300, 100); 
      tst3TF.setLocation(420, 100); 
      classAvgTA.setLocation(70, 230); 
      prevB.setLocation(120, 370); 
      exitB.setLocation(220, 370); 
      nextB.setLocation(320, 370); 
      calcGrade.setLocation(420, 370); 
   
             //add the labels, text fields, and buttons to the pane 
      pane.add(headingL); 
      pane.add(stNameL); 
      pane.add(stNameTF); 
      pane.add(tst1L); 
      pane.add(tst1TF); 
      pane.add(tst2L); 
      pane.add(tst2TF); 
      pane.add(tst3L); 
      pane.add(classAvgTA); 
      pane.add(calcGrade); 
      pane.add(prevB); 
      pane.add(exitB); 
      pane.add(nextB); 
                
      setVisible(true);              //show the window 
      setDefaultCloseOperation(EXIT_ON_CLOSE); 
      System.exit(0); 
   } 
        


    public static void main (String [] args)
   { 
      new GradeCalculator(); 
   } 

   
      
   Scanner inFile = new Scanner(new FileReader("AcademicGrades.txt")); 
   {
      for (int s = 0; s < MAX_NUMBER_OF_STUDENTS; s++) 
         gradeCalc.studentList[s] = new Student(); 
      
      gradeCalc.noOfStudents =inFile.nextInt();       // get the number of students 
      gradeCalc.score = 
                     inFile.nextDouble();    // get the student's scores 
      
      gradeCalc.getStudentData(inFile); 
      gradeCalc.displayGradeAverage(0); 
   }

                     // get the student data from file 
                                                     
             
    public void getStudentData(Scanner inFile) 
   { 
      System.out.println("Grade Calculator is getting information..."); 
      System.out.println("One Moment Please"); 
   } 
    private class ButtonHandler implements ActionListener 
   { 
       public void actionPerformed (ActionEvent e) 
      { 
         if (e.getActionCommand().equals("Previous")) 
            if (displayedStudentIndex > 0) 
               displayGradeAverage(displayedStudentIndex - 1); 
            else 
               displayGradeAverage(displayedStudentIndex); 
         else if (e.getActionCommand().equals("Next")) 
            if (displayedStudentIndex + 1 < noOfStudents) 
               displayGradeAverage(displayedStudentIndex + 1); 
            else 
               displayGradeAverage(displayedStudentIndex); 
         else if (e.getActionCommand().equals("Calc Grade")) 
            displayGradeAverage(0); 
         else 
            System.exit(0); 
      } 
   } 

    public void displayGradeAverage(int stName) 
   { 
      displayedStudentIndex = stName; 
      String strName = ""; 
   
      boolean classAvg = studentList[(int) (totalScore / noOfStudents)].getClassAverage(); 
              
      stNameTF.setText(studentList[stName].getFirstName() + " " 
                                             + studentList[stName].getLastName()); 
      stAvgTA.setText(""+studentList[(int) (totalScore / 5.0)].getStudentAverage()); 
      classAvgTA.setText(""+studentList[(int) (totalScore / noOfStudents)].getClassAverage());
   
   }

}
