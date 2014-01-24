package monet.tara.transpiler.metamodel.metamodeldescription;

import monet.tara.transpiler.intellij.metamodel.file.TaraFile;
import monet.tara.transpiler.intellij.psi.TaraConcept;
import monet.tara.transpiler.intellij.psi.TaraConceptConstituents;
import monet.tara.transpiler.intellij.psi.TaraConceptSignature;
import monet.tara.transpiler.intellij.psi.impl.TaraUtil;

import java.util.ArrayList;
import java.util.List;

public class MetamodelTranslator {

	public static Definition[] translate(TaraFile[] taraFiles) {
		ArrayList<Definition> definitions = new ArrayList<>();
		for (TaraFile taraFile : taraFiles)
			definitions.addAll(translateConcepts(TaraUtil.getConcepts(taraFile.getProject())));
		return definitions.toArray(new Definition[definitions.size()]);
	}

	private static ArrayList<Definition> translateConcepts(List<TaraConcept> concepts) {
		ArrayList<Definition> definitions = new ArrayList<>();
		for (TaraConcept concept : concepts) {
			Definition definition = translateSignature(concept.getConceptSignature(), concept.getName());
			translateBody(concept.getConceptBody().getConceptConstituentsList(),definition);
			definitions.add(definition);
		}
		return definitions;
	}

	private static Definition translateBody(List<TaraConceptConstituents> constituents, Definition definition) {
		for (TaraConceptConstituents constituent : constituents) {
		}

		return null;
	}

	private static Definition translateSignature(TaraConceptSignature conceptSignature, String name) {
		Definition definition = new Definition(name);
		if (conceptSignature.getModifier() != null)
			if (conceptSignature.getModifier().getText().equals("abstract"))
				definition.setAbstractModifier();
			else definition.setFinalModifier();
		if (conceptSignature.getConceptAnnotation() != null)
			definition.add(Definition.ExtensionType.valueOf(conceptSignature.getConceptAnnotation().getText()));
		return definition;
	}
}
