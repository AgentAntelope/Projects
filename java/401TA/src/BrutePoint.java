import java.util.*;
import java.io.*;

public class BrutePoint{
	public static void main(String[] args) throws IOException{
		int size = -1, tick = 0, x, y;
		CollinearPoint[] points = new CollinearPoint[5];
		CollinearPoint p, q, r, s;
		String fileName = "";
		boolean fileExists = true;
		
		File file;
		Scanner keyboard = new Scanner(System.in);

		do{
			if(!fileExists){
				System.out.println(fileName + " not found, please enter a valid file name.\n");
			}
			
			System.out.print("Enter a filename: ");
			fileName = keyboard.nextLine();
			file = new File(fileName);
			
			fileExists = file.exists();
		}while(!fileExists);
		
		Scanner fileInput = new Scanner(file);
		
		StdDraw.setYscale(0, 32768);
		StdDraw.setXscale(0, 32768);
		
		while(fileInput.hasNext()){
			if(size < 0){
				size = fileInput.nextInt();
				points = new CollinearPoint[size];
			}
		
			x = fileInput.nextInt();
			y = fileInput.nextInt();
			
			points[tick] = new CollinearPoint(x, y);
			tick++;
		}
		
		fileInput.close();
		
		for(int i = 0; i < points.length; i++){
			Point drawPoints = points[i];
			
			drawPoints.draw();
		}
		
		Arrays.sort(points);
		
		for(int i = 0; i < size; i++){
			for(int j = i + 1; j < size; j++){
				for(int k = j + 1; k < size; k++){
					for(int l = k + 1; l < size; l++){
						p = points[i];
						q = points[j];
						r = points[k];
						s = points[l];
												
						if(CollinearPoint.areCollinear(p, q, r, s) == true){
							p.drawTo(q);
							q.drawTo(r);
							r.drawTo(s);
							
							System.out.println(p + " -> " + q + " -> " +  r + " -> " + s);
						}
					}
				}
			}
		}
	}
}