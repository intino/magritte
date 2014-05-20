package monet.::projectName::.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HeaderReference extends Navigatable, ::projectProperName::PsiElement {

	\@NotNull
	List<? extends Identifier> getIdentifierList();
}