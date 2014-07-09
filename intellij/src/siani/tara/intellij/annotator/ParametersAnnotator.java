package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element)) return;
		Concept concept = TaraPsiImplUtil.getContextOf(element);
		Model heritage = TaraLanguage.getHeritage(((TaraFile)element.getContainingFile()).getParentModel());
		DeclaredNode node;
		if (heritage == null || (node = findNode(concept, heritage)) == null) return;
		NodeObject object = node.getObject();
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(element, Parameters.class);
		if (parameters == null && !object.getVariables().isEmpty() || (parameters != null) &&
			parameters[0].getParameters().length != object.getVariables().size()) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(object));
			errorAnnotation.registerFix(new AddParametersFix((Signature) element, object.getVariables()));
		}
	}

	private DeclaredNode findNode(Concept concept, Model tree) {
		String metaQualifiedName = TaraUtil.getMetaQualifiedName(concept);
		DeclaredNode node = tree.get(metaQualifiedName);
		return (node != null) ? node : tree.get(asAnonymous(metaQualifiedName));

	}

	private String asAnonymous(String name) {
		return name;
//		String subpath = name.substring(0, name.lastIndexOf("."));
//		return subpath + "." + "[" + name.substring(name.lastIndexOf(".") + 1) + "@annonymous]";
	}


	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
