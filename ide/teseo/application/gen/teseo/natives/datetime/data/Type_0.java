package teseo.natives.datetime.data;



/**#/Users/oroncal/workspace/tara/ide/liskov/application/src/teseo/Teseo.tara#40#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.datetime.DateTimeData self;

	@Override
	public String value() {
		return "java.time.LocalDateTime";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.datetime.DateTimeData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.datetime.DateTimeData.class;
	}
}