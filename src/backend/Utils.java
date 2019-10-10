package backend;
import java.util.Arrays;


public class Utils {
	public static SelfEvaluating evalSequence(final Expression[] exps, final Environment env) {
		if (exps.length == 1) {
			return exps[0].eval(env);
		}
		
		exps[0].eval(env);
		
		return evalSequence(
				Arrays.copyOfRange(exps, 1, exps.length), env);
	}
}
