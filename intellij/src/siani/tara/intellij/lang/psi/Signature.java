package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

public interface Signature extends TaraPsiElement {

	TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	TaraIdentifier getIdentifier();

	boolean isSub();

	@Nullable
	TaraIdentifierReference getParentReference();

	@Nullable
	MetaIdentifier getType();

	Concept getParentConcept();

	Parameters getParameters();

	TaraAddress getAddress();
}