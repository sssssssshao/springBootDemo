package com.sfy.myproj.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Feature {
    private String type = Feature.class.getSimpleName();
    /**
     * name 名称
     * cp 中心坐标点
     */
    private Map<String, Object> properties = new HashMap<>();
    /**
     * Geometry 坐标点
     */
    private Geometry geometry;

    public Feature() {}

    public Feature(String name, String cp, List<String> jsonList) {
        properties.put("name", name);
        String[] cpArray = cp.split(",");
        Double[] cpDouble = new Double[cpArray.length];
        for (int i = 0, len = cpDouble.length; i < len; i++){
            cpDouble[i] = Double.parseDouble(cpArray[i]);
        }
        properties.put("cp", cpDouble);
        geometry = new Geometry(jsonList);
    }

}