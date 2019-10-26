package backend;

public interface Equalable extends SelfEvaluating {
	LispBool isEqual(final Equalable other);
}
