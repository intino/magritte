package io.intino.magritte.builder.compiler.core.errorcollection.message;


import io.intino.magritte.builder.compiler.core.ProcessingUnit;
import io.intino.magritte.builder.compiler.core.SourceUnit;

import java.io.PrintWriter;

public class SimpleMessage extends Message {
	protected String message;
	protected Object data;
	protected ProcessingUnit owner;

	public SimpleMessage(String message, ProcessingUnit source) {
		this.message = message;
		this.data = null;
		this.owner = source;
	}

	public SimpleMessage(String message, Object data, ProcessingUnit source) {
		this.message = message;
		this.data = data;
		this.owner = source;
	}


	public void write(PrintWriter writer) {
		if (this.owner instanceof SourceUnit) {
			String name = ((SourceUnit) this.owner).getName();
			writer.println("" + name + ": " + this.message);
		} else
			writer.println(this.message);
	}

	public String getMessage() {
		return this.message;
	}

	public Object getData(){
		return data;
	}

	public ProcessingUnit getOwner() {
		return owner;
	}
}
