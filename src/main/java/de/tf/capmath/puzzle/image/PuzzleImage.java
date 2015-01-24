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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import static de.tf.capmath.puzzle.Util.*;

public class PuzzleImage {
    private final BufferedImage image;
    private final int cellSize;
    private static Logger logger = Logger.getLogger(PuzzleImage.class.getName());

    public PuzzleImage(BufferedImage image, int cellSize) {
        this.image = image;
        this.cellSize = cellSize;
    }

    public static PuzzleImage getScaledInstance(BufferedImage image, int cellSize) {
        int w = image.getWidth();
        int h = image.getHeight();
        int newWidth = w;
        int newHeight = h;
        while (newWidth % cellSize != 0) {
            newWidth += 1;
        }
        while (newHeight % cellSize != 0) {
            newHeight += 1;
        }
        logger.info(String.format("Scaling image. Original size: %d:%d. New size: %d:%d", w, h, newWidth, newHeight));
        return scale(image, newWidth, newHeight, cellSize);
    }

    private static PuzzleImage scale(BufferedImage image, int w, int h, int cellSize) {
        BufferedImage out = new BufferedImage(w, h, image.getType());
        Graphics2D g2 = out.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        double xScale = (double) w / image.getWidth();
        double yScale = (double) h / image.getHeight();
        double scale = Math.min(xScale, yScale);  // scale to fit
        double x = (w - scale * image.getWidth()) / 2;
        double y = (h - scale * image.getHeight()) / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale, scale);
        g2.drawRenderedImage(image, at);
        g2.dispose();
        return new PuzzleImage(out, cellSize);
    }

    public RasterCellInfo[][] calculateRasterInfo() {
        RasterCellInfo[][] raster = new RasterCellInfo[image.getWidth() / cellSize][image.getHeight() / cellSize];
        for (int i = 0; i < raster.length; i++) {
            for (int j = 0; j < raster[i].length; j++) {
                raster[i][j] = calculateRasterInfo(i * cellSize, j * cellSize);
            }
        }
        return raster;
    }

    private RasterCellInfo calculateRasterInfo(int row, int column) {
        int colored = 0;
        int[] red = new int[cellSize * cellSize];
        int[] green = new int[cellSize * cellSize];
        int[] blue = new int[cellSize * cellSize];
        int index = 0;
        for (int i = row; i < row + cellSize; i++) {
            for (int j = column; j < column + cellSize; j++) {
                int rgb = image.getRGB(i, j);
                red[index] = (rgb >> 16) & 0x000000FF;
                green[index] = (rgb >> 8) & 0x000000FF;
                blue[index] = (rgb) & 0x000000FF;
                colored += (red[index] != 0xFF || green[index] != 0xFF || blue[index] != 0xFF) ? 1 : -1;
                index++;
            }
        }
        return new RasterCellInfo(calculateAverageColor(red, green, blue), colored > 0, row, column, cellSize);
    }

    private Color calculateAverageColor(int[] red, int[] green, int[] blue) {
        return new Color(getAverage(red), getAverage(green), getAverage(blue));
    }

    public Dimension getSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
