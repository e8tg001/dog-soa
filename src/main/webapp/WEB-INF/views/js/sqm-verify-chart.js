


var myPieMain2 = echarts.init(document.getElementById('pieMain2'));
pieOption2 = {
    title : {
        text: '专题核实占比',
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
        data: ['属实','未属实']
    },
    series : [
        {
            name: '专题核实占比',
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
                {value:200, name:'未属实'},
                {value:800, name:'属实', selected:true}
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


var myBarMain3 = echarts.init(document.getElementById('barMain3'));
barOption3 = {
	title : {
        text: '专题核实日工作量',
        subtext: '【天】',
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