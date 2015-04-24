package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.NativeName;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.project.module.ModuleProvider;

public class VariableAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String NATIVES = "natives";

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		if (psiElement instanceof Variable) {
			Variable variable = (Variable) psiElement;
			if (variable.getNativeName() == null) return;
			if (analyzeJavaClassCreation(variable))
				holder.createErrorAnnotation(variable, MessageProvider.message("no.java.generated.class"));
		}
	}

	private boolean analyzeJavaClassCreation(Variable variable) {
		return !isCreated(nativeClass(variable.getNativeName()), variable.getProject());
	}

	private boolean isCreated(String qn, Project project) {
		return ReferenceManager.resolveJavaClassReference(project, qn) != null;
	}

	private String nativeClass(NativeName nativeName) {
		return ModuleProvider.getModuleOf(nativeName).getName() + DOT + NATIVES + DOT + nativeName.getFormattedName();
	}
}
