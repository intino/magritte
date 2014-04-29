package monet.::projectName::.intellij.annotator.imports;

import com.intellij.codeInsight.daemon.ReferenceImporter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::ReferenceImporter implements ReferenceImporter {
	\@Override
	public boolean autoImportReferenceAtCursor(\@NotNull final Editor editor, \@NotNull final PsiFile file) {
//		if (!(file instanceof ::projectProperName::File)) {
//			return false;
//		}
//		int caretOffset = editor.getCaretModel().getOffset();
//		Document document = editor.getDocument();
//		int lineNumber = document.getLineNumber(caretOffset);
//		int startOffset = document.getLineStartOffset(lineNumber);
//		int endOffset = document.getLineEndOffset(lineNumber);
//
//		List<PsiElement> elements = CollectHighlightsUtil.getElementsInRange(file, startOffset, endOffset);
//		for (PsiElement element \: elements) {
//			if (element instanceof PyReferenceExpression && isImportable(element)) {
//				final PyReferenceExpression refExpr = (PyReferenceExpression) element;
//				if (!refExpr.isQualified()) {
//					final PsiPolyVariantReference reference = refExpr.getReference();
//					if (reference.resolve() == null) {
//						AutoImportQuickFix fix = proposeImportFix(refExpr, reference);
//						if (fix != null && fix.getCandidatesCount() == 1) {
//							fix.invoke(file);
//						}
//						return true;
//					}
//				}
//			}
//		}
		return false;
	}

	\@Override
	public boolean autoImportReferenceAt(\@NotNull Editor editor, \@NotNull PsiFile file, int offset) {
//		if (!(file instanceof PyFile)) {
//			return false;
//		}
//		PsiReference element = file.findReferenceAt(offset);
//		if (element instanceof PyReferenceExpression && isImportable((PsiElement) element)) {
//			final PyReferenceExpression refExpr = (PyReferenceExpression) element;
//			if (!refExpr.isQualified()) {
//				final PsiPolyVariantReference reference = refExpr.getReference();
//				if (reference.resolve() == null) {
//					AutoImportQuickFix fix = proposeImportFix(refExpr, reference);
//					if (fix != null && fix.getCandidatesCount() == 1) {
//						fix.invoke(file);
//					}
//					return true;
//				}
//			}
//		}
		return false;
	}

	\@Nullable
	public static AutoImportQuickFix proposeImportFix(final Definition node, PsiReference reference) {
//		final String text = reference.getElement().getText();
//		final String refText = reference.getRangeInElement().substring(text); // text of the part we're working with
//
//		// don't propose meaningless auto imports if no interpreter is configured
//		final Module module = ModuleUtil.findModuleForPsiElement(node);
//		if (module != null && PythonSdkType.findPythonSdk(module) == null) {
//			return null;
//		}
//
//		// don't show auto-import fix if we're trying to reference a variable which is defined below in the same scope
//		ScopeOwner scopeOwner = PsiTreeUtil.getParentOfType(node, ScopeOwner.class);
//		if (scopeOwner != null && ControlFlowCache.getScope(scopeOwner).containsDeclaration(refText)) {
//			return null;
//		}
//
//		AutoImportQuickFix fix = new AutoImportQuickFix(node, reference, !PyCodeInsightSettings.getInstance().PREFER_FROM_IMPORT);
//		Set<String> seenFileNames = new HashSet<String>(); // true import names
//
//		PsiFile existingImportFile = addCandidatesFromExistingImports(node, refText, fix, seenFileNames);
//		if (fix.getCandidatesCount() == 0 || fix.hasProjectImports() || Registry.is("python.import.always.ask")) {
//			// maybe some unimported file has it, too
//			ProgressManager.checkCanceled(); // before expensive index searches
//			addSymbolImportCandidates(node, refText, fix, seenFileNames, existingImportFile);
//		}
//
//		for (PyImportCandidateProvider provider \: Extensions.getExtensions(PyImportCandidateProvider.EP_NAME)) {
//			provider.addImportCandidates(reference, refText, fix);
//		}
//		if (fix.getCandidatesCount() > 0) {
//			fix.sortCandidates();
//			return fix;
//		}
		return null;
	}

	/**
	 * maybe the name is importable via some existing 'import foo' statement, and only needs a qualifier.
	 * collect all such statements and analyze.
	 * NOTE\: It only makes sense to look at imports in file scope - there is no guarantee that an import in a local scope will
	 * be visible from the scope where the auto-import was invoked
	 *
	 * \@param node
	 * \@param refText
	 * \@param fix
	 * \@param seenFileNames
	 * \@return
	 */
//	\@Nullable
//	private static PsiFile addCandidatesFromExistingImports(PyElement node, String refText, AutoImportQuickFix fix,
//	                                                        Set<String> seenFileNames) {
//		PsiFile existingImportFile = null; // if there's a matching existing import, this it the file it imports
//		PsiFile file = node.getContainingFile();
//		if (file instanceof PyFile) {
//			PyFile pyFile = (PyFile) file;
//			for (PyImportElement importElement \: pyFile.getImportTargets()) {
//				existingImportFile = addImportViaElement(refText, fix, seenFileNames, existingImportFile, importElement, importElement.resolve());
//			}
//			for (PyFromImportStatement fromImportStatement \: pyFile.getFromImports()) {
//				if (!(fromImportStatement.isStarImport()) && fromImportStatement.getImportElements().length > 0) {
//					PsiElement source = fromImportStatement.resolveImportSource();
//					existingImportFile = addImportViaElement(refText, fix, seenFileNames, existingImportFile, fromImportStatement.getImportElements()[0], source);
//				}
//			}
//		}
//		return existingImportFile;
//	}

//	private static PsiFile addImportViaElement(String refText,
//	                                           AutoImportQuickFix fix,
//	                                           Set<String> seenFileNames,
//	                                           PsiFile existingImportFile,
//	                                           PyImportElement importElement,
//	                                           PsiElement source) {
//		PsiElement sourceFile = PyUtil.turnDirIntoInit(source);
//		if (sourceFile instanceof PyFileImpl) {
//			PyStatement importStatement = importElement.getContainingImportStatement();
//			String refName = null;
//			if (importStatement instanceof PyFromImportStatement) {
//				QualifiedName qName = ((PyFromImportStatement) importStatement).getImportSourceQName();
//				if (qName != null) {
//					refName = qName.toString();
//				}
//			} else {
//				QualifiedName importReferenceQName = importElement.getImportedQName();
//				if (importReferenceQName != null) {
//					refName = importReferenceQName.toString();
//				}
//			}
//			if (refName != null) {
//				if (seenFileNames.contains(refName)) {
//					return existingImportFile;
//				}
//				seenFileNames.add(refName);
//			}
//
//			PyFileImpl importSourceFile = (PyFileImpl) sourceFile;
//			PsiElement res = importSourceFile.findExportedName(refText);
//			// allow importing from this source if it either declares the name itself or represents a higher-level package that reexports the name
//			if (res != null && !(res instanceof PyFile) && !(res instanceof PyImportElement) && res.getContainingFile() != null &&
//				PsiTreeUtil.isAncestor(source, res.getContainingFile(), false)) {
//				existingImportFile = importSourceFile;
//				fix.addImport(res, importSourceFile, importElement);
//			}
//		}
//		return existingImportFile;
//	}

//	private static void addSymbolImportCandidates(PyElement node,
//	                                              String refText,
//	                                              AutoImportQuickFix fix,
//	                                              Set<String> seenFileNames,
//	                                              PsiFile existingImportFile) {
//		Project project = node.getProject();
//		List<PsiElement> symbols = new ArrayList<PsiElement>();
//		symbols.addAll(PyClassNameIndex.find(refText, project, true));
//		GlobalSearchScope scope = PyProjectScopeBuilder.excludeSdkTestsScope(node);
//		if (!isQualifier(node)) {
//			symbols.addAll(PyFunctionNameIndex.find(refText, project, scope));
//		}
//		symbols.addAll(PyVariableNameIndex.find(refText, project, scope));
//		if (isPossibleModuleReference(node)) {
//			symbols.addAll(findImportableModules(node.getContainingFile(), refText, project, scope));
//		}
//		if (!symbols.isEmpty()) {
//			for (PsiElement symbol \: symbols) {
//				if (isIndexableTopLevel(symbol)) { // we only want top-level symbols
//					PsiFileSystemItem srcfile = symbol instanceof PsiFileSystemItem ? ((PsiFileSystemItem) symbol).getParent() \: symbol.getContainingFile();
//					if (srcfile != null && isAcceptableForImport(node, existingImportFile, srcfile)) {
//						QualifiedName importPath = QualifiedNameFinder.findCanonicalImportPath(symbol, node);
//						if (symbol instanceof PsiFileSystemItem && importPath != null) {
//							importPath = importPath.removeTail(1);
//						}
//						if (importPath != null && !seenFileNames.contains(importPath.toString())) {
//							// a new, valid hit
//							fix.addImport(symbol, srcfile, importPath);
//							seenFileNames.add(importPath.toString()); // just in case, again
//						}
//					}
//				}
//			}
//		}
//	}
//
//	private static boolean isAcceptableForImport(PyElement node, PsiFile existingImportFile, PsiFileSystemItem srcfile) {
//		return srcfile != existingImportFile && srcfile != node.getContainingFile() &&
//			(ImportFromExistingAction.isRoot(srcfile) || PyNames.isIdentifier(FileUtil.getNameWithoutExtension(srcfile.getName()))) &&
//			!isShadowedModule(srcfile);
//	}
//
//	private static boolean isShadowedModule(PsiFileSystemItem file) {
//		if (file.isDirectory() || file.getName().equals(PyNames.INIT_DOT_PY)) {
//			return false;
//		}
//		String name = FileUtil.getNameWithoutExtension(file.getName());
//		final PsiDirectory directory = ((PsiFile) file).getContainingDirectory();
//		if (directory == null) {
//			return false;
//		}
//		PsiDirectory packageDir = directory.findSubdirectory(name);
//		return packageDir != null && packageDir.findFile(PyNames.INIT_DOT_PY) != null;
//	}
//
//	private static boolean isQualifier(PyElement node) {
//		return node.getParent() instanceof PyReferenceExpression && node == ((PyReferenceExpression) node.getParent()).getQualifier();
//	}
//
//	private static boolean isPossibleModuleReference(PyElement node) {
//		if (node.getParent() instanceof PyCallExpression && node == ((PyCallExpression) node.getParent()).getCallee()) {
//			return false;
//		}
//		if (node.getParent() instanceof PyArgumentList) {
//			final PyArgumentList argumentList = (PyArgumentList) node.getParent();
//			if (argumentList.getParent() instanceof PyClass) {
//				final PyClass pyClass = (PyClass) argumentList.getParent();
//				if (pyClass.getSuperClassExpressionList() == argumentList) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	private static Collection<PsiElement> findImportableModules(PsiFile targetFile, String reftext, Project project, GlobalSearchScope scope) {
//		List<PsiElement> result = new ArrayList<PsiElement>();
//		PsiFile[] files = FilenameIndex.getFilesByName(project, reftext + ".py", scope);
//		for (PsiFile file \: files) {
//			if (isImportableModule(targetFile, file)) {
//				result.add(file);
//			}
//		}
//		// perhaps the module is a directory, not a file
//		PsiFile[] initFiles = FilenameIndex.getFilesByName(project, PyNames.INIT_DOT_PY, scope);
//		for (PsiFile initFile \: initFiles) {
//			PsiDirectory parent = initFile.getParent();
//			if (parent != null && parent.getName().equals(reftext)) {
//				result.add(parent);
//			}
//		}
//		return result;
//	}
//
//	public static boolean isImportableModule(PsiFile targetFile, \@NotNull PsiFileSystemItem file) {
//		PsiDirectory parent = (PsiDirectory) file.getParent();
//		return parent != null && file != targetFile &&
//			(parent.findFile(PyNames.INIT_DOT_PY) != null ||
//				ImportFromExistingAction.isRoot(parent) ||
//				parent == targetFile.getParent());
//	}
//
//	private static boolean isIndexableTopLevel(PsiElement symbol) {
//		if (symbol instanceof PsiFileSystemItem) {
//			return true;
//		}
//		if (symbol instanceof PyClass || symbol instanceof PyFunction) {
//			return PyUtil.isTopLevel(symbol);
//		}
//		// only top-level target expressions are included in VariableNameIndex
//		return symbol instanceof PyTargetExpression;
//	}
//
//	public static boolean isImportable(PsiElement ref_element) {
//		PyStatement parentStatement = PsiTreeUtil.getParentOfType(ref_element, PyStatement.class);
//		if (parentStatement instanceof PyGlobalStatement || parentStatement instanceof PyNonlocalStatement ||
//			parentStatement instanceof PyImportStatementBase) {
//			return false;
//		}
//		return PsiTreeUtil.getParentOfType(ref_element, PyStringLiteralExpression.class, false, PyStatement.class) == null;
//	}
}