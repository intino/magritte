package monet.tara.transpiler.intellij.metamodel.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class TaraFileTypeFactory extends FileTypeFactory {
	@Override
	public void createFileTypes(@NotNull com.intellij.openapi.fileTypes.FileTypeConsumer fileTypeConsumer) {
		fileTypeConsumer.consume(TaraFileType.INSTANCE, "m2");
	}
}