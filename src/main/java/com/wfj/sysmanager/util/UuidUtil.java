package com.wfj.sysmanager.util;

import java.util.UUID;

/**
 * Created by MaYong on 2015/9/23.
 */
public class UuidUtil {

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
