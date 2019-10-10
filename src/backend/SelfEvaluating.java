package backend;


public interface SelfEvaluating extends Expression {

	@Override
	public SelfEvaluating eval(final Environment env);
}
