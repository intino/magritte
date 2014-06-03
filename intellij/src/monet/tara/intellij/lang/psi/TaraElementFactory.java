package monet.tara.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.lang.psi.impl.TaraFileImpl;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract Concept createConcept(String name);

	public abstract TaraFileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Attribute createAttribute(String name, String type);

	public abstract Import createImport(String reference);

	public abstract TaraPacket createPackage(String reference);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Attribute createWord(String name, String[] types);
}