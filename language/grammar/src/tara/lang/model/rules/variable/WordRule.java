package tara.lang.model.rules.variable;

import tara.lang.model.Primitive.Reference;
import tara.lang.model.Rule;
import tara.lang.model.rules.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordRule implements Rule<List<Reference>> {

	private static final String REJECT_INVALID_WORD_VALUES = "reject.invalid.word.values";
	private List<Object> parameters;
	private List<String> words = new ArrayList<>();
	private final Size size;
	private String externalWordClass;
	private String message = REJECT_INVALID_WORD_VALUES;

	public WordRule(List<String> words, Size size) {
		this.words = words;
		this.size = size;
		this.externalWordClass = null;
		parameters = Collections.singletonList(String.join(", ", words));
	}

	public WordRule(List<String> words, String externalWordClass, Size size) {
		this.words = words;
		this.externalWordClass = externalWordClass;
		this.size = size;
		parameters = Collections.singletonList(String.join(", ", words));
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
	public boolean accept(List<Reference> references) {
		for (Reference reference : references)
			if (!words.contains(reference.get())) return false;
		return true;
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
