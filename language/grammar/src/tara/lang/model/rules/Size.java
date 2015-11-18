package tara.lang.model.rules;

import java.util.Arrays;
import java.util.List;

public class Size implements CompositionRule {

	public static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);
	public static Size MULTIPLE_REQUIRED = new Size(1, Integer.MAX_VALUE);
	public static Size REQUIRED = new Size(1, Integer.MAX_VALUE);
	public static Size SINGLE_REQUIRED = new Size(1, 1);

	private CompositionRule into = null;
	private int min;
	private int max;

	public Size(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Size(int min, int max, CompositionRule into) {
		this.min = min;
		this.max = max;
		this.into = into;
	}


	public int min() {
		return min;
	}

	public int max() {
		return max;
	}

	public CompositionRule into() {
		return into;
	}

	public boolean accept(int min, int max) {
		return min >= this.min && max <= this.max;
	}


	public boolean accept(List list) {
		return list.size() >= this.min && list.size() <= max;
	}

	@Override
	public String errorMessage() {
		return "reject.parameter.not.in.range";
	}

	@Override
	public List<Object> errorParameters() {
		return Arrays.asList(min + "", max == Integer.MAX_VALUE ? "n" : max + "");
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Size && ((Size) obj).min() == this.min && ((Size) obj).max() == this.max;
	}

	public boolean isRequired() {
		return min > 0;
	}

	public boolean isSingle() {
		return max == 1;
	}
}