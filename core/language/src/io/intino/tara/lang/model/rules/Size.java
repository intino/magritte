package io.intino.tara.lang.model.rules;

import io.intino.tara.lang.model.Rule;

import java.util.Arrays;
import java.util.List;

public class Size implements Rule<List> {

	private Size into = null;
	private int min;
	private int max;

	public Size(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Size(int min, int max, Size into) {
		this.min = min;
		this.max = max;
		this.into = into;
	}

	public Size(Size rule) {
		this.min = rule.min();
		this.max = rule.max();
		if (rule.into() != null) this.into = new Size(rule.into().min(), rule.into().max());
	}

	public Size(Size is, Size into) {
		this.min = is.min();
		this.max = is.max();
		this.into = into;
	}

	public static Size MULTIPLE() {
		return new Size(0, Integer.MAX_VALUE);
	}

	public static Size SINGLE_REQUIRED() {
		return new Size(1, 1);
	}


	public int min() {
		return min;
	}

	public int max() {
		return max;
	}

	public Size into() {
		return into;
	}

	public boolean accept(List list) {
		return list.size() >= this.min && list.size() <= max;
	}

	@Override
	public String errorMessage() {
		return min() == 0 && max() == 0 ? "reject.type.not.exists2" : "reject.element.not.in.range";
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

	public Size is() {
		return this;
	}

	public void is(Size rule) {
		this.min = rule.min();
		this.max = rule.max();
	}

	public void into(Size rule) {
		into = rule;
	}
}
