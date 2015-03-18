package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

public interface Signature extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	TaraIdentifier getIdentifier();

	boolean isSub();

	@Nullable
	TaraIdentifierReference getParentReference();

	@Nullable
	MetaIdentifier getType();

	Node getParentConcept();

	@Nullable
	Parameters getParameters();

	@Nullable
	TaraAnnotations getAnnotations();

	@Nullable
	TaraAddress getAddress();
}