package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraMethodReference;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.MethodReferenceCreator;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;

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
		final String reference = askName();
		final TaraMethodReference methodReference = TaraElementFactory.getInstance(valued.getProject()).createMethodReference(reference);
		new MethodReferenceCreator(valued, reference, TaraUtil.getFacetConfiguration(valued)).createMethodObjectClass(expressionContext(element).getValue());
		substitute(methodReference, valued);
	}

	private void substitute(TaraMethodReference methodReference, Valued valued) {
		if (valued.getBodyValue() != null) {
			valued.getBodyValue().delete();
			valued.add(methodReference.getParent().getPrevSibling().copy());
			valued.add(methodReference.getParent().getPrevSibling().getPrevSibling().copy());
			valued.add(methodReference.getParent().getPrevSibling().copy());
			valued.add(methodReference);
		} else valued.replace(methodReference.getParent().copy());
	}

	private String askName() {
		return "reference";
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
