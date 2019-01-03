package com.jkojote.linecounter;

import java.io.*;
import java.util.function.Predicate;

public class LineCounterImpl implements LineCounter {

    @Override
    public long countLines(File from, FileFilter fileFilter, Predicate<String> lineFilter) {
        return walk(from, 0, fileFilter, lineFilter);
    }

    @Override
    public long countLines(File from) {
        return walk(from, 0, file -> true, s -> true);
    }

    @Override
    public long countLines(File from, FileFilter fileFilter) {
        return walk(from, 0, fileFilter, s -> true);
    }

    @Override
    public long countLinesForFile(File file) {
        if (file.isDirectory())
            return -1;
        long res = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while (reader.readLine() != null)
                res++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public long countLinesForFile(File file, Predicate<String> lineFilter) {
        if (file.isDirectory())
            return -1;
        long res = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineFilter.test(line)) {
                    res++;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private long walk(File from, long count, FileFilter fileFilter, Predicate<String> lineFilter) {
        if (from.isDirectory()) {
            File[] files = from.listFiles(fileFilter);
            if (files == null)
                return count;
            for (File f : files) {
                count += walk(f, 0, fileFilter, lineFilter);
            }
        } else {
            if (fileFilter.accept(from))
                count = countLinesForFile(from, lineFilter);
        }
        return count;
    }
}