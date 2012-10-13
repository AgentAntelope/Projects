//This drives the Register class

public class drive1{
   public static void main(String[] args){
      Register reg = new Register(16);
      reg.load("1101101");
      Register reg2 = new Register(16);
      reg2.load("10110");
      Register reg3 = new Register(16);
      reg3.load("0");
      Register reg4 = new Register(16);
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

      System.out.println("is reg zero? " + reg.isZero());
      System.out.println("is reg3 zero? " + reg3.isZero());
      System.out.println("reg2's sign? " + reg3.signBit());
      System.out.println("reg4's sign? " + reg4.signBit() + "\n");


      Register regc = new Register(reg);
      Register reg2c = new Register(reg2);
      regc.add(reg2c);
      System.out.println("Combine reg and reg2");
      System.out.println("reg + reg2 = " + regc.value());

      regc = new Register(reg);
      reg2c = new Register(reg2);
      regc.and(reg2c);
      System.out.println("reg and reg2 = " + regc);

      regc = new Register(reg);
      reg2c = new Register(reg2);
      regc.or(reg2c);
      System.out.println("reg or reg2 = " + regc);

      regc = new Register(reg);
      reg2c = new Register(reg2);
      regc.xor(reg2c);
      System.out.println("reg xor reg2 = " + regc);

   }
}