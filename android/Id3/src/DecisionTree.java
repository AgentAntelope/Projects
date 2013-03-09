import java.util.Map;


public class DecisionTree extends Node {
	Classifier classifier;
	FeatureSet features;
	Map<String, Node> values;
	Feature feature;
	
	public DecisionTree(Classifier clas, FeatureSet fs){
		classifier = clas;
		features = fs;
	}

	public void build(Map<String, FeatureBucket> b){
		
	}
	
	public String evaluate(FeatureBucket buckets) {
		// TODO recurse
		return null;
	}
	
	//public void build()
	
	
	
}
