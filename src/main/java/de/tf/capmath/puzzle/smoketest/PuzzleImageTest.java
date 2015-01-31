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
package de.tf.capmath.puzzle.smoketest;

import de.tf.capmath.puzzle.Util;
import de.tf.capmath.puzzle.image.PuzzleImage;
import de.tf.capmath.puzzle.image.RasterCellInfo;
import de.tf.capmath.puzzle.ui.TestFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

public class PuzzleImageTest {
    private static Logger logger = Logger.getLogger(PuzzleImageTest.class.getName());

    private static File getTestFile(String resourcesPath) {
        return new File(PuzzleImageTest.class.getResource(resourcesPath).getPath());
    }

    public static void main (String[] main) throws Exception {
        BufferedImage image = Util.loadImage(getTestFile("/images/schiff.png"));
        PuzzleImage puzzleImage = PuzzleImage.getScaledInstance(image, 7);

        RasterCellInfo[][] raster = puzzleImage.calculateRasterInfo();

        int colored = 0;
        for (int i = 0; i < raster.length; i++) {
            for (int j = 0; j < raster[i].length; j++) {
                if (raster[i][j].containsColor){
                    colored += 1;
                }
            }
        }
        logger.info(String.format("Cells: %d, Colored: %d", raster.length*raster[0].length, colored));

        Dimension imageSize = puzzleImage.getSize();
        TestFrame frame = new TestFrame(imageSize, raster);
        frame.setVisible(true);

    }



}