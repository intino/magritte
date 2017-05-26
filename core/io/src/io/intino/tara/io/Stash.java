package io.intino.tara.io;

import java.util.ArrayList;
import java.util.List;

import static io.intino.tara.io.Stash.Origin.Runtime;

public class Stash {
	public String language;
	public String path;
	public Origin origin = Runtime;
	public String builder;
	public List<String> uses = new ArrayList<>();
	public List<Concept.Content> contentRules = new ArrayList<>();
	public List<Concept> concepts = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();

	public enum Origin{
	    Platform, Product, Solution, Runtime
    }
}