import java.util.HashMap;

public class GuitarHero {
   public static void main(String[] args) {
	   GuitarString[] arr = new GuitarString[37];
	   String keyboard = "q2wejjkl4r5ty7u8i9op-[=zxdcfvgbnjmk,.;";
	   double CONCERT_A = 440.0;
       for(int i = 0; i <37; i++ ){
    	   double currentNote = CONCERT_A * Math.pow(2, (i-24)/12.0);
    	   char currentChar = keyboard.charAt(i);
    	   GuitarString temp = new GuitarString(currentNote);
    	   arr[i] = temp;
       }

       while (true){
          // check if the user has typed a key; if so, process it
          if (KeyManager.hasNextKeyTyped()) {
              char key = KeyManager.nextKeyTyped();
              if(keyboard.indexOf(key) != -1){
                  arr[keyboard.indexOf(key)].pluck();            	  
              }
          }
          // compute the superposition of samples
          double sample = 0;
          for(GuitarString a:arr){
        	  sample+= a.sample();
        	  a.tic();
          }
          // play the sample on standard audio
          StdAudio.play(sample);
          // advance the simulation of each guitar string by one step
        }
    } 
} 
