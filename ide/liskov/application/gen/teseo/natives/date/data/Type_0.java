package teseo.natives.date.data;



/**#/Users/oroncal/workspace/teseo/application/src/teseo/Teseo.tara#37#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.date.DateData self;

	@Override
	public String value() {
		return "java.time.LocalDate";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.date.DateData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.date.DateData.class;
	}
}