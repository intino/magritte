package tara.compiler.core.operation;

import tara.Language;
import tara.compiler.codegeneration.magritte.stash.StashCreator;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.lang.model.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StashGenerationOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(StashGenerationOperation.class.getName());
	private static final String STASH = ".stash";
	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private final String genLanguage;
	private Language language;

	public StashGenerationOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.getConfiguration();
		this.genLanguage = conf.generatedLanguage() != null ? conf.generatedLanguage() : conf.getModule();
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + " Generating Stashes...");
			language = model.getLanguage();
			createStash(model.components());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void createStash(List<Node> nodes) throws TaraException {
		final File stashDestiny = createStashDestiny(new File(nodes.get(0).file()));
		writeStashTo(stashDestiny, stashOf(nodes));
	}

	private Stash stashOf(List<Node> nodes) throws TaraException {
		return new StashCreator(nodes, language, genLanguage, conf.getResourcesDirectory()).create();
	}

	private String writeStashTo(File taraFile, Stash stash) {
		final byte[] content = StashSerializer.serialize(stash);
		final File file = createStashDestiny(taraFile);
		file.getParentFile().mkdirs();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			stream.write(content);
			stream.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
		return file.getPath();
	}

	private File createStashDestiny(File taraFile) {
		final File destiny = getStashFolder(taraFile);
		destiny.mkdirs();
		return new File(destiny, (conf.generatedLanguage() == null ? "Model" : conf.generatedLanguage()) + STASH);
	}

	private File getStashFolder(File taraFile) {
		return isStaticStashGeneration() ? taraFile.getParentFile() : conf.getResourcesDirectory();
	}

	private boolean isStaticStashGeneration() {
		return genLanguage == null;
	}

}
