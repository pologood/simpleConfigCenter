<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-inline" role="form" id="htmlElementConfigForm">
    <table class="table   table-hover">


        <tr>
            <td class="col-lg-1">系统名字</td>
            <td>
                <select name="systemName" class="form-control">
                    <option value="">请选择系统名字</option>
                    <c:forEach var="item" items="${systemConfig_uq_name_Map}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==htmlElementConfig.systemName}"> selected="" </c:if>
                                >${item.value.nameDesc}</option>
                    </c:forEach>
                </select>
               </td>

        </tr>
        <tr>
            <td class="col-lg-1">html组件name</td>
            <td>
                <input type="text" name="elementName" value="${htmlElementConfig.elementName}" class="form-control"/>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">html组件展示名字</td>
            <td>
                <input type="text" name="elementDisplay" value="${htmlElementConfig.elementDisplay}"
                       class="form-control"/>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">html组件类型</td>
            <td>

                <select name="elementType" class="form-control">
                    <option value="">请选择html组件类型</option>
                    <c:forEach var="item" items="${htmlElementTypeConfig}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==htmlElementConfig.elementType}"> selected="" </c:if>
                                >${item.value}</option>
                    </c:forEach>
                </select>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">html值的类型</td>
            <td>

                <select name="elementValueType" class="form-control">
                    <option value="">请选择html值的类型</option>
                    <c:forEach var="item" items="${htmlValueTypeConfig}">
                        <option value="${item.key}"
                                <c:if
                                        test="${item.key==htmlElementConfig.elementValueType}"> selected="" </c:if>
                                >${item.value}</option>
                    </c:forEach>
                </select>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">html初始值</td>
            <td>


                <textarea name="elementInitValue" value="${htmlElementConfig.elementInitValue}" class="form-control"></textarea>
            </td>

        </tr>
        <tr>
            <td class="col-lg-1">是否必填</td>
            <td>
        <input type="radio" name="elementValueCheck" value="0" class="form-control"
        <c:if
                test="${!htmlElementConfig.elementValueCheck}"> checked</c:if>
               >否
        <input type="radio" name="elementValueCheck" value="1" class="form-control"
        <c:if
                test="${htmlElementConfig.elementValueCheck}"> checked</c:if>
                >是
            </td>

        </tr>
        <tr>
            <td class="col-lg-1" colspan="2">
                <input type="button" class="btn  btn-warning" value="更新" form="htmlElementConfigForm" action='put'
                       requestUrl='/htmlElementConfig/edit' method="formRequest"/>
            </td>
            <input type="hidden" value="${htmlElementConfig.id}" name="id">
        </tr>
    </table>
</form>