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

import com.itextpdf.text.DocumentException;
import de.tf.capmath.puzzle.Util;
import de.tf.capmath.puzzle.color.Colors;
import de.tf.capmath.puzzle.image.RasterCellInfo;
import de.tf.capmath.puzzle.pdf.PdfUtil;
import de.tf.capmath.puzzle.tasks.MathTaskFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestFrame extends JFrame {
    private final RasterCellInfo[][] raster;
    private final int offsetLeft;
    private final int offsetTop;
    private final int headerTop;
    private int[] matchingValues = new int[]{4, 7, 16, 8};
    private Map<Integer, Colors> colorMapping;

    public TestFrame(Dimension imageSize, RasterCellInfo[][] raster) {
        colorMapping = new HashMap<>();
        colorMapping.put(4, Colors.Blue);
        colorMapping.put(7, Colors.Red);
        colorMapping.put(16, Colors.Green);
        colorMapping.put(8, Colors.Yellow);
        Dimension size = new Dimension(585, 812);
        this.setSize(size);
        this.raster = raster;
        this.offsetLeft = (585 - (int) imageSize.getWidth()) / 2;
        this.offsetTop = 50;
        this.headerTop = 50;
        addWindowListener();
    }

    private void addWindowListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File out = new File(new File(System.getProperty("java.io.tmpdir")), "test.pdf");
                try {
                    PdfUtil.image2Pdf(out, ((TestFrame) e.getSource()).asImage());
                    System.exit(0);
                } catch (IOException | DocumentException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //drawHeader(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < raster.length; i++) {
            for (int j = 0; j < raster[i].length; j++) {
                Color color = g.getColor();
                RasterCellInfo cell = raster[i][j];
                String text = MathTaskFactory.createTask(cell.containsColor ? true : false, matchingValues, 20);
                if (cell.containsColor) {
                    g.setColor(colorMapping.get(Integer.valueOf(matchingValues[Util.getRandomInt(3)])).getColor());
                    g.drawRect(offsetLeft + cell.x, offsetTop + cell.y, cell.cellSize, cell.cellSize);
                }

                TextUtil.TextOffsets txtOffsets = TextUtil.getTextOffsets(g, cell.cellSize, text);
                //drawText(g, cell, text, txtOffsets);
                g.setColor(color);
            }
        }
    }

    private void drawHeader(Graphics g) {
        int x = (585 - (4 * 90)) / 2;
        Font font = g.getFont();
        g.setFont(g.getFont().deriveFont(Font.BOLD, 25));
        for (int i = 0; i < matchingValues.length; i++) {
            g.setColor(colorMapping.get(Integer.valueOf(matchingValues[i])).getColor());
            g.fillRect(x, headerTop, 60, 60);
            g.setColor(Color.BLACK);
            TextUtil.TextOffsets txtOffsets = TextUtil.getTextOffsets(g, 30, matchingValues[i]+"");
            g.drawString(matchingValues[i]+"", x + txtOffsets.leftOffset + 16, headerTop + 37);
            x += 90;
        }
        g.setFont(font);
    }

    private void drawText(Graphics g, RasterCellInfo cell, String text, TextUtil.TextOffsets txtOffsets) {
        int x = offsetLeft + cell.x + txtOffsets.leftOffset;
        int y = offsetTop + cell.y + txtOffsets.topOffset - g.getFontMetrics().getHeight();
        if (text.contains("\n")){
           x += 15;
           y -= (g.getFontMetrics().getHeight() / 2);
        }

        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    public BufferedImage asImage() {
        BufferedImage image = new BufferedImage(
                this.getWidth(),
                this.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        this.paint(image.getGraphics());
        return image;
    }

}

