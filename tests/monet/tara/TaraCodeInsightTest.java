package monet.tara;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import monet.tara.metamodel.file.TaraFileType;
import monet.tara.metamodel.psi.TaraConceptDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by oroncal on 03/01/14.
 */
public class TaraCodeInsightTest extends LightCodeInsightFixtureTestCase {
	@Override
	protected String getTestDataPath() {
		return "testData";
	}

	public void testCompletion() {
		myFixture.configureByFiles("completion/CompletionTestData.m2");
		myFixture.complete(CompletionType.BASIC, 1);
		List<String> strings = myFixture.getLookupElementStrings();
		assert strings != null;
		assertTrue(strings.containsAll(Arrays.asList("Feature")));
		assertEquals(1, strings.size());
	}

	public void testFindUsages() {
		Collection<UsageInfo> usageInfos = myFixture.testFindUsages("/findUsages/FindUsagesTestData.m2");
		assertEquals(2, usageInfos.size());
	}

	public void testFolding() {
		myFixture.testFolding(getTestDataPath() + "/folding/FoldingTest.m2");
	}

	public void testReference() {
		myFixture.configureByFiles("/reference/ReferenceTestData.m2");
		PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
		String radiator = ((TaraConceptDefinition) element.getReferences()[0].resolve()).getName();
		assertEquals("Radiator", radiator);
	}

	public void testRename() {
		myFixture.configureByFiles("/rename/RenameTestData.m2");
		myFixture.renameElementAtCaret("RadiatorRenamed");
		myFixture.checkResultByFile("/rename/RenameTestData.m2", "/rename/RenameTestDataAfter.m2", true);
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
