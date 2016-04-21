package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.MethodReferenceCreator;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.qnOf;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static tara.intellij.lang.psi.impl.TaraUtil.importsFile;

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
		if (valued == null) return;
		final String name = valued.name();
		final TaraMethodReference methodReference = TaraElementFactory.getInstance(valued.getProject()).createMethodReference(name);
		new MethodReferenceCreator(valued, name, TaraUtil.getFacetConfiguration(valued)).create(expressionContext(element).getValue());
		substitute(methodReference, valued);
		removeOldImports(valued);
	}

	private void removeOldImports(Valued valued) {
		Imports imports = new Imports(valued.getProject());
		imports.save(importsFile(valued), qnOf(valued), Collections.emptySet());
	}

	private PsiElement substitute(TaraMethodReference methodReference, Valued valued) {
		if (valued.getBodyValue() != null) {
			valued.getBodyValue().delete();
			return valued.getLastChild() instanceof TaraFlags ? add(methodReference, valued, findName(valued)) : addToTheEnd(methodReference, valued);
		} else if (valued.getValue() != null) return valued.getValue().replace(methodReference.getParent().copy());
		return null;
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
