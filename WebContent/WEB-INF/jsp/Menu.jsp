<%@ page import="com.grocerymanagement.dto.*"%>
<div class="container-fluid">
                <nav class="navbar navbar-expand-md bg-dark navbar-dark">
                    <a href="#" class="navbar-brand">MENU</a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto">
                            <a href="home" class="nav-item nav-link active">Home</a>
                            
                           
                           <% if(session.getAttribute("loggedUser")!=null)
                        {
                        %>
                        <a href="./productLists" class="nav-item nav-link">Item Lists</a>
                            <a href="./profile" class="nav-item nav-link">My Account</a>
                             
                             
                                   
                                    <a href="./productPage" class="dropdown-item">Create Product</a>
                               
                            
                            <%} %>
                        </div>
                        <div class="navbar-nav ml-auto">
                        <% if(session.getAttribute("loggedUser")!=null)
                        {
                        %>
                       
                         <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"> <%=((UserDTO)session.getAttribute("loggedUser")).getFirstName() %> </a>
                                
                                <div class="dropdown-menu">
                                    <a href="./logout" class="dropdown-item">Logout</a>
                                   
                                </div>
                            </div>
                        <% 
                        }
                        else
                        {
                        %>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">User Account</a>
                                
                                <div class="dropdown-menu">
                                    <a href="./login" class="dropdown-item">Login</a>
                                    <a href="./profile" class="dropdown-item">Register</a>
                                </div>
                            </div>
                            <%
                            }%>
                        </div>
                    </div>
                </nav>
            </div>
