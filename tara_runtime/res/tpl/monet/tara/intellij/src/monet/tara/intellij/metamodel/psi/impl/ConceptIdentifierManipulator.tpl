package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;

public class ::projectProperName::DefinitionIdentifierManipulator extends AbstractElementManipulator<::projectProperName::IdentifierImpl> {
	@Override
	public ::projectProperName::IdentifierImpl handleContentChange(::projectProperName::IdentifierImpl element, TextRange range, String newContent)
		throws IncorrectOperationException {
		String newName = range.replace(element.getText(), newContent);
		element.replace(::projectProperName::ElementFactoryImpl.getInstance(element.getProject()).createNameIdentifier(newName));
		return element;
	}
}
