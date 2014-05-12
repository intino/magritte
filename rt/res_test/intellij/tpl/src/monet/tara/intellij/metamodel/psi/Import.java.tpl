package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public interface Import extends Navigatable, Iconable, ::projectProperName::PsiElement {

	\@NotNull
	::projectProperName::HeaderReference getHeaderReference();

}
