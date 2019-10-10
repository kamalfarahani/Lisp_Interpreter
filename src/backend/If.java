package backend;


public class If implements Expression {
	private final Expression predicate;
	private final Expression consequent;
	private final Expression alternative;

	public If(final Expression predicate, final Expression consequent, final Expression alternative) {
		this.predicate = predicate;
		this.consequent = consequent;
		this.alternative = alternative;
	}
	
	@Override
	public SelfEvaluating eval(Environment env) {
		if (predicate.eval(env) != null) {
			return this.consequent.eval(env);
		} else {
			return this.alternative.eval(env);
		}
	}

}
