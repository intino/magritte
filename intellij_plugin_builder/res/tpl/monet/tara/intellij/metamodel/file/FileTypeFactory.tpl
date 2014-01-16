package monet.::projectName::.intellij.metamodel.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::FileTypeFactory extends FileTypeFactory {
	\@Override
	public void createFileTypes(\@NotNull com.intellij.openapi.fileTypes.FileTypeConsumer fileTypeConsumer) {
		fileTypeConsumer.consume(::projectProperName::FileType.INSTANCE, "m1");
	}
}