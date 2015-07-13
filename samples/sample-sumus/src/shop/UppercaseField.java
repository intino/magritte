package shop;


public class UppercaseField extends Form.Field {

	@Override
	public String value() {
		return super.value().toUpperCase();
	}
}
