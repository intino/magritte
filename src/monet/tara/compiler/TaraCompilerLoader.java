package monet.tara.compiler;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.ui.EditorNotificationPanel;
import monet.tara.compiler.generator.TaracStubGenerator;
import monet.tara.metamodel.file.TaraFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;

public class TaraCompilerLoader extends AbstractProjectComponent {

	public TaraCompilerLoader(Project project) {
		super(project);
	}

	public void projectOpened() {
		CompilerManager compilerManager = CompilerManager.getInstance(myProject);
		compilerManager.addCompilableFileType(TaraFileType.INSTANCE);

		compilerManager.addTranslatingCompiler(new TaracStubGenerator(myProject),
				new HashSet<FileType>(Arrays.asList(StdFileTypes.JAVA, TaraFileType.INSTANCE)),
				new HashSet<FileType>(Arrays.asList(StdFileTypes.JAVA)));

		compilerManager.addTranslatingCompiler(new TaraCompiler(myProject),
				new HashSet<>(Arrays.asList(TaraFileType.INSTANCE, StdFileTypes.CLASS)),
				new HashSet<>(Arrays.asList(StdFileTypes.CLASS)));

		myProject.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerAdapter() {
			@Override
			public void fileOpened(@NotNull FileEditorManager source, @NotNull final VirtualFile file) {
				if (file.getName().endsWith(".m2") && file.getPath().contains(TaracStubGenerator.TARA_STUBS)) {
					final PsiClass psiClass = TaracStubGenerator.findClassByStub(myProject, file);
					if (psiClass != null) {
						final FileEditorManager fileEditorManager = FileEditorManager.getInstance(myProject);
						final FileEditor[] editors = fileEditorManager.getEditors(file);
						if (editors.length != 0)
							decorateStubFile(file, fileEditorManager, editors[0]);
					}
				}
			}
		});
	}

	private void decorateStubFile(final VirtualFile file, FileEditorManager fileEditorManager, FileEditor editor) {
		final EditorNotificationPanel panel = new EditorNotificationPanel();
		panel.setText("This stub is generated for Groovy class to make Groovy-Java cross-compilation possible");
//		panel.createActionLabel("Go to the Groovy class", new Runnable() {
//			@Override
//			public void run() {
//				final PsiClass original = GroovycStubGenerator.findClassByStub(myProject, file);
//				if (original != null) {
//					original.navigate(true);
//				}
//			}
//		});
//		panel.createActionLabel("Exclude from stub generation", new Runnable() {
//			@Override
//			public void run() {
//				final PsiClass psiClass = GroovycStubGenerator.findClassByStub(myProject, file);
//				if (psiClass != null) {
//					ExcludeFromStubGenerationAction.doExcludeFromStubGeneration(psiClass.getContainingFile());
//				}
//			}
//		});
//		fileEditorManager.addTopComponent(editor, panel);
	}

	@NotNull
	public String getComponentName() {
		return "TaraCompilerLoader";
	}
}

