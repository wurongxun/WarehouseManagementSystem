// 基于准备好的dom，初始化echarts实例


$(function () {
    var myChart = echarts.init(document.getElementById('ShelfPositionInformation_ReportForm'));

    $.get('shelfPositionInformationReportFrom.html?act=json', function (json) {
        if (json.state==0){
            // 指定图表的配置项和数据
            var option = {
                legend: {},
                tooltip: {},
                dataset: {
                    // 提供一份数据。
                    source: json.data,
                },
                // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
                xAxis: {type: 'category'},
                // 声明一个 Y 轴，数值轴。
                yAxis: {},
                // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
                series: [
                    {type: 'bar'},
                    {type: 'bar'},
                    {type: 'bar'},
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })

})



