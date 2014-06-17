package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.codegeneration.render.DefinitionTemplateRender;
import siani.tara.compiler.codegeneration.render.RenderUtils;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;
import siani.tara.lang.TreeWrapper;

import java.io.File;
import java.io.PrintWriter;

public class TemplateGenerator extends CodeGenerator {

	public static void generateDefinitionTemplates(CompilerConfiguration conf, TreeWrapper ast) throws TaraException {
		String destiny = PathManager.getSourceResIdeDir(conf.getTempDirectory()) + "fileTemplates" + PathManager.SEP + "j2ee" + PathManager.SEP;
			for (Node node : ast.getNodeTable().values())
				if (node.getObject().is(NodeObject.AnnotationType.ROOT) && !node.getName().equals("") && !node.getObject().isAbstract()) {
					writeTemplate(destiny + RenderUtils.toProperCase(conf.getProject()) + RenderUtils.toProperCase(node.getName()) + ".m1.ft", createTemplate(node));
				}
	}

	private static String createTemplate(Node node) throws TaraException {
		String definition = "Definition";
		DefinitionTemplateRender render = new DefinitionTemplateRender(definition, node);
		return render.getOutput();
	}

	private static void writeTemplate(String name, String output) throws TaraException {
		File file = new File(name);
		PrintWriter writer = getOutWriter(file);
		writer.print(output);
		writer.close();
	}

}
