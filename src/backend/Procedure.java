package backend;


public abstract class Procedure implements SelfEvaluating {
	private static final String TOO_FEW_ARGS_ERR_MSG = "Too few arguments supplied!!!";
	private static final String TOO_MANY_ARGS_ERR_MSG = "Too many arguments supplied!!!";
	
	protected final Variable[] params;
	protected final Expression[] body;
	protected final Environment env;
	
	public Procedure(final Variable[] params, final Expression[] body, final Environment env) {
		this.params = params;
		this.body = body;
		this.env = env;
	}
	
	public Procedure eval(final Environment env) {
		return this;
	}
	
	public Environment preApply(final SelfEvaluating[] args) {
		this.checkParamsArgsEqualLength(args);
		Environment newEnv = this.env.addAll(this.params, args);
		
		return newEnv;
	}
	
	public abstract SelfEvaluating apply(final SelfEvaluating[] args);
	
	private void checkParamsArgsEqualLength(SelfEvaluating[] args) {
		if (this.params.length != args.length) {
			if (this.params.length < args.length) {
				throw new RuntimeException(TOO_MANY_ARGS_ERR_MSG);
			} else {
				throw new RuntimeException(TOO_FEW_ARGS_ERR_MSG);
			}
		}
	}
}
