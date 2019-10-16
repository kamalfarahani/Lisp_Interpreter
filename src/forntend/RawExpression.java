package forntend;

public class RawExpression {
	final public String word;
	final public RawExpression[] expressions;
	
	public RawExpression(final String word, final RawExpression[] expressions) {
		this.word = word;
		this.expressions = expressions;
	}
	
	public String toString() {
		if (this.expressions == null) {
			return this.word;
		} else if (this.expressions.length == 0) {
			return "(" + this.word + ")";
		} else {
			String expsStr = "";
			for (int i = 0; i < this.expressions.length; i++) {
				expsStr += " " + this.expressions[i].toString();
			}
			
			return "(" + this.word + expsStr + ")";
		}
	}
}
