package siani.tara.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word extends Variable {
	public List<String> wordTypes;

	public Word(String name, boolean isTerminal) {
		this.name = name;
		this.isTerminal = isTerminal;
		this.wordTypes = new ArrayList<>();
	}

	public Word(String name) {
		this.name = name;
		this.wordTypes = new ArrayList<>();
	}

	public List<String> getWordTypes() {
		return wordTypes;
	}

	public void add(String word) {
		wordTypes.add(word);
	}

	public boolean contains(String word) {
		return wordTypes.contains(word);
	}

	@Override
	public String getType() {
		return toString();
	}

	@Override
	public boolean isList() {
		return false;
	}

	public String toString() {
		return name + " -> " + Arrays.toString(wordTypes.toArray());
	}

	@Override
	public Word clone() {
		Word word = new Word(name, isTerminal);
		for (String wordType : wordTypes) word.add(wordType);
		word.setProperty(isProperty);
		word.setUniversal(isUniversal);
		word.setDefaultValues(defaultValues);
		if (values != null)
			for (Object value : values) word.addValue(value);
		return word;
	}
}
