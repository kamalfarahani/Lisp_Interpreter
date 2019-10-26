package backend;

public class Equal extends PrimitiveProcedure {

	public Equal(Variable[] params, Expression[] body, Environment env) {
		super(params, body, env);
	}

	@Override
	public SelfEvaluating operate(SelfEvaluating[] args) {
		return ((Equalable) args[0]).isEqual((Equalable) args[1]);
	}

}
