package com.machinecoding.dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class DictionaryMerger {

    public static void mergeDictionaryAndChangelog(
            BufferedReader dictionaryReader,
            BufferedReader changelogReader,
            BufferedWriter outputWriter) throws IOException {

        String dictLine = dictionaryReader.readLine();
        String changeLine = changelogReader.readLine();

        while (dictLine != null || changeLine != null) {
            if (dictLine == null) {
                outputWriter.write(changeLine);
                outputWriter.newLine();
                changeLine = changelogReader.readLine();
                continue;
            }

            if (changeLine == null) {
                outputWriter.write(dictLine);
                outputWriter.newLine();
                dictLine = dictionaryReader.readLine();
                continue;
            }

            String dictWord = getWord(dictLine);
            String changeWord = getWord(changeLine);
            int cmp = dictWord.compareTo(changeWord);

            if (cmp < 0) {
                outputWriter.write(dictLine);
                outputWriter.newLine();
                dictLine = dictionaryReader.readLine();
            } else if (cmp > 0) {
                outputWriter.write(changeLine);
                outputWriter.newLine();
                changeLine = changelogReader.readLine();
            } else {
                outputWriter.write(changeLine);
                outputWriter.newLine();
                dictLine = dictionaryReader.readLine();
                changeLine = changelogReader.readLine();
            }
        }

        outputWriter.flush();
    }

    private static String getWord(String line) {
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1) {
            return line.trim();
        }
        return line.substring(0, colonIndex).trim();
    }

    public static void main(String[] args) throws IOException {
        String dictionaryData = """
                apple: a fruit
                ball: an object
                cat: furry animal
                """;

        String changelogData = """
                ball: a round object
                dog: domestic animal
                elephant: a big animal
                """;

        BufferedReader dictReader = new BufferedReader(new StringReader(dictionaryData));
        BufferedReader changeReader = new BufferedReader(new StringReader(changelogData));
        StringWriter result = new StringWriter();
        BufferedWriter writer = new BufferedWriter(result);

        mergeDictionaryAndChangelog(dictReader, changeReader, writer);

        System.out.println(result);
    }
}
