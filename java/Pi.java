import java.util.*;

/* TODO: HI */
public class Pi{
	final static float PLAIN_PIZZA_PRICE = 10.00;
	final static float PEPPERONI_PIZZA = 10.00;
	final static float CHERRY_PIE_SLICE_PRICE = 2.00;
	final static float CHERRY_PIE_PRICE = 10.00;
	final static float PI_CHARM_PRICE = 15.00;

	public static int slicePieCount(int slices){
		return slices % 6;
	}

	public static int wholePieCount(int slices, int wholePies){
		return slices / 6 + wholePies;
	}

	public static float priceForSlicePies(boolean hasPiCard, int slices){
		if(hasPiCard){
			return (CHERRY_PIE_SLICE - .25) * slices;
		}
		else{
			return CHERRY_PIE_SLICE * slices;
		}
	}

	public static float priceForWholePies(boolean hasPiCard, int wholePies){
		if(hasPiCard){
			return (CHERRY_PIE - 2.00) * wholePies;
		}
		else{
			return CHERRY_PIE * wholePies;
		}
	}

	public static float 

}