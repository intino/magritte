package siani.tara.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract Concept createConcept(String name);

	public abstract Concept createConcept(String name, String type);

	public abstract TaraBoxFileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Variable createAttribute(String name, String type);

	public abstract TaraImports createImport(String reference);

	public abstract TaraImports createMetamodelImport(String reference);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Parameters createParameters(String... string);

	public abstract Variable createWord(String name, String[] types);

	public abstract Variable createResource(String name, String types);

	public abstract PsiElement createMetaWordIdentifier(String module, String node, String name);

	public abstract TaraAddress createAddress(long address);

	public abstract Annotation createAnnotation(String name);

	public abstract TaraAnnotations createAnnotations(String name);
}