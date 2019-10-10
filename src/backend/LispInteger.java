package backend;


public class LispInteger implements Addable, Mulable {
	public final Integer value;
	
	public LispInteger(final Integer value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value.toString();
	}
	
	@Override
	public LispInteger eval(Environment env) {
		return this;
	}

	@Override
	public LispInteger add(Addable other) {
		return new LispInteger(this.value + ((LispInteger)other).value);
	}

	@Override
	public LispInteger multiply(Mulable other) {
		return new LispInteger(this.value * ((LispInteger)other).value);
	}

}
