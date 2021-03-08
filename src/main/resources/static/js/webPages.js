function getNavigation() {
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
    if (window.location.pathname != "/login"){
        if(window.location.pathname == "/index"){
            document.getElementById("navi_left").innerHTML="<li class=\"active\"><a href=\"/index\">问答界面</a></li>\n<li><a href=\"/damin\">数据管理</a></li>"
        }
        else if (window.location.pathname == "/admin"){
            document.getElementById("navi_left").innerHTML="<li><a href=\"/index\">问答界面</a></li>\n<li class=\"active\"><a href=\"/admin\">数据管理</a></li>"
        }
    }
}

function get_data_table() {
    /*  动态生成表格 ↓  */
    var tbody = document.querySelector("tbody");
    var i = 1;
    var str;
    var url = "...";/*接口地址*/
    var args = {};

    $.post(url,args,function (res) {
        /*res='{"id"=1,"problem"="1111","media_type"="123","answer"="123456"}';*/
        str = '['+res+']';

        console.log(str);

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
            td.innerHTML = obj[i].media_type;

            var td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj[i].answer;

            var td = document.createElement("td");
            tr.appendChild(td);
            var id = obj[i].id;
            td.innerHTML = "<button id=\"btn_edit_" + id + "\" name=\"编辑\">";
        }
    });

    /*  动态生成表格 ↑  */

}