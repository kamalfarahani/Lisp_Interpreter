package backend;


public class Multiply extends PrimitiveProcedure {

	public Multiply(final Variable[] params, final Expression[] body, final Environment env) {
		super(params, body, env);
	}

	@Override
	public Mulable operate(final SelfEvaluating[] args) {
		return ((Mulable)args[0]).multiply((Mulable)args[1]);
	}

}
