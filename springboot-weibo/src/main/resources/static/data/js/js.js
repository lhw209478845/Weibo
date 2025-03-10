$(window).load(function () { $(".loading").fadeOut() })
$(function () {

    echarts_2()
    echarts_3()
    echarts_4()
    echarts_6()
    echarts_7()

    function echarts_7() {
        // 基于准备好的dom，初始化echarts实例
        // 地区品牌排行榜
        var myChart = echarts.init(document.getElementById('echart7'));
        var Brandvalue = []
        var Brandname = [];
        option = {
            grid: {
                left: '0',
                top: '0',
                right: '0',
                bottom: '0%',
                containLabel: true
            },
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: Brandname,
                inverse: true,
                axisLine: { show: false },
                splitLine: { show: false },
                axisTick: { show: false },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                },

            }, {
                show: false,
                inverse: true,
                data: Brandvalue,
                axisLabel: { textStyle: { color: '#fff' } },
                axisLine: { show: false },
                splitLine: { show: false },
                axisTick: { show: false },
            }],
            series: [{
                name: '条',
                type: 'bar',
                yAxisIndex: 0,
                data: Brandvalue,
                barWidth: 15,
                itemStyle: {
                    normal: {
                        barBorderRadius: 50,
                        color: '#1089E7',
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        formatter: '{c}',
                        textStyle: { color: 'rgba(255,255,255,.5)' }
                    }
                },
            }]
        };

        $.ajax({
            url: "/brand//queryNameValue",
            dataType: "json",
            success: function (data) {
                for(let i in data){
                    Brandname.push(data[i].name);
                    Brandvalue.push(data[i].value);
                }
                myChart.setOption(option);
            }
        });

        // 使用刚指定的配置项和数据显示图表。
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }
    function echarts_2() {
        // 基于准备好的dom，初始化echarts实例 
        // 地区品牌排行榜
        var myChart = echarts.init(document.getElementById('echart2'));
        var barTitle = '用户热度';

        // var data = [];
        var datas = [
            {name: '广东', value: '88078'},
            {name: '四川', value: '72618'},
            {name: '湖北', value: '68407'},
            {name: '北京', value: '63738'},
            {name: '上海', value: '63563'},
            {name: '山东', value: '62829'},
            {name: '江苏', value: '62318'},
            {name: '福建', value: '53663'},
            {name: '浙江', value: '53198'},
            {name: '河南', value: '43216'},
            {name: '黑龙江', value: '42990'},
            {name: '河北', value: '42007'},
            {name: '江西', value: '41438'},
            {name: '湖南', value: '41409'},
            {name: '吉林', value: '40293'},
            {name: '山西', value: '32427'},
            {name: '广西', value: '32126'},
            {name: '海南', value: '32126'},
            {name: '辽宁', value: '31736'},
            {name: '安徽', value: '31504'},
            {name: '陕西', value: '23373'},
            {name: '云南', value: '22206'},
            {name: '贵州', value: '18621'},
            {name: '香港', value: '12235'},
            {name: '宁夏', value: '12212'},
            {name: '内蒙古', value: '12166'},
            {name: '天津', value: '12079'},
            {name: '甘肃', value: '11227'},
            {name: '新疆', value: '11008'},
            {name: '澳门', value: '7885'},
            {name: '重庆', value: '7832'},
            {name: '青海', value: '3234'},
            {name: '西藏', value: '2345'},
            {name: '台湾', value: '145'},
        ];
        var xdata = [];
        datas.map((item) => {
            xdata.push(item.name);
        });

        var colorList = ['#71c3ff', '#ffd684', '#ff9794', '#aaa1ff', '#ffd684', '#ff9794'];
        option = {
            backgroundColor:'#00000000',
            grid: {
                left: '0%',
                right: '0%',
                bottom: '-3%',
                top: '0%',
                containLabel: true,
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'none',
                },
                formatter: function (params) {
                    return (
                        params[0].name +
                        '<br/>' +
                        "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
                        // params[0].seriesName + ' : ' + Number((params[0].value.toFixed(4) / 10000).toFixed(2)).toLocaleString() + ' <br/>'
                        params[0].seriesName +
                        ' : ' +
                        params[0].value
                    );
                },
            },
            xAxis: {
                show: false,
                type: 'value',
            },
            yAxis: [
                {
                    type: 'category',
                    inverse: true,
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#b1e8fc',
                            fontSize: '15',
                        },
                    },
                    splitLine: {
                        show: false,
                    },
                    axisTick: {
                        show: false,
                    },
                    axisLine: {
                        show: false,
                    },
                    data: xdata,
                },
                {
                    type: 'category',
                    inverse: true,
                    axisTick: 'none',
                    axisLine: 'none',
                    show: true,
                    axisLabel: {
                        textStyle: {
                            color: '#b1e8fc',
                            fontSize: '16',
                        },
                        formatter: function (value) {
                            return value;
                        },
                    },
                },
            ],
            dataZoom: [
                {
                    show: true,
                    type: 'slider',
                    filterMode: 'filter',
                    backgroundColor: '#132c81',
                    width: 10,
                    top: 0,
                    left: 0,
                    bottom: 30,
                    start: 0, //数据窗口范围的起始百分比,表示30%
                    end: 30,
                    // zoomLock: true,
                    orient: 'vertical',
                    handleStyle: {
                        color: '#132c81',
                        borderColor: '#132c81',
                    },
                    textStyle: {
                        color: 'transparent',
                    },
                },
            ],
            series: [
                {
                    name: '微博用户地区活跃榜',
                    type: 'bar',
                    zlevel: 1,
                    itemStyle: {
                        normal: {
                            barBorderRadius: 20,
                            // color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            //     offset: 0,
                            //     color: 'rgb(57,89,255,1)'
                            // }, {
                            //     offset: 1,
                            //     color: 'rgb(46,200,207,1)'
                            // }]),
                            color: (params) => {
                                return colorList[params.dataIndex];
                            },
                        },
                    },
                    label: {
                        show: true,
                        fontSize: 14,
                        position: 'right',
                        formatter: function (params, i) {
                            let { value, dataIndex } = params;
                            return `${barTitle} {value${dataIndex}| ${value}}`;
                        },
                        rich: setLabelStyle(xdata),
                    },
                    barWidth: 15,
                    data: datas,
                },
            ],
        };

        function setLabelStyle(xData) {
            var colors = ['#71c3ff', '#ffd684', '#ff9794', '#aaa1ff', '#ff0', '#f0f'];
            let rich = {
                name: {
                    fontSize: 16,
                    padding: [0, 2, 0, 0],
                    color: '#060C11',
                },
            };

            xData.forEach((ele, i) => {
                rich['value' + i] = {
                    color: colors[i],
                    fontSize: 20,
                };
            });
            return rich;
        }
        $.ajax({
            url: "/province/queryProvinceValue",
            dataType: "json",
            success: function (item) {
                for(let i in item){
                    datas.push(item[i]);
                }

            }
        });
        myChart.setOption(option);
        // myChart.setOption(option);
        // 使用刚指定的配置项和数据显示图表。
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }
    function echarts_3() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echart3'));
        // var time = []
        var time = [4231443, 2134435, 1134435, 2544435, 2434435, 1134435, 2834435, 2234435, 2132342, 3134435, 2434435,1134435];
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    lineStyle: {
                        color: '#dddc6b'
                    }
                }
            },
            grid: {
                left: '10',
                top: '20',
                right: '30',
                bottom: '10',
                containLabel: true
            },

            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                        fontSize: 14,
                    },
                },
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.2)'
                    }

                },

                data: ['0:00', '2:00', '4:00', '6:00', '8:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00']

            }, {

                axisPointer: { show: false },
                axisLine: { show: false },
                position: 'bottom',
                offset: 20,



            }],

            yAxis: [{
                type: 'value',
                axisTick: { show: false },
                splitNumber: 4,
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.1)'
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                        fontSize: 16,
                    },
                },

                splitLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.1)',
                        type: 'dotted',
                    }
                }
            }],
            series: [
                {
                    name: '结算率',
                    type: 'line',
                    smooth: true,
                    symbol: 'circle',
                    symbolSize: 5,
                    showSymbol: false,
                    lineStyle: {

                        normal: {
                            color: 'rgba(31, 174, 234, 1)',
                            width: 2
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(31, 174, 234, 0.4)'
                            }, {
                                offset: 0.8,
                                color: 'rgba(31, 174, 234, 0.1)'
                            }], false),
                            shadowColor: 'rgba(0, 0, 0, 0.1)',
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#1f7eea',
                            borderColor: 'rgba(31, 174, 234, .1)',
                            borderWidth: 5
                        }
                    },
                    data: time

                },

            ]

        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
            url: "/time/queryTime",
            dataType: "json",
            success: function (item) {
                for(let i in item){
                    time.push(item[i]);
                }
                // myChart.setOption(option);
            }
        });
        myChart.setOption(option);
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }
    function echarts_4() {
        var myChart = echarts.init(document.getElementById('echart4'));
        var keywords = [];
        // var keywords = [{"name":"男神","value":2.64},
        //                 {"name":"好身材","value":4.03},
        //                 {"name":"校草","value":24.95},
        //                 {"name":"酷","value":4.04},
        //                 {"name":"时尚","value":5.27},
        //                 {"name":"阳光活力","value":5.80},
        //                 {"name":"初恋","value":3.09},
        //                 {"name":"英俊潇洒","value":24.71},
        //                 {"name":"霸气","value":6.33},
        //                 {"name":"腼腆","value":2.55},
        //                 {"name":"蠢萌","value":3.88},
        //                 {"name":"青春","value":8.04},
        //                 {"name":"网红","value":5.87},
        //                 {"name":"萌","value":6.97},
        //                 {"name":"认真","value":2.53},
        //                 {"name":"古典","value":2.49},
        //                 {"name":"温柔","value":3.91},
        //                 {"name":"有个性","value":3.25},
        //                 {"name":"可爱","value":9.93},
        //                 {"name":"幽默诙谐","value":3.65},
        // ]

        var option = {
            series: [{
                type: 'wordCloud',
               //maskImage: maskImage,
                sizeRange: [15, 80],
                rotationRange: [0, 0],
                rotationStep: 45,
                gridSize: 8,
                shape: 'pentagon',
                width: '100%',
                height: '100%',
                 textStyle: {
                    normal: {
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        },
                        fontFamily: 'sans-serif',
                        fontWeight: 'normal'
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                data: keywords
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        // myChart.setOption(option);
        $.ajax({
            url: "/word/queryWord",
            dataType: "json",
            success: function (item) {
                for(let i in item){
                    keywords.push(item[i])
                    myChart.setOption(option);
                }
            }
        });
        window.addEventListener("resize", function() {
            myChart.resize();
        });
    }
    function echarts_6() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echart6'));
        // var man = []
        // var women = []
        var man = [90, 80, 20, 10, 30]
        var women = [30, 20, 75, 80, 70]
        option = {

            tooltip: {
                trigger: 'axis'
            },
            radar: [{
                indicator: [{
                    text: '10-20',
                    max: 100
                }, {
                    text: '20-30',
                    max: 100
                }, {
                    text: '30-40',
                    max: 100
                }, {
                    text: '40-50',
                    max: 100
                }, {
                    text: '50-60',
                    max: 100
                }],
                textStyle: {
                    color: 'red'
                },
                center: ['50%', '50%'],
                radius: '70%',
                startAngle: 90,
                splitNumber: 4,
                shape: 'circle',

                name: {
                    padding: -5,
                    formatter: '{value}',
                    textStyle: {

                        color: 'rgba(255,255,255,.5)'
                    }
                },
                splitArea: {
                    areaStyle: {
                        color: 'rgba(255,255,255,.05)'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.05)'
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,.05)'
                    }
                }
            },],
            series: [{
                name: '雷达图',
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                data: [{
                    name: '男性',
                    value: man,
                    lineStyle: {
                        normal: {
                            color: '#03b48e',
                            width: 2,
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: '#03b48e',
                            opacity: .4
                        }
                    },
                    symbolSize: 0,

                }, {
                    name: '女性',
                    value: women,
                    symbolSize: 0,
                    lineStyle: {
                        normal: {
                            color: '#3893e5',
                            width: 2,
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: 'rgba(19, 173, 255, 0.5)'
                        }
                    }
                }]
            },]
        };
        myChart.setOption(option);
        // $.ajax({
        //     url: "/time/queryManAge",
        //     dataType: "json",
        //     success: function (item) {
        //         for(let i in item){
        //             man.push(item[i]);
        //         }
        //     }
        // });
        // $.ajax({
        //     url: "/time/queryWomenAge",
        //     dataType: "json",
        //     success: function (item) {
        //         for(let i in item){
        //             women.push(item[i]);
        //         }
        //     }
        // });
        // console.log(women)
        // console.log(man)
        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }
})


















