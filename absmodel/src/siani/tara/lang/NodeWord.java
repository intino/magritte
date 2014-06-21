package siani.tara.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeWord extends Variable {
	public List<String> wordTypes;

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
	public boolean isMultiple() {
		return false;
	}

	public String toString() {
		return name + " -> " + Arrays.toString(wordTypes.toArray());
	}

	@Override
	public NodeWord clone() {
		NodeWord nodeWord = new NodeWord(name, isTerminal);
		for (String wordType : wordTypes) nodeWord.add(wordType);
		return nodeWord;
	}
}
