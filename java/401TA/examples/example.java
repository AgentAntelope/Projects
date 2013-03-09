class example{
    public static void main(String[] args){
        Integer a = new Integer(10);
        Integer b = new Integer(20);
        int c = a + b;
        A example = new A(20);
        System.out.println(example.val);

    }

}

class A{
    public int val;
    public String name;
    public static int val2 = 10;
    public A(int val){
       this. val = val;
    }
}
