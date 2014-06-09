package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.Signature;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;
import org.jetbrains.annotations.NotNull;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element) || TaraLanguage.getHeritage() == null) return;
		Signature signature = (Signature) element;
		MetaIdentifier metaIdentifier = signature.getType();
		if (metaIdentifier == null) return;
		NodeObject node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0).getObject();
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(signature, Parameters.class);
		if (parameters == null && !node.getVariables().isEmpty() || (parameters != null) &&
			parameters[0].getParameters().length != node.getVariables().size()) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(node));
			errorAnnotation.registerFix(new AddParametersFix(signature, node.getVariables()));
		}
	}

	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
