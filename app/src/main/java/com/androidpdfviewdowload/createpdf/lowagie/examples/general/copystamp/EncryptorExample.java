/*
 * $Id: EncryptorExample.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.androidpdfviewdowload.createpdf.lowagie.examples.general.copystamp;

import java.io.FileOutputStream;

import com.androidpdfviewdowload.R;
import com.androidpdfviewdowload.createpdf.PdfTestRunner;
import com.lowagie.text.pdf.PdfEncryptor;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Encrypts an existing PDF file.
 */
public class EncryptorExample {

	/**
	 * Reads and encrypts an existing PDF file.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		System.out.println("Encryptor example");
		try {
			//Can't use filename directly
//			PdfReader reader = new PdfReader("ChapterSection.pdf");
			PdfReader reader = new PdfReader(PdfTestRunner.getActivity().getResources().openRawResource(R.raw.chaptersection));
			PdfEncryptor.encrypt(reader, new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + java.io.File.separator + "droidtext" + java.io.File.separator + "encrypted.pdf"), "Hello".getBytes(), "World".getBytes(),
					PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}