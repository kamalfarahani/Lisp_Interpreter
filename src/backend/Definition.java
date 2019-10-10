package backend;


public class Definition implements Expression {
	private final Variable variable;
	private final Expression expression;

	public Definition(final Variable variable, final Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public SelfEvaluating eval(Environment env) {
		final SelfEvaluating evaluatedValue = this.expression.eval(env);
		env.addMut(this.variable, evaluatedValue);
		
		return evaluatedValue;
	}

}
