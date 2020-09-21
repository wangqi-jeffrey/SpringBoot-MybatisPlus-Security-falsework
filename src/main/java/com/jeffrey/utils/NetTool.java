package com.jeffrey.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetTool {
    private static void DATA2NET(byte[] buf, int offset, int x) {
        buf[(offset + 0)] = (byte) (x >> 24);
        buf[(offset + 1)] = (byte) (x >> 16);
        buf[(offset + 2)] = (byte) (x >> 8);
        buf[(offset + 3)] = (byte) (x >> 0);
    }

    public static void DATA2NET(int value, byte[] buf) {
        DATA2NET(buf, 0, value);
    }

    private static long NET2DATA(byte[] buf, int offset, int length) {
        long k = 0L;
        for (int i = offset; i < offset + length; i++) {
            k = (k << 8) + (buf[i] & 0xFF);
        }
        return k;
    }

    public static long NET2DATA(byte[] buf) {
        return NET2DATA(buf, 0, buf.length);
    }

    private static byte ASC2BCD(byte asc) {
        byte bcd;
        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte) (asc - 'a' + 10);
        else
            bcd = (byte) (asc - 48);
        return bcd;
    }

    public static byte[] ASC2BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        for (int index1 = 0, index = 0; index < (asc_len + 1) / 2; index++) {
            bcd[index] = ASC2BCD(ascii[index1++]);
            bcd[index] = (byte) (((index1 >= asc_len) ? 0x00 : ASC2BCD(ascii[index1++])) + (bcd[index] << 4));
        }
        return bcd;
    }

    public static byte[] getMessageHead(String nodid, String singType, int messageLen) {
        byte[] messageHead = new byte[20];
        log.info(new String(messageHead));

        // 前六位为节点号
        System.arraycopy(nodid.getBytes(), 0, messageHead, 0, nodid.length());
		log.info(new String(messageHead));

        // 是否加密标志
        messageHead[6] = singType.getBytes()[0];
		log.info(new String(messageHead));

        // 报文长度
        byte[] headLen = new byte[]{0, 0, 0, 0};
        DATA2NET(messageLen, headLen);
        System.arraycopy(headLen, 0, messageHead, 16, headLen.length);
        return messageHead;
    }

    public static void printEncMessage(String prefix, byte[] message, int length) {
        for (int index = 0; index < length; index++) {
            if ((index + 1) % 16 == 0) log.info("\n");
        }
    }

    public static String getNodeidFromHead(byte[] messageHead) {
        byte[] nodeid = new byte[6];
        System.arraycopy(messageHead, 0, nodeid, 0, nodeid.length);
        return new String(nodeid);
    }

    public static String getSingTypeFromHead(byte[] messageHead) {
        return String.format("%c", messageHead[6]);
    }

    public static int getRecvLenFromHead(byte[] messageHead) {
        byte[] headLen = new byte[]{0, 0, 0, 0}; //±¨ÎÄ³¤¶È
        System.arraycopy(messageHead, 16, headLen, 0, headLen.length);
        return (int) NetTool.NET2DATA(headLen);
    }

    public static String getTranCD(String recvMessage) throws Exception {
        int index1 = 0, index2 = 0;
        index1 = recvMessage.indexOf("<LH_TRAN_CD>") + "<LH_TRAN_CD>".length();
        index2 = recvMessage.indexOf("</LH_TRAN_CD>", index1);

        if (index1 > 0 && index2 > index1) return recvMessage.substring(index1, index2);
        return null;
    }

}
