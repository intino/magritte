package tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.lang.model.NodeRoot;
import tara.lang.model.Node;

import java.util.List;

public interface TaraModel extends NodeRoot, PsiFile {

	@NotNull
	PsiElement addNode(@NotNull Node node) throws IncorrectOperationException;

	Node addNode(String identifier);

	Import addImport(String reference);

	@NotNull
	String getPresentableName();

	String getDSL();

	@NotNull
	List<Import> getImports();

	TaraDslDeclaration getDSLDeclaration();

	void updateDSL();
}
