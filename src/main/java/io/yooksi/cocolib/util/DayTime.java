package io.yooksi.cocolib.util;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;

public class DayTime {

	public enum Segment {

		DAWN(23000, 7000),
		NOON(6000, 7000),
		DUSK(13000, 5000),
		MIDNIGHT(18000, 5000);
		/*
		 * Store all enumerator values in an static array
		 * so we avoid cloning array with values() on each call
		 */
		private static final Segment[] VALUES = values();
		private static final Segment[] REVERSE;

		/*
		 * These values are used to read the segments in a reverse
		 * orderly fashion from entries with highest to lowest time value
		 */
		static
		{
			REVERSE = new Segment[]{ DAWN, MIDNIGHT, DUSK, NOON };
		}

		private final long time;
		private final long duration;

		Segment(long time, long duration) {

			this.time = time;
			this.duration = duration;
		}

		/**
		 * @param time game time expressed in ticks.
		 * @return closest {@code Segment} that matches the given time.
		 * 		For example a time of {@code 8000} would match {@link #NOON}.
		 */
		public static Segment get(long time) {

			for (Segment segment : REVERSE)
			{
				if (time >= segment.time)
					return segment;
			}
			return DAWN;
		}

		/**
		 * This is a performance efficient way of retrieving the next enum value.
		 * @return enum value that was defined next in the declaration order.
		 */
		public Segment getNext() {
			return VALUES[(this.ordinal() + 1) % VALUES.length];
		}

		/**
		 * @return duration of this day segment measured in game ticks.
		 */
		public long getDuration() {
			return duration;
		}

		/**
		 * @return starting game time for this day segment.
		 */
		public long getTime() {
			return time;
		}

		/**
		 * Time format sensitive way of getting amount of time elapsed
		 * from the defined start of this segment to the given time.
		 *
		 * @param currentTime current day time in the world.
		 * @return amount of time elapsed from start of this segment to given
		 * 		time expressed in game ticks.
		 */
		@Range(from = 0, to = 23999)
		public long getElapsedTime(long currentTime) {
			return currentTime >= time ? currentTime - time : 24000 - time + currentTime;
		}

		/**
		 * Time format sensitive way of getting amount of time elapsed since the
		 * defined start of this segment to the current time in the given world.
		 *
		 * @param world instance of the world to get the current time from.
		 * @return amount of time (game ticks) elapsed from start of this segment
		 * 		to the current time in the given world.
		 *
		 * @see #getElapsedTime(long)
		 */
		@Range(from = 0, to = 23999)
		public long getElapsedTime(World world) {
			return getElapsedTime(getTimeOfDay(world));
		}
	}

	/**
	 * Performance efficient way of retrieving all day segment values,
	 * since calling {@link Segment#values()} clones the array on each call.
	 *
	 * @return an array of {@code DayTime.Segment} enum values.
	 */
	@Contract(pure = true)
	public static Segment[] getSegments() {
		return Segment.VALUES;
	}

	/**
	 * @return the day time (time wrapped within a day)
	 */
	@Contract("null -> fail")
	public static long getTimeOfDay(World world) {
		return world.getDayTime() % 24000L;
	}

	/**
	 * @param world instance of the world to check for the rule.
	 * @return {@code true} if daytime cycle is enabled in the given world
	 * 		  through {@code doDaylightCycle} game rule.
	 */
	@Contract("null -> fail")
	public static boolean isDaylightCycleEnabled(World world) {
		return world.getWorldInfo().getGameRulesInstance().getBoolean(GameRules.DO_DAYLIGHT_CYCLE);
	}
}
