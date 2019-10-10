package backend;


public class Add extends PrimitiveProcedure {

	public Add(final Variable[] params, final Expression[] body, final Environment env) {
		super(params, body, env);
	}

	@Override
	public Addable operate(final SelfEvaluating[] args) {
		return ((Addable)args[0]).add((Addable)args[1]);
	}

}
