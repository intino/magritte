package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.documentation.TaraDocumentationFormatter;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public interface Node extends NodeContainer, Parametrized, Navigatable, Iconable {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	Identifier getIdentifierNode();

	@Nullable
	Body getBody();

	@NotNull
	default Collection<Doc> getDoc() {
		return EMPTY_LIST;
	}

	@NotNull
	Signature getSignature();

	boolean isSub();

	boolean isMain();

	List<Node> getSubNodes();

	Node getContainer();

	boolean isFacet();

	boolean isAbstract();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isEnclosed();

	boolean isFacetInstance();

	boolean isTerminalInstance();

	boolean isAnnotatedAsMain();

	@Nullable
	TaraAddress getAddress();

	@NotNull
	List<Annotation> getAnnotations();

	List<Flag> getFlags();

	Annotations getAnnotationsNode();

	List<String> getInheritedFlags();

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
	List<Parameter> getParameterList();

	List<Node> getNodeSiblings();

	List<Node> getIncludes();

	List<Variable> getVariables();

	List<VarInit> getVarInits();

	List<NodeReference> getInnerNodeReferences();

	List<FacetApply> getFacetApplies();

	List<FacetTarget> getFacetTargets();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	void addInstanceName(TaraAddress address);

	String toString();

	boolean equals(Object obj);

	int hashCode();

	@Nullable
	default String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		Collection<Doc> docs = this.getDoc();
		String comment;
		for (Doc doc : docs) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
			text.append(trimmed.trim()).append("\n");
		}
		return TaraDocumentationFormatter.doc2Html(this, text.toString());
	}
}

