package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.DefinitionTemplateRender;
import monet.tara.compiler.codegeneration.render.RenderUtils;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.Node;
import monet.tara.lang.NodeObject;
import monet.tara.lang.TreeWrapper;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class TemplateGenerator extends CodeGenerator {

	public static void generateDefinitionTemplates(CompilerConfiguration conf, TreeWrapper ast) throws TaraException {
		String destiny = PathManager.getSourceResIdeDir(conf.getTempDirectory()) + "fileTemplates" + PathManager.SEP + "j2ee" + PathManager.SEP;
		for (List<Node> nodes : ast.getNodeNameLookUpTable().values())
			for (Node node : nodes)
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
