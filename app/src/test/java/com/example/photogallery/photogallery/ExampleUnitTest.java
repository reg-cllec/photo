package com.example.photogallery.photogallery;

import com.example.photogallery.photogallery.entity.Images;
import com.example.photogallery.photogallery.network.HandleXML;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void get_photos() throws Exception{
        ArrayList<Images> photos = HandleXML.updatePhotos();
        assertEquals(photos.size(), 100);
    }
}