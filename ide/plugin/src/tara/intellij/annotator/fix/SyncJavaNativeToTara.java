package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraExpression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

public class SyncJavaNativeToTara extends PsiElementBaseIntentionAction implements IntentionAction {
	private PsiClass psiClass;
	public static final String NATIVE_PACKAGE = "natives";


	public SyncJavaNativeToTara(PsiClass psiClass) {
		this.psiClass = psiClass;
	}

	public SyncJavaNativeToTara() {
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		if (!(element instanceof PsiClass)) return false;
		PsiClass psiClass = (PsiClass) element;
		return isAvailable(psiClass, getDSL(element)) && ReferenceManager.resolveJavaNativeImplementation(psiClass) != null;
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof PsiClass &&
			((PsiJavaFile) psiClass.getContainingFile()).getPackageName().startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE);
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		psiClass = TaraPsiImplUtil.getContainerByType(element, PsiClass.class);
		final PsiElement destiny = ReferenceManager.resolveJavaNativeImplementation(psiClass);
		Valued valued = findValuedScope(destiny);
		if (valued == null || valued.getValue() == null || valued.getValue().getExpressionList().isEmpty() || psiClass.getAllMethods().length == 0 || psiClass.getAllMethods()[0].getBody() == null)
			return;
		final TaraExpression taraExpression = valued.getValue().getExpressionList().get(0);
		String body = psiClass.getAllMethods()[0].getBody().getText();
		body = body.substring(1, body.length() - 1);
		if (body.startsWith("return ")) body.substring("return ".length());
		taraExpression.updateText(body);
		notify(project);
	}


	private Valued findValuedScope(PsiElement element) {
		return TaraPsiImplUtil.getContainerByType(element, Valued.class);
	}

	private String getDSL(@NotNull PsiElement element) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(element));
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.getGeneratedDslName();
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
