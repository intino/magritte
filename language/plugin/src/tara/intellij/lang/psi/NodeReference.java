package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NodeReference extends tara.language.model.Node, Navigatable, TaraPsiElement {

	@Nullable
	Annotations getAnnotationsNode();

	@Nullable
	Flags getFlagsNode();

	@NotNull
	IdentifierReference getIdentifierReference();

}
