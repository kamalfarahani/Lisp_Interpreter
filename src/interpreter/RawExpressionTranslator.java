package interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import backend.Application;
import backend.Begin;
import backend.Definition;
import backend.Expression;
import backend.If;
import backend.Lambda;
import backend.LispBool;
import backend.LispInteger;
import backend.Variable;
import forntend.RawExpression;

public class RawExpressionTranslator {
	
	private static final HashMap<String, Function<RawExpression, Expression>> wordToTranslator;
	static {
		wordToTranslator = new HashMap<String, Function<RawExpression,Expression>>();
		wordToTranslator.put("define", RawExpressionTranslator::translateDefinition);
		wordToTranslator.put("begin", RawExpressionTranslator::translateBegin);
		wordToTranslator.put("lambda", RawExpressionTranslator::translateLambda);
		wordToTranslator.put("if", RawExpressionTranslator::translateIf);
	}
	
	public static Expression translateToExpression(final RawExpression rawExpression) {
		final Function<RawExpression, Expression> translator =
				wordToTranslator.get(rawExpression.word);
		
		if (translator != null) {
			return translator.apply(rawExpression);
		}
		
		if (rawExpression.expressions != null) {
			return translateApplication(rawExpression);
		}
		
		if (isBool(rawExpression)) {
			return translateLispBool(rawExpression);
		}
		
		if (isInt(rawExpression)) {
			return translateLispInteger(rawExpression);
		}
		
		return translateVariable(rawExpression);
	}
	
	private static Begin translateBegin(final RawExpression rawExpression) {
		final Expression[] exps = Arrays
			.stream(rawExpression.expressions)
			.map(raw -> translateToExpression(raw))
			.toArray(Expression[]::new);
		
		return new Begin(exps);
	}
	
	private static Lambda translateLambda(final RawExpression rawExpression) {
		final Variable[] params = new Variable[rawExpression.expressions[0].expressions.length + 1];
		final Expression[] body = new Expression[rawExpression.expressions.length - 1];
		
		params[0] = translateVariable(rawExpression.expressions[0]);
		for (int i = 1; i < params.length; i++) {
			params[i] = translateVariable(rawExpression.expressions[0].expressions[i]);
		}
		
		for (int i = 0; i < body.length; i++) {
			body[i] = translateToExpression(rawExpression.expressions[i + 1]);
		}
		
		return new Lambda(params, body);
	}
	
	private static Application translateApplication(final RawExpression rawExpression) {
		final Variable operator = new Variable(rawExpression.word);
		final Expression[] operands = Arrays
				.stream(rawExpression.expressions)
				.map(RawExpressionTranslator::translateToExpression)
				.toArray(Expression[]::new);
		
		return new Application(operator, operands);
	}
	
	private static If translateIf(final RawExpression rawExpression) {
		final Expression predicate = translateToExpression(rawExpression.expressions[0]);
		final Expression consequent = translateToExpression(rawExpression.expressions[1]);
		final Expression alternative = translateToExpression(rawExpression.expressions[2]);
		
		return new If(predicate, consequent, alternative);
	}
	
	private static Variable translateVariable(final RawExpression rawExpression) {
		return new Variable(rawExpression.word);
	}
	
	private static Definition translateDefinition(final RawExpression rawExpression) {
		final Variable variable = new Variable(rawExpression.expressions[0].word);
		final Expression value =  translateToExpression(rawExpression.expressions[1]);
		
		return new Definition(variable, value);
	}
	
	private static LispInteger translateLispInteger(final RawExpression rawExpression) {
		return new LispInteger(Integer.parseInt(rawExpression.word));
	}
	
	private static LispBool translateLispBool(final RawExpression rawExpression) {
		return new LispBool(Boolean.parseBoolean(rawExpression.word));
	}
	
	private static boolean isBool(final RawExpression rawExpression) {
		if (rawExpression.word.equals("true") || rawExpression.word.equals("false"))
			return true;
		
		return false;
	}
	
	private static boolean isInt(final RawExpression rawExpression) {
		try {
			Integer.parseInt(rawExpression.word);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
