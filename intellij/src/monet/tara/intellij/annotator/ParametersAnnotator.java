package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.MetaIdentifier;
import monet.tara.intellij.lang.psi.Parameters;
import monet.tara.intellij.lang.psi.Signature;
import monet.tara.lang.AbstractNode;
import org.jetbrains.annotations.NotNull;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element)) return;
		Signature signature = (Signature) element;
		MetaIdentifier metaIdentifier = signature.getType();
		AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
		Parameters parameters = PsiTreeUtil.getChildrenOfType(signature, Parameters.class)[0];
		if (parameters == null && !node.getVariables().isEmpty())
			holder.createErrorAnnotation(element, "parameters missed");
	}
}
