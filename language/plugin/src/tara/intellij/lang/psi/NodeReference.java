package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface NodeReference extends tara.language.model.Node, Navigatable, TaraPsiElement {


	List<Node> components();

	@Nullable
	Annotations getAnnotationsNode();

	@Nullable
	Flags getFlagsNode();

	@NotNull
	IdentifierReference getIdentifierReference();

}
