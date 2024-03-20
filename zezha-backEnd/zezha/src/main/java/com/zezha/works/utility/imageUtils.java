package com.zezha.works.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class imageUtils {

	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4*1024];
		while(!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outStream.write(tmp, 0, size);
		}
		try {
			outStream.close();
		}catch(Exception ex) {
			
		}
		return outStream.toByteArray();
	}
	
	public static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4*1024];
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outStream.write(tmp, 0, count);
			}
			outStream.close();
		}catch(Exception ex) {
			
		}
		return outStream.toByteArray();
	}
	
	public static String generateQRCodeBase64(String link) {
		int width = 500;
		int height = 500;
		String format = "png";
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height, hintMap);

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++)
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, format, baos);
			byte[] imageData = baos.toByteArray();

			return "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(imageData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
