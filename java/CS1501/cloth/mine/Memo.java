import java.util.*;
/**
 *
 * Hold the product of all the garments produced within a certain
 * rectangle.
 */
public class Memo{
    private HashMap<Integer, HashMap<Integer, ArrayList<Pattern>>> table;

    public Memo(){
       table = new HashMap<Integer, HashMap<Integer, ArrayList<Pattern>>>();
    }

    public int getValue(int w, int h){
        if(!table.containsKey(w)){
            return 0;
        }
        int sum = 0;
        for(Pattern p: table.get(w).get(h)){
            sum += p.value;
        }
        return sum;
    }

    public ArrayList<Pattern> getPatterns(int w, int h){
        if(!table.containsKey(w)){
            ArrayList<Pattern> temp = new ArrayList<Pattern>();
            HashMap<Integer, ArrayList<Pattern>> temp2 = new HashMap<>();
            table.put(w, temp2);
            temp2.put(h, temp);
        }
        return table.get(w).get(h);
    }

    /**
     * putPatterns:
     *      Give an array of patterns and put it into the w, h memo.
     *      Will automatically do this of there are none there.
     *
     * @param w: The cloth width
     * @param h: The cloth height
     * @param patterns: The patterns to be place.
     */
    public void putPatterns(int w, int h, ArrayList<Pattern> patterns){
        if(!table.containsKey(w)){
            HashMap<Integer, ArrayList<Pattern>> temp2 = new HashMap<>();
            table.put(w, temp2);
        }
        table.get(w).put(h, patterns);

    }

    public boolean hasMemo(int w, int h){
        return table.containsKey(w) && table.get(w).containsKey(h) && table.get(w).get(h) != null;
    }
}
