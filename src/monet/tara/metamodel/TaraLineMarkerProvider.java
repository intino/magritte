package monet.tara.metamodel;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiIdentifier;
import monet.tara.metamodel.psi.impl.TaraUtil;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class TaraLineMarkerProvider extends RelatedItemLineMarkerProvider {
	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiIdentifier) {
			PsiIdentifier psiIdentifier = (PsiIdentifier) element;
			if (psiIdentifier.getText().equals("Tara") && psiIdentifier.getNextSibling().getText().equals(":") && psiIdentifier.getNextSibling().getNextSibling() != null) {
				PsiExpressionStatement identifier = (PsiExpressionStatement) psiIdentifier.getNextSibling().getNextSibling();
				String value = identifier.getText();
				Project project = element.getProject();
				final List<TaraConceptDefinition> properties = TaraUtil.findConcept(project, value.replace(";", ""));
				if (properties.size() > 0) {
					NavigationGutterIconBuilder<PsiElement> psiElementNavigationGutterIconBuilder = NavigationGutterIconBuilder.create(TaraIcons.FILE).setTargets(properties);
					NavigationGutterIconBuilder<PsiElement> builder =
							psiElementNavigationGutterIconBuilder.setTooltipText("Navigate to the concept");
					result.add(builder.createLineMarkerInfo(element));
				}
			}
		}
	}
}
