/**
 * Created by Administrator on 2017/6/21.
 * 老是用别人的软件 实现远程开机，现在终于可以使用java实现远程开机了 好爽
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 远程开机
 *
 * @author Administrator
 */
public class JavaWakeup {
    public static void main(String[] args) throws IOException {
        int port = 20105;
        String macAddress = "00-E0-B4-0C-9F-10";
        // String destIP = "255.255.255.255";// 广播地址
        String destIP = "192.168.3.210";// 广播地址
        // 检测 mac 地址,并将其转换为二进制
        byte[] destMac = getMacBytes(macAddress);
        if (destMac == null) {
            return;
        }

        InetAddress destHost = InetAddress.getByName(destIP);
        // construct packet data
        byte[] magicBytes = new byte[102];
        // 将数据包的前6位放入0xFF即 "FF"的二进制
        for (int i = 0; i < 6; i++) {
            magicBytes[i] = (byte) 0xFF;
        }

        // 从第7个位置开始把mac地址放入16次
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < destMac.length; j++) {
                magicBytes[6 + destMac.length * i + j] = destMac[j];
            }
        }
        // create packet
        DatagramPacket dp = null;
        dp = new DatagramPacket(magicBytes, magicBytes.length, destHost, port);
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
        ds.close();
        System.out.println("ok");
    }

    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
}

