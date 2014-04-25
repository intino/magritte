package monet.tara.intellij.codeinsight;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.impl.JavaLineMarkerProvider;
import com.intellij.openapi.editor.colors.EditorColorsManager;

public class TaraLineMarkerProvider extends JavaLineMarkerProvider {

	public TaraLineMarkerProvider(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}
}
