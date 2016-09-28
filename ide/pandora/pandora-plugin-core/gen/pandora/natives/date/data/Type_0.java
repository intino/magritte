package pandora.natives.date.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#25#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.date.DateData self;

	@Override
	public String value() {
		return "java.time.LocalDate";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.date.DateData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.date.DateData.class;
	}
}