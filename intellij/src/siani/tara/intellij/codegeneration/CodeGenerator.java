package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CodeGenerator {


	final Project project;
	final Module module;
	final TaraBoxFile file;
	final Inflector inflector;
	final PsiDirectory srcDirectory;
	final String[] FACETS_PATH;
	final String SRC = "src";
	final String MAGRITTE_MORPHS = "magritte.morphs";

	public CodeGenerator(TaraBoxFile file) {
		this.file = file;
		this.project = file.getProject();
		this.module = ModuleProvider.getModuleOf(file);
		inflector = InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) file.getManager(), getSrcDirectory(TaraUtil.getSourceRoots(file)));
		FACETS_PATH = new String[]{project.getName().toLowerCase(), "extensions"};
	}

	protected Inflector getInflector(PsiElement element) {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(element)).getLanguage());
	}

	protected PsiFile[] getFiles(Set<PsiClass> classes) {
		Set<PsiFile> psiFiles = new HashSet<>();
		for (PsiClass aClass : classes) psiFiles.add(aClass.getContainingFile());
		return psiFiles.toArray(new PsiFile[psiFiles.size()]);
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("Src directory not found");
	}

	PsiDirectory createPackageDirectory(final PsiDirectory parent, final String name) {
		PsiDirectory subdirectory = parent.findSubdirectory(name);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, parent.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = DirectoryUtil.createSubdirectories(name, parent, ".");
			}
		};
		action.execute();
		return destiny[0];
	}
}
