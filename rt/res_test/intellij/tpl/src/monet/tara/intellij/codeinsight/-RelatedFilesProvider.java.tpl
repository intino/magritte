package monet.::projectName::.intellij.codeinsight;

import com.intellij.navigation.GotoRelatedItem;
import com.intellij.navigation.GotoRelatedProvider;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.uiDesigner.compiler.Utils;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ::projectProperName::RelatedFilesProvider extends GotoRelatedProvider {

	\@NotNull
	\@Override
	public List<? extends GotoRelatedItem> getItems(\@NotNull PsiElement context) {
		PsiClass psiClass = PsiTreeUtil.getParentOfType(context, PsiClass.class, false);
		if (psiClass != null) {
			while (psiClass != null) {
				List<PsiFile> forms = findFormsBoundToClass(psiClass);
				if (!forms.isEmpty()) {
					return GotoRelatedItem.createItems(forms, "::projectProperName::");
				}
				psiClass = PsiTreeUtil.getParentOfType(psiClass, PsiClass.class);
			}
		} else {
			PsiFile file = context.getContainingFile();
			if (file.getFileType() == ::projectProperName::FileType.INSTANCE) {
				try {
					String className = Utils.getBoundClassName(file.getText());
					if (className != null) {
						Project project = file.getProject();
						PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(className, GlobalSearchScope.allScope(project));
						if (aClass != null) {
							return Collections.singletonList(new GotoRelatedItem(aClass, "Java"));
						}
					}
				} catch (Exception ignore) {

				}
			}
		}
		return Collections.emptyList();
	}

	private List<PsiFile> findFormsBoundToClass(PsiClass psiClass) {
		return Collections.EMPTY_LIST;
	}
}