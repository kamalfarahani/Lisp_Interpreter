package backend;

public class LispBool implements SelfEvaluating {
	public final Boolean value;
	
	public LispBool(final Boolean value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value.toString();
	}
	
	@Override
	public SelfEvaluating eval(Environment env) {
		return this;
	}

}
