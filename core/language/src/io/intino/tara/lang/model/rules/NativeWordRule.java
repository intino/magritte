package io.intino.tara.lang.model.rules;

import io.intino.tara.lang.model.rules.variable.NativeRule;

import java.util.Collections;
import java.util.List;

public class NativeWordRule extends NativeRule {

	private List<String> words;

	public NativeWordRule(List<String> words) {
		super("", "", Collections.emptyList());
		this.words = words;
	}

	public List<String> words() {
		return words;
	}


	@Override
	public String toString() {
		return "NativeWordRule{" + String.join(",", words) + '}';
	}
}
