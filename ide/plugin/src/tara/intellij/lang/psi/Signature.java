package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

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

	FacetTarget getFacetTarget();

	@Nullable
	Tags getTags();

	Flags getFlags();

	@Nullable
	Annotations getAnnotations();

	@Nullable
	TaraAnchor getAnchor();
}