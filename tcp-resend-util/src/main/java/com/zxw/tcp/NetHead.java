package com.zxw.tcp; 

import java.util.Arrays;

//import com.sys.common.util.security.HexUtils;

/** 
 *   <B>说       明</B>:客户端通讯UDP头bean 对应文档的_P2P_UDP_HEAD
 *
 * @author  作  者  名：郑雄伟<br/>
 *		    E-mail ：zhengxiongwei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2017年6月29日 下午8:03:30 
 */
public class NetHead {
		
	/**
	 * 产品类型:_edp
	 */
	String dwFlag;
	/**
	 * 版本:0x00010001
	 */
	int dwVersion;
	/**
	 * 数据包大小(不包含头)
	 */
	int dwSize;
	/**
	 * 数据包的crc
	 */
	String dwCrc;
	/**
	 * 客户端的sessionId
	 */
	String szSessionId ;
	/**
	 * 自增长
	 */
	int dwMsgCode;
	/**
	 * 服务功能号
	 */
	long dwMaxCode;
	/**
	 * 子功能号
	 */
	long dwMinCode;
	/**
	 * 包头大小
	 */
	int wHeadSize;
	/**
	 * 类型:0压缩-1不压缩
	 */
	int wType;
	/**
	 * UDP包个数
	 */
	int wCount;
	/**
	 * 第几个UDP包
	 */
	int wIndex;
	/**
	 * 客户端所属区域
	 */
	String areaId ;
	public final static int HEAD_SIZE = 84;
	byte[] mCBuffer;
	
	public String getDwFlag() {
		return dwFlag;
	}

	public void setDwFlag(String dwFlag) {
		this.dwFlag = dwFlag;
	}

	public int getDwVersion() {
		return dwVersion;
	}

	public void setDwVersion(int dwVersion) {
		this.dwVersion = dwVersion;
	}

	public int getDwSize() {
		return dwSize;
	}

	public void setDwSize(int dwSize) {
		this.dwSize = dwSize;
	}

	public String getDwCrc() {
		return dwCrc;
	}

	public void setDwCrc(String dwCrc) {
		this.dwCrc = dwCrc;
	}

	public String getSzSessionId() {
		return szSessionId;
	}

	public void setSzSessionId(String szSessionId) {
		this.szSessionId = szSessionId;
	}

	public int getDwMsgCode() {
		return dwMsgCode;
	}

	public void setDwMsgCode(int dwMsgCode) {
		this.dwMsgCode = dwMsgCode;
	}

	public long getDwMaxCode() {
		return dwMaxCode;
	}

	public void setDwMaxCode(long dwMaxCode) {
		this.dwMaxCode = dwMaxCode;
	}

	public long getDwMinCode() {
		return dwMinCode;
	}

	public void setDwMinCode(long dwMinCode) {
		this.dwMinCode = dwMinCode;
	}

	public int getwHeadSize() {
		return wHeadSize;
	}

	public void setwHeadSize(int wHeadSize) {
		this.wHeadSize = wHeadSize;
	}

	public int getwType() {
		return wType;
	}

	public void setwType(int wType) {
		this.wType = wType;
	}

	public int getwCount() {
		return wCount;
	}

	public void setwCount(int wCount) {
		this.wCount = wCount;
	}

	public int getwIndex() {
		return wIndex;
	}

	public void setwIndex(int wIndex) {
		this.wIndex = wIndex;
	}

	public byte[] getmCBuffer() {
		return mCBuffer;
	}

	public void setmCBuffer(byte[] mCBuffer) {
		this.mCBuffer = mCBuffer;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Override
	public String toString() {
		return "NetHead [dwFlag=" + dwFlag + ", dwVersion=" + dwVersion
				+ ", dwSize=" + dwSize + ", dwCrc=" + dwCrc + ", szSessionId="
				+ szSessionId + ", dwMsgCode=" + dwMsgCode + ", dwMaxCode="
				+ dwMaxCode + ", dwMinCode=" + dwMinCode + ", wHeadSize="
				+ wHeadSize + ", wType=" + wType + ", wCount=" + wCount
				+ ", wIndex=" + wIndex + ", areaId=" + areaId + "]";
	}

	/**
	 * 获取C的字节数组
	 * 
	 * @return 字节数组本对象
	 */
	public byte[] getCBuffer() {
		if (mCBuffer == null) {
			mCBuffer = new byte[HEAD_SIZE];
			//flag
			byte[] flagByte = this.dwFlag.getBytes();
			System.arraycopy(flagByte, 0, mCBuffer, 0, 4);
			//version
			byte versionByte[] = ByteConvertUtils.intToByteArray(this.getDwVersion());
			System.arraycopy(versionByte, 0, mCBuffer, 4, 4);
			//size
			byte sizeByte[] = ByteConvertUtils.intToByteArray(this.getDwSize());
			System.arraycopy(sizeByte, 0, mCBuffer, 8, 4);
			//crc
			byte crcByte[] = ByteConvertUtils.hexStringToByteArray(this.getDwCrc());
			System.arraycopy(crcByte, 0, mCBuffer, 12, 4);
			//sessionId
			byte sessionIdByte[] = ByteConvertUtils.hexStringToByteArray(this.getSzSessionId());
			//[102, 48, 51, 97, 97, 97, 54, 102, 97, 51, 51, 99, 52, 100, 57, 49, 98, 55, 55, 50, 99, 101, 53, 50, 99, 48, 49, 100, 101, 97, 52, 55]
			System.arraycopy(sessionIdByte, 0, mCBuffer, 16, 16);
			//msgCode
			byte msgCodeByte[] = ByteConvertUtils.intToByteArray(this.getDwMsgCode());
			System.arraycopy(msgCodeByte, 0, mCBuffer, 32, 4);
			//maxCode
			byte maxCodeByte[] = ByteConvertUtils.longToByteArray(this.getDwMaxCode());
			System.arraycopy(maxCodeByte, 0, mCBuffer, 36, 4);
			//minCode
			byte minCodeByte[] = ByteConvertUtils.longToByteArray(this.getDwMinCode());
			System.arraycopy(minCodeByte, 0, mCBuffer, 40, 4);
			//headSize
			byte headSizeByte[] = ByteConvertUtils.intToByteArray(this.getwHeadSize());
			System.arraycopy(headSizeByte, 0, mCBuffer, 44, 2);
			//type
			byte typeByte[] = ByteConvertUtils.intToByteArray(this.getwType());
			System.arraycopy(typeByte, 0, mCBuffer, 46, 2);
			//count
			byte countByte[] = ByteConvertUtils.intToByteArray(this.getwCount());
			System.arraycopy(countByte, 0, mCBuffer, 48, 2);
			//index
			byte indexByte[] = ByteConvertUtils.intToByteArray(this.getwIndex());
			System.arraycopy(indexByte, 0, mCBuffer, 50, 2);
			//areaId
			byte areaIdByte[] = getAreaIds(this.getAreaId());
			System.arraycopy(areaIdByte, 0, mCBuffer, 52, 32);
		}
		return mCBuffer;
	}

	public NetHead(){		
	}
	
	
	private byte[] getAreaIds(String areaIds){
		byte[] result = new byte[32];
		if(areaIds == null){
			throw new NullPointerException("areaId can not be null");
		} else {
			byte[] areaBytes = areaIds.getBytes();
			System.arraycopy(areaBytes, 0, result, 0, areaBytes.length);
		}
		return result;
		
	}


	public NetHead(String dwFlag, int dwVersion, int dwSize, String dwCrc,
			String szSessionId, int dwMsgCode, int dwMaxCode, int dwMinCode,
			short wHeadSize, short wType, short wCount, short wIndex) {
		super();
		this.dwFlag = dwFlag;
		this.dwVersion = dwVersion;
		this.dwSize = dwSize;
		this.dwCrc = null;
		this.szSessionId = szSessionId;
		this.dwMsgCode = dwMsgCode;
		this.dwMaxCode = dwMaxCode;
		this.dwMinCode = dwMinCode;
		this.wHeadSize = wHeadSize;
		this.wType = wType;
		this.wCount = wCount;
		this.wIndex = wIndex;
	}

	public NetHead(int maxCode,String minCode,String szSessionId) {
		this.dwVersion = 1;
		this.dwMaxCode = maxCode;
		this.dwMinCode = Integer.parseInt(minCode, 16);
		this.dwMsgCode = 1;
		this.szSessionId = szSessionId;
		this.dwSize = 0;
		this.wCount = 1;
		this.wIndex = 1;
	}

	/**
	 * 解析udp头
	 */
	public static NetHead parseHead(byte[] content){
		NetHead a =new NetHead();
		byte[] flagByte = Arrays.copyOfRange(content, 0, 4);
		a.dwFlag = ByteConvertUtils.getString(flagByte);
		//version
		byte[] versionByte = Arrays.copyOfRange(content, 4, 8);
		a.dwVersion = ByteConvertUtils.getInt(versionByte);
		//size
		byte[] sizeByte = Arrays.copyOfRange(content, 8, 12);
		a.dwSize = ByteConvertUtils.getInt(sizeByte);
		//crc
		byte[] crcByte = Arrays.copyOfRange(content, 12, 16);
		a.dwCrc = null;
		//a.dwCrc = HexUtils.toHexString(crcByte);
		//sessionId
		byte[] sessionIdByte = Arrays.copyOfRange(content, 16, 32);
		a.szSessionId = null;
		//a.szSessionId = HexUtils.toHexString(sessionIdByte);
		//msgCode
		byte[] msgCodeByte = Arrays.copyOfRange(content, 32, 36);
		a.dwMsgCode = ByteConvertUtils.getInt(msgCodeByte);
		//maxCode
		byte[] maxCodeByte = Arrays.copyOfRange(content, 36, 40);
		a.dwMaxCode = ByteConvertUtils.getInt(maxCodeByte);
		//minCode
		byte[] minCodeByte = Arrays.copyOfRange(content, 40, 44);
		a.dwMinCode = ByteConvertUtils.getInt(minCodeByte);
		//headSize
		byte[] headSizeByte = Arrays.copyOfRange(content, 44, 46);
		a.wHeadSize = ByteConvertUtils.getShort(headSizeByte);
		//type
		byte[] typeByte = Arrays.copyOfRange(content, 46, 48);
		a.wType = ByteConvertUtils.getShort(typeByte);
		//count
		byte[] countByte = Arrays.copyOfRange(content, 48, 50);
		a.wCount = ByteConvertUtils.getShort(countByte);
		//index
		byte[] indexByte = Arrays.copyOfRange(content, 50, 52);
		a.wIndex = ByteConvertUtils.getShort(indexByte);
		return a ;
	}
}
 