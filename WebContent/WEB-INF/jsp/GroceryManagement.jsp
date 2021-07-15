<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<!-- Google Fonts -->
<link href="css/swap.css" rel="stylesheet">

<!-- CSS Libraries -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/all.min.css" rel="stylesheet">
<link href="slick/slick.css" rel="stylesheet">
<link href="slick/slick-theme.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/theme1/style.css" rel="stylesheet">

	<!-- JavaScript Libraries -->
	<script src="js/jquery-min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="easing/easing.min.js"></script>
	<script src="slick/slick.min.js"></script>

</head>

<body>

	

	<div class="nav">
		<tiles:insertAttribute name="menu" />

	</div>
	
	<tiles:insertAttribute name="body" />




	


	
</body>
</html>
