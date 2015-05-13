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

	boolean isFacet();

	boolean isAbstract();

	boolean isProperty();

	boolean isFeature();

	boolean FeatureInstance();

	boolean isEnclosed();

	boolean isFacetInstance();

	boolean isAnnotatedAsRoot();

	@Nullable
	TaraAddress getAddress();

	@NotNull
	List<Annotation> getAnnotations();

	List<Flag> getFlags();

	Annotations getAnnotationsNode();

	Flags getFlagsNode();

	Collection<String> getAssumedFlags();

	void addInheritedFlags(String... flags);

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

