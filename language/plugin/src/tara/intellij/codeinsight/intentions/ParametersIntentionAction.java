package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Parameters;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public abstract class ParametersIntentionAction extends PsiElementBaseIntentionAction implements IntentionAction {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		PsiElement parametersScope = getParametersScope(element);
		return element.isWritable() && parametersScope != null && ((Parameters) parametersScope).areExplicit();
	}

	PsiElement getParametersScope(PsiElement element) {
		PsiElement parent = element.getParent();
		while (parent != null && !PsiFile.class.isInstance(parent) && !Node.class.isInstance(parent)) {
			if (parent instanceof Parameters) return parent;
			parent = parent.getParent();
		}
		return null;
	}

	String getContextNameOf(TaraFacetApply inFacet) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(inFacet);
		if (contextOf instanceof TaraFacetApply)
			return contextOf.getFirstChild().getText();
		if (contextOf instanceof Node) return ((Node) contextOf).getType();
		return null;
	}

//	List<Variable> getAllowedFacet(Node node, String name, String context) {
//		FacetTarget target = node.getObject().getAllowedFacetByContext(name, context);
//		return target != null ? target.getVariables() : null;
//	}


	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}