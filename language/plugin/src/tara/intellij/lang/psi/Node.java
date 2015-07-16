package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.documentation.TaraDocumentationFormatter;
import tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public interface Node extends tara.language.model.Node, NodeContainer, Parametrized, Navigatable, Iconable {

	@Nullable
	String name();

	String qualifiedName();


	@Override
	void name(String name);

	@Override
	String plate();

	@Override
	List<String> secondaryTypes();

	Identifier getIdentifierNode();

	@Nullable
	Body getBody();

	@NotNull
	Signature getSignature();

	boolean isSub();

	boolean isMain();

	List<Node> subs();

	Node container();

	boolean isFacet();

	boolean isAbstract();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isEnclosed();

	boolean isFacetInstance();

	boolean isTerminalInstance();

	boolean isAnnotatedAsMain();

	@Override
	boolean isRequired();

	@Override
	boolean isSingle();

	@Override
	boolean isNamed();

	@Override
	boolean isFinal();

	@Override
	boolean isTerminal();

	@Override
	boolean intoSingle();

	@Override
	boolean intoRequired();

	@Nullable
	TaraAddress getAddress();

	Annotations getAnnotationsNode();

	Flags getFlagsNode();

	boolean contains(String type);

	Node parent();

	@NotNull
	Node resolve();

	List<NodeReference> referenceComponents();

	List<FacetApply> facets();

	List<FacetTarget> facetTargets();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	void addInstanceName(TaraAddress address);

	@Override
	default boolean isAnonymous() {
		return name() == null;
	}

	@NotNull
	default List<Doc> getDoc() {
		return EMPTY_LIST;
	}

	@Nullable
	default String getDocCommentText() {
		StringBuilder text = new StringBuilder();
		List<Doc> docs = this.getDoc();
		String comment;
		for (Doc doc : docs) {
			comment = doc.getText();
			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
			text.append(trimmed.trim()).append("\n");
		}
		return TaraDocumentationFormatter.doc2Html(this, text.toString());
	}

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	String toString();

	boolean equals(Object obj);

	int hashCode();
}

