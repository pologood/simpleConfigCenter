<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form class="form-inline" role="form" id="queryHtmlElementConfigForm">
    <table class="table  table-hover" style="width: 40%">
        <tr>
            <td>

                <select name="systemName" class="form-control">
                    <option value="">请选择系统名字</option>
                    <c:forEach var="item" items="${systemConfig_uq_name_Map}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==parameterMap.systemName[0]}"> selected="" </c:if>
                                >${item.value.nameDesc}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="elementName" placeholder="html组件name" class="form-control"
                       value="${parameterMap.elementName[0]}"/></td>
            <td>
                <select name="elementType" class="form-control">
                    <option value="">请选择html组件类型</option>
                    <c:forEach var="item" items="${htmlElementTypeConfig}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==parameterMap.htmlElementTypeConfig[0]}"> selected="" </c:if>
                                >${item.value}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="elementValueType" class="form-control">
                    <option value="">请选择html值的类型</option>
                    <c:forEach var="item" items="${htmlValueTypeConfig}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==parameterMap.elementValueType[0]}"> selected="" </c:if>
                                >${item.value}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="button" class="btn  btn-warning" value="查询" action='get'
                       requestUrl='/htmlElementConfig/list' method="submit" handler="queryHtmlElementConfig"/>
            </td>
        </tr>
    </table>
</form>
共<c:out value="${count}"/>条数据
<br>
<table class="table table-bordered table-hover">
    <thead>
    <tr>

        <th>系统名字</th>
        <th>html组件name</th>
        <th>html组件展示名字</th>
        <th>html组件类型</th>
        <th>html值的类型</th>
        <th>html初始值</th>
        <th>是否必填</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="result" items="${htmlElementConfigList}" varStatus="status">
        <tr>

            <td class="col-lg-1">  ${systemConfig_uq_name_Map[result.systemName].nameDesc} </td>
            <td class="col-lg-1">${result.elementName} </td>
            <td class="col-lg-1">${result.elementDisplay} </td>
            <td class="col-lg-1">${htmlElementTypeConfig[result.elementType]} </td>
            <td class="col-lg-1">${htmlValueTypeConfig[result.elementValueType]} </td>
            <td class="col-lg-1">${result.elementInitValue} </td>
            <td class="col-lg-1">${result.elementValueCheck} </td>
            <td class="col-lg-1">${result.status} </td>


            <td class="col-lg-1">
                <input type="button" class="btn  btn-warning" value="编辑" action='get'
                       requestUrl='/htmlElementConfig/toEdit?id=${result.id}' method="submit"
                       handler="toEditHtmlElementConfig"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../../common/page.jsp"></jsp:include>