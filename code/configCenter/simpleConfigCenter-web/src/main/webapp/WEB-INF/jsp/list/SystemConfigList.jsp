<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form class="form-inline" role="form" id="querySystemConfigForm">
    <table class="table  table-hover" style="width: 40%">
        <tr>
            <td><input type="text" name="name" placeholder="系统名字" class="form-control" value="${parameterMap.name[0]}"/>
            </td>
            <td><input type="text" name="nameDesc" placeholder="系统描述" class="form-control"
                       value="${parameterMap.nameDesc[0]}"/></td>
            <td>
                <input type="button" class="btn  btn-warning" value="查询" action='get'
                       requestUrl='/systemConfig/list' method="submit" handler="querySystemConfig"/>
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
        <th>系统描述</th>
        <th>操作</th>
    </tr>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="result" items="${systemConfigList}" varStatus="status">
        <tr>

            <td class="col-lg-1">${result.name} </td>
            <td class="col-lg-1">${result.nameDesc} </td>
            <td class="col-lg-1">
                <input type="button" class="btn  btn-warning" value="编辑" action='get'
                       requestUrl='/systemConfig/toEdit?id=${result.id}' method="submit" handler="toEditSystemConfig"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../../common/page.jsp"></jsp:include>