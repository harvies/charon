package io.github.harvies.charon.picture.compress;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Iterator;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {

        FileConfig.TMP_PATH = "/Users/harvies/images/tmp";
        Iterator<File> fileIterator = org.apache.commons.io.FileUtils.iterateFiles(new File("/Users/harvies/images/1"), new String[]{"jpg","jpeg", "JPG", "png", "gif","bmp"}, false);
        fileIterator.forEachRemaining(file -> {
            try {
                FileContext fileContext = new FileContext();
                fileContext.setRecursion(true);
                fileContext.setMaxSize(512);
                fileContext.setFile(file);
                fileContext.setFileFullName(file.getName());
                FileConfig.debug = true;
                FileCompressUtils.compress(fileContext);
                log.info("fileContext:[{}]", fileContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
