package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.MethodReferenceCreator;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.List;

import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class ConvertToMethodReference extends ClassCreationIntention {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return expressionContext(element) != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Node node = getContainerNodeOf(element);
		if (node == null) return;
		final Valued valued = TaraPsiImplUtil.getContainerByType(element, Valued.class);
		final String reference = valued.name();
		final TaraMethodReference methodReference = TaraElementFactory.getInstance(valued.getProject()).createMethodReference(reference);
		new MethodReferenceCreator(valued, reference, TaraUtil.getFacetConfiguration(valued)).createMethodObjectClass(expressionContext(element).getValue());
		substitute(methodReference, valued);
	}

	private PsiElement substitute(TaraMethodReference methodReference, Valued valued) {
		if (valued.getBodyValue() != null) {
			valued.getBodyValue().delete();
			return valued.getLastChild() instanceof TaraFlags ? add(methodReference, valued, findName(valued)) : addToTheEnd(methodReference, valued);
		} else return valued.getValue().replace(methodReference.getParent().copy());
	}

	private Identifier findName(Valued valued) {
		final List<Identifier> childrenOfType = new ArrayList<>(PsiTreeUtil.findChildrenOfType(valued, Identifier.class));
		return childrenOfType.get(childrenOfType.size() - 1);
	}

	private PsiElement addToTheEnd(TaraMethodReference methodReference, PsiElement valued) {
		valued.add(methodReference.getParent().getPrevSibling().copy());
		valued.add(methodReference.getParent().getPrevSibling().getPrevSibling().copy());
		valued.add(methodReference.getParent().getPrevSibling().copy());
		return valued.add(methodReference);
	}

	private PsiElement add(TaraMethodReference methodReference, Valued valued, PsiElement anchor) {
		valued.addAfter(methodReference.getParent().getPrevSibling().copy(), anchor);
		valued.addAfter(methodReference.getParent().getPrevSibling().getPrevSibling().copy(), anchor.getNextSibling());
		valued.addAfter(methodReference.getParent().getPrevSibling().copy(), anchor.getNextSibling().getNextSibling());
		valued.addAfter(methodReference.getParent().copy(), anchor.getNextSibling().getNextSibling().getNextSibling());
		return methodReference;
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Convert to method reference";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	private Expression expressionContext(@NotNull PsiElement element) {
		return TaraPsiImplUtil.getContainerByType(element, Expression.class);
	}
}
