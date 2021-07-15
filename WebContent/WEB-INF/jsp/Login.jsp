
<script>
function validate(){
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;
	if(userName=="")
		{
		document.getElementById("userNameErrorMessage").innerHTML="enter user name"
		return false;
		}
	if(password=="")
	{
	document.getElementById("passwordErrorMessage").innerHTML="enter password"
	return false;
	}
		
	return true;
};
function clearError()
{
	document.getElementById("userNameErrorMessage").innerHTML="";
	document.getElementById("passwordErrorMessage").innerHTML="";
};
function checkEmptyUserName(){
	var userName = document.getElementById("userName").value;
	if(userName=="")
	{
	document.getElementById("userNameErrorMessage").innerHTML="enter user name"
	return false;
	}
}
function checkEmptyUserPassword()
{
	if(password=="")
	{
	document.getElementById("passwordErrorMessage").innerHTML="enter password"
	return false;
	}
	
	return true;
}
</script>
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
	<form action="./loginUser" method="post" modelAttribute="user"
		id="LogUser" onsubmit="return validate()">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a class="nav-link active" id="account-nav" data-toggle="pill"
							href="#account-tab" role="tab"><i class="fa fa-user-alt"></i>Enter
							Credentials</a>

					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>
								Login
								<p style="color: green">${Message}</p>
								<p style="color: red">${ErrorMessage}</p>
								<div class="row">
									<div class="col-md-9">
										<input class="form-control" onFocus="clearError()"
											onBlur="checkEmptyUserName()" type="text"
											placeholder="User Id" name="userName" id="userName">
									</div>
									<div class="col-md-3">
										<p id="userNameErrorMessage"
											style="color: red; font-size: 15px;"></p>
									</div>
									<div class="col-md-9">
										<input class="form-control" type="password"
											onBlur="checkEmptyUserPassword()" placeholder="Password"
											name="password" id="password">
									</div>
									<div class="col-md-3">
										<p id="passwordErrorMessage"
											style="color: red; font-size: 15px;"></p>
									</div>

								</div>

								<div class="row">

									<div class="col-md-4">

										<input type="submit" class="btn" value="Log In"></input> <a
											href="./forgotPassword" class="btn">Forgot Password</a>
									</div>
									<div class="col-md-4"></div>
									<div class="col-md-4">

										<a href="./profile" class="btn">Sign Up</a>
									</div>
								</div>
						</div>




					</div>
				</div>
			</div>
		</div>
	</form>
</div>
