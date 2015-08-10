package tara.intellij.framework;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;

public class FrameworkSetupHelper {


	protected static Library addProjectLibrary(final Module module, final String name, final File jarsDirectory, final VirtualFile[] sources) {
		return new WriteAction<Library>() {
			protected void run(@NotNull final Result<Library> result) throws MalformedURLException {
				final LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(module.getProject());
				Library library = libraryTable.getLibraryByName(name);
				if (library == null) {
					library = libraryTable.createLibrary(name);
					final Library.ModifiableModel model = library.getModifiableModel();
					final VirtualFile vFile = VfsUtil.findFileByURL(jarsDirectory.toURI().toURL());
					if (vFile == null) return;
					vFile.refresh(true, true);
					model.addJarDirectory(vFile, false);
					for (VirtualFile sourceRoot : sources) model.addRoot(sourceRoot, OrderRootType.SOURCES);
					model.commit();
				}
				result.setResult(library);
			}
		}.execute().getResultObject();
	}
}
