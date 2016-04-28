package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

import java.util.List;

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

	Node parent();

	@Nullable
	Parameters getParameters();

	FacetTarget getFacetTarget();

	@NotNull
	List<? extends Facet> getFacetApplyList();

	@Nullable
	Tags getTags();

	Flags getFlags();

	@Nullable
	Annotations getAnnotations();

	@Nullable
	TaraAnchor getAnchor();

	@Nullable
	TaraWithTable getWithTable();
}