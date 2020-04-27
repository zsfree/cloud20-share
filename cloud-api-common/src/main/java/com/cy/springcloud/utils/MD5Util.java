package com.cy.springcloud.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @ClassName MD5Util
 * @Description TODO
 * @Author zs
 * @Date 2020/4/12 12:22
 * @Version 1.0
 **/
public class MD5Util
{
    private static final Log log = LogFactory.get(MD5Util.class);

    /**
     * 字符串的MD5
     * @param plainText
     * @return
     */
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件的MD5
     * @param filePath
     * @return
     */
    public static String getFileMD5(String filePath){
        String value = null;
        FileInputStream in = null;
        try {
            File file = new File(filePath);
            in = new FileInputStream(file);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int c;
            while ((c = in.read(buffer)) != -1) {
                md5.update(buffer, 0, c);
            }
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16).toUpperCase();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return value;
    }

    public static void main(String args[]) {
        String str = "abc";
        Long b1 = System.currentTimeMillis();

        for(int i =0;i<1;i++){
            //MD5.encode(str);
            System.out.println(MD5Util.encode(str)); //900150983cd24fb0d6963f7d28e17f72

//          Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//          //md5.setEncodeHashAsBase64(false);
//          //md5.encodePassword(str, "");
//          System.out.println(md5.encodePassword(str, ""));
        }
        Long e1 = System.currentTimeMillis();
        System.out.println("MD5.encode耗时:"+(e1-b1));
    }
}
