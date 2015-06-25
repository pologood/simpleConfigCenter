<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form class="form-inline" role="form" id="queryDataConfigForm">
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
            <td><input type="text" name="dataKey" placeholder="数据唯一标识" class="form-control"
                       value="${parameterMap.dataKey[0]}"/></td>
            <td><input type="text" name="dataDesc" placeholder="数据描述" class="form-control"
                       value="${parameterMap.dataDesc[0]}"/></td>

            <td>
                <input type="button" class="btn  btn-warning" value="查询" action='get'
                       requestUrl='/dataConfig/list' method="submit" handler="queryDataConfig"/>
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
        <th>数据唯一标识</th>
        <th>数据描述</th>
        <th>序列化数据</th>
        <th>操作</th>
    </tr>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="result" items="${dataConfigList}" varStatus="status">
        <tr>

            <td class="col-lg-1">${systemConfig_uq_name_Map[result.systemName].nameDesc} </td>
            <td class="col-lg-1">${result.dataKey} </td>
            <td class="col-lg-1">${result.dataDesc} </td>
            <td class="col-lg-1">${result.data} </td>


            <td class="col-lg-1">
                <input type="button" class="btn  btn-warning" value="编辑" action='get'
                       requestUrl='/dataConfig/toEdit?id=${result.id}' method="submit" handler="toEditDataConfig"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../../common/page.jsp"></jsp:include>