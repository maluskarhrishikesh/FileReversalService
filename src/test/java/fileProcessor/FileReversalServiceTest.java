package fileProcessor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class FileReversalServiceTest {

    private FileReversalService fileReversalService;
    private BufferedReader mockBufferedReader;
    private BufferedWriter mockBufferedWriter;

    @Before
    public void setUp() {
        fileReversalService = new FileReversalService();
        mockBufferedReader = mock(BufferedReader.class);
        mockBufferedWriter = mock(BufferedWriter.class);
    }

    @Test
    public void testReverseFileContents_success() throws IOException {
        // Mock the behavior of BufferedReader
        when(mockBufferedReader.readLine())
                .thenReturn("ABC")  // Return "ABC" for the first call
                .thenReturn(null);   // Return null to indicate the end of the file

        // Spy on the FileReversalService to replace BufferedReader and BufferedWriter
        FileReversalService fileReversalServiceSpy = Mockito.spy(fileReversalService);
        doReturn(mockBufferedReader).when(fileReversalServiceSpy)
                .createBufferedReader(anyString());
        doReturn(mockBufferedWriter).when(fileReversalServiceSpy)
                .createBufferedWriter(anyString());

        // Call the method to test
        fileReversalServiceSpy.reverseFileContents("input.txt", "output.txt");

        // Verify that the BufferedWriter wrote the reversed content "CBA"
        verify(mockBufferedWriter).write("CBA");
    }

    @Test(expected = IOException.class)
    public void testReverseFileContents_fileReadFails() throws IOException {
        // Mock the readLine method to throw an IOException
        when(mockBufferedReader.readLine()).thenThrow(new IOException());

        // Spy on the FileReversalService
        FileReversalService fileReversalServiceSpy = Mockito.spy(fileReversalService);
        doReturn(mockBufferedReader).when(fileReversalServiceSpy)
                .createBufferedReader(anyString());
        doReturn(mockBufferedWriter).when(fileReversalServiceSpy)
                .createBufferedWriter(anyString());

        // Call the method and expect an IOException
        fileReversalServiceSpy.reverseFileContents("input.txt", "output.txt");
    }
}
