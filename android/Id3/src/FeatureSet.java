import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class FeatureSet implements Iterable {
	Set<Feature> features;
	public Map<Integer, Feature> numberMap;
	int currentCount;
	
	public FeatureSet(){
		features = new HashSet<Feature>();
		numberMap = new HashMap<Integer, Feature>();
	}
	

	
	public FeatureSet(Set<Feature> featur, Map<Integer, Feature> numMap, int current){
		features = featur;
		numberMap = numMap;
		currentCount = current;
	}	
	
	public FeatureSet add(Feature f){
		Set<Feature> newF = new HashSet<Feature>(features);
		newF.add(f);
		numberMap.put(currentCount, f);
		currentCount++;
		return new FeatureSet(newF, this.numberMap, currentCount);
	}
	
	public Feature getAt(int i){
		return numberMap.get(i);
	}
	
	public FeatureSet remove(Feature f){
		Set<Feature> newF = new HashSet<Feature>(features);
		newF.remove(f);
		return new FeatureSet(newF, this.numberMap, currentCount);		
	}
	
	public Set<Feature> getSet(){
		return features;
	}
	
	public String toString(){
		return features.toString() + "    " + numberMap.toString();
	}

	public Iterator<Feature> iterator() {
		return features.iterator();
	}

}
