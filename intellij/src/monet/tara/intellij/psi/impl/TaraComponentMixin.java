package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.TokenType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import monet.tara.compiler.intellij.psi.TaraComponent;
import monet.tara.compiler.intellij.psi.TaraDoc;
import monet.tara.intellij.metamodel.file.TaraFile;
import monet.tara.intellij.psi.IConcept;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TaraComponentMixin extends ASTWrapperPsiElement {

	public TaraComponentMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public SearchScope getUseScope() {
		// concept ref can occur in any file
		return GlobalSearchScope.allScope(getProject());
	}

	public void delete() throws IncorrectOperationException {
		final ASTNode parentNode = getParent().getNode();
		assert parentNode != null;
		ASTNode node = getNode();
		ASTNode prev = node.getTreePrev();
		ASTNode next = node.getTreeNext();
		parentNode.removeChild(node);
		if ((prev == null || prev.getElementType() == TokenType.WHITE_SPACE) && next != null &&
			next.getElementType() == TokenType.WHITE_SPACE) {
			parentNode.removeChild(next);
		}
	}

	@Override
	public String getName() {
		return TaraPsiImplUtil.getIdentifier((TaraComponent) this);
	}

	public TaraFile getTaraFile() throws PsiInvalidElementAccessException {
		return (TaraFile) super.getContainingFile();
	}

	@Nullable
	public String getDocCommentText() {
		StringBuilder text = new StringBuilder();
//		TaraDoc doc = ((TaraComponent) this).getDoc();
//		String comment;
//
//		if (doc != null) {
//			comment = doc.getText();
//			String trimmed = StringUtil.trimStart(StringUtil.trimStart(comment, "#"), "!");
//			text.insert(0, trimmed.trim());
//			if (text.length() == 0) return null;
//		} else
//			text.append(this.getText());
		return text.toString();
	}

	private String extractDoc(List<TaraDoc> doc) {
		StringBuilder text = new StringBuilder();
		for (TaraDoc taraDoc : doc) {
			text.append((taraDoc.getText()!= null)? taraDoc.getText(): ""  +"\n");
		}
		return text.toString();
	}

	public PsiElement getPsiElement() {
		return this;
	}

	@NotNull
	public PsiElement setName(String newName) {
		return TaraPsiImplUtil.setName(((TaraComponent) this).getConceptSignature(), newName);
	}

	public PsiElement getIdentifierNode() {
		return TaraPsiImplUtil.getIdentifierNode((IConcept) this);
	}
}
