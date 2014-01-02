package monet.tara.jps.incremental;

import org.jetbrains.jps.builders.java.JavaBuilderExtension;

import java.io.File;

public class TaraJavaBuilderExtension extends JavaBuilderExtension {
	@Override
	public boolean shouldHonorFileEncodingForCompilation(File file) {
		return TaraBuilder.isTaraFile(file.getAbsolutePath());
	}
}
