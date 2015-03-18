package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface NodeReference extends Navigatable, TaraPsiElement {

	boolean isAggregated();

	@Nullable
	TaraAnnotations getAnnotations();

	@NotNull
	TaraIdentifierReference getIdentifierReference();

	void addInheritedAnnotations(String... annotations);

	Collection<String> getInheritedAnnotations();
}
