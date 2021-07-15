<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.jsp.jstl.core.*"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	function addItemRow(form) {
		
		document.getElementById(form).action = "./addItems";
		document.getElementById(form).submit();
		
		  return false;
	}
function removeItemRow(form) {
		
		document.getElementById(form).action = "./removeItems";
		document.getElementById(form).submit();
		
		  return false;
	}
</script>
<!-- My Account Start -->
<div class="my-account">
	<form:form action="./addProductList" method="post" id="listCreate"
		modelAttribute="productListDTO">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a class="nav-link active" id="account-nav" data-toggle="pill"
							href="#account-tab" role="tab"><i class="fa fa-user-alt"></i>Products
							List</a>

					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>Items Details</h4>
							<p style="color: green">${Message}</p>
							<p style="color: green">${ErrorMessage}</p>
							<div class="row">
								<div class="col-md-6">
									<form:input class="form-control" type="hidden" path="listId"
										id="listId"></form:input>
									<form:input class="form-control" type="text"
										placeholder="List Name" path="listName" id="listName"></form:input>
								</div>
							</div>
							<div class="tab-pane fade show active" id="account-tab"
								role="tabpanel" aria-labelledby="account-nav">
								<div class="row">
									<%-- <c:forEach var="product"
										items="${productListDTO.getProductList()}"
										varStatus="productIndex">


										<form:input class="form-control" type="hidden"
											placeholder="Product Id"
											path="productList[${productIndex.index}].productId"
											id="productId"></form:input>
											
										<div class="col-md-12">
											<label for="index">${productIndex.index+1}.</label>
											<form:select class="form-control" placeholder="Product Name"
												path="productList[${productIndex.index}].productName"
												id="productId" >

												<form:options items="${allProductList}" />


											</form:select>
										</div>
										
										


									</c:forEach> --%>
									<div class="col-md-6">
										<%-- <c:forEach var="product"
										items="${productListDTO.getProductList()}"
										varStatus="productIndex">


										<form:input class="form-control" type="hidden"
											placeholder="Product Id"
											path="productList[${productIndex.index}].productId"
											id="productId"></form:input>
											
										
											<form:select class="form-control" placeholder="Product Name"
												path="productList[${productIndex.index}].productName"
												id="productId" >

												<form:options items="${allProductList}" />


											</form:select>
										
										
										


									</c:forEach> --%>
										<a href="home" class="nav-item nav-link active">Selected Products</a>
										<form:select class="form-control" placeholder="Products List"
											path="unSelectedItems" id="unSelectedItems" multiple="true"
											size="4">

											<form:options items="${productListDTO.getProductList()}" />


										</form:select>
										<div style="margin-left: auto; margin-right: 0;">
											<input class="btn" value="Remove Items" type="button" onclick="return removeItemRow('listCreate')"></input>
										</div>
									</div>
									<div class="col-md-6">
										<a href="home" class="nav-item nav-link active">Products</a>
										<form:select class="form-control" placeholder="Products List"
											path="selectedItems" id="selectedItems" multiple="true"
											size="4">

											<form:options items="${allProductList}" />


										</form:select>
										<div style="margin-left: auto; margin-right: 0;">
											<input class="btn" value="Add Items" type="button" onclick="return addItemRow('listCreate')"></input>
										</div>
									</div>

								</div>
							</div>
						</div>


						<div class="col-md-12">
							&nbsp;
						</div>

						<div class="col-md-12">
							<input type="submit" class="btn" placeholder="Save List"
								value="Save Detail"></input> <br> <br>
						</div>





					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
