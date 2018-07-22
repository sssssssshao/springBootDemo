package com.sfy.myproj.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfy.myproj.entity.Coords;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
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
 *                 "ID_0": 102,
 *                 "ID_1": 1,
 *                 "ISO": "HKG"
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
public class FeatureCollection {
    private String type = FeatureCollection.class.getSimpleName();
    private List<Feature> features = new ArrayList<>();

    public FeatureCollection() {
    }

    public FeatureCollection(List<Coords> coordsList) {
        Map<String, List<String>> jsonListMap = new HashMap<>();
        Map<String, String> cpMap = new HashMap<>();
        for (Coords coords : coordsList) {
            String name = coords.getChinaName();
            List<String> jsonList = new ArrayList<>();
            if (!cpMap.containsKey(name)) {
                if (!StringUtils.isEmpty(coords.getCp())) {
                    cpMap.put(name, coords.getCp());
                }
            }
            if (jsonListMap.containsKey(name)) {
                jsonList = jsonListMap.get(name);
            }
            jsonList.add(coords.getBound());
            jsonListMap.put(name, jsonList);
        }

        for (String key : jsonListMap.keySet()) {
            Feature feature = new Feature(key, cpMap.get(key), jsonListMap.get(key));
            features.add(feature);
        }
    }
}


