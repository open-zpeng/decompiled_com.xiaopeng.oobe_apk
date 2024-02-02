package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
/* loaded from: classes2.dex */
public final class Lister {
    private static final ArchiveStreamFactory factory = new ArchiveStreamFactory();

    public static void main(String[] strArr) throws Exception {
        ArchiveInputStream createArchiveInputStream;
        if (strArr.length == 0) {
            usage();
            return;
        }
        PrintStream printStream = System.out;
        printStream.println("Analysing " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            PrintStream printStream2 = System.err;
            printStream2.println(file + " doesn't exist or is a directory");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        if (strArr.length > 1) {
            createArchiveInputStream = factory.createArchiveInputStream(strArr[1], bufferedInputStream);
        } else {
            createArchiveInputStream = factory.createArchiveInputStream(bufferedInputStream);
        }
        PrintStream printStream3 = System.out;
        printStream3.println("Created " + createArchiveInputStream.toString());
        while (true) {
            ArchiveEntry nextEntry = createArchiveInputStream.getNextEntry();
            if (nextEntry != null) {
                System.out.println(nextEntry.getName());
            } else {
                createArchiveInputStream.close();
                bufferedInputStream.close();
                return;
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [archive-type]");
    }
}
