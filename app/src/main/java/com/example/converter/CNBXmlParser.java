/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.converter;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CNBXmlParser {
    private static final String ns = null;

    public List<com.example.converter.Entry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Entry> entries = new ArrayList<Entry>();

        parser.require(XmlPullParser.START_TAG, ns, "kurzy");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Log.d("XML","Name = " + name);
            // Starts by looking for the entry tag
            if (name.equals("radek")) {
                entries.add(readEntry(parser));
            	
            } else {
            	parser.next();
            }
        }
        return entries;
    }

    

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
  
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "radek");
        String code = null;
        String currency = null;
        String country = null;
        int amount = 0;
        Double rate = 0.0;

        Log.d("readEntry","START");

            String name = parser.getName();


            for (int i = 0; i < parser.getAttributeCount(); i++) {
            	Log.d("attr","i = " + i + " name = " + parser.getAttributeName(i) + " value = " + parser.getAttributeValue(i));
            	
            	if (parser.getAttributeName(i).equals("kod")) {
            		code = parser.getAttributeValue(i);
            	}
            	if(parser.getAttributeName(i).equals("mena")){
                    currency = parser.getAttributeValue(i);
                }
            	if(parser.getAttributeName(i).equals("mnozstvi")){
                    amount = Integer.parseInt(parser.getAttributeValue(i));
                }
                if(parser.getAttributeName(i).equals("kurz")){
                    String tempRate = parser.getAttributeValue(i).replace(",", ".");
                    rate = Double.parseDouble(tempRate);
                }
                if(parser.getAttributeName(i).equals("zeme")){
                    country = parser.getAttributeValue(i);
                }
        }
            
        parser.next();
            
        return new Entry(code, country, currency, amount, rate);
    }
  
}
