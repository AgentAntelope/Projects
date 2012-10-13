public class driver3{
   public static void main(String[] args){
      LinearFeedbackShiftRegister reg = new LinearFeedbackShiftRegister(16,8);
      reg.load("0110100001011001");
      System.out.println("reg = " + reg);
      System.out.println("generate 20 bits via reg: " + reg.generateBits(20));
      System.out.println("reg = " + reg);
   }
}