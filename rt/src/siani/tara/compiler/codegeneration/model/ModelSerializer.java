package siani.tara.compiler.codegeneration.model;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelSaver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSerializer {
	private static final Logger LOG = Logger.getLogger(ModelSerializer.class.getName());

	CompilerConfiguration conf;

	public ModelSerializer(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void serialize(Model model) throws TaraException {
		boolean save = ModelSaver.save(model, conf.getModelsDirectory());
		if (!save) throw new TaraException("Error sav¡ng model.");
		try {
			new File(conf.getModelsDirectory(), model.getName() + ".reload").createNewFile();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new TaraException("Error sav¡ng model.");
		}
	}
}
