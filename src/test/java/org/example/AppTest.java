package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AppTest {
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        sysInBackup = System.in; // backup System.in to restore it later
        sysOutBackup = System.out; // backup System.out to restore it later
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }



    @Test
    public void testFileMode() throws Exception {
        final String expectedOutput =
                "Created a parking lot with 6 slots\n"
                        + "Allocated slot number: 1\n"
                        + "Allocated slot number: 2\n"
                        + "Allocated slot number: 3\n"
                        + "Allocated slot number: 4\n"
                        + "Allocated slot number: 5\n"
                        + "Allocated slot number: 6\n"
                        + "Slot number 4 is free\n"
                        + "Slot No.    Registration No    Colour\n"
                        + "1           KA-01-HH-1234      White\n"
                        + "2           KA-01-HH-9999      White\n"
                        + "3           KA-01-BB-0001      Black\n"
                        + "5           KA-01-HH-2701      Blue\n"
                        + "6           KA-01-HH-3141      Black\n"
                        + "Allocated slot number: 4\n"
                        + "Sorry, parking lot is full\n"
                        + "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n"
                        + "1, 2, 4\n"
                        + "6\n"
                        + "Not found\n";
        App.main(new String[] {"file_input.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }

}