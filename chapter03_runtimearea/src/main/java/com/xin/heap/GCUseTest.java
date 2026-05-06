package com.xin.heap;

import java.util.ArrayList;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-04 15:25
 * @github https://github.com/xyf527
 * @copyright
 */

public class GCUseTest {

    public static void main(String[] args) {

        ArrayList<byte[]> list = new ArrayList<byte[]>();

        while (true) {
            byte[] arr = new byte[100];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
