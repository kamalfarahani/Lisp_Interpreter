package backend;


public class Begin implements Expression {
	private final Expression[] exps;

	public Begin(final Expression[] exps) {
		this.exps = exps;
	}
	
	@Override
	public SelfEvaluating eval(Environment env) {
		return Utils.evalSequence(exps, env);
	}

}
