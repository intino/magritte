package monet.tara.transpiler.intellij.plugingeneration;

import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import monet.tara.transpiler.RendersFactory;
import monet.tara.transpiler.TemplateFactory;
import monet.tara.transpiler.intellij.metamodel.file.TaraFile;
import monet.tara.transpiler.metamodel.metamodeldescription.Definition;
import monet.tara.transpiler.metamodel.metamodeldescription.MetamodelTranslator;
import monet.tara.transpiler.metamodel.render.DefinitionRender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class TaraToJavaGenerator {

	public static void toJava(Project project, TaraFile[] taraFiles, String outputPath, RunContentDescriptor console) throws IOException {
		Definition[] rootDefinitions = translateMetamodel(taraFiles);
		for (Definition rootDefinition : rootDefinitions) {
			String templatePath = TemplateFactory.getTemplate("Definition").replace("tara", project.getName().toLowerCase());
			writeTemplateBasedFile(openGeneratedFileOutput(new File(outputPath + templatePath + rootDefinition.getName() + ".java")), getRender(project, taraFiles));
		}
	}

	private static DefinitionRender getRender(Project project, TaraFile[] taraFiles) {
		return (DefinitionRender) RendersFactory.getRender("Definition", project.getName(), translateMetamodel(taraFiles));
	}

	private static Definition[] translateMetamodel(TaraFile[] taraFiles) {
		return MetamodelTranslator.translate(taraFiles);
	}

	private static void writeTemplateBasedFile(PrintWriter printWriter, DefinitionRender render) {
		printWriter.print(render.getOutput());
	}

	private static PrintWriter openGeneratedFileOutput(File file) throws IOException {
		file.getParentFile().mkdirs();
		file.createNewFile();
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
		return printWriter;
	}
}
