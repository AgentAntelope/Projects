import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FeatureBucket {
	Map<String, Map<String, Integer>> buckets;
	
	public FeatureBucket(Feature s, List<String> classifications){
		
		Set<String> attributes = s.getSet();
		buckets = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> basicClassifications = new HashMap<String, Integer>();
		for(String classi: classifications){
			basicClassifications.put(classi, 0);
		}
		
		
		for(String attribute: attributes){
			buckets.put(attribute, new HashMap<String, Integer>(basicClassifications));
		}
		
	}
	
	public Set<String> getAttributes(){
		return buckets.keySet();
	}
	
	public void addToBucket(String attribute, String classi){
		int previousValue = buckets.get(attribute).get(classi);
		buckets.get(attribute).put(classi, (1 + previousValue));
	}
	
	public int totalElements(){
		int count = 0; 
		
		for(String attribute: buckets.keySet()){
			count += attributeElementCount(attribute);
		}
		
		return count;
	}
	
	public int attributeElementCount(String attribute){
		int count = 0; 
		
		for(String classification: buckets.get(attribute).keySet()){
			count += buckets.get(attribute).get(classification);
		}
		return count;
	}
	
	public String toString(){
		return buckets.toString();
	}
}
