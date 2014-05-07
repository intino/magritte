package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class IdentifierManipulator extends AbstractElementManipulator<TaraIdentifierImpl> {
	@Override
	public TaraIdentifierImpl handleContentChange(@NotNull TaraIdentifierImpl element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
		String newName = range.replace(element.getText(), newContent);
		element.replace(TaraElementFactoryImpl.getInstance(element.getProject()).createNameIdentifier(newName));
		return element;
	}
}