
//srcipt标签式引入
var myBarAndLineMain = echarts.init(document.getElementById('barAndLineMain'));
barAndLineOption = {
	title: {
		text: '7月每日 投诉 通话量环比趋势',
		subtext: '【次】',
        textStyle : {           
            fontSize : 14
        }
	},	
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {        
    },
    legend: {
    	bottom:'6',
        data:['投诉','投诉-环比']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '总量',
            min: 0,
            max: 70,            
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: '百分比',
            min: -40,
            max: 100,
            interval: 10,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'投诉',
            type:'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data:[6, 5, 9, 24, 28, 30, 34, 38, 44, 18, 6, 23 ,6, 50, 9, 24, 28, 30, 17, 18, 48, 18, 30, 23, 16, 15, 9, 24, 28, 30]
            ,markLine : {
	            data : [
	                {type : 'average', name : '平均值'}
	            ]
	        }
        },
        {
            name:'投诉-环比',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, -2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, -16.5, 2.0, -6.2, 2.0, -2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, -16.5, 12.0, -6.2, 2.0, -2.2, 3.3, 4.5, 6.3, 10.2]
        }
    ]
};

//加载bar的数据进行展示
myBarAndLineMain.setOption(barAndLineOption);

//var myLineMain = echarts.init(document.getElementById('lineMain'));
option = {
    title: {
        text: '7月 投诉 通话量趋势',
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
            name:'累计通话量',
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
        }
    ]
};
//加载bar的数据进行展示
//myLineMain.setOption(option);


var myLineMain3 = echarts.init(document.getElementById('lineMain3'));
lineMain3 = {
    title: {
        text: '7月 投诉 通话总时长趋势',
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
        text: '7月 投诉 每通电话平均时长趋势',
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

var myPieMain = echarts.init(document.getElementById('pieMain'));
pieOption = {
    title : {
        text: '7月 投诉 业务品牌通话量',
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
        text: '7月 投诉 客户满意度',
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
        text: '7月 投诉 业务品牌客户满意度',
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


var myBarMain3 = echarts.init(document.getElementById('barMain3'));
barOption3 = {
	title : {
        text: '7月 投诉 静音时长趋势',
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