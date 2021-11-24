/*
 * Copyright 2017 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.security;

/**
 * 哈希工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1 2017-01-11
 */
public class HexUtils {

    private static final char[] HEX_DIGITS_UC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] HEX_DIGITS_LC = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private HexUtils() {
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param lowerCase 是否返回小写的摘要信息
     * @param data      摘要信息
     * @return 16进制字符串
     */
    public static String encodeHexStr(byte[] data, boolean lowerCase) {
        char[] chars = encodeHex(data, lowerCase);
        return (chars == null) ? null : new String(chars);
    }

    /**
     * 字节数组转16进制字符数组
     *
     * @param lowerCase 是否返回小写的摘要信息
     * @param data      摘要信息
     * @return 16进制字符数组
     */
    public static char[] encodeHex(byte[] data, boolean lowerCase) {
        if (null == data) {
            return null;
        }
        // 选择大小写
        char[] hexDigits = HEX_DIGITS_UC;
        if (lowerCase) {
            hexDigits = HEX_DIGITS_LC;
        }
        // 转为16进制字符串
        int length = data.length;
        char[] chars = new char[length * 2];
        int k = 0;
        for (byte byte0 : data) {
            chars[k++] = hexDigits[byte0 >>> 0x4 & 0xf];
            chars[k++] = hexDigits[byte0 & 0xf];
        }
        return chars;
    }
}
