package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.util.Pool;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

class KryoFactory {
	private static final Pool<Kryo> kryoPool = new Pool<>(true, false, 8) {
		protected Kryo create() {
			return KryoFactory.create();
		}
	};

	public static Kryo get() {
		return kryoPool.obtain();
	}

	private static Kryo create() {
		Kryo kryo = new Kryo();
		Log.ERROR();
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		return kryo;
	}

	static class LocalDateTimeSerializer extends Serializer<LocalDateTime> {

		@Override
		public void write(Kryo kryo, Output output, LocalDateTime object) {
			output.writeLong(object.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		}

		@Override
		public LocalDateTime read(Kryo kryo, Input input, Class<? extends LocalDateTime> aClass) {
			return LocalDateTime.ofInstant(new Date(input.readLong()).toInstant(), ZoneId.systemDefault());
		}
	}
}
