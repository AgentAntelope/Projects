public class Calculator{
    public static void main(String[] args){
        String example = "1 + 2 + 3 * 5 / 6";

    }

    public static int calculate(String partition, String op){
        try{
            int i = Integer.parseInt(partition);
            return i;
        }
        catch(Exception e){
        }
        String[] subPartition = partition.split(op);
        int val = 0;
        boolean firstValue = true;
        for(int i = 0; i < subPartition.length; i++){
            if(op.equals("/")){

            }

        }
        return 0;
    }

}
