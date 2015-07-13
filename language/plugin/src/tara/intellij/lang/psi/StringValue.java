package tara.intellij.lang.psi;

import com.intellij.psi.NavigatablePsiElement;

public interface StringValue extends NavigatablePsiElement, TaraPsiElement {

	String getValue();

	boolean isMultiLine();
}
