package monet.tafat.intellij.metamodel.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class TafatFileTypeFactory extends FileTypeFactory {
	@Override
	public void createFileTypes(@NotNull com.intellij.openapi.fileTypes.FileTypeConsumer fileTypeConsumer) {
		fileTypeConsumer.consume(TafatFileType.INSTANCE, "m1");
	}
}