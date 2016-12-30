package io.intino.tara.plugin.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.HashMap;
import io.intino.tara.plugin.documentation.TaraDocumentationProvider;
import io.intino.tara.plugin.lang.TaraLanguage;
import io.intino.tara.plugin.lang.psi.Identifier;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraFacetTarget;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.plugin.lang.psi.TaraVariable;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeContainer;
import io.intino.tara.lang.model.Variable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EditElementDocumentation extends PsiElementBaseIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final File docFile = TaraDocumentationProvider.getDocumentationFile(element);
		createIfNecessary(docFile);
		Map<String, String> docMap = TaraDocumentationProvider.extractDocumentationFrom(docFile);
		if (docMap == null) docMap = new HashMap<>();
		final String qn = createQn(element);
		DialogBuilder builder = new DialogBuilder(project);
		final String text = createDialog(builder, docMap.get(qn));
		docMap.put(qn, text);
		TaraDocumentationProvider.saveDocumentation(docMap, docFile);
	}

	public void createIfNecessary(File docFile) {
		if (!docFile.exists()) try {
			docFile.createNewFile();
		} catch (IOException e) {
			Notifications.Bus.notify(new Notification("Tara", "Tara", "Error creating documentation file", NotificationType.ERROR), null);
		}
	}

	public String createDialog(DialogBuilder builder, String content) {
		builder.setTitle("Edit documentation");
		final JTextArea textArea = new JTextArea(content);
		textArea.setSize(600, 400);
		textArea.setMinimumSize(new Dimension(600, 400));
		builder.setCenterPanel(textArea);
		builder.setPreferredFocusComponent(textArea);
		builder.resizable(false);
		builder.removeAllActions();
		builder.showModal(true);
		return textArea.getText();
	}

	private String createQn(PsiElement element) {
		final Variable variable = TaraPsiImplUtil.getContainerByType(element, Variable.class);
		if (variable != null) return createVariableQn(variable);
		final Node node = TaraPsiImplUtil.getContainerByType(element, Node.class);
		if (node != null) return createNodeQn(node);
		return "";

	}

	private String createNodeQn(Node node) {
		return node.qualifiedName();
	}

	private String createVariableQn(Variable variable) {
		return variable.container().qualifiedName() + "." + variable.name();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getLanguage().equals(TaraLanguage.INSTANCE) && isDocumentable(TaraPsiImplUtil.getContainerByType(element, Identifier.class));
	}

	private boolean isDocumentable(Identifier identifier) {
		if (identifier == null) return false;
		final TaraVariable variable = TaraPsiImplUtil.getContainerByType(identifier, TaraVariable.class);
		if (variable != null) return identifier.equals(variable.getIdentifier());
		final NodeContainer nodeContainer = TaraPsiImplUtil.getContainerByType(identifier, NodeContainer.class);
		if (nodeContainer != null) {
			if (nodeContainer instanceof TaraNode) return identifier.equals(((TaraNode) nodeContainer).getSignature().getIdentifier());
			else if (nodeContainer instanceof TaraFacetTarget && ((TaraFacetTarget) nodeContainer).getIdentifierReference() != null)
				return ((TaraFacetTarget) nodeContainer).getIdentifierReference().getIdentifierList().contains(identifier);
		}
		return false;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@NotNull
	@Override
	public String getText() {
		return "Edit documentation";
	}
}