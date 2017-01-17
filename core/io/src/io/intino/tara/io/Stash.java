package io.intino.tara.io;

import java.util.ArrayList;
import java.util.List;

public class Stash {
	public String language;
	public List<String> uses = new ArrayList<>();
	public List<Concept.Content> contentRules = new ArrayList<>();
	public List<Concept> concepts = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();
}