package com.sfy.myproj.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {
 *     "type": "FeatureCollection",
 *     "features": [
 *         {
 *             "type": "Feature",
 *             "properties": {
 *                 "name": "Central and Western",
 *                 "cp": [xx,xx]
 *             },
 *             "geometry": {
 *                 "type": "MultiPolygon",
 *                 "coordinates": [
 *                     [
 *                         [
 *                             [xx,xx],[xx,xx],....
 *                         ]
 *                     ],
 *                     [
 *                         [
 *                             [xx,xx],[xx,xx],....
 *                         ]
 *                     ]
 *                 ]
 *             }
 *         }
 *     ]
 * }
 */
@Data
public class Geometry {
    private String type = "Polygon";//MultiPolygon
    private List<List<? extends Object>> coordinates = new ArrayList<>();

    public Geometry() {}

    public Geometry(List<String> jsonList) {
        getCoords(jsonList);
    }

    public void getCoords(List<String> jsonList) {
        for (String json: jsonList) {
            List<Double[]> list = new ArrayList<>();
            Matcher m = Pattern.compile("\\d+.\\d+,(\\s*)\\d+.\\d+").matcher(json);
            while (m.find()) {
                String match = m.group();
                if (match.contains(",")) {
                    String[] coord = match.split(",");
                    list.add(new Double[]{Double.parseDouble(coord[0].trim()), Double.parseDouble(coord[1].trim())});
                }
            }
            if (jsonList.size() > 1) {
                List<List<Double[]>> newList = new ArrayList<>();
                newList.add(list);
                coordinates.add(newList);
            } else {
                coordinates.add(list);
            }
        }
        type = coordinates.size() > 1 ? "MultiPolygon": type;
    }
}
