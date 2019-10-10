package backend;

import java.util.Arrays;


public class Application implements Expression {
	final private Expression operator;
	final private Expression[] operands;
	
	public Application(final Expression operator, final Expression[] operands) {
		this.operator = operator;
		this.operands = operands;
	}

	@Override
	public SelfEvaluating eval(Environment env) {
		SelfEvaluating[] listOfValues = Arrays
				.stream(operands)
				.map(e -> e.eval(env))
				.toArray(size -> new SelfEvaluating[size]);
		
		final Procedure procedure = (Procedure)this.operator.eval(env);
		return procedure.apply(listOfValues);
	}

}
