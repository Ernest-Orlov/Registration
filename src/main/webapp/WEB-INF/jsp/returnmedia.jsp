<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local/local" var="loc"/>

<fmt:message bundle="${loc}" key="local.home" var="home"/>
<fmt:message bundle="${loc}" key="local.returnMedia" var="returnMedia"/>
<fmt:message bundle="${loc}" key="local.searchByPassportId" var="searchByPassportId"/>
<fmt:message bundle="${loc}" key="local.button.search" var="search"/>
<fmt:message bundle="${loc}" key="local.mediaDetails" var="mediaDetails"/>
<fmt:message bundle="${loc}" key="local.loanDescription" var="loanDescription"/>
<fmt:message bundle="${loc}" key="local.userDetails" var="userDetails"/>
<fmt:message bundle="${loc}" key="local.action" var="action"/>
<fmt:message bundle="${loc}" key="local.format" var="format"/>
<fmt:message bundle="${loc}" key="local.startDate" var="startDate"/>
<fmt:message bundle="${loc}" key="local.endDate" var="endDate"/>
<fmt:message bundle="${loc}" key="local.name" var="name"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.passportId" var="passportId"/>
<fmt:message bundle="${loc}" key="local.giveOut" var="giveOut"/>
<fmt:message bundle="${loc}" key="local.button.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.deleteConfirmation" var="deleteConfirmation"/>
<fmt:message bundle="${loc}" key="local.userSearch" var="userSearch"/>
<fmt:message bundle="${loc}" key="local.restriction" var="restriction"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.close" var="close"/>


<html lang="zxx">

<head>
    <!-- Title -->
    <title>..:: LIBRARIA ::..</title>

    <!-- Meta -->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1">

    <!-- Start: Css Section -->
    <jsp:include page="/WEB-INF/jsp/parts/css.jsp"/>
    <!-- End: Css Section -->

</head>

<body>

<!-- Start: Header Section -->
<jsp:include page="/WEB-INF/jsp/parts/header.jsp"/>
<!-- End: Header Section -->

<!-- Start: Page Banner -->
<section class="page-banner services-banner">
    <div class="container">
        <div class="banner-header">
            <h2>${returnMedia}</h2>
            <span class="underline center"></span>
        </div>
        <div class="breadcrumb">
            <ul>
                <li><a href="${pageContext.request.contextPath}/Controller">${home}</a></li>
                <li>${returnMedia}</li>
            </ul>
        </div>
    </div>
</section>
<!-- End: Page Banner -->

<!-- Start: User verification Section -->
<div id="content" class="site-content">
    <div id="primary" class="content-area">
        <main id="main" class="site-main">
            <div class="cart-main">
                <div class="container">
                    <section class="search-filters">
                        <div class="filter-box">
                            <h3>${userSearch}</h3>
                            <form action="Controller" method="get">
                                <div class="col-md-10 col-sm-6">
                                    <div class="form-group">
                                        <input class="form-control" placeholder="${searchByPassportId}"
                                               id="keywords" name="search" type="text">
                                        <input type="hidden" name="command" value="return_media_form">
                                    </div>
                                </div>

                                <div class="col-md-2 col-sm-6">
                                    <div class="form-group">
                                        <input class="form-control" type="submit" value="${search}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="clear"></div>
                    </section>
                    <c:if test="${requestScope.returnMediaMsg != null}">
                        <div class="center-content">
                            <div class="clear"></div>
                            <br>
                            <h3 class="section-title"><c:out value="${returnMediaMsg}"/></h3>
                        </div>
                    </c:if>
                    <div class="center-content">
                    </div>
                    <div class="col-md-12">
                        <div class="page type-page status-publish hentry">
                            <div class="entry-content">
                                <div class="table-tabs" id="responsiveTabs">
                                    <div id="sectionA" class="tab-pane fade in active">
                                        <jsp:useBean id="deliveryTypes" scope="request"
                                                     type="java.util.List<by.jwd.library.bean.DeliveryType>"/>
                                        <c:if test="${not empty deliveryTypes}">
                                            <table class="table table-bordered shop_table cart">
                                                <thead>
                                                <tr>
                                                    <th class="product-name"></th>
                                                    <th class="product-name">${mediaDetails}</th>
                                                    <th class="product-price">${loanDescription}</th>
                                                    <th class="product-price">${userDetails}</th>
                                                    <th class="product-price">${price}</th>
                                                    <th class="product-subtotal">${action}</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="item" items="${deliveryTypes}">
                                                    <tr class="cart_item">
                                                        <td data-title="Product" class="product-name"
                                                            data-th="Title">
                                                            <a href="${pageContext.request.contextPath}/Controller?command=media_detail&mediaId=${item.loanType.mediaDetail.mediaID}"><img
                                                                    style="margin-right: 1px; height: 180px;"
                                                                    src="${item.loanType.mediaDetail.picture}"
                                                                    alt="cart-product-1"></a>
                                                        </td>
                                                        <td data-title="Price" class="product-price"
                                                            data-th="Pickup Location ">
                                                                        <span class="product-detail">
                                                                                <a href="${pageContext.request.contextPath}/Controller?command=media_detail&mediaId=${item.loanType.mediaDetail.mediaID}">${item.loanType.mediaDetail.title} </a>
                                                                            <div>
                                                                            <span><strong>${format}: </strong>${item.loanType.mediaDetail.format}</span>
                                                                            </div>
                                                                              <div>
                                                                            <span><strong>ISBN: </strong>${item.loanType.mediaDetail.iSBN}</span>
                                                                             </div>
                                                                              <c:if test="${not empty item.loanType.mediaDetail.restriction}">
                                                                                  <div>
                                                                                    <span><strong>${restriction}: </strong>${item.loanType.mediaDetail.restriction}</span>
                                                                                  </div>
                                                                              </c:if>
                                                                        </span>
                                                        </td>
                                                        <td data-title="Price" class="product-price"
                                                            data-th="Pickup Location ">
                                                                 <span class="product-detail">
                                                                     <div>
                                                                         <div><strong>${startDate}: </strong></div>
                                                                         <div><tag:dateformater
                                                                                 date="${item.loanType.startDate}"
                                                                                 local="${sessionScope.local}"></tag:dateformater></div>
                                                                     </div>
                                                                     <div>
                                                                         <div><strong>${endDate}: </strong></div>
                                                                         <div><tag:dateformater
                                                                                 date="${item.loanType.endDate}"
                                                                                 local="${sessionScope.local}"></tag:dateformater></div>
                                                                 </div>
                                                                 </span>
                                                        </td>
                                                        <td data-title="Price" class="product-price"
                                                            data-th="Pickup Location ">
                                                                 <span class="product-detail">
                                                                     <div>
                                                                         <div><strong>${name}: </strong></div>
                                                                         <div>${item.user.name}</div>
                                                                         <div><strong>${email}: </strong></div>
                                                                         <div>${item.user.email}</div>
                                                                         <div><strong>${passportId}: </strong></div>
                                                                         <div>${item.user.passportId}</div>
                                                                     </div>
                                                                 </span>
                                                        </td>
                                                        <td data-title="Price" class="product-price"
                                                            data-th="Pickup Location ">
                                                                 <span class="product-detail">
                                                                     <div>
                                                                         <div>${item.loanType.mediaDetail.price}</div>
                                                                     </div>
                                                                 </span>
                                                        </td>
                                                        <td data-title="Price" class="product-price"
                                                            data-th="Pickup Location ">
                                                            <div>
                                                                <a href="${pageContext.request.contextPath}/Controller?command=return_media&loanId=${item.loanType.loanTypeId}&copyId=${item.loanType.copyId}"
                                                                   class="btn btn-dark-gray">${close}</a></div>
                                                            <div><br></div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- End: User Verification Section -->

<!-- Start: Footer -->
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
<!-- End: Footer -->

<!-- Start: Scripts -->
<jsp:include page="/WEB-INF/jsp/parts/scripts.jsp"/>
<!-- End: Scripts -->

</body>


</html>

