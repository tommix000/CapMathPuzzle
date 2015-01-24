/*
Version: 0.0.1-SNAPSHOT
Author: Thomas Fricker <tommi.fricker@googlemail.com>
URL: https://github.com/tommix000/CapMathPuzzle
Follow me on Twitter: @tommix000

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.tf.capmath.puzzle.image;

import java.awt.*;

public class RasterCellInfo {
    public final Color color;
    public final boolean containsColor;
    public final int x;
    public final int y;
    public final int cellSize;

    public RasterCellInfo(Color color, boolean containsColor, int x, int y, int cellSize){
        this.color = color;
        this.containsColor = containsColor;
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
    }

    @Override
    public String toString() {
        return String.format("Color: %s, contains color: %s", color.toString(), String.valueOf(containsColor));
    }

}
