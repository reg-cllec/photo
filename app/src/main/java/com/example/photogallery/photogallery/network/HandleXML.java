package com.example.photogallery.photogallery.network;

import android.util.Log;

import com.example.photogallery.photogallery.Constants;
import com.example.photogallery.photogallery.entity.Images;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class HandleXML {
    private ArrayList<Images> photos = new ArrayList<>();

    public static ArrayList<Images> updatePhotos() {
        String xmlqueryString = Constants.API_URL_GET_RECENT;
        HandleXML xmlObj = new HandleXML(xmlqueryString);
        xmlObj.fetchXML();

        while (xmlObj.parsingComplete) ;
        ArrayList<Images> photos = xmlObj.getPhotos();
        return photos;
    }

    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public HandleXML(String url) {
        this.urlString = url;
    }

    public ArrayList<Images> getPhotos() {
        return photos;
    }


    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                Log.i("@@@@@@@@@@@", "" + name);
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("photo")) {
                            String title = myParser.getAttributeValue(5);
                            String url = myParser.getAttributeValue(9);
                            Images photo = new Images(title, url);
                            photos.add(photo);
                        } else {
                        }
                        break;
                }
                event = myParser.next();
            }//End of While
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("PRINT photos", photos.toString());
    }

    public void fetchXML() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
