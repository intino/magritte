package pandora.natives.object.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#34#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.object.ObjectData self;

	@Override
	public String value() {
		return pandora.DataTypes.formatName(self);
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.object.ObjectData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.object.ObjectData.class;
	}
}