package siani.tara.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word extends Variable {
	private List<String> wordTypes;

	public Word(String name) {
		this(name, false);
	}

	public Word(String name, boolean inherited) {
		super(inherited);
		this.name = name;
		this.wordTypes = new ArrayList<>();
	}

	public List<String> getWordTypes() {
		return wordTypes;
	}

	public void setWordTypes(List<String> wordTypes) {
		this.wordTypes = wordTypes;
	}

	public void add(String word) {
		wordTypes.add(word);
	}

	public boolean contains(String word) {
		return wordTypes.contains(word);
	}

	public int indexOf(String word) {
		return wordTypes.indexOf(word);
	}

	@Override
	public String getType() {
		return getName();
	}

	public String toString() {
		return name + " -> " + Arrays.toString(wordTypes.toArray());
	}

	@Override
	public Word clone() {
		Word word = new Word(name);
		for (String wordType : wordTypes) word.add(wordType);
		for (Annotation annotation : annotations) word.add(annotation);
		word.setDefaultValues(defaultValues);
		if (values != null)
			for (Object value : values) word.addValue(value);
		return word;
	}
}
