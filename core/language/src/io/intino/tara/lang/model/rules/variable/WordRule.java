package io.intino.tara.lang.model.rules.variable;

import io.intino.tara.lang.model.Primitive;

import java.util.Collections;
import java.util.List;

public class WordRule implements VariableRule<List<Primitive.Reference>> {

	private static final String REJECT_INVALID_WORD_VALUES = "reject.invalid.word.values";
	private List<Object> parameters;
	private List<String> words;
	private String aClass;
	private String message = REJECT_INVALID_WORD_VALUES;

	public WordRule(List<String> words) {
		this.words = words;
		this.aClass = null;
		parameters = Collections.singletonList(String.join(", ", words));
	}

	public WordRule(List<String> words, String aClass) {
		this.words = words;
		this.aClass = aClass;
		parameters = Collections.singletonList(String.join(", ", words));
	}

	public List<String> words() {
		return words;
	}

	public boolean isCustom() {
		return aClass != null;
	}

	public String externalClass() {
		return aClass;
	}

	@Override
	public boolean accept(List<Primitive.Reference> references) {
		for (Primitive.Reference reference : references)
			if (!words.contains(reference.get())) return false;
		return true;
	}

	@Override
	public boolean accept(List<Primitive.Reference> references, String metric) {
		return accept(references);
	}

	@Override
	public String errorMessage() {
		return message;
	}

	@Override
	public List<Object> errorParameters() {
		return parameters;
	}
}
