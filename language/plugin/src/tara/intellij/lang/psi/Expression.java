package tara.intellij.lang.psi;

import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;

public interface Expression extends NavigatablePsiElement, TaraPsiElement, PsiLanguageInjectionHost {

	boolean isValidHost();

	@NotNull
	LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper();

	String getValue();

	PsiLanguageInjectionHost updateText(@NotNull String text);

	boolean isMultiLine();
}
