package forntend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
	public static final String SYNTAX_ERROR_MSG = "Syntax Error";

	public static RawExpression parse(final List<String> exprList) {
		if (!exprList.get(0).equals("(")) {
			return new RawExpression(exprList.get(0), null);
		}
		
		final ArrayList<ArrayList<String>> listOfExprLists = 
				getListOfExprLists(exprList.subList(2, exprList.size() - 1));
		
		final RawExpression[] rawExpressions = 
				new RawExpression[listOfExprLists.size()];
		
		for (int i = 0; i < rawExpressions.length; i++) {
			rawExpressions[i] = parse(listOfExprLists.get(i));
		}
		
		return new RawExpression(exprList.get(1), rawExpressions);
	}
	
	public static ArrayList<String> programStrToList(final String programStr) {
		final String[] programArr = Arrays.stream(programStr
				.replace("(", " ( ")
				.replace(")", " ) ")
				.split(" ")).filter(str -> !str.equals("")).toArray(String[]::new);
		
		return new ArrayList<String>(Arrays.asList(programArr));
	}
	
	private static ArrayList<ArrayList<String>> getListOfExprLists(final List<String> exprs) {
		final ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < exprs.size(); i++) {
			if (!exprs.get(i).equals("(")) {
				final ArrayList<String> e = new ArrayList<String>();
				e.add(exprs.get(i));
				result.add(e);
			} else {
				final ArrayList<String> subExp = getSubExp(exprs, i);
				result.add(subExp);
				
				i = i + subExp.size() - 1;
			}
		}
		
		return result;
	}
	
	private static ArrayList<String> getSubExp(final List<String> exp, final int index) {
		if (!exp.get(index).equals("(")) {
			throw new RuntimeException(SYNTAX_ERROR_MSG);
		}
		
		int counter = 1;
		final ArrayList<String> result = new ArrayList<String>();
		result.add(exp.get(index));
		for (int i = index + 1; i < exp.size(); i++) {
			if (exp.get(i).equals("(")) counter++;
			if (exp.get(i).equals(")")) counter--;
			
			result.add(exp.get(i));
			
			if (counter == 0) break;
		}
		
		return result;
	}
	
}
