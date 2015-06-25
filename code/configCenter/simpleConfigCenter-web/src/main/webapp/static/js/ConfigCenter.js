var ConfigCenter = (function () {
    var instance;
    var _ = function () {
        var me = this;
        // 事件对象

        me.handler={
            callbacks:{
                queryDataConfig:function(data){
                    $('#content').html(data);
                },
                toEditDataConfig:function(data){
                    $('#content').html(data);
                },
                queryHtmlElementConfig:function(data){
                    $('#content').html(data);
                },
                toEditHtmlElementConfig:function(data){
                    $('#content').html(data);
                },
                querySystemConfig:function(data){
                    $('#content').html(data);
                },
                toEditSystemConfig:function(data){
                    $('#content').html(data);
                },
                getConfigDataHTML:function(data){
                    $('.table>tbody').append(data.content);
                }

            },
            callPara:{

                queryDataConfig:function(){
                    var paraData = $('#queryDataConfigForm').serialize();
                    return paraData;
                },
                toEditDataConfig:function(){
                    return "";
                },
                queryHtmlElementConfig:function(){
                    var paraData = $('#queryHtmlElementConfigForm').serialize();
                    return paraData;
                },
                toEditHtmlElementConfig:function(){
                    return "";
                },
                querySystemConfig:function(){
                    var paraData = $('#querySystemConfigForm').serialize();
                    return paraData;
                },
                toEditSystemConfig:function(){
                    return "";
                },
                getConfigDataHTML:function(data){
                   var requestUrl = data['requestUrl'].value;
                    requestUrl = requestUrl + "?systemName=test";
                    data['requestUrl'].value = requestUrl;
                }
            }
        }

        me.showEnableTip = {
            "生效": {"text": "失效", "class": "btn btn-info", "tip": "生效成功"},
            "失效": {"text": "生效", "class": "btn btn-warning", "tip": "失效成功"}
        }


        /*        me.createForm=function()
         {
         var form = jQuery('<form   name="roleResoucesTreeForm"></form>');
         var oldElement = $('input[name="saveTree"]');
         var newElement = jQuery(oldElement).clone();
         jQuery(newElement).appendTo(form);
         return form;
         }*/
        me.formRequest = {//数据来源于form
            this_me: this,
            event: '',
            callbackUrl: '',
            content: 'content',
            request: function (event) {
                this.event = event;
                var paraData = $('#' + event['form'].value).serialize();
                var action = event["action"].value;
                if (action == 'new') {
                    window.open(event['requestUrl'].value);
                } else {

                    if( event['handler']){
                        var handler = event['handler'].value;
                        me.handler.callPara[handler](event);
                        me.ajax(action, event['requestUrl'].value, paraData, me.handler.callbacks[handler], this, event);
                    }else{
                        me.ajax(action, event['requestUrl'].value, paraData, this.callback, this);
                    }
                }
            },
            callback: function (data) {
                var status = data.status;
                if (status == true) {
                    alert("成功");
                } else {
                    alert(data.reason);
                }
            }
        }


        me.submit = {
            request: function (event) {
                var action = event["action"].value;
                if (action == "delete") {
                    if (!window.confirm("要删除吗？")) {
                        return;
                    }
                }
                var handler = event['handler'].value;
                var paraData = me.handler.callPara[handler](event);
                me.ajax(action, event['requestUrl'].value, paraData, me.handler.callbacks[handler], this, event);
            }
        }
        me.ajax = function (type, url, paraData, fnMethod, fnObj, event) {
            me.releaseLastAjax();
            $.ajax({
                type: type,
                url: url,
                data: paraData,
                cache: false,
                async: true,
                success: function (result) {
                    fnMethod.call(fnObj, result, event);
                }
            })
        }

        me.updateAjax = function (paraData,url,fn,fnP1){
            $.ajax({
                type: "post",
                url: url,
                cache: false,
                async:true,
                data:paraData+"&_t="+new Date().getTime(),
                success: function (result) {
                    fn.call(null,result,fnP1);//回调
                }
            })
        }

        me.ajaxRequestForcontent = function (url, paraData, domId) {
            me.releaseLastAjax();
            var ajaxRequest = $.ajax({
                type: "get",
                url: url,
                async: true,
                data: paraData + "&_t=" + new Date().getTime(),
                complete: function (data) {
                    $('.theme-popover-mask').fadeOut(1);
                    $('#' + domId).html(data.responseText);

                },
                success: function () {
                }
            })
            me.ajaxRequesArr.push(ajaxRequest);//临时存放ajax请求对象
        }


        /*        $.ajax.beforeSend(function(){
         me.releaseLastAjax();
         })*/

        me.ajaxRequesArr = [];

        me.releaseLastAjax = function () {
            for (var i = 0; i < me.ajaxRequesArr.length; i++) {
                var status = me.ajaxRequesArr[i].status;
                if (status != 200) {
                    me.ajaxRequesArr[i].abort();
                }
            }
            me.ajaxRequesArr = [];
        }



        var me = this;
        me.map = new Map();
        me.initTopMenusHTML = function () {
            me.map.put("systemList",'   <li class="active"><a href="javascript:void(0)" src="/systemConfig/list">系统列表</a></li>' +
                ' <li><a href="javascript:void(0)"  src="/systemConfig/toAdd">系统添加</a></li>');
            me.map.put("configList",'   <li class="active"><a href="javascript:void(0)" src="/dataConfig/list">配置列表</a></li>' +
                ' <li><a href="javascript:void(0)"  src="/dataConfig/toAdd">配置添加</a></li>');
            me.map.put("htmlList",'   <li class="active"><a href="javascript:void(0)" src="/htmlElementConfig/list">配置列表</a></li>' +
            ' <li><a href="javascript:void(0)"  src="/htmlElementConfig/toAdd">配置添加</a></li>');


        }
        me.topMenu = {
            html:'<a class="blog-nav-item " href="javascript:void(0)" id="configList">配置管理</a>'+
            '<a class="blog-nav-item blog-active" href="javascript:void(0)" id="systemList">系统管理</a>'+
            '<a class="blog-nav-item blog-active" href="javascript:void(0)" id="htmlList">html组件管理</a>'
        }
        me.getHTML = function (key) {
            return me.map.get(key);
        }
        me.loadDefaultSidebar = function () {//加载菜单
            me.initTopMenusHTML();
            $('#topSidebar').html(me.topMenu.html);//top menu
            $('#leftSidebar').html(me.getHTML('resourcesList'));//左侧菜单
        }
        me.loadDefaultPage = function () {//加载默认显示的页面
            var a = $('#topSidebar').find("a")[0];
            $(a).click();
            var li = $('#leftSidebar').find("li")[0];
            $(li).click();
        }

        me.button = {
            taskStatusBtn:{//任务状态按钮类型
                info:'<button type="button" class="btn btn-info">未执行</button>',
                warning:'<button type="button" class="btn btn-warning">锁定</button>',
                success:'<button type="button" class="btn btn-success">完成</button>'
            }
        }

        me.querySubTaskTimer = {
            intevalId:'',
            request:function(obj){//刷新子任务列表
                if($(obj.target).attr("checked")){
                    var excuteFunc = "$('#subTaskQuery').click()";
                    me.excuteInterval.startInterval(excuteFunc,$(obj.target).val());
                }else{
                    this.stop();
                }
            },
            stop:function(){//停止
                me.excuteInterval.stopInterval();
            }
        }

        me.excuteInterval ={//执行定时任务
            intevalId:'0',
            startInterval:function(excuteFunc,interval){//停止定时任务
                this.intevalId = window.setInterval(excuteFunc , interval);
            },

            stopInterval:function(){//停止定时任务
                window.clearInterval(this.intevalId);
            }
        }




    }
    return {
        getInstance: function () {
            if (!instance) {
                instance = new _();
            }
            return instance;
        }
    }


})()

var Event = function () {
    this.target;
    this.obj = {};
    this.setSource = function (objs) {
        this.obj = objs.attributes;
        this.target = objs;
        this.obj.target = objs;
    }
    this.getSource = function () {
        return this.obj;
    }
    this.getTarget = function () {
        return this.target;
    }
}