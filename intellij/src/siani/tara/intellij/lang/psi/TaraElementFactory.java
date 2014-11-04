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

	public abstract TaraBoxFileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Variable createAttribute(String name, String type);

	public abstract Import createImport(String reference);

	public abstract TaraBox createBox(String reference);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Variable createWord(String name, String[] types);

	public abstract Variable createResource(String name, String types);

	public abstract TaraBox createBoxReference(String project, String module, String packageName);

	public abstract PsiElement createMetaWordIdentifier(String module, String node, String name);

	public abstract TaraAddress createAddress(long address);

	public abstract TaraAnnotations createAnnotation(String name);

	public abstract TaraAnnotationsAndFacets createAnnotationAndFacetWith(String name);
}