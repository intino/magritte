package siani.tara.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract Concept createConcept(String name);

	public abstract TaraFileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Attribute createAttribute(String name, String type);

	public abstract Import createImport(String reference);

	public abstract TaraBox createBox(String reference);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Attribute createWord(String name, String[] types);

	public abstract Attribute createResource(String name, String types);

	public abstract MetaIdentifier createMetaIdentifier(String module, String name);

	public abstract PsiElement createMetaWordIdentifier(String module,String node, String name);
}