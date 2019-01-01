package com.jkojote.linecounter;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

public class LineCounterTest {

    private LineCounter counter = new LineCounterImpl();

    @Test
    public void count() {
        String srcDir = "src/test/java/com/jkojote/linecounter";
        FileFilter txtOnly = file -> file.getName().endsWith(".txt");
        assertEquals(8, counter.countLines(new File(srcDir), txtOnly));
    }
}
