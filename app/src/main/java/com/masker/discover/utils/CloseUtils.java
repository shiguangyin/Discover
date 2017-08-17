package com.masker.discover.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Author: masker
 * Date: 2017/8/17
 * Description:
 */

public class CloseUtils {
    public static void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
