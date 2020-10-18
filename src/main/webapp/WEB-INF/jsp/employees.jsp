<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/sber.common.js" defer></script>
<script type="text/javascript" src="resources/js/sber.employees.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="employees.title"/></h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate"><spring:message code="employees.startDate"/></label>
                            <input class="form-control" name="startDate" id="startDate" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="endDate"><spring:message code="employees.endDate"/></label>
                            <input class="form-control" name="endDate" id="endDate" autocomplete="off">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="employees.filter"/>
                </button>
            </div>
        </div>
        <br/>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="employees.lastName"/></th>
                <th><spring:message code="employees.firstName"/></th>
                <th><spring:message code="employees.middleName"/></th>
                <th><spring:message code="employees.dateBirthday"/></th>
                <th><spring:message code="employees.salary"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="lastName" class="col-form-label"><spring:message code="employees.lastName"/></label>
                        <input class="form-control" id="lastName" name="lastName"
                               placeholder="<spring:message code="employees.lastName"/>">
                    </div>

                    <div class="form-group">
                        <label for="firstName" class="col-form-label"><spring:message
                                code="employees.firstName"/></label>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               placeholder="<spring:message code="employees.firstName"/>">
                    </div>

                    <div class="form-group">
                        <label for="middleName" class="col-form-label"><spring:message
                                code="employees.middleName"/></label>
                        <input type="text" class="form-control" id="middleName" name="middleName"
                               placeholder="<spring:message code="employees.middleName"/>">
                    </div>

                    <div class="form-group">
                        <label for="dateBirthday" class="col-form-label"><spring:message code="employees.dateBirthday"/></label>
                        <input class="form-control" id="dateBirthday" name="dateBirthday" autocomplete="off"
                               placeholder="<spring:message code="employees.dateBirthday"/>">
                    </div>

                    <div class="form-group">
                        <label for="salary" class="col-form-label"><spring:message code="employees.salary"/></label>
                        <input type="text" class="form-control" id="salary" name="salary">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="employees"/>
</jsp:include>
</html>