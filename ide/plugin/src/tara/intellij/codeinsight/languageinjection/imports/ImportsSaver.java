package tara.intellij.codeinsight.languageinjection.imports;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.NodeContainer;
import tara.lang.model.Primitive;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportsSaver implements ProjectComponent {

	private final Project project;
	private Imports imports;
	private MessageBusConnection connection;

	protected ImportsSaver(Project project, FileEditorManager fileEditorManager) {
		this.project = project;
		if (project.isInitialized()) imports = new Imports(project);
		else imports = null;
	}

	private final FileEditorManagerListener myListener = new FileEditorManagerListener() {
		@Override
		public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
		}

		@Override
		public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile sourceFile) {
			if (!sourceFile.isValid() || !project.isInitialized()) return;
			final PsiFile file = PsiManager.getInstance(project).findFile(sourceFile);
			if (!isJavaNativeScratch(file)) return;
			if (imports == null) imports = new Imports(project);
			String moduleName = getModuleName(source);
			String qn = getQn(source);
			if (moduleName == null || qn.isEmpty()) return;
			WriteCommandAction.runWriteCommandAction(project, () -> imports.save(moduleName, qn, getImports(file)));
			removeOldComments(file);
		}

		@Override
		public void selectionChanged(@NotNull FileEditorManagerEvent event) {
		}
	};


	private String getQn(FileEditorManager source) {
		final PsiFile taraFile = PsiManager.getInstance(project).findFile(source.getSelectedFiles()[0]);
		if (taraFile == null) return "";
		final FileEditor editor = source.getSelectedEditor(source.getSelectedFiles()[0]);
		if (editor == null) return "";
		final PsiElement elementAt = taraFile.findElementAt(((PsiAwareTextEditorImpl) editor).getEditor().getCaretModel().getOffset());
		if (elementAt == null) return "";
		final Valued valued = TaraPsiImplUtil.getContainerByType(elementAt, Valued.class);
		final NodeContainer container = TaraPsiImplUtil.getContainerOf(valued);
		if (container == null || valued == null) return "";
		return container.qualifiedName() + "." + valued.name();
	}

	private String getModuleName(FileEditorManager source) {
		final PsiFile taraFile = PsiManager.getInstance(project).findFile(source.getSelectedFiles()[0]);
		source.getSelectedEditor(source.getSelectedFiles()[0]);
		if (taraFile == null) return null;
		return ModuleProvider.getModuleOf(taraFile).getName();
	}

	private boolean isJavaNativeScratch(PsiFile file) {
		return file != null && file.getName().startsWith("Java Fragment");
	}

	public Set<String> getImports(PsiFile file) {
		if (file == null) return Collections.emptySet();
		final PsiImportList importList = ((PsiJavaFile) file).getImportList();
		if (importList == null) return Collections.emptySet();
		return Arrays.asList(importList.getAllImportStatements()).stream().map(PsiElement::getText).collect(Collectors.toSet());
	}

	@Override
	public void projectOpened() {
		StartupManager.getInstance(project).runWhenProjectIsInitialized(this::initListener);
	}

	private void initListener() {
		connection = project.getMessageBus().connect(project);
		connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, myListener);
	}

	@Override
	public void projectClosed() {
		disposeComponent();
	}

	@Override
	public void initComponent() {

	}

	@Override
	public void disposeComponent() {
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "Native Imports saver";
	}


	@Deprecated
	private void removeOldComments(PsiFile file) {
		PsiClass psiClass = getInterface((PsiJavaFile) file);
		if (psiClass == null) return;
		WriteCommandAction.runWriteCommandAction(file.getProject(), () -> {
			if (psiClass.getDocComment() != null) psiClass.getDocComment().delete();
		});
	}

	public PsiClass getInterface(PsiJavaFile file) {
		if (file.getClasses().length == 0) return null;
		for (PsiClass aClass : file.getClasses()[0].getInterfaces())
			if (!aClass.getName().equalsIgnoreCase(Primitive.FUNCTION.getName())) return aClass;
		return null;
	}
}
