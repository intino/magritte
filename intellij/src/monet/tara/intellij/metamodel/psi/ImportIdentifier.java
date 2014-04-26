package monet.tara.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ImportIdentifier extends Navigatable, TaraPsiElement {

	@NotNull
	List<? extends Identifier> getIdentifierList();
}
