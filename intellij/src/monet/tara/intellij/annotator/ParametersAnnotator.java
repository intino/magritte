package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.MetaIdentifier;
import monet.tara.intellij.lang.psi.Parameters;
import monet.tara.intellij.lang.psi.Signature;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.NodeAttribute;
import monet.tara.lang.Reference;
import monet.tara.lang.Variable;
import org.jetbrains.annotations.NotNull;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element) || TaraLanguage.getHeritage() == null) return;
		Signature signature = (Signature) element;
		MetaIdentifier metaIdentifier = signature.getType();
		AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(signature, Parameters.class);
		if (parameters == null && !node.getVariables().isEmpty()) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(node));
			errorAnnotation.registerFix(new AddParametersFix(signature, node.getVariables()));
		}
	}

	private String variablesToString(AbstractNode node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) {
			builder.append(", ");
			if (variable instanceof NodeAttribute)
				builder.append(((NodeAttribute) variable).getPrimitiveType()).append(" ");
			if (variable instanceof Reference)
				builder.append(((Reference) variable).getNode()).append(" ");
			builder.append(variable.getName());
		}
		return builder.toString().substring(2);
	}
}
