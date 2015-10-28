package tara.lang.model.rules;

import tara.lang.model.Primitive.Reference;
import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordRule implements Rule<Reference> {

	private List<String> words = new ArrayList<>();
	private String externalWordClass;

	public WordRule(List<String> words) {
		this.words = words;
		this.externalWordClass = null;
	}

	public WordRule(List<String> words, String externalWordClass) {
		this.words = words;
		this.externalWordClass = externalWordClass;
	}

	public List<String> words() {
		return words;
	}

	public boolean isCustom() {
		return externalWordClass != null;
	}

	public String externalWordClass() {
		return externalWordClass;
	}

	@Override
	public boolean accept(Reference value) {
		return words.contains(value.get());
	}

	@Override
	public String errorMessage() {
		return "reject.invalid.word.values";
	}

	@Override
	public List<String> errorParameters() {
		return Collections.singletonList(String.join(", ", words));
	}
}
