/*
 * $Id: ExtraStyles.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

package com.androidpdfviewdowload.createpdf.lowagie.examples.fonts.styles;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Demonstrates how to underline and strike through text.
 * 
 * @author blowagie
 */

public class ExtraStyles {

	/**
	 * Underline or strike through text.
	 * 
	 * @param args
	 *            no arguments needed here
	 */
	public static void main(String[] args) {

		System.out.println("Underline and Strike through.");

		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2:
			// we create a writer that listens to the document
			PdfWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "ExtraStyles.pdf"));
			HtmlWriter.getInstance(document, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "ExtraStyles.html"));

			// step 3: we open the document
			document.open();
			// step 4:
			Font font;
			Chunk chunk;
			font = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.UNDERLINE);
			chunk = new Chunk("underline", font);
			document.add(chunk);
			font = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
			chunk = new Chunk(" and ", font);
			document.add(chunk);
			font = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.STRIKETHRU);
			chunk = new Chunk("strike through", font);
			document.add(chunk);

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
	}
}