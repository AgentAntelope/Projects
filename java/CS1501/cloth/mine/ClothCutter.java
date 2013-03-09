import java.util.*;

public class ClothCutter{
    private int width, height;
    private ArrayList<Pattern> patterns;
    private Memo memo;

    /**
     * ClothCutter
     *      Constructor use in conjunction with optimize
     * @param w : Width of the cloth.
     * @param h : Height of the cloth.
     * @param pat: The patterns in which we are trying to cut the cloth into.
     *
     */
    public ClothCutter(int w, int h, ArrayList<Pattern> pat){
        width = w;
        height = h;
        patterns = pat;
        memo = new Memo();
    }

    public static void main(String[] args){
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
         patterns.add(new Pattern(2,2,1,"A")) ;
        patterns.add(new Pattern(2,6,4,"B")) ;
        patterns.add(new Pattern(4,2,3,"C")) ;
        patterns.add(new Pattern(5,3,5,"D")) ;

        new ClothCutter(30, 15, patterns).optimize();
    }

    public void optimize(){
            System.out.println(worth(cut(width, height)));
            System.out.println(cut(width, height));
    }

    public List<Pattern> cut(int width, int height){
        assert(width >= 0);
        assert(height >= 0);
        // We already calculated dis shit, don't do it again!
        if(memo.hasMemo(width, height)){
            return memo.getPatterns(width, height);
        }

        int bestValue = 0;
        List<Pattern> ret = new ArrayList<Pattern>();

        Rectangle rect = new Rectangle(width, height);
        for(Pattern p: patterns){
            // Does the pattern fit? If yes, FUCKIN FIT IT!!!
            if(p.fits(width, height)){
                //Cut horizontal
                List<Rectangle> verts =  rect.split(p.width, p.height, Rectangle.VERTICAL);
                List<Pattern> patV = new ArrayList<Pattern>();
                patV.add(p);
                int verticalWorth = p.value;
                for(Rectangle r: verts){
                    patV.addAll(cut(r.width, r.height));
                    verticalWorth += worth(cut(r.width, r.height));
                }


                List<Rectangle> horizontal =  rect.split(p.width, p.height, Rectangle.VERTICAL);
                List<Pattern> patH = new ArrayList<Pattern>();
                patH.add(p);
                int horizontalWorth = p.value;
                for(Rectangle r: horizontal){
                    horizontalWorth += worth(cut(r.width, r.height));
                    patH.addAll(cut(r.width, r.height));
                }

                //Is it better than the current best?
                if(horizontalWorth > bestValue){
                    bestValue = horizontalWorth;
                    ret = patH;
                }


                if(verticalWorth > bestValue){
                    bestValue = verticalWorth;
                    ret = patV;
                }
            }
        }

        System.out.printf("%d, %d: %d\n", width, height, bestValue);
        memo.putPatterns(width, height, (ArrayList<Pattern>)ret);
        return ret;
    }


    public int worth(List<Pattern> patterns){
        int sum = 0;
        for(Pattern p: patterns){
            sum += p.value;
        }
        return sum;
    }


}
