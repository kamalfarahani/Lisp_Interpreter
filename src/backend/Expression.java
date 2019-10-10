package backend;


public interface Expression {
	public SelfEvaluating eval(Environment env);
}
