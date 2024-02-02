package org.apache.commons.compress.archivers.sevenz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes2.dex */
public class CLI {
    private static final byte[] BUF = new byte[8192];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum Mode {
        LIST("Analysing") { // from class: org.apache.commons.compress.archivers.sevenz.CLI.Mode.1
            @Override // org.apache.commons.compress.archivers.sevenz.CLI.Mode
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) {
                System.out.print(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    PrintStream printStream = System.out;
                    printStream.print(" " + sevenZArchiveEntry.getCompressedSize() + MqttTopic.TOPIC_LEVEL_SEPARATOR + sevenZArchiveEntry.getSize());
                }
                if (sevenZArchiveEntry.getHasLastModifiedDate()) {
                    PrintStream printStream2 = System.out;
                    printStream2.print(" " + sevenZArchiveEntry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!sevenZArchiveEntry.isDirectory()) {
                    PrintStream printStream3 = System.out;
                    printStream3.println(" " + getContentMethods(sevenZArchiveEntry));
                    return;
                }
                System.out.println("");
            }

            private String getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : sevenZArchiveEntry.getContentMethods()) {
                    if (!z) {
                        sb.append(", ");
                    }
                    z = false;
                    sb.append(sevenZMethodConfiguration.getMethod());
                    if (sevenZMethodConfiguration.getOptions() != null) {
                        sb.append("(");
                        sb.append(sevenZMethodConfiguration.getOptions());
                        sb.append(")");
                    }
                }
                return sb.toString();
            }
        },
        EXTRACT("Extracting") { // from class: org.apache.commons.compress.archivers.sevenz.CLI.Mode.2
            @Override // org.apache.commons.compress.archivers.sevenz.CLI.Mode
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
                File file = new File(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    if (!file.isDirectory() && !file.mkdirs()) {
                        throw new IOException("Cannot create directory " + file);
                    }
                    PrintStream printStream = System.out;
                    printStream.println("created directory " + file);
                    return;
                }
                PrintStream printStream2 = System.out;
                printStream2.println("extracting to " + file);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    throw new IOException("Cannot create " + parentFile);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    long size = sevenZArchiveEntry.getSize();
                    long j = 0;
                    while (j < size) {
                        int read = sevenZFile.read(CLI.BUF, 0, (int) Math.min(size - j, CLI.BUF.length));
                        if (read >= 1) {
                            j += read;
                            fileOutputStream.write(CLI.BUF, 0, read);
                        } else {
                            throw new IOException("reached end of entry " + sevenZArchiveEntry.getName() + " after " + j + " bytes, expected " + size);
                        }
                    }
                } finally {
                    fileOutputStream.close();
                }
            }
        };
        
        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        Mode(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 0) {
            usage();
            return;
        }
        Mode grabMode = grabMode(strArr);
        PrintStream printStream = System.out;
        printStream.println(grabMode.getMessage() + " " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            PrintStream printStream2 = System.err;
            printStream2.println(file + " doesn't exist or is a directory");
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        while (true) {
            try {
                SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
                if (nextEntry == null) {
                    return;
                }
                grabMode.takeAction(sevenZFile, nextEntry);
            } finally {
                sevenZFile.close();
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list|extract]");
    }

    private static Mode grabMode(String[] strArr) {
        if (strArr.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, strArr[1].toUpperCase());
    }
}
