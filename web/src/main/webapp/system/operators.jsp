<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/commons/header.jsp"%>
	<jsp:include page="/commons/list_js.jsp">
		<jsp:param name="search_name" value="system_operator"/>
		<jsp:param name="edit_url" value="/system/operator_edit.action"/>
	</jsp:include>
</head>

<body >

<s:form action="operator_list" namespace="/system"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="operator.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
					<input type="button"  value="<delmar:message key="common.button.search" />"  class="input_submit" id="search_but">
                  <s:submit method="create" cssClass="input_submit" value="%{#session.resource.get('common.button.create')}"/>
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}"/>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.operatorList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		<c:if test="${list.init=='N'}">
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</c:if>
		</display:column>    
		<display:column  media="html csv excel xml pdf rtf" titleKey="common.label.sequence">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   sortable="true" media="html" titleKey="operator.column.name">
		     
		      <a href="<c:url value="/system/operator_edit.action?id=${list.id}"/>">
     				 <c:out value="${list.name}"/></a>
		     </display:column>
		     <display:column property="name" media="csv excel xml pdf rtf"
							titleKey="operator.column.name"/>
			<display:column property="value"  escapeXml="true"  sortable="true" titleKey="operator.column.value"/>
		    <display:column property="remark"  escapeXml="true"  sortable="true" titleKey="operator.column.remark"/>
		    <display:column property="init"  escapeXml="true" sortable="true" titleKey="operator.column.init"/>
		    
		    

		</display:table>




</td>
</tr>
</table>


</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
