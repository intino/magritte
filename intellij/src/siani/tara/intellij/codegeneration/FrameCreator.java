package siani.tara.intellij.codegeneration;

import com.intellij.psi.PsiElement;
import org.siani.itrules.Frame;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FrameCreator {

	public static Frame create(TaraBoxFile box) {
		Frame frame = new Frame("box");
		frame.addSlot("import", getImports(box));
		for (final Concept concept : box.getConcepts())
			add(concept, frame);
		return frame;
	}

	private static void add(final Concept concept, Frame frame) {
		final Frame newFrame = new Frame(getTypes(concept));
		frame.addSlot("Concept", newFrame);
		newFrame.addSlot("Doc", concept.getDocCommentText());
		for (final Variable variable : concept.getVariables())
			newFrame.addSlot("variables", new Frame(getTypes(variable)) {{
				addSlot("Name", variable.getName());
				addSlot("Type", variable.getType());
				addSlot("DefaultValues", variable.getDefaultValuesAsString());
			}});
		for (final Parameter parameter : concept.getParameters())
			newFrame.addSlot("parameters", new Frame("parameter") {{
				if (parameter.isExplicit())
					addSlot("Name", ((TaraExplicitParameter) parameter).getIdentifier());
				addSlot("value", parameter.getValue().getText());
			}});
		for (final TaraFacetTarget target : concept.getFacetTargets())
			newFrame.addSlot("targets", new Frame("target") {{
				addSlot("Destiny", target.getIdentifierReference().getText());
			}});
		for (Concept inner : concept.getInnerConcepts()) add(inner, newFrame);
		for (Concept sub : concept.getSubConcepts()) add(sub, frame);
	}

	private static String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		for (String annotation : variable.getAnnotations())
			list.add(annotation);
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(Concept concept) {
		List<String> list = new ArrayList<>();
		if (concept.isFacet()) list.add("facet");
		if (concept.isIntention()) list.add("intention");
		for (PsiElement element : concept.getAnnotations())
			list.add(element.getText());
		return list.toArray(new String[list.size()]);
	}

	private static Collection<String> getImports(TaraBoxFile box) {
		List<String> imports = new ArrayList<>();
		for (Import anImport : box.getImports())
			if (!anImport.isMetamodelImport())
				imports.add(anImport.getHeaderReference().getText());
		return imports;
	}
}
