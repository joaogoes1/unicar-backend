package com.unicar.util.parsers;

import com.google.type.LatLng;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatLngStringToLatLng {
    public static LatLng parse(String latLngString) {
        Pattern latitudePattern = Pattern.compile("lat: ([0-9\\.\\-]+)");
        Pattern longitudePattern = Pattern.compile("lng: ([0-9\\.\\-]+)");
        final Matcher latitudeMatcher = latitudePattern.matcher(latLngString);
        final Matcher longitudeMatcher = longitudePattern.matcher(latLngString);
        LatLng latLng = null;
        if (latitudeMatcher.find() && longitudeMatcher.find()) {
            latLng = LatLng
                    .newBuilder()
                    .setLatitude(Double.parseDouble(latitudeMatcher.group(1)))
                    .setLongitude(Double.parseDouble(longitudeMatcher.group(1)))
                    .build();
        }
        return latLng;
    }
}
