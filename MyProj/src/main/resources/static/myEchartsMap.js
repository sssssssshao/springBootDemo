var myMap = (function(){
    var title = '数据中心',
        scatterOption = [],
        legendNameArray = [],
        legendDataJson = {},
        geoCoordMap = {},
        nameMap = {},
        data = {},
        option;
    var convertData = function (data) {
        var res = [];
        if (data instanceof Array) {
            res = returnRes(data);
        } else {
            var temp = {};
            for (var key in data) {
                var tempRes = returnRes(data[key]);
                if (tempRes) {
                    for (var i in tempRes) {
                        if (temp[tempRes[i].name]) {
                            temp[tempRes[i].name].value.push(tempRes[i].value[2]);
                        } else {
                            temp[tempRes[i].name] = tempRes[i];
                        }
                    }
                }
            }
            for (var key in temp) {
                res.push(temp[key]);
            }
        }
        // console.log(res);
        return res;
    };
    var returnRes = function(data) {
        var result = [];
        for (var i = 0; i < data.length; i++) {
            var name = data[i].name;
            var geoCoord = geoCoordMap[name];
            // console.log(data[i], geoCoord)
            if (geoCoord) {
                result.push({
                    name: name,
                    value: geoCoord.concat(data[i].value),
                });
            }
        }
        return result;
    };
    var putScatterOption = function(key, data){
        scatterOption.push({
            name: key,
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: 'pin', //气泡
            symbolSize: 70,
            label: {
                normal: {
                    show: true,
                    formatter: function (params) {
                        return params.data.value[2];
                    },
                    textStyle: {
                        color: '#fff',
                        fontSize: 15,
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: '#F62157', //标志颜色
                }
            },
            data: data,
        });
    };
    var initOptions = function (data, mapFeatures){
        for (var i in mapFeatures) {
            // 地区名称
            var name = mapFeatures[i].properties.name;
            // 地区经纬度
            geoCoordMap[name] = mapFeatures[i].properties.cp;
        }
        for (var key in data) {
            legendNameArray.push(key);
            for (var i in data[key]) {
                if (!legendDataJson[key]) {
                    legendDataJson[key] = {};
                }
                legendDataJson[key][data[key][i].name] = data[key][i].value;
            }
            putScatterOption(key, convertData(data[key]));
        }
    }

    return {
        init(dom) {
            var myChart = echarts.init(dom);
            myChart.showLoading();

            nameMap = $.getJSON('/data.json', function(json){
                data = json;
            });
            nameMap = $.getJSON('/nameMap.json', function(json){
                nameMap = json;
            });
            $.getJSON('/geo.json', function (geoJson) {
                echarts.registerMap('HK', geoJson);
                var mapFeatures = geoJson.features;
                myChart.hideLoading();

                initOptions(data, mapFeatures);

                myChart.hideLoading();

                myChart.setOption(option = {
                    backgroundColor: '#0a173a',
                    title: {
                        text: title,
                        subtext: '数据来自一标三实',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: function (params) {
                            // console.log(params);
                            var result = nameMap[params.name];
                            if (typeof(params.data) != "undefined" && typeof(params.data.value) != "undefined") {
                                if (params.data.value instanceof Array && params.data.value.length == 3) {
                                    result += ':' + params.data.value[2];
                                }
                            }
                            return result;
                        }
                    },
                    geo: {
                        show: true,
                        map: "HK",
                        roam: true, // 是否开启鼠标缩放和平移漫游
                        label: {
                            normal: {
                                fontSize: 14,
                                color: '#7cffe7',
                                show: false,
                            },
                            emphasis: {
                                color: '#20296a',
                                show: false,
                            },
                        },
                        itemStyle: {
                            normal: {// 默认状态下地图上各模块显示的情况
                                areaColor: '#001e43', //模块区域中的颜色
                                borderColor: '#00bbfc' //模块区域的边线颜色
                            },
                            emphasis:{
                                color: {
                                    type: 'linear',
                                    x: 0,
                                    y: 0,
                                    x2: 0,
                                    y2: 1,
                                    colorStops: [
                                        {
                                            offset: 0, color: '#1fabf0' // 0% 处的颜色
                                        },
                                        {
                                            offset: 1, color: '#7cffe7' // 100% 处的颜色
                                        }
                                    ]
                                },
                                borderColor: '#fdac11', //模块区域的边线颜色
                            }
                        }
                    },
                    legend: {
                        type: 'scroll',
                        selectedMode: 'single',
                        orient: 'vertical',
                        y: 'bottom',
                        x: 'right',
                        data: legendNameArray,
                        selected: [legendNameArray[0]],
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    series: [
                        {
                            type: 'map',
                            map: 'HK',
                            geoIndex: 0,
                            aspectScale: 0.75, //长宽比
                            showLegendSymbol: false, // 存在legend时显示
                            animation: false,
                            data: convertData(data),
                            nameMap: nameMap
                        },
                    ].concat(scatterOption)
                });
            });
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
            myChart.on('click', function (params) {
                if (legendDataJson[params.seriesName]) {
                    alert(legendDataJson[params.seriesName][params.name]);
                }
            });
        }
    }
})();