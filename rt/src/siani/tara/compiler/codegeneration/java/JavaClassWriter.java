package siani.tara.compiler.codegeneration.java;

import siani.tara.compiler.codegeneration.CodeGenerator;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Node;

import java.io.File;
import java.io.PrintWriter;

public class JavaClassWriter extends CodeGenerator {
	private final Node node;

	public JavaClassWriter(Node node) {
		this.node = node;
	}

	public void write(File outDirectory) throws TaraException {
		String nodePath = toClassName(node.getQualifiedName());
		ClassRender render = new ClassRender(node);
		String output = render.getOutput();
		PrintWriter outWriter = getOutWriter(new File(outDirectory.getAbsolutePath(),  nodePath));
		outWriter.write(output);
		outWriter.close();
	}


	private String toClassName(String s) {
		String path = s.replaceAll("\\.", File.separator);
		return path + ".java";

	}
}
