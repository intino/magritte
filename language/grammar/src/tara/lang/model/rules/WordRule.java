package tara.lang.model.rules;

import tara.lang.model.Primitive.Reference;
import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class WordRule implements Rule<Reference> {

	private List<String> words = new ArrayList<>();
	private boolean custom;

	public WordRule(List<String> words) {
		this.words = words;
		this.custom = false;
	}

	public WordRule(List<String> words, boolean custom) {
		this.words = words;
		this.custom = custom;
	}

	public List<String> words() {
		return words;
	}

	public boolean isCustom() {
		return custom;
	}

	@Override
	public boolean accept(Reference value) {
		return words.contains(value.get());
	}

	@Override
	public String errorMessage() {
		return "Invalid value. Expected one of these: " + String.join(", ", words);
	}
}
