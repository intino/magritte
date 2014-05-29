package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class IdentifierManipulator extends AbstractElementManipulator<::projectProperName::IdentifierImpl> {
	\@Override
	public ::projectProperName::IdentifierImpl handleContentChange(\@NotNull ::projectProperName::IdentifierImpl element, \@NotNull TextRange range, String newContent) throws IncorrectOperationException {
		String newName = range.replace(element.getText(), newContent);
		element.replace(::projectProperName::ElementFactoryImpl.getInstance(element.getProject()).createNameIdentifier(newName));
		return element;
	}
}