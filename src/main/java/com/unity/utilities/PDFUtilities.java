package com.unity.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

public class PDFUtilities {
	PDDocument pdDocument;
	PDFTextStripper stripper;

	public PDFUtilities(File filePath) {
		try {
			pdDocument = PDDocument.load(filePath);
			stripper = new PDFTextStripper();
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	public String readPdfAsString() {
		String pdfContents = null;
		try {
			pdfContents = stripper.getText(pdDocument);
			pdDocument.close();
		} catch (IOException e) {
			Assert.fail(e.toString());
		}
		return pdfContents;
	}
}
