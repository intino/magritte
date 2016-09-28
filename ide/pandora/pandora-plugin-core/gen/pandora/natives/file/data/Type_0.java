package pandora.natives.file.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#30#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.file.FileData self;

	@Override
	public String value() {
		return "java.io.File";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.file.FileData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.file.FileData.class;
	}
}