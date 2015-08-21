package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.language.model.Node;

public interface Signature extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	TaraMetaIdentifier getMetaIdentifier();

	@Nullable
	TaraIdentifier getIdentifier();

	boolean isSub();

	@Nullable
	TaraIdentifierReference getParentReference();

	@Nullable
	MetaIdentifier getType();

	Node getParentNode();

	@Nullable
	Parameters getParameters();

	@Nullable
	Tags getTags();

	Flags getFlags();

	@Nullable
	Annotations getAnnotations();

	@Nullable
	TaraAddress getAddress();
}