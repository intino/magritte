package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.annotator.fix.RemoveDefinitionFix;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

public class DefinitionAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Definition)
			isDuplicated((Definition) element);
	}

	private void isDuplicated(Definition definition) {
		if (definition.getIdentifierNode() != null && ::projectProperName::Util.findDuplicates(definition.getProject(), definition) != 1)
			annotateAndFix(definition.getIdentifierNode(), new RemoveDefinitionFix(definition), ::projectProperName::Bundle.message("duplicate.definition.key.error.message"));
	}
}
