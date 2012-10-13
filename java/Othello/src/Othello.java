import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Othello extends JFrame {
	//Added to suppress warnings.
	private static final long serialVersionUID = 1L;
	
	public Othello(){
		super("Othello");
		GameBoard gameBoard = new GameBoard();
		
		this.add(gameBoard);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
	
	public static void main(String[]Args){
		new Othello();
	}
}

/*
 * The main game board, holds individual squares with enumerated types.
 */
class GameBoard extends JPanel{
	GameSquare[][] tiles;
	public GameBoard(){
		super();
		tiles = new GameSquare[8][8];
		setPreferredSize(new Dimension(581,331));
		initBoard();

	}
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D r = (Graphics2D)g;
		for(int i = 0; i <= 7; i++){
			for(int j = 0; j <= 7; j++)
				tiles[i][j].draw(r);
		}
	}
	
	private void initBoard(){
		for(int i = 0; i <= 7; i++){
			for(int j = 0; j <= 7; j++){
				tiles[i][j] =new GameSquare(i*40+5,j*40+5);
			}
		}
		tiles[3][3].current = squareState.black;
		tiles[3][4].current = squareState.white;
		tiles[4][4].current = squareState.black;
		tiles[4][3].current = squareState.white;		
	}
}

class GameSquare extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	public squareState current;	//Used to tell the state and item in the square

	
	public GameSquare(int x, int y){
		super();
		this.height = 40;
		this.width = 40;
		this.x = x;
		this.y = y;
		current = squareState.empty;
	}
	public void draw(Graphics2D g2d){
		//124,108,107
		g2d.setColor(new Color(135,75,49));
		g2d.fill(this);
		g2d.setColor(new Color(47,43,41));
		g2d.draw(this);
		if(current == squareState.black){
			
			g2d.setColor(Color.black);
			g2d.fill(new Ellipse2D.Double(this.x+5, this.y+5, 30, 30));
		}
		if(current == squareState.white){
		
			g2d.setColor(Color.white);
			g2d.fill(new Ellipse2D.Double(this.x+4, this.y+4, 30, 30));			
		}
	}
}
enum squareState {white, black, empty;}
