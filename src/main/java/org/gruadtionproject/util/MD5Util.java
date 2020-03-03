package org.gruadtionproject.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String getMD5(long seckillId, String slat) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
