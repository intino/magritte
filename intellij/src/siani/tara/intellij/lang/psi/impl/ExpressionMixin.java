package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Expression;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.TaraStringLiteralScaper;
import siani.tara.intellij.lang.psi.TaraTypes;

public class ExpressionMixin extends ASTWrapperPsiElement {

	public ExpressionMixin(ASTNode node) {
		super(node);
	}


	public String getValue() {
		return getCleanedValue();
	}

	@NotNull
	private String getCleanedValue() {
		if (!isMultiLine())
			return this.getText().substring(1, this.getTextLength() - 1);
		else return getCleanMultiLine();
	}

	private String getCleanMultiLine() {
		StringBuilder value = new StringBuilder();
		PsiElement child = this.getFirstChild();
		while (child != null) {
			if (!child.getNode().getElementType().equals(TaraTypes.EXPRESSION_BEGIN) && !child.getNode().getElementType().equals(TaraTypes.EXPRESSION_END))
				value.append(child.getText());
			child = child.getNextSibling();
		}
		return value.toString().trim();

	}

	public boolean isMultiLine() {
		return this.getText().trim().startsWith("-");
	}

	public boolean isValidHost() {
		return true;
	}


	public PsiLanguageInjectionHost updateText(@NotNull String text) {
		TaraElementFactory factory = TaraElementFactory.getInstance(getProject());
		String replace = text.startsWith("\"") ? text.substring(1, text.length() - 1) : text;
		final String indent = getIndent();
		final Expression expression = (Expression) (isMultiLine() ?
			factory.createMultiLineExpression(replace, indent, getQuote()) :
			factory.createExpression(replace));
		if (isMultiLine()) {
			expression.getFirstChild().replace(this.getFirstChild().copy());
			expression.getLastChild().getPrevSibling().replace(this.getLastChild().getPrevSibling().copy());
		}
		return (PsiLanguageInjectionHost) this.replace(expression);
	}

	private String getQuote() {
		PsiElement child = this.getFirstChild();
		while (child != null) {
			if (child.getNode().getElementType().equals(TaraTypes.EXPRESSION_BEGIN))
				return child.getText();
			child = child.getNextSibling();
		}
		return "";
	}

	private String getIndent() {
		PsiElement child = this.getFirstChild();
		while (child != null) {
			if (child.getNode().getElementType().equals(TaraTypes.NEWLINE))
				return child.getText().substring(1);
			child = child.getNextSibling();
		}
		return "";
	}

	@NotNull
	public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
		return new TaraStringLiteralScaper<PsiLanguageInjectionHost>((PsiLanguageInjectionHost) this) {
		};
	}
}
