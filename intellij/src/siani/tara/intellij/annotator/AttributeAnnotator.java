package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.fix.RemoveAttributeFix;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.List;

public class AttributeAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Variable)
			checkDuplicated((Variable) element);
	}

	private void checkDuplicated(Variable variable) {
		if (findAttributeDuplicates(variable).length != 1)
			annotateAndFix(variable, new RemoveAttributeFix(variable), MessageProvider.message("duplicate.attribute.key.error.message"));
	}

	@NotNull
	private Variable[] findAttributeDuplicates(Variable variable) {
		List<Variable> result = new ArrayList<>();
		List<Variable> variables = TaraPsiImplUtil.getVariablesInBody((Body) variable.getParent());
		for (Variable taraVariable : variables)
			if (taraVariable.getName() != null && taraVariable.getName().equals(variable.getName()))
				result.add(taraVariable);
		return result.toArray(new Variable[result.size()]);
	}

}
