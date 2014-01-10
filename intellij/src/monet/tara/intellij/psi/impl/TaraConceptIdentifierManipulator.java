package monet.tara.intellij.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.psi.impl.TaraIdentifierImpl;

public class TaraConceptIdentifierManipulator extends AbstractElementManipulator<TaraIdentifierImpl> {
	@Override
	public TaraIdentifierImpl handleContentChange(TaraIdentifierImpl element, TextRange range, String newContent)
		throws IncorrectOperationException {
		String newName = range.replace(element.getText(), newContent);
		element.replace(TaraElementFactoryImpl.getInstance(element.getProject()).createNameIdentifier(newName));
		return element;
	}
}
