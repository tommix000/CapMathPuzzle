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
package de.tf.capmath.puzzle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Util {

    public static int getAverage(int[] values) {
        return Arrays.stream(values).reduce((a, b) -> a + b).getAsInt() / values.length;
    }

    public static BufferedImage loadImage(File testFile) throws IOException {
        return ImageIO.read(testFile);
    }

    public static int getRandomInt(int max){
        double rand = Math.random();
        double result = rand * (double)max;
        long round = Math.round(result);
        int ret = (int)round;
        System.out.println(rand + " : " + result + " : " + round + " : " +  ret);
        return ret;
    }

    public static int getRandomInt(int from, int to){
        return from + getRandomInt(to - from);
    }
}
