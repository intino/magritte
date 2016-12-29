package io.intino.tara.plugin.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.plugin.lang.psi.impl.TaraModelImpl;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;

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
	List<? extends Facet> facets();

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