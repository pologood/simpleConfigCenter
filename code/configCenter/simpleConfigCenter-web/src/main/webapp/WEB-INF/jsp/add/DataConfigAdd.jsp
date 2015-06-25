<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-inline" role="form" id="dataConfigForm">
    <table class="table   table-hover">


        <tr>
            <td class="col-lg-1">系统名字</td>
            <td>
                <select name="systemName" class="form-control" changeMethod="submit"
                        action='get'
                        requestUrl='/dataConfig/getConfigDataHTML'  handler="getConfigDataHTML"
                        >
                    <option value="">请选择系统名字</option>
                    <c:forEach var="item" items="${systemConfig_uq_name_Map}">
                        <option value="${item.key}"
                                >${item.value.nameDesc}</option>
                    </c:forEach>
                </select>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">数据唯一标识</td>
            <td>
                <input type="text" name="dataKey" class="form-control"/>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">数据描述</td>
            <td>
                <input type="text" name="dataDesc" class="form-control"/>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">序列化数据</td>
            <td>
                <textarea name="data" class="form-control"></textarea>
            </td>

        </tr>



        <tr>
            <td class="col-lg-1" colspan="2">
                <input type="button" class="btn  btn-warning" value="增加" form="dataConfigForm" action='post'
                       requestUrl='/dataConfig/save' method="formRequest"/>
            </td>
        </tr>
    </table>
</form>