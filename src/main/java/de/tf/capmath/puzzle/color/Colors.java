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
package de.tf.capmath.puzzle.color;

import java.awt.*;

public enum Colors {
    Black(Color.BLACK), Green(Color.GREEN), Yellow(Color.YELLOW), Blue(Color.BLUE), Red(Color.RED);
    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public static Color getRandomColor() {
        int random = (int) Math.round(Math.random() * 4);
        return Colors.get(random).getColor();
    }

    private static Colors get(int index){
        return Colors.values()[index];
    }
}
