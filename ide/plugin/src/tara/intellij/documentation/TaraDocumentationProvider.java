package tara.intellij.documentation;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.codeinsight.completion.CompletionUtils;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.semantics.Documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;


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
			return TaraDocumentationFormatter.doc2Html(null, findDoc(TaraPsiImplUtil.getContainerNodeOf(originalElement)));
		if (element instanceof Node) return ((Node) element).doc();
		if (element instanceof CompletionUtils.FakeElement)
			return findDoc(((CompletionUtils.FakeElement) element).getType(), originalElement);
		return "";
	}

	private String findDoc(Node node) {
		return findDoc(node.type(), (PsiElement) node);
	}

	private String findDoc(String type, PsiElement anElement) {
		final Language language = TaraLanguage.getLanguage(anElement.getContainingFile());
		if (language == null || language instanceof Proteo) return "";
		final Documentation doc = language.doc(type);
		return doc != null ? doc.description() : "";
	}

	public static File getDocumentationFile(PsiElement element) {
		final String resourcesRoot = TaraUtil.getResourcesRoot(element);
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
			Notifications.Bus.notify(new Notification("Tara", "Documentation File not found", "", NotificationType.ERROR), null);
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
			Notifications.Bus.notify(new Notification("Tara", "Documentation File not found", "", NotificationType.ERROR), null);
			return false;
		}


	}
}