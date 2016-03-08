package com.yunchengke.app.utils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 字符串工具类
 * 
 * @author guoyuanzhuang@gmail.com
 * @Description: 
 */
public class StringUtils {
	private final static int SPLIT_SIZE = 1000;

	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static Pattern idNumPattern = Pattern
			.compile("\\d{18}|\\d{17}(X|x)");
	private final static Pattern phonePattern = Pattern.compile("1[0-9]{10}");

	/**
	 * 判断给定字符串是否空白串。
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		return TextUtils.isEmpty(input);
		/*
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
		*/
	}

	/**
	 * 判断密码的合法性
	 * 
	 * @param pwd
	 * @return
	 * @Description: 
	 */
	public static boolean isPasswordValid(String pwd) {
		return !(pwd.length() < 6 || pwd.length() > 18);

	}
	/**
	 * 替换字符串的空白字符
	 * @param src 需要处理的字符串
	 * @param replacement 替代的字符串
	 * @return 处理后的字符串
	 */
	public static String replaceSpaceWith(String src, String replacement){
		return src.replaceAll("\\s+", replacement);
	}

	/**
	 * 去掉字符串中的空格
	 * @param src 需要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String removeAllSpace(String src){
		return replaceSpaceWith(src, "");
	}
	/**
	 * 把空格替换成%20的JSON格式，避免因为空格导致JSON失效
	 * @param src 需要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String replaceSpaceToJsonFormat(String src){
		return replaceSpaceWith(src, "%20");
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 判断是不是一个合法的身份证号
	 * 
	 * @param idcard
	 * @return
	 * @Description: 
	 */
	public static boolean isIDCard(String idcard) {
		return idNumPattern.matcher(idcard).matches();
	}

	/**
	 * 判断是不是一个合法的手机号码
	 */
	public static boolean isPhoneNum(String phoneNum) {
		return phonePattern.matcher(phoneNum).matches();
	}

	public static String getFormatAmount(double amount) {
		DecimalFormat bf = new DecimalFormat("###,##0.00");
		return bf.format(amount);
	}

	/**
	 * 四舍五入保留两位小数
	 * 
	 * @param dou
	 * @return
	 */
	public static String formatDouble2(double dou) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(dou);
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str.replaceAll(",", ""));
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 字符串转long
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 * @Description: 
	 */
	public static long toLong(String str, long defValue) {
		try {
			return Long.parseLong(str.replaceAll(",", ""));
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 字符串转转Double
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static double toDouble(String str, double defValue) {
		try {
			return Double.parseDouble(str.replaceAll(",", ""));
		} catch (Exception e) {
		}
		return defValue;
	}
	
	public static float toFloat(String str, float defValue) {
		try {
			return Float.parseFloat(str.replaceAll(",", ""));
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 截取字符串后四位
	 * 
	 * @param bankcardno
	 * @return
	 * @Description: 
	 */
	public static String getLast4Str(String bankcardno) {
		String overcardno = "";
		if (bankcardno.length() >= 4) {
			overcardno = bankcardno.substring(bankcardno.length() - 4,
					bankcardno.length());
		}
		return overcardno;
	}

	/**
	  * 将输入流转为字符串
	  */
	 public static String streamToString(InputStream mInputStream) {
		 java.util.Scanner tempScanner = new java.util.Scanner(mInputStream);
		 java.util.Scanner s = tempScanner.useDelimiter("\\A");
		 String tempStr = s.hasNext() ? s.next() : "";
		 tempScanner.close();
		 return tempStr;
	 }

	/**
	 * 将字符串根据长度分割
	 * 
	 * @param longStr
	 * @return
	 * @Description: 
	 */
	public static List<String> splitString(String longStr) {
		List<String> arrayStr = new ArrayList<String>();
		for (int i = 0; i <= longStr.length() / SPLIT_SIZE; i++) {
			int start = i * SPLIT_SIZE;
			int end = (i + 1) * SPLIT_SIZE;
			end = end > longStr.length() ? longStr.length() : end;
			// AppLog.i(longStr.substring(start, end));
			String tempStr = longStr.substring(start, end);
			arrayStr.add(tempStr);
		}
		return arrayStr;
	}

	public static String getShiftKey(String key) {
		if (TextUtils.isEmpty(key)) {
			key = AESUtil.KEY;
		}
		return key.substring(9) + key.substring(0, 9);
	}

	/**
	 * deal with phone
	 * 
	 * @param phone
	 * @return
	 * @Description: 
	 */
	public static String getDealPhone(String phone) {
		if (phone != null && phone.length() == 11) {
			return phone.substring(0, 3) + "****" + phone.substring(7);
		}
		return phone;
	}

	/**
	 * deal with realname
	 * 
	 * @param realname
	 * @return
	 * @Description: 
	 */
	public static String getDealName(String realname) {
		if (realname != null && realname.length() >= 2) {
			return realname.replaceAll(realname.substring(0, 1), "*");
		}
		return realname;
	}

	/**
	 * deal with idcardno
	 * 
	 * @param idcardno
	 * @return
	 * @Description: 
	 */
	public static String getDealIDCardno(String idcardno) {
		if (idcardno != null && idcardno.length() == 18) {
			return idcardno.replaceAll(idcardno.substring(6, 14), "****");
		}
		return idcardno;
	}

	/**
	 * 将金额转化为万
	 * 
	 * @param amount
	 * @return
	 * @Description: 
	 */
	public static String getTotalAmount(String amount) {
		String totalAmountStr = "";
		try {
			double totalAmount = toDouble(amount, 0.00);
			if (totalAmount > 0 && totalAmount % 10000 == 0) {
				totalAmountStr = (int) totalAmount / 10000 + "";
			} else {
				totalAmountStr = totalAmount / 10000 + "";
			}
		} catch (Exception e) {
			// 
		}
		return totalAmountStr;
	}

	/**
	 * 根据url解析生成map
	 * 
	 * @param urlcontent
	 *            (title=title&content=content&url=url)
	 * @return
	 */
	public static Map<String, String> getUrlMap(String urlcontent) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		if (!TextUtils.isEmpty(urlcontent)) {
			if (urlcontent.contains("&")) {
				String[] params = urlcontent.split("&");
				for (int i = 0; i < params.length; i++) {
					if (params[i].contains("=")) {
						String[] values = params[i].split("=");
						if (values != null && values.length > 1) {
							mapRequest.put(values[0], values[1]);
						}
					}
				}
			} else {
				if (urlcontent.contains("=")) {
					String[] values = urlcontent.split("=");
					if (values != null && values.length > 1) {
						mapRequest.put(values[0], values[1]);
					}
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 根据字符串分割数组
	 * 
	 * @param channel
	 *            (1-2-3-4)
	 * @return
	 */
	public static List<Integer> getShareChannel(String channel) {
		List<Integer> showList = null;
		try {
			if (!TextUtils.isEmpty(channel)) {
				showList = new ArrayList<Integer>();
				if (channel.indexOf("-") > 0) {
					String[] channelArr = channel.split("-");
					for (int i = 0; i < channelArr.length; i++) {
						showList.add(Integer.parseInt(channelArr[i]));
					}
				} else {
					showList.add(Integer.parseInt(channel));
				}
			}
		} catch (Exception e) {
			// 
		}
		return showList;
	}

	public static String getBannerEnvent(String links) {
		String linksName = links.substring(links.lastIndexOf("/") + 1,
				links.lastIndexOf("."));
		return linksName;
	}
	
	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {          
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {              
        if (c[i] == 12288) {                 
        c[i] = (char) 32;                  
        continue;
         }
         if (c[i] > 65280 && c[i] < 65375)
            c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    } 
	
	
	public static String formatPhoneNumber(String phone) {
		String result = null;
		
		// 取前3个字符串，然后后面每四个分隔		
		if (phone.length() > 3) {
			String str = phone.substring(3, phone.length());
			result = phone.substring(0, 3) + " " + insertSpacePer4Char(str);
		} else {
			result = phone;
		}
		
		return result;
	}
	
	public static String insertSpacePer4Char(String num) {
		StringBuffer str = new StringBuffer();
		int iStart = 0, iEnd = 4;
		for (int i = 0; i< num.length(); i++ ) {		
			if (iEnd >= num.length()) {
				str.append(num.substring(iStart, num.length()));
				break;
			} else {				
				str.append(num.substring(iStart, iEnd));				
			}
			str.append(" ");
			iStart += 4;
			iEnd += 4;			
		}		
		return str.toString();
	}

}
