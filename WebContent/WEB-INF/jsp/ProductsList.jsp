<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a href="./addList" class="nav-item nav-link">Add
							New List</a>
						
					</div>
				</div>
				
				<div class="col-md-9">
				
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>ProuctLists</h4>
							<p style="color: green">${Message}</p>
							<p style="color: green">${ErrorMessage}</p>


						<div class="row align-items-center product-slider product-slider-4">
			           <c:forEach var="personDTO" items="${productLists}">
						<div class="col-md-6">
							<a href="./viewList?listId=${personDTO.listId}" class="nav-item nav-link">	<label for="index">${personDTO.listName}.</label> </a>
									
						</div>

								
								
						</c:forEach>


								
							</div>

						</div>



					</div>
				</div>
			</div>
		</div>
	
</div>
