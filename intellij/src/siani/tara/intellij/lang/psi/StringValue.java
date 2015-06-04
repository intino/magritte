package siani.tara.intellij.lang.psi;

import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;

public interface StringValue extends NavigatablePsiElement, TaraPsiElement, PsiLanguageInjectionHost {

	String getValue();

	PsiLanguageInjectionHost updateText(@NotNull String text);

	boolean isValidHost();

	boolean isMultiLine();

	@NotNull
	LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper();
}
