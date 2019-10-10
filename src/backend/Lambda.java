package backend;


public class Lambda implements Expression {
	private final Variable[] params;
	private final Expression[] body;
	
	public Lambda(final Variable[] params, final Expression[] body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public SelfEvaluating eval(final Environment env) {
		return new CompoundProcedure(this.params, this.body, env);
	}
	
}
