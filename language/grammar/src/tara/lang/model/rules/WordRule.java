package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class WordRule implements Rule<String> {


	private List<String> words = new ArrayList<>();

	public WordRule(List<String> words) {
		this.words = words;
	}

	public WordRule() {
	}

	public List<String> words() {
		return words;
	}

	@Override
	public boolean accept(String value) {
		return false;
	}
}
