package com.jkojote.linecounter;

import java.io.File;
import java.io.FileFilter;

public class Demo {
    public static void main(String[] args) {
        LineCounter counter = new LineCounterImpl();
        String src = "src/main/java/com/jkojote/linecounter/";
        FileFilter javaSourceCode = file -> {
            if (file.isDirectory())
                return true;
            return file.getName().endsWith(".java");
        };
        System.out.println(counter.countLines(new File(src), javaSourceCode));
    }
}