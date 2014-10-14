package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraVarInit;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Reference;
import siani.tara.lang.Variable;

import static siani.tara.lang.Primitives.*;

public class VarInitAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraVarInit.class.isInstance(element)) return;
		VarInit varInit = (VarInit) element;
		Model model = TaraLanguage.getMetaModel(element.getContainingFile());
		Concept concept = TaraPsiImplUtil.getConceptContextOf(element);
		if (concept == null) return;
		Node node = findNode(concept, model);
		if (node == null) return;
		Variable variable = null;
		for (Variable var : node.getObject().getVariables())
			if (var.getName().equals(varInit.getName()))
				variable = var;
		if (variable == null) {
			holder.createErrorAnnotation(element.getNode(), "Variable not found");
			return;
		}
		String valueType = varInit.getValueType();
		if (!valueType.equalsIgnoreCase(variable.getType())
			&& !(valueType.equals(NATURAL) && variable.getType().equals(INTEGER))
			&& !(valueType.equals(REFERENCE) && variable instanceof Reference))
			holder.createErrorAnnotation(element.getNode(), "Incompatible types. Found " +
				valueType + ". " + variable.getType() + " expected");
	}
}
