package io.github.harvies.charon.util.jvm;

import io.github.harvies.charon.util.FileUtils;
import org.openjdk.jol.heap.HeapDumpException;
import org.openjdk.jol.heap.HeapDumpReader;
import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.util.Multiset;

import java.io.File;
import java.io.IOException;

public class HeapDumpTest {
    public static void main(String[] args) throws IOException, HeapDumpException {
        HeapDumpReader heapDumpReader = new HeapDumpReader(new File(FileUtils.getCurrentUserHomePath() + "/java_error_in_idea.hprof"));
        Multiset<ClassData> classDataMultiset = heapDumpReader.parse();
        System.out.println(classDataMultiset);
    }
}
