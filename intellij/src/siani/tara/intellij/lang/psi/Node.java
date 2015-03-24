package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public interface Node extends Navigatable, Iconable, TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	Identifier getIdentifierNode();

	@Nullable
	Body getBody();

	@NotNull
	Collection<Doc> getDoc();

	@NotNull
	Signature getSignature();

	boolean isSub();

	boolean isRoot();

	Collection<Node> getSubNodes();

	Node getContainer();

	boolean isIntention();

	boolean isFacet();

	boolean isAddressed();

	boolean isAbstract();

	boolean isAggregated();

	boolean isProperty();

	boolean isComponent();

	boolean isAnnotatedAsAggregated();

	boolean isAnnotatedAsAssociated();

	@Nullable
	TaraAddress getAddress();

	@NotNull
	List<Annotation> getAnnotations();

	Annotations getAnnotationsNode();

	Collection<String> getAssumedAnnotations();

	void addInheritedAnnotations(String... annotations);

	boolean contains(String type);

	@Override
	Icon getIcon(@IconFlags int i);

	PsiElement setName(String newName);

	Node getParentNode();

	@Nullable
	String getName();

	boolean isAnonymous();

	String getQualifiedName();

	String getMetaQualifiedName();

	@Nullable
	String getType();

	String getFullType();

	@NotNull
	Node resolve();

	void setFullType(String type);

	@NotNull
	Collection<Parameter> getParameterList();

	Parameters getParameters();

	Collection<Node> getNodeSiblings();

	Collection<Node> getInnerNodes();

	Collection<Variable> getVariables();

	Collection<VarInit> getVarInits();

	Collection<NodeReference> getInnerNodeReferences();

	Collection<FacetApply> getFacetApplies();

	Collection<TaraFacetTarget> getFacetTargets();

	@Nullable
	String getParentName();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	void addAddress(TaraAddress address);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}

