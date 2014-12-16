package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.codegeneration.MeasureClassCreator;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CreateMeasureClassIntention implements IntentionAction {

	private final String className;
	private final String destinyPath;
	public static final String SRC = "src";


	public CreateMeasureClassIntention(String className, String destinyPath) {
		this.className = className;
		this.destinyPath = destinyPath;
	}

	@NotNull
	@Override
	public String getText() {
		return "Create " + className + " class";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create class";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file instanceof TaraBoxFile;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(file));
		PsiDirectoryImpl srcPsiDirectory = new PsiDirectoryImpl((PsiManagerImpl) file.getManager(), srcDirectory);
		PsiDirectory destiny = findDestiny(file, srcPsiDirectory, destinyPath);
		MeasureClassCreator creator = new MeasureClassCreator(project, destiny, className);
		creator.createClass();
	}

	private PsiDirectory findDestiny(PsiFile file, final PsiDirectoryImpl srcDirectory, final String destinyName) {
		PsiDirectory subdirectory = srcDirectory.findSubdirectory(destinyName);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] destiny = createPath(file, srcDirectory, destinyName);
		return destiny[0];
	}

	private PsiDirectory[] createPath(final PsiFile file, final PsiDirectoryImpl srcDirectory, final String destinyName) {
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(file.getProject(), file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = srcDirectory;
				List<String> split = Arrays.asList(destinyName.split("\\."));
				for (String subDirName : split) {
					if (destiny[0] == null) break;
					destiny[0] = destiny[0].findSubdirectory(subDirName) == null ?
						DirectoryUtil.createSubdirectories(subDirName, destiny[0], ".") :
						destiny[0].findSubdirectory(subDirName);
				}
			}
		};
		action.execute();
		return destiny;
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
