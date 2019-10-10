package backend;


public class Quoted implements SelfEvaluating {
	private final Expression exp;
	
	public Quoted(final Expression exp) {
		this.exp = exp;
	}

	@Override
	public Quoted eval(Environment env) {
		return this;
	}
}
