package tara.lang.semantics.errorcollector;

import tara.lang.model.Element;
import tara.lang.semantics.MessageProvider;

import java.util.List;
import java.util.stream.Collectors;

public class SemanticException extends Exception {

	private final transient SemanticNotification notification;
	private boolean fatal;

	public SemanticException(SemanticNotification notification) {
		this.notification = notification;
	}

	public SemanticNotification getNotification() {
		return notification;
	}

	@Override
	public String getMessage() {
		return MessageProvider.message(notification.key(), notification.parameters().toArray());
	}

	public String[] getParameters() {
		List<String> parameters = notification.parameters().stream().filter(o -> o != null).map(Object::toString).collect(Collectors.toList());
		return parameters.toArray(new String[parameters.size()]);
	}

	public boolean isFatal() {
		return true;
	}

	public SemanticNotification.Level level() {
		return notification.level();
	}

	public String key() {
		return notification.key();
	}

	public Element origin() {
		return notification.origin();
	}
}
