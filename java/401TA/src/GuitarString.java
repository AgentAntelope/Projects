import java.util.Random;


public class GuitarString {
	RingBuffer buff;
	int time;
	double sampleRate;
	Random rand;
	GuitarString(double frequency){
		rand = new Random();
		double samplingRate =(44100 / frequency);
		int rate;
		if(samplingRate-(int)samplingRate >= .5){
			rate = (int)samplingRate + 1;
		}
		else{
			rate = (int)samplingRate;
			
		}
		buff = new RingBuffer( rate);
		for(int i = 0; i < rate; i++){
			buff.enqueue(0);
		}
		time = 0;
		sampleRate = rate;
		// create a guitar string of the given frequency, using a sampling rate of 44,100
		
	}

	GuitarString(double[] init){
		buff = new RingBuffer(init.length);
		for(double i: init){
			buff.enqueue(i);
			System.out.print(i + " ");
		}
		// create a guitar string whose size and initial values are given by the array
	}
	void pluck(){
		// set the buffer to white noise 
		while(buff.size() > 0){
			buff.dequeue();
		}
		for(int i = 0; i < sampleRate; i++){
			double rando = (rand.nextInt(11)-5)/10.0;
			buff.enqueue(rando);
		}
	}
	void tic(){
		 // advance the simulation one time step
		time++;
		double val = buff.dequeue();
		val += buff.peek();
		val /= 2;
		val*=.996;
		buff.enqueue(val);
	 }
	double sample(){
		return buff.peek();
		// return the current sample 
	}
	int time(){
		return time;
		 // return number of tics
	}
	

	public static void main(String[] args) {
	       int N = 25;
	       double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };
	        GuitarString testString = new GuitarString(samples);
	       for (int i = 0; i < N; i++) {
	           int t = testString.time();
	           double sample = testString.sample();
	           System.out.printf("%6d %8.4f\n", t, sample);
	           testString.tic();
	       }
	       
	} 
}
