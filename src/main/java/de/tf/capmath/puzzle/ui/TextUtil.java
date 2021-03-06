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
package de.tf.capmath.puzzle.ui;

import de.tf.capmath.puzzle.image.RasterCellInfo;

import java.awt.*;

public class TextUtil {

    public static TextOffsets getTextOffsets(Graphics g, int cellSize, String text){
        FontMetrics metrics = g.getFontMetrics();
        int hgt = metrics.getHeight();
        int adv = metrics.stringWidth(text);
        Dimension textSize = new Dimension(adv, hgt);
        int leftTextOffset = (cellSize - textSize.width) / 2;
        int topTextOffset = (cellSize - textSize.height - (textSize.height / 2));
        return new TextOffsets(leftTextOffset, topTextOffset);
    }

    public static class TextOffsets {
        public final int leftOffset;
        public final int topOffset;

        private TextOffsets(int leftOffset, int topOffset) {
            this.leftOffset = leftOffset;
            this.topOffset = topOffset;
        }
    }

}
