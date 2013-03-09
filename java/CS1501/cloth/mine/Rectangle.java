import java.util.*;


public class Rectangle{
    public int width, height;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    Rectangle(int w, int h){
        width = w;
        height = h;
    }

    public List<Rectangle> split(int widthSplit, int heightSplit, int type){

        List<Rectangle> rectangles = new ArrayList<Rectangle>();
         if(this.width == widthSplit && this.height == heightSplit){
        }
       else if(type == VERTICAL){
           /*
            *   *----------------------------*
            *   |       |                    |
            *   |       |                    |
            *   |       |                    |
            *   *----------------------------*
            *
            */
           rectangles.add(new Rectangle(this.width - widthSplit, heightSplit));
           if(this.height > heightSplit){
                rectangles.add(new Rectangle(this.width, this.height - heightSplit));
           }

        }
       else{
           rectangles.add(new Rectangle(this.width, this.height - heightSplit));
           if(this.width > widthSplit){
                rectangles.add(new Rectangle(this.width - widthSplit, this.height));
           }
       }

       return rectangles;
    }
}
