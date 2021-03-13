function getNavigation() {/* js 生成导航栏 */
    var a = "";
    a = "<nav class=\"navbar navbar-default\">\n" +
        "    <div class=\"container-fluid\">\n" +
        "        <!-- Brand and toggle get grouped for better mobile display -->\n" +
        "        <div class=\"navbar-header\">\n" +
        "            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n" +
        "                <span class=\"sr-only\">Toggle navigation</span>\n" +
        "                <span class=\"icon-bar\"></span>\n" +
        "                <span class=\"icon-bar\"></span>\n" +
        "                <span class=\"icon-bar\"></span>\n" +
        "            </button>\n" +
        "            <a class=\"navbar-brand\" href=\"#\">蚕</a>\n" +
        "        </div>\n" +
        "\n" +
        "        <!-- Collect the nav links, forms, and other content for toggling -->\n" +
        "        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
        "            <ul class=\"nav navbar-nav\" id=\"navi_left\">\n" +
        "                <li class=\"active\"><a href=\"#\">登录界面<span class=\"sr-only\">(current)</span></a></li>\n" +
        "            </ul>\n" +
        "            <ul class=\"nav navbar-nav navbar-right\">\n" +
        "                <li class=\"dropdown\">\n" +
        "                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">更多 <span class=\"caret\"></span></a>\n" +
        "                    <ul class=\"dropdown-menu\">\n" +
        "                        <li><a href=\"#\">待开发</a></li>\n" +
        "                        <li role=\"separator\" class=\"divider\"></li>\n" +
        "                        <li><a href=\"#\">获取帮助</a></li>\n" +
        "                        <li><a href=\"#\">关于我们</a></li>\n" +
        "                    </ul>\n" +
        "                </li>\n" +
        "            </ul>\n" +
        "        </div><!-- /.navbar-collapse -->\n" +
        "    </div><!-- /.container-fluid -->\n" +
        "</nav>"

    /*document.getElementById("navi_bar").innerHTML=a;*/
    $(".navi_bar").html(a);
    /*window.alert(window.location.pathname);*/
    if (window.location.pathname != "/login" && window.location.pathname !="/login/"){
        if(window.location.pathname == "/index" || window.location.pathname =="/index/"){
            document.getElementById("navi_left").innerHTML="<li class=\"active\"><a href=\"/index\">问答界面</a></li>\n<li><a href=\"/admin\">数据管理</a></li>"
        }
        else if (window.location.pathname == "/admin" || window.location.pathname =="/admin/"){
            document.getElementById("navi_left").innerHTML="<li><a href=\"/index\">问答界面</a></li>\n<li class=\"active\"><a href=\"/admin\">数据管理</a></li>"
        }
    }
}
function get_data_table() {/* js 表格数据 */
    /*  动态生成表格 ↓  */
    var tbody = document.querySelector("tbody");
    var i = 1;
    var str;
    var url = "/admin/retrieve";/*接口地址*/
    var args = {};

    tbody.innerHTML="";
    $.ajaxSettings.async=true;
    $.post(url,args,function (res) {
        /*res='{"id"=1,"problem"="1111","media_type"="123","answer"="123456"}';*/
        str = '['+res+']';
        /*console.log(str);*/

        var obj = JSON.parse(str);

        for(var i = 0;i < obj.length;i++){
            var tr = document.createElement("tr");

            tbody.appendChild(tr);

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = 1+i;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj[i].problem;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj[i].type;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj[i].media_type;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj[i].answer;

            var td = document.createElement("td");
            tr.appendChild(td);
            var id = obj[i].id;
            td.innerHTML = "<button id=" + id + " type=\"button\" onclick=\"edit_one(id)\">编辑</button><button id=" + id + " type=\"button\" onclick=\"delete_one(id)\">删除</button>";
        }
        goPage(1);
    });

    $.ajaxSettings.async=false;
    /*  动态生成表格 ↑  */

    /*hide_btn();*/
}
function edit_one(n) {
    /*  生成编辑窗口  */
    var tbody = document.querySelector("tbody");
    var i = 1;
    var str;
    var url = "/admin/retrieve";/*接口地址*/
    var args = {};
    var obj;

    if (n !== -1){
        $.ajaxSettings.async=false;
        $.post(url,args,function (res) {str=res;/*window.alert(str);*/});
        $.ajaxSettings.async=true;
        /*window.alert(str);*/
        obj = JSON.parse('['+str+']');
    }
    else {
        obj = JSON.parse('[{"id":-1,"problem":"","type":"","media_type":"","answer":""}]');
    }
     tbody.innerHTML="";
    for(var i = 0;i < obj.length;i++){

        if (obj[i].id == n){
            var tr = document.createElement("tr");
            tbody.appendChild(tr);

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = 1+i;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = "<div class=\"input-group\">\n                   <input id=\"input_problem\" type=\"text\" class=\"form-control\" value=\""+obj[i].problem+"\" aria-describedby=\"basic-addon1\"> </div>";

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = "<div class=\"input-group\">\n                   <input id=\"input_type\" type=\"text\" class=\"form-control\" value= \""+obj[i].type+"\" aria-describedby=\"basic-addon1\"> </div>";

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = "<div class=\"input-group\">\n                   <input id=\"input_media_type\" type=\"text\" class=\"form-control\" value= \""+obj[i].media_type+"\" aria-describedby=\"basic-addon1\"> </div>";

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = "<div class=\"input-group\">\n                   <input id=\"input_answer\" type=\"text\" class=\"form-control\" value=\""+obj[i].answer+"\" aria-describedby=\"basic-addon1\"> </div>";

            var td = document.createElement("td");
            tr.appendChild(td);
            var id = obj[i].id;
            td.innerHTML = "<button id="+id+" type=\"submit\" onclick=\"submit_data(id)\">提交</button>";

        }
    }
    hide_btn();
}
function delete_one(n) {
    if (window.confirm("确定要删除吗？"==true)){
        var url = "/admin/delete";
        var args = {id:n};

        $.post(url,args,function (res) {if (res=="success"){window.alert("删除成功!");}})
    }
}
function submit_data(id) {/* 提交修改 */
    var type = $("#input_type").val();
    var problem = document.getElementById("input_problem").value;
    var media_type = document.getElementById("input_media_type").value;
    var answer = document.getElementById("input_answer").value;
    var url="/admin/editData";
    var args ={id:id,problem:problem,type:type,media_type:media_type,answer:answer};
    /*window.alert(id);
    window.alert(problem)
    window.alert(media_type)
    window.alert(answer)*/
    $.ajaxSettings.async=false;
    $.post(url,args,function (res) {if(res=="success"){window.alert("提交成功！")}});
    $.ajaxSettings.async=true;
    /*$.ajax(url,{
        type: "POST",
        async:false,
        dataType:"json",
        data:{"id":id,"problem":problem,"media_type":media_type,"answer":answer},
        success:window.alert("提交成功！")
    });*/
    initial();
}
function hide_btn() {/*隐藏按钮*/
    if (document.getElementById("input_problem")){console.log("hide:add,display:back"); $("#btn_add").css("display","none");  /*隐藏添加按钮*/  $("#btn_back").css("display","block"); /*显示返回按钮*/} else {console.log("hide:back,display:add"); $("#btn_add").css("display","block"); /*取消隐藏添加按钮*/ $("#btn_back").css("display","none"); /*隐藏返回按钮*/}
}
function initial() {
    getNavigation();
    if (window.location.pathname == "/admin" || window.location.pathname == "/admin/"){get_data_table();hide_btn();}
}
/**
 * 分页函数
 * pno--页数
 * psize--每页显示记录数
 * 分页部分是从真实数据行开始，因而存在加减某个常数，以确定真正的记录数
 * 纯js分页实质是数据行全部加载，通过是否显示属性完成分页功能
 **/
function goPage(pno){
    var itable = document.getElementById("idData");
    var num = itable.rows.length;//表格所有行数(所有记录数)
    console.log(num);
    var totalPage = 0;//总页数
    var pageSize = 15;//每页显示行数
    //总共分几页
    if(num/pageSize > parseInt(num/pageSize)){
        totalPage=parseInt(num/pageSize)+1;
    }else{
        totalPage=parseInt(num/pageSize);
    }
    var currentPage = pno;//当前页数
    var startRow = (currentPage - 1) * pageSize+1;//开始显示的行  31
    var endRow = currentPage * pageSize;//结束显示的行   40
    endRow = (endRow > num)? num : endRow;    //40
    console.log(endRow);
    //遍历显示数据实现分页
    for(var i=1;i<(num+1);i++){
        var irow = itable.rows[i-1];
        if(i>=startRow && i<=endRow){
            irow.style.display = "table-row";
        }else{
            irow.style.display = "none";
        }
    }
    var pageEnd = document.getElementById("pageEnd");
    var tempStr = "<span>共"+totalPage+"页</span>";


//.bind("click",{"newPage":pageIndex},function(event){
//        goPage((pageIndex-1)*pageSize+1,(pageIndex-1)*pageSize+pageSize);
//    }).appendTo('#pages');
    if(currentPage>1){
        tempStr += "<span class='btn' href=\"#\" onClick=\"goPage("+(1)+")\">首页</span>";
        tempStr += "<span class='btn' href=\"#\" onClick=\"goPage("+(currentPage-1)+")\">上一页</span>"
    }else{
        tempStr += "<span class='btn'>首页</span>";
        tempStr += "<span class='btn'>上一页</span>";
    }

    for(var pageIndex= 1;pageIndex<totalPage+1;pageIndex++){
        tempStr += "<a onclick=\"goPage("+pageIndex+")\"><span>"+ pageIndex +"</span></a>";
    }

    if(currentPage<totalPage){
        tempStr += "<span class='btn' href=\"#\" onClick=\"goPage("+(currentPage+1)+")\">下一页</span>";
        tempStr += "<span class='btn' href=\"#\" onClick=\"goPage("+(totalPage)+")\">尾页</span>";
    }else{
        tempStr += "<span class='btn'>下一页</span>";
        tempStr += "<span class='btn'>尾页</span>";
    }

    document.getElementById("barcon").innerHTML = tempStr;

}