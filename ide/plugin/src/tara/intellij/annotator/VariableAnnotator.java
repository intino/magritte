package tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.fix.CreateRuleClassIntention;
import tara.intellij.annotator.fix.LinkToJavaIntention;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

public class VariableAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String NATIVES = "natives";
	private static final String RULES = "rules";

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		holder = annotationHolder;
		if (psiElement instanceof Variable) {
			TaraVariable variable = (TaraVariable) psiElement;
			if (!Primitive.NATIVE.equals(variable.type()))
				return;
			if (variable.getRuleContainer() != null && variable.getRuleContainer().getRule() != null && !hasCorrespondingJavaClass(variable)) {
				final Annotation errorAnnotation = holder.createErrorAnnotation(variable, MessageProvider.message("no.java.generated.class"));
				final IntentionAction correspondingFix = getCorrespondingFix(variable);
				if (correspondingFix != null) errorAnnotation.registerFix(correspondingFix);
			} else if (Primitive.NATIVE.equals(variable.type()) && !hasSignature(variable)) {
				holder.createErrorAnnotation(variable, MessageProvider.message("no.java.signature.found"));
			}
		}
	}

	private boolean hasSignature(TaraVariable variable) {
		final TaraRuleContainer ruleContainer = variable.getRuleContainer();
		if (ruleContainer == null) return false;
		final PsiClass psiClass = (PsiClass) ReferenceManager.resolveJavaClassReference(variable.getProject(), nativeClass(ruleContainer.getRule(), variable.type()));
		return psiClass != null && (psiClass.getMethods().length != 0);
	}

	private boolean hasCorrespondingJavaClass(TaraVariable variable) {
		return isCreated(variable.getProject(), nativeClass(variable.getRuleContainer().getRule(), variable.type()));
	}

	private boolean isCreated(Project project, String qn) {
		return ReferenceManager.resolveJavaClassReference(project, qn) != null;
	}

	private String nativeClass(Rule rule, Primitive type) {
		return getGeneratedDslName(rule).toLowerCase() + DOT + getPackage(type) + DOT + rule.getText();
	}

	private Object getPackage(Primitive type) {
		return type.equals(Primitive.NATIVE) ? NATIVES : RULES;
	}

	private IntentionAction getCorrespondingFix(TaraVariable variable) {
		if (Primitive.WORD.equals(variable.type()))
			return new CreateRuleClassIntention(variable.rule());
		else if (Primitive.NATIVE.equals(variable.type())) return new LinkToJavaIntention(variable);
		else return null;
	}

	private String getGeneratedDslName(PsiElement element) {
		final Module moduleOf = ModuleProvider.getModuleOf(element);
		if (moduleOf == null) return "";
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(moduleOf);
		if (facet == null) return moduleOf.getName();
		return facet.getConfiguration().getGeneratedDslName();
	}
}
