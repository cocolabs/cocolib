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
package io.yooksi.cocolib.gui;

public class PlaneGeometry {

	public enum Axis { X, Y }

	/**
	 * Set of numerical coordinates in two-dimensional space.
	 *
	 * @see <a href="https://en.wikipedia.org/wiki/Cartesian_coordinate_system">
	 * 		Cartesian coordinate system</a>
	 */
	public static class Coordinates {

		public int x, y;

		public Coordinates(int x, int y) {
			this.x = x; this.y = y;
		}

		/**
		 * Shorthand method to change {@code Coordinates} values.
		 * @see #update(Axis, int)
		 */
		public void update(int x, int y) {

			this.x = x;
			this.y = y;
		}

		/**
		 * Shorthand method to change {@code Coordinates} values.
		 * @see #update(int, int)
		 */
		public void update(Axis axis, int value) {

			switch(axis) {
				case X: x = value;
				case Y: y = value;
			}
		}
	}

	/**
	 * Size of the two dimensional plane.
	 */
	public static class Dimensions {

		private int width, height;

		public Dimensions(int width, int height) {
			this.width = width; this.height = height;
		}

		/**
		 * Shorthand method to change {@code Dimensions} values.
		 * <p>
		 *     Note that invoking this from {@link ConstantDimensions ConstantDimensions}
		 *     will result in a {@code UnsupportedOperationException} due to immutable design.
		 * </p>
		 */
		public void update(int width, int height) {

			this.width = width;
			this.height = height;
		}

		/**
		 * @return {@code true} if internal dimensions match given arguments
		 */
		public boolean isEqual(int width, int height) {
			return this.width == width && this.height == height;
		}

		/**
		 * @return size of the plane along {@code x} axis.
		 */
		public int getWidth() {
			return width;
		}

		/**
		 * @return size of the plane along {@code y} axis.
		 */
		public int getHeight() {
			return height;
		}
	}

	/**
	 * {@code Immutable} size of the two dimensional plane.
	 * <p>
	 *     Internal dimensions values are not intended to be mutable and calling
	 *     {@link #update update} method will result in a {@code UnsupportedOperationException}.
	 * </p>
	 */
	public static class ConstantDimensions extends Dimensions {

		public ConstantDimensions(int width, int height) {
			super(width, height);
		}

		/**
		 * @throws UnsupportedOperationException because dimension values are immutable.
		 */
		@Override
		public void update(int width, int height) {
			throw new UnsupportedOperationException();
		}
	}
}
