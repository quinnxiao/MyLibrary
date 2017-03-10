package com.quinn.mylibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭实现了Closeable接口的流工具类
 */

public class CloseableUtil {
    private CloseableUtil(){}

    public static void closeQuietly(Closeable closeable){
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
