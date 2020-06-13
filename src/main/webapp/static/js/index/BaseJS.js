
function del() {
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;
    }
}
function formatter(date){// 重写默认日期格式
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours().toString();
    var minute = date.getMinutes().toString();
    var second = date.getSeconds().toString();
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' +second;
}
function parser(date) {
    //重写formatter 必须 重写parser, 否则无论选择哪天, 日期均为当天日期
    //xxxx-x-x xx:xx:xx
    if (!date) return new Date();
    var array = date.split(" ");// 分成日期和时间两部分
    var arrayDate = array[0].split("-");
    var yearStr = arrayDate[0];
    var monthStr = arrayDate[1];
    var dayStr = arrayDate[2];
    var arrayTime = array[1].split(":");
    var hour = arrayTime[0];
    var minute = arrayTime[1];
    var second = arrayTime[2];
    var year = parseInt(yearStr, 10);
    var month = parseInt(monthStr, 10);
    var day = parseInt(dayStr, 10);
    if (!isNaN(year) && !isNaN(month) && !isNaN(day) && !isNaN(hour) && !isNaN(minute) && !isNaN(second)) {
        return new Date(year, month - 1, day, hour, minute, second);
    } else {
        return new Date();
    }
}