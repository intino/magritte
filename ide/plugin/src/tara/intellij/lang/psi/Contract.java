package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

import java.util.List;

public interface Contract extends Navigatable, TaraPsiElement {

	String getFormattedName();

	List<TaraIdentifier> getIdentifierList();

}
