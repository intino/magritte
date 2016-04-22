package tara.intellij.actions;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import tara.Language;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestClassCreator {


	static void creteTestClass(Module module, String dsl, String newName) {
		final PsiDirectory psiDirectory = testDirectory(module);
		if (psiDirectory == null) return;
		final TaraFacetConfiguration conf = TaraFacet.of(module).getConfiguration();
		final PsiClass aClass = JavaDirectoryService.getInstance().createClass(psiDirectory, newName + "Test", "Tara" + (conf.isOntology() ? "Ontology" : "") + "Test", false, templateParameters(module, conf, dsl, newName));
		assert aClass != null;
		VfsUtil.markDirtyAndRefresh(true, true, true, psiDirectory.getVirtualFile());
	}

	private static Map<String, String> templateParameters(Module module, TaraFacetConfiguration conf, String dsl, String newName) {
		Map<String, String> map = new HashMap();
		map.put("NAME", newName);
		final Language language = LanguageManager.getLanguage(dsl, false, module.getProject());
		map.put("APPLICATION", conf.applicationOutDsl());
		if (language != null) map.put("PLATFORM", language.metaLanguage());
		return map;
	}

	private static PsiDirectory testDirectory(Module module) {
		final List<VirtualFile> sourceRoots = TaraUtil.getSourceRoots(module);
		for (VirtualFile sourceRoot : sourceRoots)
			if (sourceRoot.isDirectory() && "test".equals(sourceRoot.getName()))
				return PsiManager.getInstance(module.getProject()).findDirectory(sourceRoot);
		return null;
	}
}
