<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-inline" role="form" id="htmlElementConfigForm">
<table class="table   table-hover"  >
    
           
                   <tr>
     <td class="col-lg-1">系统名字</td>
     <td>
         <select name="systemName" class="form-control" >
             <option value="">请选择系统名字</option>
             <c:forEach var="item" items="${systemConfig_uq_name_Map}">
                 <option value="${item.key}"
                         >${item.value.nameDesc}</option>
             </c:forEach>
         </select>
               </td>

  </tr>	           
                   <tr>
     <td class="col-lg-1">html组件name</td>
     <td>
                            <input type="text" name="elementName" class="form-control"/>
               </td>

  </tr>	           
                   <tr>
     <td class="col-lg-1">html组件展示名字</td>
     <td>
                            <input type="text" name="elementDisplay" class="form-control"/>
               </td>

  </tr>	           
                   <tr>
     <td class="col-lg-1">html组件类型</td>
     <td>
         <select name="elementType" class="form-control">
             <option value="">请选择html组件类型</option>
             <c:forEach var="item" items="${htmlElementTypeConfig}">
                 <option value="${item.key}"
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
                         >${item.value}</option>
             </c:forEach>
         </select>
               </td>

  </tr>	           
                   <tr>
     <td class="col-lg-1">html初始值</td>
     <td>

         <textarea name="elementInitValue" value="" class="form-control"></textarea>
如果是select、radio，请填写json
               </td>

  </tr>

    <tr>
        <td class="col-lg-1">是否必填</td>
        <td>
            <input type="radio" name="elementValueCheck" value="0" class="form-control">否
            <input type="radio" name="elementValueCheck" value="1" class="form-control">是
        </td>

    </tr>
    <tr>
        <td class="col-lg-1" colspan="2">
            <input type="button"   class="btn  btn-warning" value="增加" form="htmlElementConfigForm"  action='post'
                   requestUrl='/htmlElementConfig/save'  method="formRequest"/>
        </td>
    </tr>
</table>
    </form>