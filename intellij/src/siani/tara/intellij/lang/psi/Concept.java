package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public interface Concept extends Navigatable, Iconable, TaraPsiElement {

	TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	Identifier getIdentifierNode();

	@Nullable
	Body getBody();

	@NotNull
	Doc[] getDoc();

	@NotNull
	Signature getSignature();

	boolean isSub();

	boolean isRoot();

	Collection<Concept> getSubConcepts();

	Concept getContainer();

	boolean isIntention();

	boolean isFacet();

	boolean isAddressed();

	boolean isAggregated();

	boolean isMetaAggregated();

	boolean isProperty();

	boolean isComponent();

	boolean isAnnotatedAsAggregated();

	TaraAddress getAddress();

	@NotNull
	List<Annotation> getNormalAnnotations();

	List<Annotation> getMetaAnnotations();

	Annotations getAnnotations();

	boolean contains(String type);

	@Override
	Icon getIcon(@IconFlags int i);

	PsiElement setName(String newName);

	Concept getParentConcept();

	@Nullable
	String getName();

	boolean isAnonymous();

	String getQualifiedName();

	String getMetaQualifiedName();

	@Nullable
	String getType();

	@NotNull
	Parameter[] getParameterList();

	Parameters getParameters();

	Collection<Concept> getConceptSiblings();

	Collection<Concept> getInnerConcepts();

	Collection<Variable> getVariables();

	Collection<VarInit> getVarInits();

	TaraConceptReference[] getConceptLinks();

	FacetApply[] getFacetApplies();

	Collection<TaraFacetTarget> getFacetTargets();

	@Nullable
	String getParentConceptName();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	void addAddress(TaraAddress address);

	String toString();

	boolean equals(Object obj);

	public int hashCode();
}

