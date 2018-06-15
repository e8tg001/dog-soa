var myLineMain = echarts.init(document.getElementById('lineMain'));
option = {
    title: {
        text: '7月通话量趋势',
        subtext: '【次】',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        show: false,
    },
    legend: {
    	bottom:'6',
        data:['每日通话量','每日首解量','每日建单量']
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}'
        }
    },
    series: [
        {
            name:'每日通话量',
            type:'line',
            data:[110, 119, 105, 130, 120, 103, 210,110, 119, 105, 130, 120, 103, 220,310, 119, 105, 130, 120, 103, 210,110, 119, 105, 130, 120, 103, 210, 221,190],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        },
        {
            name:'每日首解量',
            type:'line',
            data:[61, 80, 95, 65, 70, 83, 91,60, 49, 75, 103, 102, 83, 102,81, 51, 85, 103, 32, 73, 21,31, 59, 91, 30, 20, 34, 41, 92,79],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        },
        {
            name:'每日建单量',
            type:'line',
            data:[11, 10, 5, 13, 20, 3, 10,10, 9, 5, 10, 10, 8, 10,10, 5, 5, 3, 3, 10, 6,10, 9, 5, 13, 12, 3, 11, 7,9],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        }
    ]
};
//加载bar的数据进行展示
myLineMain.setOption(option);

var myLineMain3 = echarts.init(document.getElementById('lineMain3'));
lineMain3 = {
    title: {
        text: '7月通话时长趋势',
        subtext: '【秒】',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        show: false,
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} '
        }
    },
    series: [
        {
            name:'累计通话时长',
            type:'line',
            data:[11000, 10019, 10005, 10030, 12000, 10003, 10010,10010, 10019, 10050, 10300, 10200, 8003, 2020,10100, 5019, 10005, 10030, 3200, 1030, 2100,1010, 1019, 1050, 1300, 1200, 10300, 2010, 2021,1090],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        }        
    ]
};
//加载bar的数据进行展示
myLineMain3.setOption(lineMain3);

var myLineMain4 = echarts.init(document.getElementById('lineMain4'));
lineMain4 = {
    title: {
        text: '7月每通电话平均时长趋势',
        subtext: '【秒】',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        show: false,
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} '
        }
    },
    series: [
    	{
            name:'每通电话平均时长',
            type:'line',
            data:[110, 109, 100, 103, 120, 103, 100,110, 119, 150, 103, 102, 80, 202,101, 201, 105, 100, 32, 130, 210,101, 101, 105, 130, 120, 103, 210, 221,109],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        }
        
    ]
};
//加载bar的数据进行展示
myLineMain4.setOption(lineMain4);

var myLineMain2 = echarts.init(document.getElementById('lineMain2'));
option2 = {
    title: {
        text: '7月质检成绩趋势',
        subtext: '【平均分】',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        show: false,
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} '
        }
    },
    series: [
        {
            name:'成绩',
            type:'line',
            data:[10, 19, 15, 13, 12, 10, 10,11, 11, 10, 7, 9, 10, 20,10, 19, 10, 13, 12, 10, 21,11, 11, 10, 10, 12, 10, 10, 11,9],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        }
    ]
};
//加载bar的数据进行展示
myLineMain2.setOption(option2);

var myRadarMain = echarts.init(document.getElementById('radarMain'));
radarOption = {
    title: {
        text: '7月服务能力评分',
        subtext: '【平均分】',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip: {},
    legend: {
    	top: '7%',
        data: ['业务服务能力','解决问题能力']
    },
    radar: [
        {
            indicator: [
                { text: '礼貌用语3分' , max: 3 },
                { text: '倾听与确认3分' , max: 3 },
                { text: '吐字清晰3分' , max: 3 },
                { text: '主动3分' , max: 3 },
                { text: '耐心3分' , max: 3 },
                { text: '情绪安抚能力3分' , max: 3 }
            ],
            center: ['25%', '55%'],
            radius: 90,
            startAngle: 60,
            splitNumber: 4,
            shape: 'circle',
            name: {
                formatter:'【{value}】',
                textStyle: {
                    color:'#72ACD1'
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                    'rgba(114, 172, 209, 0.4)', 'rgba(114, 172, 209, 0.6)',
                    'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            }
        },
        {
            indicator: [
                { text: '条理清晰2分', max: 2 },
                { text: '表达技巧2分', max: 2 },
                { text: '全面性7分', max: 7 },
                { text: '准确性7分', max: 7 },
                { text: '熟练程度7分', max: 7 },
                { text: '提供有效解决方案7分', max: 7 }
            ],            
            center: ['75%', '55%'],
            radius: 90,
            startAngle: 60,
            splitNumber: 4,
            shape: 'circle',
            name: {
                formatter:'【{value}】',
                textStyle: {
                    color:'#72ACD1'
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                    'rgba(114, 172, 209, 0.4)', 'rgba(114, 172, 209, 0.6)',
                    'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            }
            
        }
    ],
    series: [
    	{
            name: '雷达图',
            type: 'radar',
            itemStyle: {
                emphasis: {
                    // color: 各异,
                    lineStyle: {
                        width: 4
                    }
                }
            },
            data: [
                {
                    value: [3,2,3,2,1,3],
                    name: '业务服务能力',
                    areaStyle: {
                        normal: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    }
                }
            ]
        },
        {
            name: '成绩单',
            type: 'radar',
            radarIndex: 1,
            data: [
                {
                    value: [1,1,2,7,5,7],
                    name: '解决问题能力',
                    areaStyle: {
                        normal: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    }
                }
            ]
        }
    ]
};

myRadarMain.setOption(radarOption);

var myPieMain = echarts.init(document.getElementById('pieMain'));
pieOption = {
    title : {
        text: '7月业务品牌通话量',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        right: '10%',
		top: '20%',
		formatter: function (name) {
		    return  name ;
		},
        data: ['固话业务','宽带业务','2G业务','3G业务','4G业务','融合业务','小灵通业务','其他']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '60%',
            center: ['40%', '60%'],
            label: {
                normal: {
                    show: true,
                    position: 'outside',
                    formatter: '{b}:{c} ({d}%)'
                }
            },
            data:[
                {value:335, name:'固话业务'},
                {value:310, name:'宽带业务'},
                {value:234, name:'2G业务'},
                {value:135, name:'3G业务'},
                {value:1548, name:'4G业务'},
                {value:135, name:'融合业务'},
                {value:135, name:'小灵通业务'},
                {value:135, name:'其他'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
myPieMain.setOption(pieOption);


var myPieMain2 = echarts.init(document.getElementById('pieMain2'));
pieOption2 = {
    title : {
        text: '7月客户满意度',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        right: '11%',
		top: '20%',
        data: ['非常满意','满意','一般','不满意','未参与']
    },
    series : [
        {
            name: '客户满意度',
            type: 'pie',
            radius : '60%',
            center: ['40%', '60%'],
            label: {
                normal: {
                    show: true,
                    position: 'outside',
                    formatter: '{b}:{c} ({d}%)'
                }
            },
            data:[
                {value:230, name:'非常满意'},
                {value:435, name:'满意'},
                {value:739, name:'一般'},
                {value:31, name:'不满意'},
                {value:3000, name:'未参与', selected:true}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
myPieMain2.setOption(pieOption2);

var myBarMain = echarts.init(document.getElementById('barMain'));
barOption = {
	title : {
        text: '7月业务品牌客户满意度',
        textStyle : {           
            fontSize : 14
        }
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {    	
		top: '7%',
        data: ['非常满意','满意','一般','不满意','未参与']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: ['固话业务','宽带业务','2G业务','3G业务','4G业务','融合业务','小灵通业务','其他']
    },
    series: [
    	{
            name: '非常满意',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [120, 102, 101, 134, 190, 130, 120,178]
        },
        {
            name: '满意',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [320, 302, 301, 334, 390, 330, 320,278]
        },
        {
            name: '一般',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [420, 402, 401, 434, 490, 430, 420,378]
        },
        {
            name: '不满意',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [120, 62, 101, 134, 90, 230, 210,91]
        },
        {
            name: '未参与',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [220, 182, 191, 234, 290, 330, 310,420]
        }
    ]
};
myBarMain.setOption(barOption);

var myBarMain2 = echarts.init(document.getElementById('barMain2'));
barOption2 = {
	title : {
        text: '7月业务品牌质检成绩',
        subtext: '【平均分】',
        textStyle : {           
            fontSize : 14
        }
    },
    color: ['#c23531'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['固话业务','宽带业务','2G业务','3G业务','4G业务','融合业务','小灵通业务','其他'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'平均分',
            type:'bar',
            barWidth: '60%',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data:[10, 32, 40, 63, 90, 70, 50,40],
	        markLine : {
	        	lineStyle:{
	        		normal:{
						color: '#c23531'
					}
	        	},				
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }            
        }
    ]
};
myBarMain2.setOption(barOption2);


var myBarMain3 = echarts.init(document.getElementById('barMain3'));
barOption3 = {
	title : {
        text: '7月静音时长趋势',
        subtext: '【秒】',
        textStyle : {           
            fontSize : 14
        }
    },
    color: ['#c23531'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'累计静音时长',
            type:'bar',
            barWidth: '60%',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data:[110, 119, 105, 130, 120, 103, 210,110, 119, 105, 130, 120, 103, 220,310, 119, 105, 130, 120, 103, 210,110, 119, 105, 130, 120, 103, 210, 221,110],
	        markLine : {
	        	lineStyle:{
	        		normal:{
						color: '#c23531'
					}
	        	},				
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }            
        }
    ]
};
myBarMain3.setOption(barOption3);