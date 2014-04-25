package monet.tara.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ImportIdentifier extends Navigatable, PsiElement {

	@NotNull
	List<? extends Identifier> getIdentifierList();
}
