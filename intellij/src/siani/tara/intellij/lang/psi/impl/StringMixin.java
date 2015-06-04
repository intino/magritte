package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import static siani.tara.intellij.lang.lexer.TaraPrimitives.NATIVE;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getCorrespondingAllow;

public class StringMixin extends ASTWrapperPsiElement {

	public StringMixin(ASTNode astNode) {
		super(astNode);
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
			if (!child.getNode().getElementType().equals(TaraTypes.QUOTE_BEGIN) && !child.getNode().getElementType().equals(TaraTypes.QUOTE_END))
				value.append(child.getText());
			child = child.getNextSibling();
		}
		return value.toString().trim();

	}

	public boolean isValidHost() {
		return isNativeValue();
	}

	private boolean isNativeValue() {
		PsiElement element = this;
		while (element != null) {
			if (element instanceof Variable || element instanceof VarInit || element instanceof Parameter) break;
			element = element.getContext();
		}
		return element != null && isNative(element);
	}

	private boolean isNative(PsiElement element) {
		return element instanceof Parameter && NATIVE.equals(getCorrespondingAllow(TaraPsiImplUtil.getContainerNodeOf(element), ((Parameter) element)).type()) ||
			element instanceof VarInit && NATIVE.equals(getCorrespondingAllow(TaraPsiImplUtil.getContainerNodeOf(element), ((VarInit) element)).type()) ||
			element instanceof Variable && NATIVE.equals(((Variable) element).getType());
	}

	public boolean isMultiLine() {
		return this.getText().trim().startsWith("-");
	}


	public PsiLanguageInjectionHost updateText(@NotNull String text) {
		TaraElementFactory factory = TaraElementFactory.getInstance(getProject());
		String replace = text.startsWith("\"") ? text.substring(1, text.length() - 1) : text;
		final String indent = getIndent();
		final StringValue string = (StringValue) (isMultiLine() ?
			factory.createMultiLineString(replace, indent, getQuote()) :
			factory.createString(replace));
		if (isMultiLine()) {
			string.getFirstChild().replace(this.getFirstChild().copy());
			string.getLastChild().getPrevSibling().replace(this.getLastChild().getPrevSibling().copy());
		}
		return (PsiLanguageInjectionHost) this.replace(string);
	}

	private String getQuote() {
		PsiElement child = this.getFirstChild();
		while (child != null) {
			if (child.getNode().getElementType().equals(TaraTypes.QUOTE_BEGIN))
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
