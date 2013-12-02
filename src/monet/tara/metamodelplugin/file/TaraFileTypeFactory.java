package monet.tara.metamodelplugin.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import monet.tara.metamodelplugin.file.TaraFileType;
import org.jetbrains.annotations.NotNull;

public class TaraFileTypeFactory extends FileTypeFactory {
	@Override
	public void createFileTypes(@NotNull com.intellij.openapi.fileTypes.FileTypeConsumer fileTypeConsumer) {
		fileTypeConsumer.consume(TaraFileType.INSTANCE, "m2");
	}
}