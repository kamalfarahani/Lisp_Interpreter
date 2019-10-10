package backend;


public abstract class PrimitiveProcedure extends Procedure {

	public PrimitiveProcedure(final Variable[] params, final Expression[] body, final Environment env) {
		super(params, body, env);
	}

	@Override
	public SelfEvaluating apply(final SelfEvaluating[] args) {
		final Environment env = super.preApply(args);
		
		return this.operate(args);
	}

	public abstract SelfEvaluating operate(final SelfEvaluating[] args);
}
