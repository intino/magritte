package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.Collection;
import java.util.List;

public interface Node extends Navigatable, Iconable, TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

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

	Node container();

	boolean isIntention();

	boolean isFacet();

	boolean isAddressed();

	boolean isAbstract();

	boolean isAggregated();

	boolean isAssociated();

	boolean isProperty();

	boolean isPropertyInstance();

	boolean isComponent();

	boolean isIntentionInstance();

	boolean isFacetInstance();

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

	Node getParentNode();

	@Nullable
	String getName();

	String getQualifiedName();

	@Nullable
	String getType();

	String getFullType();

	void setFullType(String type);

	@NotNull
	Node resolve();

	@NotNull
	Collection<Parameter> getParameters();

	Collection<Node> getNodeSiblings();

	Collection<Node> getInnerNodes();

	Collection<Variable> getVariables();

	Collection<VarInit> getVarInits();

	Collection<NodeReference> getInnerNodeReferences();

	Collection<FacetApply> getFacetApplies();

	Collection<TaraFacetTarget> getFacetTargets();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	void addAddress(TaraAddress address);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}

