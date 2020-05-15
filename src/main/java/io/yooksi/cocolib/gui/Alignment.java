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

import static io.yooksi.cocolib.gui.PlaneGeometry.*;

public enum Alignment {

	TOP_LEFT {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(offset.getWidth(), offset.getHeight());
		}
	},
	TOP_RIGHT {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(Alignment.getOffsetX(frame, size, offset), offset.getHeight());
		}
	},
	TOP_CENTER {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(Alignment.getCenterX(frame, size, offset), offset.getHeight());
		}
	},
	BOTTOM_LEFT {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(offset.getWidth(), Alignment.getOffsetY(frame, size, offset));
		}
	},
	BOTTOM_RIGHT {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(
					Alignment.getOffsetX(frame, size, offset),
					Alignment.getOffsetY(frame, size, offset)
			);
		}
	},
	BOTTOM_CENTER {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(
					Alignment.getCenterX(frame, size, offset),
					Alignment.getOffsetY(frame, size, offset)
			);
		}
	},
	CENTER {
		@Override
		Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset) {
			return new Coordinates(
					Alignment.getCenterX(frame, size, offset),
					Alignment.getCenterY(frame, size, offset)
			);
		}
	};

	/**
	 * @return coordinate along {@code x} axis with the given offset applied.
	 */
	private static int getOffsetX(Dimensions frame, Dimensions size, Dimensions offset) {
		return frame.getWidth() - size.getWidth() - offset.getWidth();
	}

	/**
	 * @return coordinate along {@code y} axis with the given offset applied.
	 */
	private static int getOffsetY(Dimensions frame, Dimensions size, Dimensions offset) {
		return frame.getHeight() - size.getHeight() - offset.getHeight();
	}

	/**
	 * @return <b>centered</b> coordinate along {@code x} axis with the given offset applied.
	 */
	private static int getCenterX(Dimensions frame, Dimensions size, Dimensions offset) {
		return frame.getWidth() / 2 - size.getWidth() / 2 + offset.getWidth();
	}

	/**
	 * @return <b>centered</b> coordinate along {@code y} axis with the given offset applied.
	 */
	private static int getCenterY(Dimensions frame, Dimensions size, Dimensions offset) {
		return frame.getHeight() / 2 - size.getHeight() / 2 + offset.getHeight();
	}

	/**
	 * Calculates and returns the position in the {@code 2D} plane for this alignment.
	 * based on the size of the object we are trying to position, the size of the outer
	 * frame that holds the object we are trying to position and the additive offset.
	 *
	 * @param frame size of the outer frame that holds the object.
	 * @param size size of the object we are trying to position.
	 * @param offset coordinate offset from the edge of the frame.
	 */
	abstract Coordinates getPosition(Dimensions frame, Dimensions size, Dimensions offset);
}
