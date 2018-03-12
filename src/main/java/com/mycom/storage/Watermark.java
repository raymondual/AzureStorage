package com.mycom.storage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * Water mark utility class
 * 
 * @author Neusoft
 */
public class Watermark {
    
    public Watermark() {
        //
    }
	
    public void manipulatePdf(InputStream inputStream, OutputStream outputStream, String markword) {
        try {
            // text water mark
            Font f = new Font(FontFamily.HELVETICA, 30);
            Phrase p = new Phrase(markword, f);
            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
            
            PdfReader reader = new PdfReader(inputStream);
            int n = reader.getNumberOfPages();
 
            PdfStamper stamper;
            stamper = new PdfStamper(reader, outputStream);
            // properties
            PdfContentByte over;
            Rectangle pagesize;
            float x, y;
            // loop over every page
            for (int i = 1; i <= n; i++) {
                pagesize = reader.getPageSizeWithRotation(i);
                x = (pagesize.getLeft() + pagesize.getRight()) / 2;
                y = (pagesize.getTop() + pagesize.getBottom()) / 2;
                over = stamper.getOverContent(i);
                over.saveState();
                over.setGState(gs1);
                ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
                over.restoreState();
            }
            stamper.close();
            reader.close();            
        } catch (IOException ex) {
            PrintHelper.printException(ex);
        } catch (DocumentException e) {
            PrintHelper.printException(e);
        }
    }
    
    public void manipulateImg(InputStream inputStream, OutputStream outputStream, String markword) {
        try {
            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
 
            Image srcImg = ImageIO.read(inputStream);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // add water mark
            Color color = new Color(255,255,255,128);
            java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 35);
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // set image background color
            g.setColor(color);
            // set font
            g.setFont(font);

            //set coordinate
            int x = srcImgWidth - getWatermarkLength(markword, g);  
            int y = srcImgHeight - getWatermarkHeight(g);  
            // draw water mark
            g.drawString(markword, x, y); 
            g.dispose();  
            // output image 
            ImageIO.write(bufImg, "jpg", outputStream);
            outputStream.flush();  
            outputStream.close();
        } catch (IOException ex) {
            PrintHelper.printException(ex);
        }
    }    
    
	private static final String REMOTE_PDF_URL = "http://localhost/My.pdf";
	private static final String REMOTE_IMG_URL = "http://localhost/Maserati.jpg";

	private void manipulatePdf(String dest, String markword) {
        try {
            // text water mark
            Font f = new Font(FontFamily.HELVETICA, 30);
            Phrase p = new Phrase(markword, f);
            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
            
            URL url = new URL(REMOTE_PDF_URL);
 
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000); 
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            PrintHelper.printInfo("Connect OK");
 
            int fileLenght = httpURLConnection.getContentLength();
            PrintHelper.printInfo("File size:" + (fileLenght / 1024.0) + " KB");
 
            PrintHelper.printInfo("Download ...");
            
            DataInputStream inputStream = new DataInputStream(httpURLConnection.getInputStream());
            PdfReader reader = new PdfReader(inputStream);
            int n = reader.getNumberOfPages();
 
            PdfStamper stamper;
			try {
				stamper = new PdfStamper(reader, new FileOutputStream(dest));
	            // properties
	            PdfContentByte over;
	            Rectangle pagesize;
	            float x, y;
	            // loop over every page
	            for (int i = 1; i <= n; i++) {
	                pagesize = reader.getPageSizeWithRotation(i);
	                x = (pagesize.getLeft() + pagesize.getRight()) / 2;
	                y = (pagesize.getTop() + pagesize.getBottom()) / 2;
	                over = stamper.getOverContent(i);
	                over.saveState();
	                over.setGState(gs1);
	                ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
	                over.restoreState();
	            }
	            stamper.close();
	            reader.close();
			} catch (DocumentException e) {
			    PrintHelper.printException(e);
			}
            
            httpURLConnection.disconnect();
            PrintHelper.printInfo("Done.");
        } catch (IOException ex) {
            PrintHelper.printInfo("file is not exist or time out");
            PrintHelper.printException(ex);
        }
    }
    
	private void manipulateImg(String dest, String markword) {
        try {
            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
            
            URL url = new URL(REMOTE_IMG_URL);
 
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            PrintHelper.printInfo("Connect OK");
 
            int fileLenght = httpURLConnection.getContentLength();
            PrintHelper.printInfo("File size:" + (fileLenght / 1024.0) + " KB");
 
            PrintHelper.printInfo("Download ...");
            
            DataInputStream inputStream = new DataInputStream(httpURLConnection.getInputStream());

            Image srcImg = ImageIO.read(inputStream);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // add water mark
            Color color = new Color(255,255,255,128);
            java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 35);
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // set image background color
            g.setColor(color);
            // set font
            g.setFont(font);

            //set coordinate
            int x = srcImgWidth - getWatermarkLength(markword, g);  
            int y = srcImgHeight - getWatermarkHeight(g);  
            // draw water mark
            g.drawString(markword, x, y); 
            g.dispose();  
            // output image 
            FileOutputStream outImgStream = new FileOutputStream(dest);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();  
            outImgStream.close();
            
            httpURLConnection.disconnect();
            PrintHelper.printInfo("Done.");
        } catch (IOException ex) {
            PrintHelper.printInfo("file is not exist or time out");
            PrintHelper.printException(ex);
        }
    }
    
	private int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    } 
    
	private int getWatermarkHeight(Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).getHeight();
    } 
    
    public static void main(String[] args) throws IOException, DocumentException {
    	
    	String markName = "Francesco Totti";
        String pdfDest = "D:/Jobs.pdf";
        new Watermark().manipulatePdf(pdfDest, markName);
      
        String imgDest = "D:/MyImg.jpg";
        new Watermark().manipulateImg(imgDest, markName);
    }
}
