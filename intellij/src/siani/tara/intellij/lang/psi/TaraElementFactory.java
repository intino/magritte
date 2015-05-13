package siani.tara.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.Map;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract Node createConcept(String name);

	public abstract Node createConcept(String name, String type);

	public abstract TaraModelImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Variable createAttribute(String name, String type);

	public abstract TaraImports createImport(String reference);

	public abstract TaraDslDeclaration createDslDeclaration(String name);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Parameters createParameters(String... string);

	public abstract Parameters createExplicitParameters(Map<String, String> parameters);

	public abstract Variable createWord(String name, String[] types);

	public abstract Variable createResource(String name, String types);

	public abstract PsiElement createMetaWordIdentifier(String module, String node, String name);

	public abstract TaraAddress createAddress(String address);

	public abstract Annotation createAnnotation(String name);

	public abstract Annotations createAnnotations(String name);

	public abstract PsiElement createNewLineIndent();

	public abstract PsiElement createNewLineIndent(int level);

	public abstract PsiElement createInlineNewLineIndent();

	public abstract PsiElement createWhiteSpace();

	public abstract PsiElement createBodyNewLine();

	public abstract PsiElement createBodyNewLine(int level);

	public abstract PsiElement createInlineNewLine();
}