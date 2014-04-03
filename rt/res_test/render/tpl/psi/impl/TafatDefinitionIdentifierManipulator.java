package monet.tafat.intellij.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;

public class TafatDefinitionIdentifierManipulator extends AbstractElementManipulator<TafatIdentifierImpl> {
	@Override
	public TafatIdentifierImpl handleContentChange(TafatIdentifierImpl element, TextRange range, String newContent)
		throws IncorrectOperationException {
		String newName = range.replace(element.getText(), newContent);
		element.replace(TafatElementFactoryImpl.getInstance(element.getProject()).createNameIdentifier(newName));
		return element;
	}
}
