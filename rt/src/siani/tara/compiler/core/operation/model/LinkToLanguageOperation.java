package siani.tara.compiler.core.operation.model;

import siani.tara.Language;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.dependencyresolver.LanguageDependencyResolver;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.model.LanguageLoader;
import siani.tara.model.Model;

import java.util.logging.Logger;

public class LinkToLanguageOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelDependencyResolutionOperation.class.getName());
	private static final String PROTEO = "Proteo";
	private CompilationUnit compilationUnit;

	public LinkToLanguageOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Model model) {
		String parent = model.getParentModelName();
		if (parent == null || parent.equals(PROTEO)) return;
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Catching info from language");
			CompilerConfiguration conf = compilationUnit.getConfiguration();
			Language language = LanguageLoader.load(conf.getLanguage(),conf.getLanguageDirectory());
			if (language == null) throw new TaraException("Error finding language.", true);
			new LanguageDependencyResolver(model, language).resolve();
		} catch (TaraException e) {
			LOG.severe("Error linking with language: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}
}
