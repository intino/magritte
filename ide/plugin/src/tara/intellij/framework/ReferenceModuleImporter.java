package tara.intellij.framework;

import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class ReferenceModuleImporter extends FrameworkSetupHelper {

	private static final String FRAMEWORK = "framework";
	private static final String TARA_PREFIX = "Tara -> ";
	private static final String DSL = "dsl";
	private final File referenceModel;
	private final String dsl;

	public ReferenceModuleImporter(File referenceModel, String dsl) {
		this.referenceModel = referenceModel;
		this.dsl = dsl;
	}

	void importFromReferenceModule(ModifiableRootModel rootModel) {
		File dslDirectory = new File(referenceModel, DSL);
		addDslLibToProject(rootModel, dslDirectory);
	}

	private void addDslLibToProject(ModifiableRootModel rootModel, File dslDirectory) {
		final Library library = addProjectLibrary(rootModel.getModule(), TARA_PREFIX + dsl, dslDirectory, VirtualFile.EMPTY_ARRAY);
		rootModel.addLibraryEntry(library);
	}


}
