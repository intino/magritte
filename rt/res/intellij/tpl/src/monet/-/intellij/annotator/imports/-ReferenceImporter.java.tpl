package monet.::projectName::.intellij.annotator.imports;

import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::ReferenceImporter {
	\@NotNull
	public static List<ImportQuickFix> proposeImportFix(final PsiElement node) {
		List<Definition> definitions = ::projectProperName::Util.findRootDefinition(node.getProject(), node.getText());
		ArrayList<ImportQuickFix> quickFixes = new ArrayList<>();
		if (definitions.isEmpty()) return Collections.EMPTY_LIST;
		for (Definition definition \: definitions) quickFixes.add(new ImportQuickFix((::projectProperName::File) node.getContainingFile(),definition));
		return quickFixes;
	}
}