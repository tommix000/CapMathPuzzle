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

public class TestFrame extends JFrame {
    private final RasterCellInfo[][] raster;
    private final int offsetLeft;
    private final int offsetTop;
    private int[] matchingValues = new int[]{4, 7, 16};

    public TestFrame(Dimension imageSize, RasterCellInfo[][] raster) {
        Dimension size = new Dimension(585, 812);
        this.setSize(size);
        this.raster = raster;
        this.offsetLeft = (585 - (int) imageSize.getWidth()) / 2;
        this.offsetTop = 50;
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
        for (int i = 0; i < raster.length; i++) {
            for (int j = 0; j < raster[i].length; j++) {
                Color color = g.getColor();
                RasterCellInfo cell = raster[i][j];
                String text = MathTaskFactory.createTask(false, matchingValues, 20);;
                if (cell.containsColor) {
                    text = MathTaskFactory.createTask(true, matchingValues, 20);
                    g.setColor(Colors.getRandomColor());
                    g.drawRect(offsetLeft + cell.x, offsetTop + cell.y, cell.cellSize, cell.cellSize);
                }
                TextUtil.TextOffsets txtOffsets = TextUtil.getTextOffsets(g, cell, text);
                g.setColor(Color.BLACK);
                g.drawString(text, offsetLeft + cell.x + txtOffsets.leftOffset, offsetTop + cell.y + txtOffsets.topOffset);
                g.setColor(color);
            }
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

