package siani.tara.intellij;

import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import siani.tara.intellij.lang.file.TaraFileType;

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



	public void testFolding() {
		myFixture.testFolding(getTestDataPath() + FOLDING + SEPARATOR + "FoldingTest.m");
	}


	public void testAnnotator() {
		myFixture.configureByFiles(ANNOTATION + SEPARATOR + "AnnotatorTestData.m");
		myFixture.checkHighlighting(true, true, true);
	}

	public void testRename() {
		myFixture.configureByFiles(RENAME + SEPARATOR + "RenameTestData.m");
		myFixture.renameElementAtCaret("TermRenamed");
		myFixture.checkResultByFile(RENAME + SEPARATOR + "RenameTestData.m", RENAME + SEPARATOR + "RenameTestDataAfter.m", true);
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