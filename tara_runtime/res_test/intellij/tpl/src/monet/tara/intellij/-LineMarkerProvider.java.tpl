package monet.::projectName::.intellij;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiIdentifier;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class ::projectProperName::LineMarkerProvider extends RelatedItemLineMarkerProvider {
	\@Override
	protected void collectNavigationMarkers(\@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiIdentifier) {
			PsiIdentifier psiIdentifier = (PsiIdentifier) element;
			if (psiIdentifier.getText().equals("::projectProperName::") && psiIdentifier.getNextSibling().getText().equals("\:") && psiIdentifier.getNextSibling().getNextSibling() != null) {
				PsiExpressionStatement identifier = (PsiExpressionStatement) psiIdentifier.getNextSibling().getNextSibling();
				String value = identifier.getText();
				Project project = element.getProject();
				final List<Definition> definitionList = ::projectProperName::Util.findRootDefinition(project, value.replace(";", ""));
				if (definitionList.size() > 0) {
					NavigationGutterIconBuilder<PsiElement> psiElementNavigationGutterIconBuilder = NavigationGutterIconBuilder.create(::projectProperName::Icons.ICON_13).setTargets(definitionList);
					NavigationGutterIconBuilder<PsiElement> builder =
						psiElementNavigationGutterIconBuilder.setTooltipText("Navigate to the definition");
					result.add(builder.createLineMarkerInfo(element));
				}
			}
		}
	}
}
