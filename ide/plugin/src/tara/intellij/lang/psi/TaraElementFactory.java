package tara.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.Node;
import tara.lang.model.Primitive;

import java.util.Map;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract Node createNode(String name);

	public abstract TaraNode createNode(String name, String type);

	public abstract TaraModelImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract TaraVariable createVariable(String name, Primitive type);

	public abstract TaraImports createImport(String reference);

	public abstract TaraDslDeclaration createDslDeclaration(String name);

	public abstract PsiElement createNewLine();

	public abstract PsiElement createEmptyParameters();

	public abstract Parameters createParameters(boolean string);

	public abstract Parameters createParameters(String... string);

	public abstract Parameters createExplicitParameters(Map<String, String> parameters);

	public abstract PsiElement createParameterSeparator();

	public abstract TaraVariable createWord(String name, String[] types);

	public abstract TaraVariable createResource(String name, String types);

	public abstract PsiElement createMetaWordIdentifier(String module, String node, String name);

	public abstract TaraAddress createAddress(String address);

	public abstract TaraAnnotation createAnnotation(String name);

	public abstract TaraAnnotations createAnnotations(String name);

	public abstract PsiElement createNewLineIndent();

	public abstract PsiElement createNewLineIndent(int level);

	public abstract PsiElement createInlineNewLineIndent();

	public abstract PsiElement createWhiteSpace();

	public abstract PsiElement createBodyNewLine();

	public abstract PsiElement createBodyNewLine(int level);

	public abstract PsiElement createInlineNewLine();

	public abstract PsiElement createExpression(String text);

	public abstract PsiElement createMultiLineExpression(String text, String oldIndent, String indent, String quote);
}