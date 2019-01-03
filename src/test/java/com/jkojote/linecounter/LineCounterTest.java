package com.jkojote.linecounter;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class LineCounterTest {

    private LineCounter counter = new LineCounterImpl();

    @Test
    public void count() {
        String srcDir = "src/test/java/com/jkojote/linecounter";
        FileFilter txtOnly = file -> file.getName().endsWith(".txt");
        assertEquals(9, counter.countLines(new File(srcDir), txtOnly));
        // count all lines that doesn't start with #
        Predicate<String> lineFilter = s -> !s.startsWith("#");
        // there is only one line that starts with #
        assertEquals(8, counter.countLines(new File(srcDir), txtOnly, lineFilter));
        assertEquals(1, counter.countLines(new File(srcDir), txtOnly, lineFilter.negate()));
    }
}
