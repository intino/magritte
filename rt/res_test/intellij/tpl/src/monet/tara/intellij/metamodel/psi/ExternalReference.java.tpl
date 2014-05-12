package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ExternalReference extends Navigatable, ::projectProperName::PsiElement {

	\@NotNull
	List<? extends Identifier> getIdentifierList();


}