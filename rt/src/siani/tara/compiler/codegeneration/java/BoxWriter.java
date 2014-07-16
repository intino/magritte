package siani.tara.compiler.codegeneration.java;

import siani.tara.compiler.codegeneration.CodeGenerator;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Node;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import static java.io.File.separator;

public class BoxWriter extends CodeGenerator {

	private final String boxDirectory = "magritte" + separator + "model" + separator;
	Set<Node> nodes = new HashSet<>();
	String name;

	public void write(File outDir) throws TaraException {
		String boxName = toClassName(name);
		BoxRender render = new BoxRender(nodes, boxName);
		String output = render.getOutput();
		PrintWriter outWriter = getOutWriter(new File(outDir.getAbsolutePath() + separator + boxDirectory, boxName + "Box.java"));
		outWriter.write(output);
		outWriter.close();
	}


	public boolean addNode(Node s) {
		return nodes.add(s);
	}


	public void setName(String name) {
		this.name = name;
	}

	private String toClassName(String s) {
		String[] split = s.split("\\.");
		String result = "";
		for (String word : split) result += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
		return result;

	}
}
