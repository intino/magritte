package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConceptReference extends Navigatable, TaraPsiElement {

	public boolean isAggregated();

	@Nullable
	TaraAnnotations getAnnotations();

	@NotNull
	TaraIdentifierReference getIdentifierReference();
}
