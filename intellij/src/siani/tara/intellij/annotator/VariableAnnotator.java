package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.Contract;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.module.ModuleProvider;

public class VariableAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String NATIVES = "natives";
	private static final String METRICS = "metrics";

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		holder = annotationHolder;
		if (psiElement instanceof Variable) {
			Variable variable = (Variable) psiElement;
			if (!TaraPrimitives.NATIVE.equals(variable.getType()))
				return;
			if (analyzeJavaClassCreation(variable))
				holder.createErrorAnnotation(variable, MessageProvider.message("no.java.generated.class"));
		}
	}

	private boolean analyzeJavaClassCreation(Variable variable) {
		return !isCreated(nativeClass(variable.getContract(), variable.getType()), variable.getProject());
	}

	private boolean isCreated(String qn, Project project) {
		return ReferenceManager.resolveJavaClassReference(project, qn) != null;
	}

	private String nativeClass(Contract contract, String type) {
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract));
		return taraFacetByModule == null ? "" :
			taraFacetByModule.getConfiguration().getGeneratedDslName().toLowerCase() + DOT + (type.equals(TaraPrimitives.NATIVE) ? NATIVES : METRICS) + DOT + contract.getFormattedName();
	}
}
