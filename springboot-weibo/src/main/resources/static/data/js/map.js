
$(function () {
    map();
    function map() {
        // 基于准备好的dom，初始化echarts实例
        var myChart= echarts.init(document.getElementById('map'));
        var datas = [];
        // var datas = [
        //     { name: '北京', value: 350000 },
        //     { name: '天津', value: 120000 },
        //     { name: '上海', value: 300000 },
        //     { name: '重庆', value: 92000 },
        //     { name: '河北', value: 25000 },
        //     { name: '河南', value: 20000 },
        //     { name: '云南', value: 500 },
        //     { name: '辽宁', value: 3050 },
        //     { name: '黑龙江', value: 80000 },
        //     { name: '湖南', value: 2000 },
        //     { name: '安徽', value: 24580 },
        //     { name: '山东', value: 40629 },
        //     { name: '新疆', value: 36981 },
        //     { name: '江苏', value: 13569 },
        //     { name: '浙江', value: 24956 },
        //     { name: '江西', value: 15194 },
        //     { name: '湖北', value: 41398 },
        //     { name: '广西', value: 41150 },
        //     { name: '甘肃', value: 17630 },
        //     { name: '山西', value: 27370 },
        //     { name: '内蒙古', value: 27370 },
        //     { name: '陕西', value: 97208 },
        //     { name: '吉林', value: 88290 },
        //     { name: '福建', value: 19978 },
        //     { name: '贵州', value: 94485 },
        //     { name: '广东', value: 89426 },
        //     { name: '青海', value: 35484 },
        //     { name: '西藏', value: 97413 },
        //     { name: '四川', value: 54161 },
        //     { name: '宁夏', value: 56515 },
        //     { name: '海南', value: 54871 },
        //     { name: '台湾', value: 48544 },
        //     { name: '香港', value: 49474 },
        //     { name: '澳门', value: 34594 }
        // ]
        var option = {
            tooltip: {  //这里设置提示框
                trigger: 'item',  //数据项图形触发
                backgroundColor: "black",  //提示框浮层的背景颜色。
                //字符串模板(地图): {a}（系列名称），{b}（区域名称），{c}（合并数值）,{d}（无）
                 formatter:'地区：{b}<br/>用户活跃度：{c}'
            },
            series: [
                {
                    name: '用户活跃度',
                    type: 'map',
                    mapType: 'china',
                    roam: false,//是否开启鼠标缩放和平移漫游
                    itemStyle: {//地图区域的多边形 图形样式
                        normal: {//是图形在默认状态下的样式
                            label: {
                                show: true,//是否显示标签
                                textStyle: {
                                    color: "black"
                                }
                            },
                            areaColor:'rgba(37, 157, 255, 0.2)',
                        },
                        zoom: 1.5,  //地图缩放比例,默认为1
                        emphasis: {//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                            label: { show: true },
                            areaColor: 'rgba(2,37,101,.8)'
                        }
                    },
                    top: "3%",//组件距离容器的距离
                    data: datas
                },
            ]

        };

        $.ajax({
            url: "/province/query",
            dataType: "json",
            success: function (item) {
                for(let i in item){
                    datas[i] = item[i];
                }
                myChart.setOption(option);
            }
        });
    }

})

