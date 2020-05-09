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
	}
}
