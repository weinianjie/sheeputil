package com.yige.opt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: weinianjie
 * Date: 2011-8-29
 * Time: 17:15:40
 * To change this template use File | Settings | File Templates.
 */
public class StringTool {
    
    /**
     * Join
     * @param s
     * @param delimiter
     * @return
     */
    public static final String join(AbstractCollection<String> s, String delimiter) {
         if (s == null || s.isEmpty()) return "";
         Iterator<String> iter = s.iterator();
         StringBuffer buffer = new StringBuffer(iter.next());
         while (iter.hasNext()) buffer.append(delimiter).append(iter.next());
         return buffer.toString();
    }

    //TODO AbstractCollection<Integer> 

    /**
     * Join
     * @param s
     * @param delimiter
     * @return
     */
    public static final String join(String[] s, String delimiter){
        if (s == null || s.length == 0) return "";
        StringBuffer buffer = new StringBuffer(s[0]);
        for (int i = 1;i < s.length;i++) buffer.append(delimiter).append(s[i]);
        return buffer.toString();
    }

    /**
     * Join成字符串
     * @param s
     * @param delimiter
     * @return
     */
    public static final String join(Integer[] s, String delimiter){
        if (s == null || s.length == 0) return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append(s[0]);
        for (int i = 1;i < s.length;i++) buffer.append(delimiter).append(s[i]);
        return buffer.toString();
    }


    /**
     * 如果字符串为null,则返回"",否则,返回字符串.trim()
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static final String null2Trim(String str) {
        return str == null ? "" : str.trim();
    }
    
    /**
     * 批量trim字符
     * 
     * @author weinianjie
     * @created 2011-10-22
     *  
     * @param strings
     */
    public static final void list2Trim(String...strings){
    	for(String s:strings){
    		s = s == null ? "":s.trim();
    	}     	
    }
    
    /**
     * 批量trim字符
     * 
     * @author weinianjie
     * @created 2011-10-22
     *  
     * @param strings
     */    
    public static final void list2Trim(List<String> strings){
    	for(String s:strings){
    		s = s == null ? "":s.trim();
    	}    	
    }
    
    /**
     * 批量trim字符
     * 
     * @author weinianjie
     * @created 2011-10-22
     *  
     * @param strings
     */ 
    public static final void array2Trim(String[] strings){
    	for(String s:strings){
    		s = s == null ? "":s.trim();
    	}    	
    }

    /**
     * 字符串转成InputStream
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static final InputStream str2InputStream(String str) {
        if (!null2Trim(str).equals("")) {
            ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
            return stream;
        }
        return null;
    }

    /**
     * 冒泡法排序字符串
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param strArray
     * @return
     */
    public static final String[] sort(String[] strArray) {
        if (strArray == null)
            return null;
        String tmp = "";
        for (int i = 0; i < strArray.length; i++) {
            for (int j = 0; j < strArray.length - i - 1; j++) {
                if (strArray[j].compareTo(strArray[j + 1]) < 0) {
                    tmp = strArray[j];
                    strArray[j] = strArray[j + 1];
                    strArray[j + 1] = tmp;
                }
            }
        }
        return strArray;
    }

    /**
     * ISO转换成GBK
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static final String iso2gbk(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("iso-8859-1"), "GBK");
    }

    /**
     * GBK转换成ISO
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static final String gbk2Iso(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("GBK"), "iso-8859-1");
    }

    /**
     * GBK转换成UTF-8
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static final String gbk2Utf8(String str) {
        try {
            return new String(str.getBytes("GBK"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * UTF-8转换成GBK
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static final String utf82Gbk(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 正则表达式验证,查询(大小写敏感)
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param regx
     *            正则表达式
     * @return
     */
    public static final boolean regexMatch(String str, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 正则表达式验证,查询(大小写不敏感)
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param regx
     *            正则表达式
     * @return
     */
    public static final boolean regexMatchInsensitive(String str, String regx) {
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * 全部替换字符串内的匹配字符串.大小写敏感
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param regx
     *            正则表达式
     * @param replaceStr
     *            要替换的结果
     * @return
     */
    public static final String regexReplaceAll(String str, String regx, String replaceStr) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll(replaceStr);
    }

    /**
     * 从开始截取字符串.汉字算两个字节.不把汉字从中间截开
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param symbol
     * @param len
     *            长度
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static final String subStringFromStartByByteNum(String symbol, int len)
            throws UnsupportedEncodingException {
        int counterOfDoubleByte;
        byte b[];
        counterOfDoubleByte = 0;
        b = symbol.getBytes("GBK");
        if (b.length <= len)
            return symbol;
        for (int i = 0; i < len; i++) {
            if (b[i] < 0)
                counterOfDoubleByte++;
        }
        if (counterOfDoubleByte % 2 == 0)
            return new String(b, 0, len, "GBK");
        else
            return new String(b, 0, len - 1, "GBK");

    }

    /**
     * 判断传入字符串是否为数字
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 过滤掉特殊HTML标签
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static String subNormString(String str) {
        if (str == null)
            return "";
        return str.replaceAll("<[.[^<]]*>", "").replace("<", "");
    }

    /**
     * 扩展String的substring方法。过滤掉特殊HTML标签
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param beginIndex
     *            开始位置
     * @param endIndex
     *            结束位置
     * @return
     */
    public static String subNormString(String str, int beginIndex, int endIndex) {
        if (str == null)
            return "";
        str = str.replaceAll("<[.[^<]]*>", "");
        int length = str.length();
        endIndex = (endIndex < 0) ? 0 : endIndex;
        endIndex = (endIndex > length) ? length : endIndex;
        beginIndex = (beginIndex < 0) ? 0 : beginIndex;
        beginIndex = (beginIndex > endIndex) ? endIndex : beginIndex;
        str = str.substring(beginIndex, endIndex);
        return str.replace("<", "");
    }

    /**
     * 过滤null
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @return
     */
    public static String replaceNull(String str) {
        return (str == null) ? "" : str;
    }

    /**
     * 将null替换成想要的值
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String replaceNull(String str, String defaultStr) {
        return (str == null) ? defaultStr : str;
    }

    /**
     * 截断字符串
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param str
     * @param beginIndex
     *            开始位置
     * @param endIndex
     *            结束位置
     * @return
     */
    public static String substring(String str, int beginIndex, int endIndex) {
        if (str == null)
            return "";
        int length = str.length();
        endIndex = (endIndex < 0) ? 0 : endIndex;
        endIndex = (endIndex > length) ? length : endIndex;
        beginIndex = (beginIndex < 0) ? 0 : beginIndex;
        beginIndex = (beginIndex > endIndex) ? endIndex : beginIndex;
        str = str.substring(beginIndex, endIndex);
        return str;
    }

    /**
     * 返回转换的int值。
     *
     * @author zhaolei
     * @created 2011-4-20
     *
     * @param o
     * @param defaultint
     * @return
     */
    public static int parseInt(Object o, int defaultint) {
        try {
            return Integer.parseInt(String.valueOf(o));
        } catch (NumberFormatException e) {
            return defaultint;
        }
    }
    /**
     * 检查字符串是否有内容。
     *
     * @author lichengwu@sankuai.com
     * @created 2011-5-5
     *
     * @param str
     * @return
     */
    public static boolean isBlank(Object obj){
        if(obj==null)
            return true;
        if(obj instanceof String){
            String str=(String)obj;
            return str==null ? true : "".equals(str.trim());
        }
        try {
            String str = String.valueOf(obj);
            return str==null ? true : "".equals(str.trim());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 将空白字符串替换成指的字符串
     *
     * @author lichengwu
     * @created 2011-7-11
     *
     * @param dest
     * @param str
     * @return
     */
    public static String changeNull(String dest,String str){
        if(!isBlank(dest)){
            return dest;
        }
        return str;
    }    
}
