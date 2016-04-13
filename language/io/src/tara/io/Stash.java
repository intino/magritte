package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Stash {
	public String language;
	public int platformRefactorId = -1;
	public int applicationRefactorId = -1;
	public List<String> uses = new ArrayList<>();
	public List<Concept.Content> contentRules = new ArrayList<>();
	public List<Concept> concepts = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();
	public List<Prototype> prototypes = new ArrayList<>();
}