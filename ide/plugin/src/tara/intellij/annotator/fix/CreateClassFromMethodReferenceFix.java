package tara.intellij.annotator.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.intentions.MethodReferenceCreator;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

public class CreateClassFromMethodReferenceFix extends ClassCreationIntention {
	private final Valued valued;

	private final Module module;
	private final TaraFacetConfiguration conf;
	private PsiDirectory destiny;

	public CreateClassFromMethodReferenceFix(Valued valued) {
		this.valued = valued;
		this.module = ModuleProvider.getModuleOf(valued);
		this.conf = TaraUtil.getFacetConfiguration(module);
		this.destiny = TaraUtil.findNativesDirectory(module, TaraUtil.outputDsl(valued));
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Create method reference";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile() instanceof TaraModel && destiny != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiMethod method = new MethodReferenceCreator(valued, element.getText()).create("");
		if (method != null) {
			QuickEditHandler handler = new QuickEditHandler(project, editor, method.getContainingFile(), method);
			if (!ApplicationManager.getApplication().isUnitTestMode()) handler.navigate();
		}
	}

}
