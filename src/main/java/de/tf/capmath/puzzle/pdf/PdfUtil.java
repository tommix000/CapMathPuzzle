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
package de.tf.capmath.puzzle.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class PdfUtil {
    private static Logger logger = Logger.getLogger(PdfUtil.class.getName());

    public static void image2Pdf(File pdf, BufferedImage image) throws IOException, DocumentException {
        logger.info(String.format("Writing PDF to %s", pdf.getAbsolutePath()));
        Document document = new Document(PageSize.A4, 5, 5, 5, 5);
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();
            PdfContentByte pdfCB = new PdfContentByte(writer);
            Image img = Image.getInstance(pdfCB, image, 1);
            document.add(img);
        } finally {
            document.close();
            writer.close();
        }
    }

}
