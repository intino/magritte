package tara.intellij.codeinsight.livetemplates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;

public class TaraTemplateContext extends TemplateContextType {
	public TaraTemplateContext() {
		super("Tara", "Tara");
	}

	@Override
	public boolean isInContext(@NotNull PsiFile file, int offset) {
		return file instanceof TaraModel;
	}
}
