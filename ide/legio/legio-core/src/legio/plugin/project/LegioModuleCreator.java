package legio.plugin.project;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import legio.plugin.file.LegioFileType;
import org.siani.itrules.model.Frame;
import tara.intellij.lang.psi.TaraModel;

import java.io.File;

class LegioModuleCreator {
	private final Module module;

	LegioModuleCreator(Module module) {
		this.module = module;
	}

	public TaraModel create() {
		final String legio = LegioTemplate.create().format(new Frame().addTypes("legio").addSlot("name", module.getName()));
		final File destiny = new File(new File(module.getModuleFilePath()).getParent(), "configuration.legio");
		final PsiFile legioFile = PsiFileFactory.getInstance(module.getProject()).createFileFromText(destiny.getAbsolutePath(), LegioFileType.instance(), legio);
		return legioFile instanceof TaraModel ? (TaraModel) legioFile : null;
	}
}
