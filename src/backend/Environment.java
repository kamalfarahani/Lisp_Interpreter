package backend;
import java.util.HashMap;


public class Environment {
	private final HashMap<String, SelfEvaluating> hashMap;
	
	public Environment(final HashMap<String, SelfEvaluating> hashMap) {
		this.hashMap = hashMap;
	}
	
	public SelfEvaluating lookup(Variable var) {
		return this.hashMap.get(var.name);
	}
	
	@SuppressWarnings("unchecked")
	public Environment add(Variable var, SelfEvaluating value) {
		final HashMap<String, SelfEvaluating> newHashMap = 
				(HashMap<String, SelfEvaluating>) this.hashMap.clone();
		
		newHashMap.put(var.name, value);
		
		return new Environment(newHashMap);
	}
	
	@SuppressWarnings("unchecked")
	public Environment addAll(Variable[] vars, SelfEvaluating[] values) {
		final HashMap<String, SelfEvaluating> newHashMap =
				(HashMap<String, SelfEvaluating>) this.hashMap.clone();
		
		for (int i = 0; i < vars.length; i++) {
			newHashMap.put(vars[i].name, values[i]);
		}
		
		return new Environment(newHashMap);
	}
	
	public void addMut(Variable var, SelfEvaluating value) {
		this.hashMap.put(var.name, value);
	}
}
