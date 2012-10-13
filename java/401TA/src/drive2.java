
public class drive2{
   public static void main(String[] args){
      ShiftRegister reg = new ShiftRegister(16);
      reg.load("1101101");
      ShiftRegister reg2 = new ShiftRegister(16);
      reg2.load("10110");
      ShiftRegister reg3 = new ShiftRegister(16);
      reg3.load("011111");
      ShiftRegister reg4 = new ShiftRegister(16);
      reg4.load("1001111101010101");
      
      System.out.println("loading registers ...");
      System.out.println("reg = " + reg);
      System.out.println("reg's value = " + reg.value() + "\n");
      System.out.println("reg2 = " + reg2);
      System.out.println("reg2's value = " + reg2.value() + "\n");
      System.out.println("reg3 = " + reg3);
      System.out.println("reg3's value = " + reg3.value() + "\n");
      System.out.println("reg4 = " + reg4);
      System.out.println("reg4's value = " + reg4.value() + "\n");

      ShiftRegister regc = new ShiftRegister(reg);
      ShiftRegister reg2c = new ShiftRegister(reg2);
      regc.shiftLeft();
      System.out.println("shift reg left, reg = " + regc);
      System.out.println("reg's value = " + regc.value() + "\n");
      System.out.println("shift reg2 right, reg2 = " + reg2c);
      System.out.println("reg2's value = " + reg2c.value() + "\n");

      regc = new ShiftRegister(reg4);
      System.out.println("reg4 = " + regc);
      regc.shiftRight();
      System.out.println("shift reg4 right with sign extension, reg4 = " + regc + "\n");

      regc = new ShiftRegister(reg3);
      System.out.println("reg3 = " + regc);
      System.out.println("returned value after shifing reg3 right with sign extension= " + regc.shiftRight());
      System.out.println("shift reg3 right, reg3 = " + regc + "\n");
   }
}