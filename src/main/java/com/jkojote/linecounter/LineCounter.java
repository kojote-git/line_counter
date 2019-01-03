package com.jkojote.linecounter;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Predicate;

public interface LineCounter {

    long countLines(File from);

    long countLines(File from, FileFilter fileFilter);

    long countLinesForFile(File file);

    long countLinesForFile(File file, Predicate<String> lineFilter);

    default long countLines(File from, FileFilter fileFilter, Predicate<String> lineFilter) {
        return countLines(from, fileFilter, s -> true);
    }
}