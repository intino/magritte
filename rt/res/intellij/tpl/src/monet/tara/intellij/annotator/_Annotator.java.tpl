package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.annotator.fix.RenameDefinitionFix;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

public class DefinitionAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Definition) {
			isDuplicated((Definition) element);
			if (element.getParent() instanceof ::projectProperName::File)
				isNameEqualsFileName((Definition) element);
		}
	}

	private void isNameEqualsFileName(Definition definition) {
		if (definition.getIdentifierNode() != null && !definition.getIdentifierNode().getText().equals(definition.getFile().getPresentableName()))

			annotateAndFix(definition.getIdentifierNode(), new RenameDefinitionFix(definition), ::projectProperName::Bundle.message("prime.definition.name.different.file.error.message"));
	}

	private void isDuplicated(Definition definition) {
		if (definition.getIdentifierNode() != null && ::projectProperName::Util.findDuplicates(definition.getProject(), definition) != 1)
			annotateAndFix(definition.getIdentifierNode(), new RenameDefinitionFix(definition), ::projectProperName::Bundle.message("duplicate.definition.key.error.message"));
	}
}
