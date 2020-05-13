/*
 *  Copyright (C) 2020 Matthew Cain
 *
 *  This file is part of CocoLib.
 *
 *  CocoLib is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CocoLib. If not, see <https://www.gnu.org/licenses/>.
 */
package io.yooksi.cocolib.lang;

public class MathTools {

	/** Prevent class instantiation */
	private MathTools() {
	}

	/**
	 * Ensure the given value is within specified {@code min-max} range.
	 *
	 * @param value {@code int} value to process
	 * @param min minimum allowed value
	 * @param max maximum allowed value
	 *
	 * @return {@code int} value within specified range (inclusive).
	 */
	public static int getValueInRange(int value, int min, int max) {
		return Math.min(Math.max(value, min), max);
	}

	/**
	 * @return {@code true} if the given value is within specified {@code min-max} range.
	 */
	public static boolean isValueInRange(int value, int min, int max) {
		return (value >= min) && (value <= max);
	}

	/**
	 * @return what percent param {@code value} is from param {@code from}.
	 */
	public static double getPercentage(double value, double from) {
		return value / from * 100;
	}
}
