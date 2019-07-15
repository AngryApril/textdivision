package edu.epam.training.textdivision.test;

import edu.epam.training.textdivision.main.service.FileDataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

/**
 * Created by alexey.valiev on 5/31/19.
 */
public class FileDataReaderTest {

    @Test
    @BeforeMethod
    public void testReadToString(){

        FileDataReader fileDataReader = new FileDataReader();
        ArrayList<String> expected = new ArrayList<>();
        Optional<String> actual = fileDataReader.readToString("empty.txt");

        assertEquals(actual,Optional.empty());

    }

    @Test
    public void testFileNotFound(){
        FileDataReader fileDataReader = new FileDataReader();

        Optional<String> actual = fileDataReader.readToString("s57846drt97ovuy");

        assertEquals(actual, Optional.empty());
    }
}
