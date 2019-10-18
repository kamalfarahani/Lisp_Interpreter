package interpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import backend.Add;
import backend.Environment;
import backend.Multiply;
import backend.SelfEvaluating;
import backend.Variable;
import forntend.Parser;
import forntend.RawExpression;

public class Main {
	public static void main(String[] args) {
		final Environment env = new Environment(new HashMap<String, SelfEvaluating>());
		addPrimitiveFunctionsToEnv(env);
		repl(env);
	}
	
	private static void repl(final Environment env) {
		final BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("λ > ");
			
			try {
				final String programStr = reader.readLine();
				final RawExpression rawExpression = 
						Parser.parse(Parser.programStrToList(programStr));
				
				final SelfEvaluating result = RawExpressionTranslator
					.translateToExpression(rawExpression)
					.eval(env);
				
				System.out.println(result);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
	
	private static void addPrimitiveFunctionsToEnv(final Environment env) {
		final Variable[] vars = new Variable[] { new Variable("x"), new Variable("y") };
		final Add add = new Add(vars , null, env);
		final Multiply multiply = new Multiply(vars, null, env);
		
		env.addMut(new Variable("+"), add);
		env.addMut(new Variable("*"), multiply);
	}
}
