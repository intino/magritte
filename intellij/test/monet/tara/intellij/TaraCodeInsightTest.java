package monet.tara.intellij;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.TaraConcept;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TaraCodeInsightTest extends LightCodeInsightFixtureTestCase {
	private static final String ANNOTATION = "annotation";
	private static final String COMPLETION = "completion";
	private static final String FIND_USAGES = "findUsages";
	private static final String FOLDING = "folding";
	private static final String REFERENCE = "reference";
	private static final String RENAME = "rename";


	private final String SEPARATOR = System.getProperty("path.separator");

	@Override
	protected String getTestDataPath() {
		return "res_test" + SEPARATOR;
	}

	public void testCompletion() {
		myFixture.configureByFiles(COMPLETION + SEPARATOR + "CompletionTestData.m2");
		myFixture.complete(CompletionType.BASIC, 1);
		List<String> strings = myFixture.getLookupElementStrings();
		assert strings != null;
		assertTrue(strings.containsAll(Arrays.asList("Feature")));
		assertEquals(1, strings.size());
	}

	public void testFindUsages() {
		Collection<UsageInfo> usageInfos = myFixture.testFindUsages(FIND_USAGES + SEPARATOR + "FindUsagesTestData.m2");
		assertEquals(2, usageInfos.size());
	}

	public void testFolding() {
		myFixture.testFolding(getTestDataPath() + FOLDING + SEPARATOR + "FoldingTest.m2");
	}

	public void testReference() {
		myFixture.configureByFiles(REFERENCE + SEPARATOR + "ReferenceTestData.m2");
		PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
		String radiator = ((TaraConcept) element.getReferences()[0].resolve()).getName();
		assertEquals("Radiator", radiator);
	}

	public void testAnnotator() {
		myFixture.configureByFiles(ANNOTATION + SEPARATOR + "AnnotatorTestData.m2");
		myFixture.checkHighlighting(true, true, true);
	}

	public void testRename() {
		myFixture.configureByFiles(RENAME + SEPARATOR + "RenameTestData.m2");
		myFixture.renameElementAtCaret("TermRenamed");
		myFixture.checkResultByFile(RENAME + SEPARATOR + "RenameTestData.m2", RENAME + SEPARATOR + "RenameTestDataAfter.m2", true);
	}

	public void testCommenter() {
		myFixture.configureByText(TaraFileType.INSTANCE, "<caret>website = http://en.wikipedia.org/");
		CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
		commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
		myFixture.checkResult("#website = http://en.wikipedia.org/");
		commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
		myFixture.checkResult("website = http://en.wikipedia.org/");
	}
}
