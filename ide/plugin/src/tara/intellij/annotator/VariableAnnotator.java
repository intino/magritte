package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Primitives;
import tara.language.model.Variable;

public class VariableAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String NATIVES = "natives";
	private static final String METRICS = "metrics";

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		holder = annotationHolder;
		if (psiElement instanceof Variable) {
			TaraVariable variable = (TaraVariable) psiElement;
			if (!Primitives.NATIVE.equals(variable.type()))
				return;
			if (variable.getAttributeType() != null && variable.getAttributeType().getContract() != null && hasCorrespondingJavaClass(variable))
				holder.createErrorAnnotation(variable, MessageProvider.message("no.java.generated.class"));
		}
	}

	private boolean hasCorrespondingJavaClass(TaraVariable variable) {
		return !isCreated(nativeClass(variable.getAttributeType().getContract(), variable.type()), variable.getProject());
	}

	private boolean isCreated(String qn, Project project) {
		return ReferenceManager.resolveJavaClassReference(project, qn) != null;
	}

	private String nativeClass(Contract contract, String type) {
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract));
		return taraFacetByModule == null ? "" :
			taraFacetByModule.getConfiguration().getGeneratedDslName().toLowerCase() + DOT + (type.equals(Primitives.NATIVE) ? NATIVES : METRICS) + DOT + contract.getFormattedName();
	}
}
