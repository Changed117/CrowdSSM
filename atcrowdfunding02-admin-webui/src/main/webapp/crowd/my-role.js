// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {
    //获取分页数据
    var pageInfo = getPageInfoRemote();

    // 调用函数填充表格
    fillTableBody(pageInfo);
}

// 访问远端服务器获取pageInfo
function getPageInfoRemote() {

    var ajaxResult =  $.ajax({
        "url":"roles/get/page/info.json",
        "type":"post",
        "data":{
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        //关闭异步
        "async": false,
        "dataType": "json"
    });

    // 获取响应状态码
    var statusCode = ajaxResult.status;

    if(statusCode != 200){
        layer.msg("失败！响应状态码="+statusCode+"说明信息="+ajaxResult.statusText);
        return null;
    }

    var resultEntity = ajaxResult.responseJSON;

    var result = resultEntity.result;

    //如果响应失败显示错误信息
    if (result == "FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }

    //全部成功后获取pageInfo
    var pageInfo = resultEntity.data;

    return pageInfo;
}

// 填充页面表格
function fillTableBody(pageInfo) {
    // 清除tbody的旧内容
    $("#rolePageBody").empty();

    // 判断pageInfo是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0){
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉！没有查到您要的数据</td></tr>");
        return ;
    }

    // 填充tbody
    for (var i = 0; i < pageInfo.list.length; i++){
        var role = pageInfo.list[i];

        var roleId = role.id;

        var roleName = role.name;

        var numberTd = "<td>"+(i+1)+"</td>";
        var checkboxTd = "<td><input type='checkbox'></td>";
        var roleNameTd = "<td>"+roleName+"</td>";

        var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button type='button' class='btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";

        var buttonTd = "<td>"+checkBtn+" "+pencilBtn+" "+removeBtn+"</td>";

        var tr = "<tr>"+numberTd+checkboxTd+roleNameTd+buttonTd+"</td>";

        $("#rolePageBody").append(tr);
    }


}

// 生成翻页导航条
function generateNavigator(pageInfo) {



}

// 翻页时的回调函数
function paginationCallback(pageIndex, jQuery) {
    
}