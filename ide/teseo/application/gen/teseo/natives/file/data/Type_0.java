package teseo.natives.file.data;



/**#/Users/oroncal/workspace/tara/ide/liskov/application/src/teseo/Teseo.tara#42#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.file.FileData self;

	@Override
	public String value() {
		return "java.io.File";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.file.FileData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.file.FileData.class;
	}
}