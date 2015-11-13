package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraStringLiteralScaper;
import tara.intellij.lang.psi.TaraTypes;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.util.Arrays;
import java.util.List;

import static tara.lang.model.Primitive.*;

public class ExpressionMixin extends ASTWrapperPsiElement {

	public ExpressionMixin(ASTNode node) {
		super(node);
	}

	private static List<Primitive> invalidTypes = Arrays.asList(REFERENCE, DATE, FILE);

	public String getValue() {
		return getCleanedValue();
	}

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
		return this.getText().trim().startsWith("--");
	}

	public boolean isValidHost() {
		return isValidType();
	}

	private boolean isValidType() {
		Primitive type = getType(getContainerOfExpression());
		return type != null && !invalidTypes.contains(type);
	}

	private Primitive getType(PsiElement element) {
		if (element instanceof Variable) return ((Variable) element).type();
		if (element instanceof Parameter) return ((Parameter) element).inferredType();
		return null;
	}

	private PsiElement getContainerOfExpression() {
		PsiElement element = this.getParent();
		while (element != null && !(element instanceof Variable) && !(element instanceof Parameter))
			element = element.getParent();
		return element;
	}


	public PsiLanguageInjectionHost updateText(@NotNull String text) {
		TaraElementFactory factory = TaraElementFactory.getInstance(getProject());
		String replace = text.startsWith("\'") ? text.substring(1, text.length() - 1) : text;
		final String indent = getIndent();
		final Expression expression = (Expression) (isMultiLine() ?
			factory.createMultiLineExpression(replace.trim(), oldIndentation(replace), indent, getQuote()) :
			factory.createExpression(replace.trim().replaceAll("\n+\t+", " ")));
		if (isMultiLine()) {
			expression.getFirstChild().replace(this.getFirstChild().copy());
			expression.getLastChild().getPrevSibling().replace(this.getLastChild().getPrevSibling().copy());
		}
		return expression != null ? (PsiLanguageInjectionHost) this.replace(expression) : (PsiLanguageInjectionHost) this;
	}

	public void toInline() {

	}

	public void toMultiline() {

	}

	private String oldIndentation(String body) {
		body = body.replace("     ", "\t");
		final String trimmed = body.trim();
		String indent = "";
		for (int i = 0; i < (body.length() - trimmed.length()); i++) indent += "\t";
		return indent;
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

	public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
		return new TaraStringLiteralScaper<PsiLanguageInjectionHost>((PsiLanguageInjectionHost) this) {
		};
	}
}
