package siani.tara.compiler.codegeneration.model;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.Model;
import siani.tara.lang.util.ModelSaver;

import java.io.File;
import java.io.IOException;

public class ModelSerializer {

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
			throw new TaraException("Error sav¡ng model.");
		}
	}
}
