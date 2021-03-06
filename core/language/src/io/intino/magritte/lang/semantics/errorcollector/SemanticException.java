package io.intino.magritte.lang.semantics.errorcollector;

import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.semantics.MessageProvider;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SemanticException extends Exception {

	private final transient SemanticNotification notification;

	public SemanticException(SemanticNotification notification) {
		this.notification = notification;
	}

	public SemanticNotification getNotification() {
		return notification;
	}

	@Override
	public String getMessage() {
		if (notification.key() == null) return "";
		return MessageProvider.message(notification.key(), notification.parameters().toArray());
	}

	public String[] getParameters() {
		List<String> parameters = notification.parameters().stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.toList());
		return parameters.toArray(new String[parameters.size()]);
	}

	public boolean isFatal() {
		return level().equals(SemanticNotification.Level.ERROR);
	}

	public SemanticNotification.Level level() {
		return notification.level();
	}

	public String key() {
		return notification.key();
	}

	public Element[] origin() {
		return notification.origin();
	}
}
