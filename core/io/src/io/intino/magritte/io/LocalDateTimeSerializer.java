package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

class LocalDateTimeSerializer extends Serializer<LocalDateTime> {

	@Override
	public void write(Kryo kryo, Output output, LocalDateTime object) {
		output.writeLong(object.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	}

	@Override
	public LocalDateTime read(Kryo kryo, Input input, Class<LocalDateTime> type) {
		return LocalDateTime.ofInstant(new Date(input.readLong()).toInstant(), ZoneId.systemDefault());
	}
}
