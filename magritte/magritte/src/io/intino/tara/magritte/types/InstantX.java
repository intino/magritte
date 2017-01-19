package io.intino.tara.magritte.types;

import java.time.*;
import java.time.temporal.*;

public class InstantX implements Comparable<InstantX> {

    private Instant instant;

    public InstantX(Instant instant) {
        this.instant = instant;
    }

    public static InstantX now() {
        return new InstantX(Instant.now());
    }

    public static InstantX now(Clock clock) {
        return new InstantX(Instant.now(clock));
    }

    public static InstantX ofEpochSecond(long epochSecond) {
        return new InstantX(Instant.ofEpochSecond(epochSecond));
    }

    public static InstantX ofEpochSecond(long epochSecond, long nanoAdjustment) {
        return new InstantX(Instant.ofEpochSecond(epochSecond, nanoAdjustment));
    }

    public static InstantX ofEpochMilli(long epochMilli) {
        return new InstantX(Instant.ofEpochMilli(epochMilli));
    }

    public static InstantX from(TemporalAccessor temporal) {
        return new InstantX(Instant.from(temporal));
    }

    public static InstantX parse(CharSequence text) {
        return new InstantX(Instant.parse(text));
    }

    public boolean isSupported(TemporalField field) {
        return instant.isSupported(field);
    }

    public boolean isSupported(TemporalUnit unit) {
        return instant.isSupported(unit);
    }

    public ValueRange range(TemporalField field) {
        return instant.range(field);
    }

    public int get(TemporalField field) {
        return instant.get(field);
    }

    public long getLong(TemporalField field) {
        return instant.getLong(field);
    }

    public long getEpochSecond() {
        return instant.getEpochSecond();
    }

    public int getNano() {
        return instant.getNano();
    }

    public InstantX with(TemporalAdjuster adjuster) {
        return new InstantX(instant.with(adjuster));
    }

    public InstantX with(TemporalField field, long newValue) {
        return new InstantX(instant.with(field, newValue));
    }

    public InstantX truncatedTo(TemporalUnit unit) {
        return new InstantX(instant.truncatedTo(unit));
    }

    public InstantX plus(TemporalAmount amountToAdd) {
        return new InstantX(instant.plus(amountToAdd));
    }

    public InstantX plus(long amountToAdd, TemporalUnit unit) {
        return new InstantX(instant.plus(amountToAdd, unit));
    }

    public InstantX plusSeconds(long secondsToAdd) {
        return new InstantX(instant.plusSeconds(secondsToAdd));
    }

    public InstantX plusMillis(long millisToAdd) {
        return new InstantX(instant.plusMillis(millisToAdd));
    }

    public InstantX plusNanos(long nanosToAdd) {
        return new InstantX(instant.plusNanos(nanosToAdd));
    }

    public InstantX minus(TemporalAmount amountToSubtract) {
        return new InstantX(instant.minus(amountToSubtract));
    }

    public InstantX minus(long amountToSubtract, TemporalUnit unit) {
        return new InstantX(instant.minus(amountToSubtract, unit));
    }

    public InstantX minusSeconds(long secondsToSubtract) {
        return new InstantX(instant.minusSeconds(secondsToSubtract));
    }

    public InstantX minusMillis(long millisToSubtract) {
        return new InstantX(instant.minusMillis(millisToSubtract));
    }

    public InstantX minusNanos(long nanosToSubtract) {
        return new InstantX(instant.minusNanos(nanosToSubtract));
    }

    public <R> R query(TemporalQuery<R> query) {
        return instant.query(query);
    }

    public Temporal adjustInto(Temporal temporal) {
        return instant.adjustInto(temporal);
    }

    public long until(InstantX instantX, TemporalUnit unit) {
        return instant.until(instantX.instant, unit);
    }

    public OffsetDateTime atOffset(ZoneOffset offset) {
        return instant.atOffset(offset);
    }

    public ZonedDateTime atZone(ZoneId zone) {
        return instant.atZone(zone);
    }

    public long toEpochMilli() {
        return instant.toEpochMilli();
    }

    public boolean isAfter(InstantX otherInstant) {
        return instant.isAfter(otherInstant.instant);
    }

    public boolean isBefore(InstantX otherInstant) {
        return instant.isBefore(otherInstant.instant);
    }

    public LocalDateTime toLocalDateTime(ZoneId zone) {
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Override
    public boolean equals(Object otherInstant) {
        return otherInstant instanceof InstantX && instant.equals(((InstantX) otherInstant).instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }

    @Override
    public String toString() {
        return instant.toString();
    }

    @Override
    public int compareTo(InstantX o) {
        return instant.compareTo(o.instant);
    }
}
