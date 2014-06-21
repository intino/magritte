package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public interface Signature extends TaraPsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	@Nullable
	TaraIdentifier getIdentifier();

	boolean isCase();

	boolean isBase();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

	@Nullable
	MetaIdentifier getType();

	Parameters getParameters();
}

