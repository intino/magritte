package monet.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HeaderReference extends Navigatable, TaraPsiElement {

	@NotNull
	List<? extends Identifier> getIdentifierList();
}
