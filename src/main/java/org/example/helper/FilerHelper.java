package org.example.helper;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class FilerHelper {

    public static String UTF_FOR_BASE64 (String file)  {
        byte[] bytes = new byte[0];
            bytes = file.getBytes();

        byte[] d = Base64.getEncoder().encode(bytes);
        //TODO: set return tips in void function for data base
        return new String(d);
    }

    public static void BASE64_FOR_UTF (String encoder) {
        byte[] bytes = Base64.getDecoder().decode(encoder);
        StringBuffer bf = new StringBuffer(new String(bytes));
    }

    public static void main(String[] args) throws IOException {

    }

}
