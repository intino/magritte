package monet.tara.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.PsiNamedElement;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface Concept extends Navigatable, Iconable, PsiNamedElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

	TaraBody getBody();

	boolean isPolymorphic();

	boolean isMorph();

	@Override
	Icon getIcon(@IconFlags int i);
}

