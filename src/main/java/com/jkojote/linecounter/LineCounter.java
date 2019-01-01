package com.jkojote.linecounter;

import java.io.File;
import java.io.FileFilter;

public interface LineCounter {

    long countLines(File from);

    long countLines(File from, FileFilter fileFilter);

    long countLinesForFile(File file);
}