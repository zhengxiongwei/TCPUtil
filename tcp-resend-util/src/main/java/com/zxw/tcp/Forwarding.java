package com.zxw.tcp; 

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

//import com.sys.common.util.security.CRCUtils;
//import com.vrv.cems.base.AsymmetricCipherHandler;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：郑雄伟<br/>
 *		    E-mail ：zhengxiongwei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2017年6月29日 下午8:00:58 
 */
public class Forwarding {
	
	protected static final String AREAID = "serverAreaMain";
	public static final String PRODUCT_AGREEMENT_FLAG = "_edp";
	protected static final boolean ISZIP = true;
	private static final AtomicInteger msgCodeCounter = new AtomicInteger(0);
	protected static final String SESSIONID = createClientDefaultSessionId();
	
	
	private static byte[] getNetHead(byte[] data, long maxCode, long minCode) {
		NetHead head = new NetHead();
		head.setDwFlag("PRODUCT_AGREEMENT_FLAG");
		head.setDwVersion(0x01010101);
		head.setDwSize(data.length);
		String dataCrc = "";
		//String dataCrc = CRCUtils.getCRC32StringValue(data);
		head.setDwCrc(dataCrc);

		head.setSzSessionId(SESSIONID);

		head.setDwMsgCode(msgCodeCounter.incrementAndGet());
		head.setDwMaxCode(maxCode);
		head.setDwMinCode(minCode);
		head.setwHeadSize((short) NetHead.HEAD_SIZE);
		head.setwType((short) 0);
		head.setwCount((short) 1);
		head.setwIndex((short) 1);
		head.setAreaId(AREAID);
		return head.getCBuffer();
	}

	private static byte[] getDatagrampacket(byte[] headData, byte[] bodyData) {
		byte[] arrays = new byte[headData.length + bodyData.length];
		System.arraycopy(headData, 0, arrays, 0, headData.length);
		System.arraycopy(bodyData, 0, arrays, headData.length, bodyData.length);
		return arrays;
	}

	private static byte[] encryptTC(String data) {
		return null;
		//return AsymmetricCipherHandler.encryptByPublicKey(data);
	}

	public static byte[] getSendContent(String maxCodeStr, String minCodeStr,
			String postData) {
		if (maxCodeStr == null || minCodeStr == null || postData == null
				|| postData.trim().length() == 0) {
			return new byte[0];
		}
		// 获取data数据
		byte[] data = encryptTC(postData);
		long maxCode = Long.parseLong("0x" + maxCodeStr, 16);
		long minCode = Long.parseLong("0x" + minCodeStr, 16);
		// 获取请求头信息
		byte[] headData = getNetHead(data, maxCode, minCode);
		// 获取完整请求数据包
		return getDatagrampacket(headData, data);
	}
	
	private static String createClientDefaultSessionId() {
		char[] cs = new char[32];
		Arrays.fill(cs, '0');
		return new String(cs);
	}
}
 