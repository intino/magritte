package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.NodeContainer;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Documentation;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TaraMetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraMetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		final PsiElement destiny = findDestiny();
		if (destiny != null) results.add(new PsiElementResolveResult(destiny));
		return results.toArray(new ResolveResult[results.size()]);
	}

	@Nullable
	private PsiElement findDestiny() {
		Language language = TaraLanguage.getLanguage(myElement.getContainingFile());
		final Node node = TaraPsiImplUtil.getContainerNodeOf(myElement);
		if (language == null || node == null) return null;
		final Documentation doc = language.doc(node.resolve().type());
		if (doc == null) return null;
		PsiFile file = findFile(doc.file());
		if (file == null) return null;
		return searchNodeIn(TaraUtil.getAllNodeContainersOfFile((TaraModel) file));
	}

	private Node searchNodeIn(List<NodeContainer> nodes) {
		for (NodeContainer node : nodes)
			if (node instanceof Node && myElement.getText().equals(((Node) node).name()))
				return (Node) node;
		return null;
	}

	@Nullable
	private PsiFile findFile(String file) {
		try {
			final VirtualFile virtualFile = VfsUtil.findFileByURL(new File(file).toURI().toURL());
			if (virtualFile == null) return null;
			return PsiManager.getInstance(myElement.getProject()).findFile(virtualFile);
		} catch (MalformedURLException ignored) {
			return null;
		}
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return new Object[0];
	}
}