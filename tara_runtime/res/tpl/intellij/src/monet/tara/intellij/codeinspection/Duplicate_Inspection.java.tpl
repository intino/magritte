package monet.::projectName::.intellij.codeinspection;

import com.intellij.codeInspection.*;
import com.intellij.codeInspection.ex.GlobalInspectionContextImpl;
import com.intellij.codeInspection.reference.RefManager;
import com.intellij.concurrency.JobLauncher;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.util.ProgressWrapper;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Processor;
import com.intellij.util.text.CharArrayUtil;
import gnu.trove.THashSet;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by oroncal on 09/01/14.
 */
public class DuplicateDefinitionInspection extends GlobalSimpleInspectionTool {
	private static final Logger LOG = Logger.getInstance("#com.intellij.codeInspection.DuplicatePropertyInspection");

	public boolean CURRENT_FILE = true;
	public boolean MODULE_WITH_DEPENDENCIES = false;

	public boolean CHECK_DUPLICATE_IDENTIFIERS = true;

	\@Override
	public void checkFile(\@NotNull PsiFile file, \@NotNull InspectionManager manager, \@NotNull ProblemsHolder problemsHolder, \@NotNull GlobalInspectionContext globalContext, \@NotNull ProblemDescriptionsProcessor problemDescriptionsProcessor) {
		checkFile(file, manager, (GlobalInspectionContextImpl) globalContext, globalContext.getRefManager(), problemDescriptionsProcessor);
	}


	\@SuppressWarnings({"HardCodedStringLiteral"})
	private static void surroundWithHref(StringBuffer anchor, PsiElement element, final boolean isValue) {
		if (element != null) {
			final PsiElement parent = element.getParent();
			PsiElement elementToLink = isValue ? parent.getFirstChild() : parent.getLastChild();
			if (elementToLink != null) {
				HTMLComposer.appendAfterHeaderIndention(anchor);
				HTMLComposer.appendAfterHeaderIndention(anchor);
				anchor.append("<a HREF=\"");
				try {
					final PsiFile file = element.getContainingFile();
					if (file != null) {
						final VirtualFile virtualFile = file.getVirtualFile();
						if (virtualFile != null) {
							anchor.append(new URL(virtualFile.getUrl() + "#" + elementToLink.getTextRange().getStartOffset()));
						}
					}
				} catch (MalformedURLException e) {
					LOG.error(e);
				}
				anchor.append("\">");
				anchor.append(elementToLink.getText().replaceAll("\\$", "\\\\\\$"));
				anchor.append("</a>");
				compoundLineLink(anchor, element);
				anchor.append("<br>");
			}
		} else {
			anchor.append("<font style=\"font-family:verdana; font-weight:bold; color:#FF0000\";>");
			anchor.append(InspectionsBundle.message("inspection.export.results.invalidated.item"));
			anchor.append("</font>");
		}
	}

	\@SuppressWarnings({"HardCodedStringLiteral"})
	private static void compoundLineLink(StringBuffer lineAnchor, PsiElement psiElement) {
		final PsiFile file = psiElement.getContainingFile();
		if (file != null) {
			final VirtualFile vFile = file.getVirtualFile();
			if (vFile != null) {
				Document doc = FileDocumentManager.getInstance().getDocument(vFile);
				final int lineNumber = (doc != null ? doc.getLineNumber(psiElement.getTextOffset()) : 0) + 1;
				lineAnchor.append(" ").append(InspectionsBundle.message("inspection.export.results.at.line")).append(" ");
				lineAnchor.append("<a HREF=\"");
				try {
					assert doc != null;
					int offset = doc.getLineStartOffset(lineNumber - 1);
					offset = CharArrayUtil.shiftForward(doc.getCharsSequence(), offset, " \t");
					lineAnchor.append(new URL(vFile.getUrl() + "#" + offset));
				} catch (MalformedURLException e) {
					LOG.error(e);
				}
				lineAnchor.append("\">");
				lineAnchor.append(Integer.toString(lineNumber));
				lineAnchor.append("</a>");
			}
		}
	}

	private void checkFile(final PsiFile file, final InspectionManager manager, GlobalInspectionContextImpl context, final RefManager refManager, final ProblemDescriptionsProcessor processor) {
		if (!(file instanceof ::projectProperName::FileImpl) || !context.isToCheckFile(file, this)) return;
		final PsiSearchHelper searchHelper = PsiSearchHelper.SERVICE.getInstance(file.getProject());
		final ::projectProperName::FileImpl ::projectName::File = (::projectProperName::FileImpl) file;
		final List<Definition> definitions = ::projectProperName::Util.getRootDefinitions(::projectName::File.getProject());
		Module module = ModuleUtil.findModuleForPsiElement(file);
		if (module == null) return;
		final GlobalSearchScope scope = CURRENT_FILE
			? GlobalSearchScope.fileScope(file)
			: MODULE_WITH_DEPENDENCIES
			? GlobalSearchScope.moduleWithDependenciesScope(module)
			: GlobalSearchScope.projectScope(file.getProject());
		final Map<String, Set<PsiFile>> processedValueToFiles = Collections.synchronizedMap(new HashMap<String, Set<PsiFile>>());
		final Map<String, Set<PsiFile>> processedKeyToFiles = Collections.synchronizedMap(new HashMap<String, Set<PsiFile>>());
		final ProgressIndicator original = ProgressManager.getInstance().getProgressIndicator();
		final ProgressIndicator progress = ProgressWrapper.wrap(original);
		ProgressManager.getInstance().runProcess(new Runnable() {
			\@Override
			public void run() {
				if (!JobLauncher.getInstance().invokeConcurrentlyUnderProgress(definitions, progress, false, new Processor<Definition>() {
					\@Override
					public boolean process(final Definition definition) {
						if (original != null) {
							if (original.isCanceled()) return false;
							original.setText2(::projectProperName::Bundle.message("searching.for.definition.key.progress.text", definition.getName()));
						}
						processTextUsages(processedKeyToFiles, definition.getName(), processedValueToFiles, searchHelper, scope);
						return true;
					}
				})) throw new ProcessCanceledException();

				List<ProblemDescriptor> problemDescriptors = new ArrayList<>();
				Map<String, Set<String>> keyToDifferentValues = new HashMap<>();
				if (CHECK_DUPLICATE_IDENTIFIERS) {
					prepareDuplicateKeysByFile(processedKeyToFiles, manager, keyToDifferentValues, problemDescriptors, file, original);
				}
				if (!problemDescriptors.isEmpty()) {
					processor.addProblemElement(refManager.getReference(file),
						problemDescriptors.toArray(new ProblemDescriptor[problemDescriptors.size()]));
				}
			}
		}, progress);
	}

	private static void processTextUsages(final Map<String, Set<PsiFile>> processedTextToFiles,
	                                      final String text,
	                                      final Map<String, Set<PsiFile>> processedFoundTextToFiles,
	                                      final PsiSearchHelper searchHelper,
	                                      final GlobalSearchScope scope) {
		if (!processedTextToFiles.containsKey(text)) {
			if (processedFoundTextToFiles.containsKey(text)) {
				final Set<PsiFile> filesWithValue = processedFoundTextToFiles.get(text);
				processedTextToFiles.put(text, filesWithValue);
			} else {
				final Set<PsiFile> resultFiles = new HashSet<>();
				findFilesWithText(text, searchHelper, scope, resultFiles);
				if (resultFiles.isEmpty()) return;
				processedTextToFiles.put(text, resultFiles);
			}
		}
	}

	private void prepareDuplicateKeysByFile(final Map<String, Set<PsiFile>> keyToFiles,
	                                        final InspectionManager manager,
	                                        final Map<String, Set<String>> keyToValues,
	                                        final List<ProblemDescriptor> problemDescriptors,
	                                        final PsiFile psiFile,
	                                        final ProgressIndicator progress) {
		for (String key : keyToFiles.keySet()) {
			if (progress != null) {
				progress.setText2(::projectProperName::Bundle.message("duplicate.definition.key.progress.indicator.text", key));
				if (progress.isCanceled()) throw new ProcessCanceledException();
			}
			final StringBuffer message = new StringBuffer();
			int duplicatesCount = 0;
			Set<PsiFile> psiFilesWithDuplicates = keyToFiles.get(key);
			for (PsiFile file : psiFilesWithDuplicates) {
				if (!(file instanceof ::projectProperName::FileImpl)) continue;
				::projectProperName::FileImpl ::projectName::File = (::projectProperName::FileImpl) file;
				final List<Definition> definitionsByName = ::projectProperName::Util.findRootDefinition(::projectName::File.getProject(), key);
				for (Definition definition : definitionsByName) {
					if (duplicatesCount == 0)
						message.append(::projectProperName::Bundle.message("duplicate.definition.display.name", key));
					surroundWithHref(message, definition.getFirstChild(), false);
					duplicatesCount++;
				}
			}
			if (duplicatesCount > 1) {
				problemDescriptors.add(manager.createProblemDescriptor(psiFile, message.toString(), false, null, ProblemHighlightType.GENERIC_ERROR_OR_WARNING));
			}
		}

	}


	private static void findFilesWithText(String stringToFind,
	                                      PsiSearchHelper searchHelper,
	                                      GlobalSearchScope scope,
	                                      final Set<PsiFile> resultFiles) {
		final List<String> words = StringUtil.getWordsIn(stringToFind);
		if (words.isEmpty()) return;
		Collections.sort(words, new Comparator<String>() {
			\@Override
			public int compare(final String o1, final String o2) {
				return o2.length() - o1.length();
			}
		});
		for (String word : words) {
			final Set<PsiFile> files = new THashSet<>();
			searchHelper.processAllFilesWithWord(word, scope, new CommonProcessors.CollectProcessor<>(files), true);
			if (resultFiles.isEmpty())
				resultFiles.addAll(files);
			else
				resultFiles.retainAll(files);
			if (resultFiles.isEmpty()) return;
		}
	}

	\@Override
	\@NotNull
	public String getDisplayName() {
		return ::projectProperName::Bundle.message("duplicate.definition.display.name");
	}

	\@Override
	\@NotNull
	public String getGroupDisplayName() {
		return ::projectProperName::Bundle.message("group.names.::projectName::.files");
	}

	\@Override
	\@NotNull
	public String getShortName() {
		return "DuplicateDefinitionInspection";
	}

	\@Override
	public boolean isEnabledByDefault() {
		return false;
	}

	\@Override
	public JComponent createOptionsPanel() {
		return new OptionsPanel().myWholePanel;
	}

	public class OptionsPanel {
		private JRadioButton myFileScope;
		private JRadioButton myModuleScope;
		private JRadioButton myProjectScope;
		private JCheckBox myDuplicateKeys;
		private JPanel myWholePanel;

		OptionsPanel() {
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(myFileScope);
			buttonGroup.add(myModuleScope);
			buttonGroup.add(myProjectScope);

			myFileScope.setSelected(CURRENT_FILE);
			myModuleScope.setSelected(MODULE_WITH_DEPENDENCIES);
			myProjectScope.setSelected(!(CURRENT_FILE || MODULE_WITH_DEPENDENCIES));

			myFileScope.addActionListener(new ActionListener() {
				\@Override
				public void actionPerformed(ActionEvent e) {
					CURRENT_FILE = myFileScope.isSelected();
				}
			});
			myModuleScope.addActionListener(new ActionListener() {
				\@Override
				public void actionPerformed(ActionEvent e) {
					MODULE_WITH_DEPENDENCIES = myModuleScope.isSelected();
					if (MODULE_WITH_DEPENDENCIES) {
						CURRENT_FILE = false;
					}
				}
			});
			myProjectScope.addActionListener(new ActionListener() {
				\@Override
				public void actionPerformed(ActionEvent e) {
					if (myProjectScope.isSelected()) {
						CURRENT_FILE = false;
						MODULE_WITH_DEPENDENCIES = false;
					}
				}
			});

			myDuplicateKeys.setSelected(CHECK_DUPLICATE_IDENTIFIERS);
			myDuplicateKeys.addActionListener(new ActionListener() {
				\@Override
				public void actionPerformed(ActionEvent e) {
					CHECK_DUPLICATE_IDENTIFIERS = myDuplicateKeys.isSelected();
				}
			});
		}
	}
}