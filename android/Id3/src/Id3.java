import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Id3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Make sure we have all the args we need.
		if(args.length < 3){
			System.out.println("You have a problem with your ARRRGGSSSSS");
			System.exit(0);
		}
		
		//Parse the classifier into a set of sets of items.
		try {
			Map<String, FeatureBucket> buckets = new HashMap<String, FeatureBucket>();
			
			Scanner config = new Scanner(new File(args[0]));
			Scanner trainer = new Scanner(new File(args[1])); 
			Scanner test = new Scanner(new File(args[2]));
			List<String> classifications = new ArrayList<String>();
			FeatureSet features = configData(config, classifications);
			
			System.out.println(classifications);
			System.out.println(features);
			
			//Set up all the buckets for adding features.
			for(Object f: features){
				Feature feature = (Feature)f;
				FeatureBucket bucket = new FeatureBucket(feature, classifications);
				buckets.put(feature.getName(), bucket);
			}
						
			// Put training data into the buckets
			while(trainer.hasNextLine()){
				String trainingLine = trainer.nextLine();
				String[] split = trainingLine.split(",");
				System.out.println(trainingLine);

				String classifier = split[1];
				for(int i = 2; i < split.length; i++){
					Feature curFeature = features.getAt(i - 2);
					System.out.println(curFeature);
					FeatureBucket bucket = buckets.get(curFeature.getName());
					bucket.addToBucket(split[i], classifier);
				}
			}
			System.out.println(buckets);
			System.out.println(InformationGain.calculate(buckets));

			
						
		} catch (FileNotFoundException e) {

		}
	}
	
	public static FeatureSet configData(Scanner config, List<String> classifications){
		// TODO GET THE NUMBER YOU FOOL!
		config.nextLine();
		String classifiers = config.nextLine();
		String[] split = classifiers.split(",");
		
		for(String var: split){
			classifications.add(var);
		}
		
		FeatureSet features = new FeatureSet();
		
		//Get each Feature.
		while(config.hasNextLine()){
			String featureList = config.nextLine();
			features = features.add(Feature.getFeatureFromLine(featureList));
		}
		return features;
		
	}

}
