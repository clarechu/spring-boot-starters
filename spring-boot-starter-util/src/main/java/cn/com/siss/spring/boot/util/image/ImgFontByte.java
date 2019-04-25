package cn.com.siss.spring.boot.util.image;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * ttf字体文件
 *
 * @author dsna
 */
@Slf4j
public class ImgFontByte {

    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(hex2byte(getFontByteStr())));
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            log.error("getFont method error " + e);
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null)
            return new byte[0];
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return new byte[0];

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            log.error("hex2byte method error " + e);
            return new byte[0];
        }
    }

    private String getFontByteStr() {
        return null;
    }
}