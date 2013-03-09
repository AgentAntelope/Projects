public class recursion{
    public static void main(String[] args){
        A(2);
    }

    /* Assume i is always positive */
    public static void A(int i){
        System.out.println("A(" + i + ")");
        if(i == 0){
            System.out.println("Reached goal");
        }
        else{
            A(i-1);
        }
    }

}
