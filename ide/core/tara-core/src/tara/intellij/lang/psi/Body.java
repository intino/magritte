package tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Variable;

import java.util.List;

public interface Body extends TaraPsiElement {

	@NotNull
	List<? extends Variable> getVariableList();

	@NotNull
	List<? extends Parameter> getVarInitList();

	@NotNull
	List<? extends Node> getNodeList();

	List<Node> getNodeLinks();

	List<PsiElement> getStatements();

}

