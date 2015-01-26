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
package de.tf.capmath.puzzle.tasks;

import de.tf.capmath.puzzle.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class MathTaskFactory {
    private static final Logger logger = Logger.getLogger(MathTaskFactory.class.getName());

    public static String createTask(boolean matching, int[] matchingValues, int maxValue) {
        int result = matching ? getMatchingValue(matchingValues) : getNonMatchingValue(matchingValues, maxValue);
        int task = Util.getRandomInt(2);
        switch (task) {
            case 0:
                return new AdditionTask().generate(result, maxValue);
            case 1:
                return new SubtractionTask().generate(result, maxValue);
            case 2:
                return new FromToTask().generate(result, maxValue);
        }
        throw new IllegalArgumentException();
    }

    private static int getNonMatchingValue(int[] matchingValues, int maxValue) {
        List<Integer> choices = new ArrayList<>();
        for (int i = 0; i < maxValue; i++) {
            if (!inMatchingValues(matchingValues, i)) {
                choices.add(i);
            }
        }
        return choices.get(Util.getRandomInt(choices.size() - 1));
    }

    private static boolean inMatchingValues(int[] matchingValues, int i) {
        for (int matchingValue : matchingValues) {
            if (matchingValue == i) {
                return true;
            }
        }
        return false;
    }

    private static int getMatchingValue(int[] matchingValues) {
        return matchingValues[Util.getRandomInt(matchingValues.length - 1)];
    }

}
