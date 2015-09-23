package tara.intellij.lang.psi;

import com.intellij.psi.NavigatablePsiElement;

public interface StringValue extends MultilineValue, NavigatablePsiElement, TaraPsiElement {

	String getValue();
}
