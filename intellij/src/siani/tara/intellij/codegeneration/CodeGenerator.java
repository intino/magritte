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
import siani.tara.intellij.TaraRuntimeException;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CodeGenerator {

	final Project project;
	final Module module;
	final TaraModel file;
	final Inflector inflector;
	final PsiDirectory srcDirectory;
	final String[] facetsPath;
	static final String SRC = "src";
	static final String MAGRITTE_MORPHS = "magritte.morphs";

	public CodeGenerator(TaraModel file) {
		this.file = file;
		this.project = file.getProject();
		this.module = ModuleProvider.getModuleOf(file);
		inflector = InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) file.getManager(), getSrcDirectory(TaraUtil.getSourceRoots(file)));
		facetsPath = new String[]{project.getName().toLowerCase(), "extensions"};
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
		for (VirtualFile virtualFile : virtualFiles)
			if (virtualFile.isDirectory() && SRC.equals(virtualFile.getName())) return virtualFile;
		throw new TaraRuntimeException("Src directory not found");
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
