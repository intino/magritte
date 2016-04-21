package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class SyncNativeWithTara extends PsiElementBaseIntentionAction {
	private static final String NATIVE_PACKAGE = "natives";


	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final PsiClass psiClass = TaraPsiImplUtil.getContainerByType(element, PsiClass.class);
		if (psiClass == null) return false;
		final PsiElement destiny = ReferenceManager.resolveJavaNativeImplementation(psiClass);
		return psiClass.getDocComment() != null && isAvailable(psiClass, getDSL(element)) && destiny != null && getValued(destiny) != null && getValued(destiny).values().get(0) instanceof Expression;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		PsiClass psiClass = TaraPsiImplUtil.getContainerByType(element, PsiClass.class);
		final PsiElement destiny = ReferenceManager.resolveJavaNativeImplementation(psiClass);
		Valued valued = getValued(destiny);
		if (valued == null) return;
		Value value = valued.getBodyValue() != null ? valued.getBodyValue() : valued.getValue();
		if (value == null || psiClass == null || psiClass.getMethods().length == 0 || psiClass.getAllMethods()[0].getBody() == null) return;
		final TaraExpression taraExpression = value instanceof TaraBodyValue ? ((TaraBodyValue) value).getExpression() : getTaraExpression((TaraValue) value);
		if (taraExpression == null) return;
		String body = psiClass.getAllMethods()[0].getBody().getText();
		body = body.substring(1, body.length() - 1);
		if (body.startsWith("return ")) body.substring("return ".length());
		taraExpression.updateText(body);
		updateImports(psiClass, valued);
		notify(project);
	}

	@Nullable
	public Valued getValued(PsiElement destiny) {
		Valued valued = findValuedScope(destiny);
		if (valued == null) return null;
		if (valued.getBodyValue() == null && valued.getValue() == null) return null;
		return valued;
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getDocComment() != null && psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof PsiJavaFile &&
			correctPackage(psiClass, dsl);
	}

	private boolean correctPackage(PsiClass psiClass, String dsl) {
		final Module module = ModuleProvider.getModuleOf(psiClass);
		final String packageName = ((PsiJavaFile) psiClass.getContainingFile()).getPackageName();
		return packageName.startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE) ||
			packageName.startsWith(Format.javaValidName().format(module.getName()).toString().toLowerCase() + '.' + NATIVE_PACKAGE);
	}

	private TaraExpression getTaraExpression(TaraValue value) {
		return value.getExpressionList().isEmpty() ? null : value.getExpressionList().get(0);
	}

	private void updateImports(PsiClass psiClass, Valued valued) {
		new Imports(valued.getProject()).save(ModuleProvider.getModuleOf(valued).getName() + (!TaraUtil.isDefinitionFile(valued.getContainingFile()) ? "_model" : ""), QualifiedNameFormatter.qnOf(valued), getImports(psiClass.getContainingFile()));
	}

	private Set<String> getImports(PsiFile file) {
		if (file == null) return Collections.emptySet();
		final PsiImportList importList = ((PsiJavaFile) file).getImportList();
		if (importList == null) return Collections.emptySet();
		return Arrays.asList(importList.getAllImportStatements()).stream().map(PsiElement::getText).collect(Collectors.toSet());
	}


	private Valued findValuedScope(PsiElement element) {
		return TaraPsiImplUtil.getContainerByType(element, Valued.class);
	}

	private String getDSL(@NotNull PsiElement element) {
		final Module module = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.outputDsl().isEmpty() ? module.getName() : configuration.outputDsl();
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Sync native with tara code";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	private void notify(Project project) {
		Notifications.Bus.notify(new Notification("Tara Language", "Synced successfully", "native", NotificationType.INFORMATION), project);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
