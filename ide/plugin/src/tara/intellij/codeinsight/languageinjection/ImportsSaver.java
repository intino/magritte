package tara.intellij.codeinsight.languageinjection;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import tara.lang.model.Primitive;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportsSaver implements ProjectComponent {

	private final Project project;
	private MessageBusConnection connection;

	protected ImportsSaver(Project project, FileEditorManager fileEditorManager) {
		this.project = project;
	}

	private final FileEditorManagerListener myListener = new FileEditorManagerListener() {
		@Override
		public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
		}

		@Override
		public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile sourceFile) {
			final PsiFile file = PsiManager.getInstance(project).findFile(sourceFile);
			if (!isJavaNativeScratch(file)) return;
			final Set<String> imports = getImports(file);
			PsiClass psiClass = getInterface((PsiJavaFile) file);
			if (psiClass == null) return;
			WriteCommandAction.runWriteCommandAction(project, () -> addImportsAsComments(psiClass, imports));
		}

		@Override
		public void selectionChanged(@NotNull FileEditorManagerEvent event) {
		}
	};

	private void addImportsAsComments(PsiClass psiClass, Set<String> imports) {
		imports.addAll(getBuiltImports(psiClass));
		final PsiDocComment doc = JavaPsiFacade.getInstance(project).getElementFactory().createDocCommentFromText("/**\n*" + String.join("\n* ", imports) + "\n*/");
		if (psiClass.getDocComment() == null) {
			final PsiElement ws = PsiParserFacade.SERVICE.getInstance(psiClass.getProject()).createWhiteSpaceFromText("\n");
			psiClass.getNode().addChild(ws.copy().getNode(), psiClass.getFirstChild().getNode());
			psiClass.getNode().addChild(doc.copy().getNode(), psiClass.getFirstChild().getNode());
		} else psiClass.getDocComment().replace(doc.copy());
	}

	private Set<String> getBuiltImports(PsiClass psiClass) {
		if (psiClass.getDocComment() == null) return Collections.emptySet();
		final String[] text = psiClass.getDocComment().getText().split("\n");
		Set<String> set = new HashSet<>();
		for (String line : text)
			if (line.contains("import ")) set.add(line.trim().startsWith("*") ? line.trim().substring(1).trim() : line.trim());
		return set;
	}

	public PsiClass getInterface(PsiJavaFile file) {
		for (PsiClass aClass : file.getClasses()[0].getInterfaces())
			if (!aClass.getName().equalsIgnoreCase(Primitive.FUNCTION.getName())) return aClass;
		return null;
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
}
