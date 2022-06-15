package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.FileCompress;

import java.util.HashMap;
import java.util.Map;

public class FileCompressContext {

    private static final Map<String, FileCompress> map = new HashMap<>();

    public static void register(FileCompress abstractFileCompress) {
        map.put(abstractFileCompress.getMimeType(), abstractFileCompress);
    }

    public static FileCompress get(String mimeType) {
        return map.get(mimeType);
    }
}
