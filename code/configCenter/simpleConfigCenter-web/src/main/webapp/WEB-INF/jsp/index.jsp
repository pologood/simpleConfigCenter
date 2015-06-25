<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>配置中心管理</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="/static/js/jquery.min.js"></script>
    <link href="/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/bootstrap/blog.css" rel="stylesheet">
    <script src="/static/js/jquery.extend.js"></script>
    <script src="/static/js/ConfigCenter.js"></script>
    <script src="/static/js/map.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>


<div class="blog-masthead">
    <div class="blog_container ">
        <nav class="blog-nav" id="topSidebar">
        </nav>
    </div>
</div>

<div class="container-fluid">

    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" id="leftSidebar">
            </ul>

        </div>
        <br>

        <div class="col-xs-12 col-sm-9" id="content">


        </div>


    </div>
</div>
</body>
</html>
<script>


    $(document).ready(function () {
        var main = ConfigCenter.getInstance();

        //initTabResource(main);

        main.loadDefaultSidebar();
        $('#topSidebar>a').live('click', function () {//点击top
            $(this).addClass("blog-active").siblings().removeClass("blog-active");
            var leftId = $(this).attr('id');
            $('#leftSidebar').html(main.getHTML(leftId));
        });

        $('#leftSidebar>li').live('click', function () {//点击左侧菜单
            $(this).css('background-color', '#f8f8f9').siblings().css('background-color', '');
            var a = $(this).find("a")[0];
            var requestUrl = $(a).attr('src');
            if (typeof(requestUrl) != 'undefined') {
                $('.theme-popover-mask').fadeIn(100);
                main.ajaxRequestForcontent(requestUrl, '', 'content');
            }
        });
        $('#pageStr li>a').live('click', function () { //翻页
            var requestUrl = $(this).attr('src');
            if (!requestUrl) return;
            main.ajaxRequestForcontent(requestUrl, '', 'content');
            //main.ajaxRequestForcontent(requestUrl, '', 'content');
        })
        main.loadDefaultPage();

        $(':input[method]').live('click', function () {
            var method = $(this).attr('method');
            if (typeof(method) != 'undefined') {
                var event = new Event();
                event.setSource(this);
                main[method].request(event.getSource());
            }
        })
        $(':input[changeMethod]').live('change', function () {
            var method = $(this).attr('changeMethod');
            if (typeof(method) != 'undefined') {
                var event = new Event();
                event.setSource(this);
                main[method].request(event.getSource());
            }
        })


    });
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = $.banBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = $.banBackSpace;

</script>