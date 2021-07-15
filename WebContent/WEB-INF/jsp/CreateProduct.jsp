<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};

	(function() {
		document.getElementById('output').style.display = 'block';

	})();
</script>


<!-- My Account Start -->
<div class="my-account">
	<form action="./product" method="post" 
		id="userCreate" enctype="multipart/form-data">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a class="nav-link active" id="account-nav" data-toggle="pill"
							href="#account-tab" role="tab"><i class="fa fa-user-alt"></i>Add
							Product</a>

					</div>
				</div>
				
				<div class="col-md-9">
				
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>Product Details</h4>
							<p style="color: green">${Message}</p>
							<p style="color: green">${ErrorMessage}</p>


							<div class="row">

								<div class="col-md-6">
								<input class="form-control" type="hidden"
										 name="productId"  id="productId"
										value="${product.productId}">
									<input class="form-control" type="text"
										placeholder="Product Name" name="productName"  id="productName"
										value="${product.productName}">
								</div>

								<div class="col-md-6">
									<input class="form-control" type="text" 
										placeholder="Product Description" name="description"
										id="description" value="${product.description}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" 
										placeholder="Price in Rupees" name="price" id="price"
										value="${product.price}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="file"  name="productImage2"
										id="productImage" placeholder="Product Image"
										value="${product.productImage}" onchange="loadFile(event)">
								</div>
								<div class="col-md-12">
									<input type="submit" class="btn"  placeholder="Create Product"
										value="Save Detail"><br> <br>
								</div>
								<div class="col-lg-3" id="outPutImage">
									<div class="product-item">
										<div class="product-image">
											<img id="output">
										</div>

									</div>
								</div>
							</div>

						</div>



					</div>
				</div>
			</div>
		</div>
	</form>
</div>
