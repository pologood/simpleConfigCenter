<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-inline" role="form" id="systemConfigForm">
    <table class="table   table-hover">


        <tr>
            <td class="col-lg-1">系统名字</td>
            <td>
                <input type="text" name="name" class="form-control"/>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">系统描述</td>
            <td>
                <input type="text" name="nameDesc" class="form-control"/>
            </td>

        </tr>


        <tr>
            <td class="col-lg-1" colspan="2">
                <input type="button" class="btn  btn-warning" value="增加" form="systemConfigForm" action='post'
                       requestUrl='/systemConfig/save' method="formRequest"/>
            </td>
        </tr>
    </table>
</form>