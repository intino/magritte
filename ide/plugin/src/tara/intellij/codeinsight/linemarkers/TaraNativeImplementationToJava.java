package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.Primitives;

import java.util.Collection;

public class TaraNativeImplementationToJava extends RelatedItemLineMarkerProvider {

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (!(element instanceof Valued)) return;
		Valued valued = (Valued) element;
		if (!isAvailable(valued)) return;
		PsiElement destiny = ReferenceManager.resolveTaraNativeImplementationToJava(valued);
		if (destiny != null) addResult(element, result, destiny);
	}

	private boolean isAvailable(Valued valued) {
		return Primitives.NATIVE.equals(valued.getInferredType());
	}

	private void addResult(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result, PsiElement destiny) {
		NavigationGutterIconBuilder<PsiElement> builder =
			NavigationGutterIconBuilder.create(AllIcons.Gutter.ImplementedMethod).setTarget(destiny).setTooltipText("Navigate to the native code");
		result.add(builder.createLineMarkerInfo(element));
	}


}