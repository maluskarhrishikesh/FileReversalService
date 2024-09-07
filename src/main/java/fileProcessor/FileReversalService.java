package fileProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReversalService {

    public void reverseFileContents(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        StringBuilder content = new StringBuilder();

        try {
            // Use the custom method to create BufferedReader
            reader = createBufferedReader(inputFilePath);
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            System.out.println("Original String="+content);
            // Reverse the content
            String reversedContent = content.reverse().toString();

            // Use the custom method to create BufferedWriter
            writer = createBufferedWriter(outputFilePath);
            writer.write(reversedContent);
            System.out.println("Reversed String="+reversedContent);

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    // This method creates a BufferedReader and can be overridden in tests
    protected BufferedReader createBufferedReader(String inputFilePath) throws IOException {
        return new BufferedReader(new FileReader(inputFilePath));
    }

    // This method creates a BufferedWriter and can be overridden in tests
    protected BufferedWriter createBufferedWriter(String outputFilePath) throws IOException {
        return new BufferedWriter(new FileWriter(outputFilePath));
    }
}
