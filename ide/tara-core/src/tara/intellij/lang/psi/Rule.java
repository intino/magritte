package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

import java.util.List;

public interface Rule extends Navigatable, TaraPsiElement, tara.lang.model.Rule {

	boolean isLambda();

	List<TaraIdentifier> getIdentifierList();
}
