
<!-- Breadcrumb Start -->
<div class="breadcrumb-wrap">
	<div class="container-fluid">
		<ul class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Home</a></li>
			<li class="breadcrumb-item active">Login</li>
		</ul>
	</div>
</div>
<!-- Breadcrumb End -->

<!-- My Account Start -->
<div class="my-account">
	<form action="./resetPassword" method="post" modelAttribute="user"
		id="LogUser">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a class="nav-link active" id="account-nav" data-toggle="pill"
							href="#account-tab" role="tab"><i class="fa fa-user-alt"></i>Enter
							User Id</a>

					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>
								Enter your user Id
								<p style="color: green">${Message}</p>
								<p style="color: red">${ErrorMessage}</p>
								<div class="row">
									<div class="col-md-12">
										<input class="form-control" type="text" placeholder="User Id"
											path="userName" name="userName" id="userName">
									</div>


								</div>

								<div class="row">

									<div class="col-md-4">

										<input type="submit" class="btn" value="Submit"></input>

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
