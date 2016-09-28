package pandora.natives.datetime.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#28#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.datetime.DateTimeData self;

	@Override
	public String value() {
		return "java.time.LocalDateTime";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.datetime.DateTimeData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.datetime.DateTimeData.class;
	}
}