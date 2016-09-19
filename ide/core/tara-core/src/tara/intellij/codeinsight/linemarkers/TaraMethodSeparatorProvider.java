package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.daemon.impl.LineMarkersPass;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraNode;
import tara.lang.model.Node;

import java.util.Collection;
import java.util.List;

public class TaraMethodSeparatorProvider implements LineMarkerProvider {

	private final DaemonCodeAnalyzerSettings myDaemonSettings;
	private final EditorColorsManager myColorsManager;


	public TaraMethodSeparatorProvider(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		myDaemonSettings = daemonSettings;
		myColorsManager = colorsManager;
	}

	@Nullable
	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
		if (!(element instanceof TaraNode) || !isRoot((Node) element)) return null;
		return LineMarkersPass.createMethodSeparatorLineMarker(element, myColorsManager);
	}

	private boolean isRoot(Node element) {
		return element.container() instanceof TaraModel;
	}

	@Override
	public void collectSlowLineMarkers(@NotNull List<PsiElement> elements, @NotNull Collection<LineMarkerInfo> result) {

	}

}
