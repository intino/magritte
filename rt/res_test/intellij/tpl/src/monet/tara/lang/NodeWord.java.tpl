package monet.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class NodeWord extends Variable {
	public List<String> wordTypes;

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

	\@Override
	public boolean isList() {
		return false;
	}

	\@Override
	public boolean isProperty() {
		return false;
	}
}
