package com.zxw.tcp; 

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

/** 
 *   <B>说       明</B>:byte类型转换工具类
 *
 * @author  作  者  名：郑雄伟<br/>
 *		    E-mail ：zhengxiongwei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2017年6月29日 下午7:57:01 
 */
public class ByteConvertUtils {
	
	/**
	 * short类型转byte数组
	 */
	public static byte[] getBytes(short data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

	/**
	 * char类型转byte数组
	 */
    public static byte[] getBytes(char data) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }

    /**
	 * int类型转byte数组
	 */
    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    /**
	 * long类型转byte数组
	 */
    public static byte[] getBytes(long data) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    /**
	 * float类型转byte数组
	 */
    public static byte[] getBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    /**
	 * double类型转byte数组
	 */
    public static byte[] getBytes(double data) {
        long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);
    }

    /**
	 * String类型转byte数组
	 */
    public static byte[] getBytes(String data, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return data.getBytes(charset);
    }

    /**
	 * short类型转byte数组
	 */
    public static byte[] getBytes(String data) {
        return getBytes(data, "GBK");
    }

    /**
	 * byte数组转short类型
	 */
    public static short getShort(byte[] bytes) {
        return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    /**
	 * byte数组转char类型
	 */
    public static char getChar(byte[] bytes) {
        return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
    }

    /**
	 * byte数组转int类型
	 */
    public static int getInt(byte[] bytes) {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }
   
    /**
	 * byte数组转long类型
	 */
    public static long getLong(byte[] bytes) {
        return(0xffL & (long)bytes[0]) | (0xff00L & ((long)bytes[1] << 8)) | (0xff0000L & ((long)bytes[2] << 16)) | (0xff000000L & ((long)bytes[3] << 24))
         | (0xff00000000L & ((long)bytes[4] << 32)) | (0xff0000000000L & ((long)bytes[5] << 40)) | (0xff000000000000L & ((long)bytes[6] << 48)) | (0xff00000000000000L & ((long)bytes[7] << 56));
    }

    /**
	 * byte数组转float类型
	 */
    public static float getFloat(byte[] bytes) {
        return Float.intBitsToFloat(getInt(bytes));
    }

    /**
	 * byte数组转double类型
	 */
    public static double getDouble(byte[] bytes) {
        long l = getLong(bytes);
        return Double.longBitsToDouble(l);
    }

    /**
	 * byte数组转String类型
	 */
    public static String getString(byte[] bytes, String charsetName) {
        return new String(bytes, Charset.forName(charsetName));
    }
    
    /**
	 * byte数组转String类型
	 */
    public static String getString(byte[] bytes) {
        return getString(bytes, "GBK");
    }
    
    /**
	 * byte数组转String类型
	 */
    public static String toStr(byte[] bytes) {
    	StringBuilder builder = new StringBuilder();
    	if(bytes.length == 0){
    		return "[" + "]";
    	}
    	for (int i = 0; i < bytes.length; i++) {
    		builder.append(bytes[i]).append(",");
		}
    	return "[" + builder.substring(0, builder.length()-1) + "]";
    }
    
    /**
	 * String类型转byte数组
	 */
    public static byte[] str2Byte(String data,String splitItem) {
    	byte[] bytes = null;
    	if(data != null && !data.equalsIgnoreCase("")){
    		String[] splitResult = data.split(splitItem);
    		bytes = new byte[splitResult.length-1];
    		for (int i = 0; i < splitResult.length; i++) {
    			bytes[i] = Byte.parseByte(splitResult[i]);
			}
    		return bytes;
    	}
    	return null;
    }
    
    /**
     * 将UnsignedInt类型转换为long
     * @param bytes
     * @return
     */
    public static long getLongByUnsignedInt(byte[] bytes){
    	long a = ((long) (bytes[3] & 0xff))    
        | ((long) (bytes[2] & 0xff)) << 8    
        | ((long) (bytes[1] & 0xff)) << 16    
        | ((long) (bytes[0] & 0xff)) << 24; 
    	return a;
    }
    
    /**
	 * byte类型转short
	 */
    public static short getShortByByte(byte bt){
    	short a = (short) ((bt & 0xff));
    	return a;
    }
    
    /** 
     * 用于建立十六进制字符的输出的小写字符数组 
     */  
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',  
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
	
	 /** 
     * 将字节数组转换为十六进制字符数组 
     *  
     * @param data 
     *            byte[] 
     * @param toDigits 
     *            用于控制输出的char[] 
     * @return 十六进制char[] 
     */  
    protected static char[] byteToChar(byte[] data) {  
        int l = data.length;  
        char[] out = new char[l << 1];  
        // two characters form the hex value.  
        for (int i = 0, j = 0; i < l; i++) {  
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];  
            out[j++] = DIGITS_LOWER[0x0F & data[i]];  
        }  
        return out;  
    }  
    
    /**
     * HexString转字节数组
     */
    public static byte[] hexStringToByteArray(String hexString){
		if(StringUtils.isBlank(hexString)){
			return null;
		}
		hexString  = hexString.toLowerCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for(int i = 0; i< length; i++){
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return reverse(d);
	}
    
    /**
     * HexString转字节数组(补丁服务用)
     * 与hexStringToByteArray的区别是：不需要转化高低位
     */
    public static byte[] patchHexStringToByteArray(String hexString){
		if(StringUtils.isBlank(hexString)){
			return null;
		}
		hexString  = hexString.toLowerCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for(int i = 0; i< length; i++){
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	
	protected static byte[] reverse(byte[] arr){
		for(int i = 0; i < arr.length / 2; i++) {
		    byte temp = arr[i];
		    arr[i] = arr[arr.length - 1 - i];
		    arr[arr.length - 1 - i] = temp;
		}
		return arr;
	}
	
	/**
	 * 数组转HexString
	 */
	public static String toHexString(byte[] byteArray){
		byteArray = reverse(byteArray);
		char[] charArray = byteToChar(byteArray);
		return new String(charArray);
	}
	
	/**
	 * 数组转HexString(补丁服务用)
	 * 与toHexString的区别是：不需要转化高低位
	 */
	public static String toPatchHexString(byte[] b){
		char[] charArray = byteToChar(b);
		return new String(charArray);
	}
	
	protected static byte charToByte(char c){
		return (byte)"0123456789abcdef".indexOf(c);
	}
	
	/**
	 * 数组转HexString
	 */
	public static byte[] intToByteArray(int intNum){
		byte[] byteArray = new byte[4];
		byteArray[0] = (byte)(intNum & 0xff);
		byteArray[1] = (byte)(intNum >> 8 & 0xff);
		byteArray[2] = (byte)(intNum >> 16 & 0xff);
		byteArray[3] = (byte)(intNum >>> 24);
		return byteArray;
	}
	
	/**
	 * 数组转HexString
	 */
	public static byte[] longToByteArray(long longNum){
		byte[] byteArray = new byte[8];
		byteArray[0] = (byte)(longNum & 0xff);
		byteArray[1] = (byte)(longNum >> 8 & 0xff);
		byteArray[2] = (byte)(longNum >> 16 & 0xff);
		byteArray[3] = (byte)(longNum >> 24 & 0xff);
		byteArray[4] = (byte)(longNum >> 32 & 0xff);
		byteArray[5] = (byte)(longNum >> 40 & 0xff);
		byteArray[6] = (byte)(longNum >> 48 & 0xff);
		byteArray[7] = (byte)(longNum >>> 56);
		return byteArray;
	}
}
 