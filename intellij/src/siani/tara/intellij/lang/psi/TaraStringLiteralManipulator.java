package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class TaraStringLiteralManipulator extends AbstractElementManipulator<StringValue> {


	@Override
	public StringValue handleContentChange(@NotNull StringValue element, String newContent) throws IncorrectOperationException {
		return super.handleContentChange(element, newContent);
	}

	@Override
	public StringValue handleContentChange(@NotNull StringValue psi, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
		final String oldText = psi.getText();
		final String newText = oldText.substring(0, range.getStartOffset()) + newContent + oldText.
			substring(range.getEndOffset());
		return (StringValue) psi.updateText(newText);
	}

	@NotNull
	@Override
	public TextRange getRangeInElement(@NotNull final StringValue element) {
		return getStringTokenRange(element);

	}

	public static TextRange getStringTokenRange(final StringValue element) {
		return TextRange.from(1, element.getTextLength() - 2);
	}


}
