import java.awt.Color;


public class PhotoMagic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Picture pic = new Picture(args[0]);
		StringBuffer bits = new StringBuffer(args[1]);
		int tapbit = Integer.parseInt(args[2]);
		transform(pic, bits, tapbit);

	}
	public static Picture transform(Picture picture, StringBuffer bits, int tapbit){
		for(int i = 0; i < picture.width(); i++){
			for(int j=0; j < picture.height(); j++){
				Color curr = picture.get(i, j);
				int red = curr.getRed();
				int green = curr.getGreen();
				int blue = curr.getBlue();

				//red = red^Byte.valueOf(TwoComp.twosComp(RandomBit.generate(8, bits, tapbit)));
				String bitsz = RandomBit.generate(8, bits, tapbit);
				red = red^Byte.valueOf(TwoComp.twosComp(bitsz));
				System.out.println(bits);
				if(red < 0){
					red += 256;
				}
				green= green^Byte.valueOf(TwoComp.twosComp(RandomBit.generate(8, bits, tapbit)));
				System.out.println(bits);
				if(green< 0){
					green += 256;
				}
				blue = blue^Byte.valueOf(TwoComp.twosComp(RandomBit.generate(8, bits, tapbit)));
				System.out.println(bits);
				if(blue < 0){
					blue += 256;
				}
				Color newColor = new Color(red,green,blue);
				picture.set(i, j, newColor);
			}
		}
		picture.show();

		return picture;
	}

}
