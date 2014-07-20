package siani.tara.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeWord extends Variable {
	public List<String> wordTypes;
	public short defaultWord = -1;
	public Short value = null;

	public NodeWord(String name, boolean isTerminal) {
		this.name = name;
		this.isTerminal = isTerminal;
		this.wordTypes = new ArrayList<>();
	}

	public NodeWord(String name) {
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
	public String getValue() {
		return (value != null) ? value.toString() : null;
	}

	@Override
	public boolean isList() {
		return false;
	}

	public int getDefaultWord() {
		return defaultWord;
	}

	public void setDefaultWord(short defaultWord) {
		this.defaultWord = defaultWord;
	}

	public String toString() {
		return name + " -> " + Arrays.toString(wordTypes.toArray());
	}

	@Override
	public NodeWord clone() {
		NodeWord nodeWord = new NodeWord(name, isTerminal);
		for (String wordType : wordTypes) nodeWord.add(wordType);
		nodeWord.setProperty(isProperty);
		return nodeWord;
	}
}
