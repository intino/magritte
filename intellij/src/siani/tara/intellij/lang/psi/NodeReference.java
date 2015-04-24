package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface NodeReference extends Navigatable, TaraPsiElement {

	boolean isAggregated();

	boolean isAssociated();

	@NotNull
	Collection<? extends Annotation> getAnnotations();

	@NotNull
	Collection<? extends Flag> getFlags();

	@NotNull
	IdentifierReference getIdentifierReference();

	void addInheritedFlags(String... flags);

	Collection<String> getInheritedFlags();
}
