
<%@ page import="com.grocerymanagement.dto.UserDTO"%>
<!-- My Account Start -->
<div class="my-account">
	<form action="./user" method="post" id="userCreate">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="nav flex-column nav-pills" role="tablist"
						aria-orientation="vertical">
						<a class="nav-link active" id="account-nav" data-toggle="pill"
							href="#account-tab" role="tab"><i class="fa fa-user-alt"></i>User
							Details</a>

					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">


						<div class="tab-pane fade show active" id="account-tab"
							role="tabpanel" aria-labelledby="account-nav">
							<h4>Login Details</h4>
							<p style="color: green">${Message}</p>
							<p style="color: green">${ErrorMessage}</p>
							<div class="row">
								<div class="col-md-6">
									<input class="form-control" type="hidden" name="userId"
										id="userId" value="${user.userId}"> <input
										class="form-control" type="text" placeholder="User Id"
										name="userName" id="userName" value="${user.userName}"
										<%if( session.getAttribute("loggedUser")!=null) 
										 {%>
										readonly <%}%>>
								</div>

								
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Password"
										name="password" id="password">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text"
										placeholder="Confirm Password">
								</div>
								
							</div>
							<h4>Contact Details</h4>
							<div class="row">

								<div class="col-md-6">
									<input class="form-control" type="text"
										placeholder="First Name" name="firstName" id="firstName"
										value="${user.firstName}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Last Name"
										name="lastName" id="lastName" value="${user.lastName}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Mobile"
										name="mobile" id="mobile" value="${user.mobile}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Phone"
										name="phone" id="phone" value="${user.phone}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Email"
										name="email" id="email" value="${user.email}">
								</div>
								<div class="col-md-6">
									<input class="form-control" type="text" placeholder="Skype Id"
										name="skypeId" id="skypeId" value="${user.skypeId}">
								</div>

							</div>
							
							


							<div class="col-md-12">
								<input type="submit" class="btn" placeholder="Create Account"
									value="Save Detail"></input> <br> <br>
							</div>
						</div>




					</div>
				</div>
			</div>
		</div>
	</form>
</div>
