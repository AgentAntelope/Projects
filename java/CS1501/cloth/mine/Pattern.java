
public class Pattern{
    int width, height, value;
    String name;

    /**
     * Pattern:
     *      Constructor.
     *
     * @param w: The width of the pattern
     * @param h: The height of the pattern.
     * @param v: Value of the pattern.
     * @param s: Name for debugging.
     *
     */
    public Pattern(int w, int h, int v, String n){
        width = w;
        height = h;
        value = v;
        name = n;
    }


    /**
     * fits:
     *      Check if a cloth will work with this pattern.
     *
     * @return: true if it fits.
     *
     **/
    public boolean fits(int clothWidth, int clothHeight){
        return clothWidth >= width && clothHeight >= height;
    }

    /**
     * equals:
     *      Check if a pattern is equal to another.
     *
     *  @param other the pattern to check against.
     *  @return boolean whether they are equal.
     **/
    public boolean equals(Pattern other){
        return value == other.value && height == other.height && width == other.width;
    }

    public String toString(){
        return String.format("[%d, %d, %s]", width, height, name);
    }


}
