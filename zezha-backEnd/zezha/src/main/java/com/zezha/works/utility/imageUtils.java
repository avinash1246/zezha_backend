package com.zezha.works.utility;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
	
}
