package io.yooksi.cocolib.gui;

public class PlaneGeometry {

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
	}

	/**
	 * Size of the two dimensional plane.
	 */
	public static class Dimensions {

		public int width, height;

		public Dimensions(int width, int height) {
			this.width = width; this.height = height;
		}
	}
}
