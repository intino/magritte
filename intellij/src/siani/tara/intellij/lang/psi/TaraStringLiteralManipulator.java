package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class TaraStringLiteralManipulator extends AbstractElementManipulator<StringValue> {

	public static TextRange getStringTokenRange(final StringValue element) {
		return TextRange.from(1, element.getTextLength() - 2);
	}

	@Override
	public StringValue handleContentChange(@NotNull StringValue element, String newContent) throws IncorrectOperationException {
		return super.handleContentChange(element, newContent);
	}

	@Override
	public StringValue handleContentChange(@NotNull StringValue stringValue, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
		return (StringValue) stringValue.updateText(newContent);
	}

	@NotNull
	@Override
	public TextRange getRangeInElement(@NotNull final StringValue element) {
		return getStringTokenRange(element);
	}
}
