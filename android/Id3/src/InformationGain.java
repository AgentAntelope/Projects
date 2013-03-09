import java.util.Map;
import java.util.Set;


public class InformationGain{


	public static String calculate(Map<String, FeatureBucket> buckets){
		String max = null;
		float entropyMax = Float.NEGATIVE_INFINITY;
		for(String feature: buckets.keySet()){
			float entropyCur = entropy(buckets.get(feature));
			if(entropyMax < entropyCur){
				max = feature;
				entropyMax = entropyCur;
			}
		}
		System.out.println(max);
		return max;
	}
	
	public static float entropy(FeatureBucket bucket) {
		float entropy = 0;
		for(String attribute: bucket.getAttributes()){
			float p = proportion(bucket, attribute);
			if(p == 0.0){
				
			}
			else{
				float intermediate = (float) (p * (Math.log(p)/Math.log(2)));
				
				entropy += intermediate;			
			}

		}
		return entropy;
	}
	
	public static float proportion(FeatureBucket bucket, String attribute){
		return (float)((float)bucket.attributeElementCount(attribute) / (float)bucket.totalElements());
	}

}
