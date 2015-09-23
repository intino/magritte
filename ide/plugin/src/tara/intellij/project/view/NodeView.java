package tara.intellij.project.view;

import com.intellij.CommonBundle;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.codeInsight.daemon.impl.DaemonProgressIndicator;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VFileProperty;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBColor;
import tara.intellij.lang.psi.TaraModel;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class NodeView extends PsiFileNode implements Navigatable {
	public static final DataKey<NodeView> DATA_KEY = DataKey.create("form.array");
	@SuppressWarnings("deprecation")
	public static final TextAttributesKey ERROR = createTextAttributesKey("ERROR",
		new TextAttributes(null, null, JBColor.RED, EffectType.WAVE_UNDERSCORE, Font.PLAIN));
	private final PsiFile taraFile;

	public NodeView(Project project, TaraModel psiFile, ViewSettings settings) {
		super(project, psiFile, settings);
		taraFile = psiFile;
		myName = getName();
	}

	public boolean equals(Object object) {
		if (object instanceof NodeView) {
			NodeView form = (NodeView) object;
			return taraFile.equals(form.taraFile);
		}
		return false;
	}

	@Override
	public Collection<AbstractTreeNode> getChildrenImpl() {
		return Collections.EMPTY_LIST;
	}

	public int hashCode() {
		return taraFile.hashCode();
	}

	public String getName() {
		return taraFile.getName().substring(0, taraFile.getName().lastIndexOf("."));
	}

	public void navigate(boolean requestFocus) {
		taraFile.navigate(requestFocus);
	}

	public boolean canNavigateToSource() {
		return taraFile.canNavigateToSource();
	}

	public boolean canNavigate() {
		return taraFile.canNavigate();
	}

	@Override
	protected void updateImpl(PresentationData data) {
		PsiFile value = getValue();
		if (value instanceof TaraModel) {
			myName = getName();
			data.setPresentableText(((TaraModel) value).getPresentableName());
		} else data.setPresentableText(value.getName());
		data.setIcon(value.getIcon(Iconable.ICON_FLAG_READ_STATUS));
		final HighlightInfo error = fileError(); //TODO
		if (error != null) {
			data.setAttributesKey(ERROR);
			data.setTooltip(error.getDescription());
		}
		VirtualFile file = getVirtualFile();
		if (file != null && file.is(VFileProperty.SYMLINK)) {
			String target = file.getCanonicalPath();
			if (target == null) {
				data.setAttributesKey(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
				data.setTooltip(CommonBundle.message("vfs.broken.link"));
			} else data.setTooltip(FileUtil.toSystemDependentName(target));
		}
	}

	private HighlightInfo fileError() {
		final Document document = FileDocumentManager.getInstance().getDocument(taraFile.getVirtualFile());
		if (document == null) return null;
		return getErrors(document);
	}

	private HighlightInfo getErrors(Document document) {
		final java.util.List<HighlightInfo> highlightInfos = DaemonCodeAnalyzerEx.getInstanceEx(taraFile.getProject()).runMainPasses(taraFile, document, new DaemonProgressIndicator());
		for (HighlightInfo highlightInfo : highlightInfos)
			if (highlightInfo.getSeverity().equals(HighlightSeverity.ERROR)) return highlightInfo;

		return null;
	}

	public boolean isValid() {
		return taraFile.isValid();
	}

}
