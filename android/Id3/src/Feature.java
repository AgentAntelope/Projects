import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Feature {
	private Set<String> attributes;
	private String featureName;
	
	public Feature(String fName, Set<String> attrs){
		attributes = attrs;
		featureName = fName;
	}
	
	public static Feature getFeatureFromLine(String lineFromFile){
		Set<String> attrs = new HashSet<String>();
		String[] split = lineFromFile.split(",");
		String name = split[0];
		
		for(int i = 1; i < split.length; i++){
			attrs.add(split[i]);
		}
		
		
		return new Feature(name, attrs);
		
	}
	
	public Set<String> getSet(){
		return new HashSet<String>(attributes);
	}
	
	public String getName(){
		return featureName;
	}
	public String toString(){
		return String.format("{Name: %s, values: %s}", featureName, attributes.toString() );
	}
}
