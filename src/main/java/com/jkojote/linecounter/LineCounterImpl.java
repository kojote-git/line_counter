package com.jkojote.linecounter;

import java.io.*;

public class LineCounterImpl implements LineCounter {

    @Override
    public long countLines(File from) {
        return walk(from, 0, file -> true);
    }

    @Override
    public long countLines(File from, FileFilter fileFilter) {
        return walk(from, 0, fileFilter);
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

    private long walk(File from, long count, FileFilter fileFilter) {
        if (from.isDirectory()) {
            File[] files = from.listFiles(fileFilter);
            if (files == null)
                return count;
            for (File f : files) {
                count += walk(f, 0, fileFilter);
            }
        } else {
            if (fileFilter.accept(from))
                count = countLinesForFile(from);
        }
        return count;
    }
}