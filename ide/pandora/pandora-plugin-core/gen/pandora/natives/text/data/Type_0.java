package pandora.natives.text.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#22#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.text.TextData self;

	@Override
	public String value() {
		return "String";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.text.TextData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.text.TextData.class;
	}
}