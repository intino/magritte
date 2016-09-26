package tara.intellij.documentation;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiPlainTextFileImpl;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.dsl.Verso;
import tara.intellij.codeinsight.completion.CompletionUtils.FakeElement;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.semantics.Documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.IntStream;

import static com.intellij.notification.NotificationType.ERROR;
import static tara.intellij.documentation.TaraDocumentationFormatter.doc2Html;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerByType;
import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveToNode;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	private static final String DOC_JSON = "doc.json";

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	@Override
	public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
		return object instanceof PsiElement ? (PsiElement) object : null;
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (originalElement instanceof MetaIdentifier)
			return doc2Html(null, findDoc(getContainerByType(originalElement, NodeContainer.class)));
		if (element instanceof MetaIdentifier)
			return doc2Html(null, findDoc(getContainerByType(element, NodeContainer.class)));
		if (element instanceof Node) return ((Node) element).doc();
		if (element instanceof FakeElement) return findDoc(((FakeElement) element).getType(), originalElement);
		if (element instanceof Identifier && getContainerByType(element, IdentifierReference.class) != null) {
			final Node resolve = resolveToNode(getContainerByType(element, IdentifierReference.class));
			return resolve != null ? ((TaraNode) resolve).getSignature().getText() : "";
		}
		if (element instanceof Identifier && getContainerByType(element, TaraSignature.class) != null)
			return getContainerByType(element, TaraSignature.class).getText();
		if (element instanceof PsiPlainTextFileImpl) return doc2Html(element, html(table(element.getText())));
		else return "**No documentation found for " + element.getText() + "**";
	}

	private String html(String table) {
		String text = "<table>";
		for (String line : table.split("\n"))
			text += "<tr><td padding=0>" + line.replace(";", "</td><td>") + "<td><tr>";
		return text + "</table>";
	}

	private String table(String text) {
		final int[] lastIndex = {0};
		IntStream.range(0, 5).forEach(i -> lastIndex[0] = text.indexOf("\n", lastIndex[0] + 1) > 0 ? text.indexOf("\n", lastIndex[0] + 1) : lastIndex[0]);
		return text.substring(0, lastIndex[0]) + (text.indexOf("\n", lastIndex[0] + 1) > 0 ? "\n..." : "");
	}

	private String findDoc(NodeContainer container) {
		return findDoc(typeOf(container), (PsiElement) container);
	}

	private String typeOf(NodeContainer container) {
		return (container instanceof Facet) ? container.type() + ":" + TaraPsiImplUtil.getContainerNodeOf((PsiElement) container).type() : container.type();
	}

	private String findDoc(String type, PsiElement anElement) {
		final Language language = TaraUtil.getLanguage(anElement);
		if (language == null || language instanceof Proteo || language instanceof Verso)
			return "**No documentation found for " + type + "**";
		final Documentation doc = language.doc(type);
		return doc != null && !doc.description().isEmpty() ? doc.description() : "**No documentation found for " + type + "**";
	}

	public static File getDocumentationFile(PsiElement element) {
		final String resourcesRoot = TaraUtil.getResourcesRoot(element).getPath();
		if (resourcesRoot.isEmpty()) return null;
		return new File(resourcesRoot, DOC_JSON);
	}

	public static Map<String, String> extractDocumentationFrom(File docFile) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Gson gson = new Gson();
		try {
			return gson.fromJson(new FileReader(docFile), type);
		} catch (FileNotFoundException e) {
			Notifications.Bus.notify(new Notification("Tara", "Documentation File not found", "", ERROR), null);
		}
		return null;
	}

	public static boolean saveDocumentation(Map<String, String> doc, File docFile) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		final Gson gson = builder.create();
		try {
			final String content = gson.toJson(doc, type);
			Files.write(content.getBytes(), docFile);
			return true;
		} catch (IOException e) {
			Notifications.Bus.notify(new Notification("Tara", "Documentation File not found", "", ERROR), null);
			return false;
		}


	}
}