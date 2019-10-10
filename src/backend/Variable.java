package backend;


public class Variable implements Expression {
	public final String name;
	
	public Variable(final String name) {
		this.name = name;
	}

	@Override
	public SelfEvaluating eval(Environment env) {
		return env.lookup(this);
	}

}
