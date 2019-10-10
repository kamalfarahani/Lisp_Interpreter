package backend;


public class CompoundProcedure extends Procedure {

	public CompoundProcedure(final Variable[] params, final Expression[] body, final Environment env) {
		super(params, body, env);
	}

	@Override
	public SelfEvaluating apply(final SelfEvaluating[] args) {
		final Environment newEnv = super.preApply(args);
		
		return Utils.evalSequence(this.body, newEnv);
	}

}
