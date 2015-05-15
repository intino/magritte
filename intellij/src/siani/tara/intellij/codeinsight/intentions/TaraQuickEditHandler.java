package siani.tara.intellij.codeinsight.intentions;


import com.intellij.codeInsight.actions.AbstractLayoutCodeProcessor;
import com.intellij.codeInsight.actions.OptimizeImportsProcessor;
import com.intellij.codeInsight.actions.RearrangeCodeProcessor;
import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.ReadonlyFragmentModificationHandler;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.EditorFactoryAdapter;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.ex.DocumentEx;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.fileEditor.impl.EditorWithProviderComposite;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import siani.tara.intellij.framework.maven.NativeTemplate;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.StringValue;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;

import javax.swing.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;

public class TaraQuickEditHandler extends DocumentAdapter implements Disposable {
	private final Project myProject;
	private final PsiFile origFile;
	private final StringValue stringValue;
	private final Editor myEditor;
	private final Document myOrigDocument;
	private final String languageName;
	private Document myNewDocument;
	private PsiFile myNewFile;
	private LightVirtualFile myNewVirtualFile;

	private EditorWindow mySplittedWindow;

	private static final Key<String> REPLACEMENT_KEY = Key.create("REPLACEMENT_KEY");
	public static final Key<TaraQuickEditHandler> QUICK_EDIT_HANDLER = Key.create("QUICK_EDIT_HANDLER");

	TaraQuickEditHandler(Project project, final PsiFile origFile, StringValue stringValue, Editor editor) {
		this.myProject = project;
		this.origFile = origFile;
		this.stringValue = stringValue;
		this.languageName = TaraUtil.getLanguage(stringValue).languageName();
		this.myEditor = editor;
		this.myOrigDocument = editor.getDocument();
		init(project, origFile, stringValue);
	}

	private void init(Project project, PsiFile origFile, StringValue stringValue) {
		FileType fileType = JavaFileType.INSTANCE;
		Language language = JavaLanguage.INSTANCE;
		PsiFileFactory factory = PsiFileFactory.getInstance(project);
		String text = createTextFromTemplate(stringValue);
		String newFileName = buildFileName(origFile, fileType, language);
		myNewFile = factory.createFileFromText(newFileName, language, text, true, false);
		AbstractLayoutCodeProcessor processor = new ReformatCodeProcessor(myProject, myNewFile, null, false);
		processor = new OptimizeImportsProcessor(processor);
		processor = new RearrangeCodeProcessor(processor);
		processor.run();
		myNewVirtualFile = ObjectUtils.assertNotNull((LightVirtualFile) myNewFile.getVirtualFile());
		myNewVirtualFile.setOriginalFile(origFile.getVirtualFile());
		assert myNewFile.getTextLength() == myNewVirtualFile.getContent().length() : "PSI / Virtual file text mismatch";
		myNewVirtualFile.setOriginalFile(origFile.getVirtualFile());
		myNewDocument = PsiDocumentManager.getInstance(project).getDocument(myNewFile);
		assert myNewDocument != null;
		EditorActionManager.getInstance().setReadonlyFragmentModificationHandler(myNewDocument, new MyQuietHandler());
		myOrigDocument.addDocumentListener(this, this);
		myNewDocument.addDocumentListener(this, this);
		EditorFactory editorFactory = ObjectUtils.assertNotNull(EditorFactory.getInstance());
		editorFactory.addEditorFactoryListener(createEditorFactoryAdapter(), this);
	}

	@NotNull
	private String buildFileName(PsiFile origFile, FileType fileType, Language language) {
		return StringUtil.notNullize(language.getDisplayName(), "Injected") + " Fragment " + "(" + origFile.getName() + ":" + "1" + ")" + "." + fileType.getDefaultExtension();
	}

	private String createTextFromTemplate(StringValue stringValue) {
		Frame frame = createFrame(stringValue);
		return NativeTemplate.create().format(frame);
	}

	@NotNull
	private Frame createFrame(StringValue stringValue) {
		Frame frame = new Frame().addTypes("native");
		VarInit variable = getVariable(stringValue);
		Node node = getContainerNode(variable);
		frame.addFrame("module", ModuleProvider.getModuleOf(stringValue).getName().toLowerCase());
		frame.addFrame("language", languageName.toLowerCase());
		Allow.Parameter correspondingAllow = TaraUtil.getCorrespondingAllow(node, variable);
		if (correspondingAllow != null) frame.addFrame("intention", correspondingAllow.contract());

		frame.addFrame("variable", variable.getName()).
			addFrame("qn", node.getQualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", firstNamedNode(node).replace(".", "_")).
			addFrame("body", stringValue.getValue().replace("\\n", "\n").replace("\\\"", "\""));
		return frame;
	}

	private String firstNamedNode(Node node) {
		if (node.getName() != null) return node.getQualifiedName();
		Node candidate = node;
		while (candidate != null && candidate.getName() == null) candidate = candidate.container();
		return candidate == null ? "" : candidate.getQualifiedName();
	}

	private Node getContainerNode(VarInit varInit) {
		return (Node) getParentByType(varInit, Node.class);
	}

	private VarInit getVariable(StringValue stringValue) {
		return (VarInit) getParentByType(stringValue, VarInit.class);
	}


	@SuppressWarnings("ConstantConditions")
	public void navigate() {
		final FileEditorManagerEx fileEditorManager = FileEditorManagerEx.getInstanceEx(myProject);
		final FileEditor[] editors = fileEditorManager.getEditors(myNewVirtualFile);
		if (editors.length == 0) {
			final EditorWindow curWindow = fileEditorManager.getCurrentWindow();
			mySplittedWindow = curWindow.split(SwingConstants.HORIZONTAL, false, myNewVirtualFile, true);
		}
		PsiMethod executeMethod = getExecuteMethod();
		int offset = isWellBuilt(executeMethod) ? 0 : executeMethod.getBody().getFirstBodyElement().getTextOffset();
		Editor editor = fileEditorManager.openTextEditor(new OpenFileDescriptor(myProject, myNewVirtualFile, offset + 2), true);
		if (editor != null) {
			editor.putUserData(QUICK_EDIT_HANDLER, this);
			final FoldingModel foldingModel = editor.getFoldingModel();
			foldingModel.runBatchFoldingOperation(new Runnable() {
				@Override
				public void run() {
					for (RangeMarker o : ContainerUtil.reverse(((DocumentEx) myNewDocument).getGuardedBlocks())) {
						String replacement = o.getUserData(REPLACEMENT_KEY);
						if (StringUtil.isEmpty(replacement)) continue;
						FoldRegion region = foldingModel.addFoldRegion(o.getStartOffset(), o.getEndOffset(), replacement);
						if (region != null) region.setExpanded(false);
					}
				}
			});
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				myEditor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
			}
		});

	}

	private boolean isWellBuilt(PsiMethod executeMethod) {
		return executeMethod == null || executeMethod.getBody() == null || executeMethod.getBody().getFirstBodyElement() == null;
	}

	@NotNull
	private EditorFactoryAdapter createEditorFactoryAdapter() {
		return new EditorFactoryAdapter() {
			int myEditorCount;

			@Override
			public void editorCreated(@NotNull EditorFactoryEvent event) {
				if (event.getEditor().getDocument() != myNewDocument) return;
				myEditorCount++;
				final EditorActionHandler editorEscape = EditorActionManager.getInstance().getActionHandler(IdeActions.ACTION_EDITOR_ESCAPE);
				new AnAction() {
					@Override
					public void update(AnActionEvent e) {
						Editor editor = CommonDataKeys.EDITOR.getData(e.getDataContext());
						e.getPresentation().setEnabled(
							editor != null && LookupManager.getActiveLookup(editor) == null &&
								TemplateManager.getInstance(myProject).getActiveTemplate(editor) == null &&
								(editorEscape == null || !editorEscape.isEnabled(editor, e.getDataContext())));
					}

					@Override
					public void actionPerformed(AnActionEvent e) {
						closeEditor();
					}
				}.registerCustomShortcutSet(CommonShortcuts.ESCAPE, event.getEditor().getContentComponent());
			}

			@Override
			public void editorReleased(@NotNull EditorFactoryEvent event) {
				if (event.getEditor().getDocument() != myNewDocument) return;
				if (--myEditorCount > 0) return;
				if (Boolean.TRUE.equals(myNewVirtualFile.getUserData(FileEditorManagerImpl.CLOSING_TO_REOPEN))) return;
				updateText();
				Disposer.dispose(TaraQuickEditHandler.this);
			}
		};
	}

	private void updateText() {
		WriteCommandAction action = new WriteCommandAction(myProject, origFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				stringValue.updateText(obtainBodyText());
			}
		};
		action.execute();
	}

	private String obtainBodyText() {
		PsiMethod method = getExecuteMethod();
		if (method == null || method.getBody() == null) return "";
		return method.getBody().getText().substring(1, method.getBody().getText().length() - 1).trim();
	}

	private PsiMethod getExecuteMethod() {
		PsiMethod[] methods = ((PsiJavaFileImpl) myNewFile).getClasses()[0].getMethods();
		for (PsiMethod method : methods)
			if ("execute".equals(method.getName()))
				return method;
		return null;
	}

	private void closeEditor() {
		boolean unsplit = false;
		if (mySplittedWindow != null && !mySplittedWindow.isDisposed()) {
			final EditorWithProviderComposite[] editors = mySplittedWindow.getEditors();
			if (editors.length == 1 && Comparing.equal(editors[0].getFile(), myNewVirtualFile)) unsplit = true;
		}
		FileEditorManager.getInstance(myProject).closeFile(myNewVirtualFile);
		if (unsplit)
			for (EditorWindow editorWindow : mySplittedWindow.findSiblings()) editorWindow.unsplit(true);
	}

	@Override
	public void dispose() {

	}

	private static class MyQuietHandler implements ReadonlyFragmentModificationHandler {
		@Override
		public void handle(final ReadOnlyFragmentModificationException e) {
			//nothing
		}
	}
}

