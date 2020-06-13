$(function (){
    var myChart = echarts.init(document.getElementById('WarehousingOutOfStock_ReportForm'));
    $.get('warehousingOutOfStockReportForm.html?act=WarehousingOutOfStockReportFormSunburst_json', function (data) {

        console.log("data.data.value :"+data.state)
        if (data.state==1) {
            //提示
            MessagerShow(data.message)
        }else {
            myChart.setOption({
                series : [
                    {
                        name: '出入库',
                        type: 'sunburst',    // 设置图表类型为
                        data:data.data,
                        label: {
                            align: 'right',
                            position: 'inside',
                            fontSize: 14,
                        }
                    }
                ]
            })
        }

    }, 'json')
})

function MessagerShow(messager) {
    $.messager.show({
        title: "温馨提示",
        msg: messager,
        timeout: 4000,
        showType: 'slide'
    })
}