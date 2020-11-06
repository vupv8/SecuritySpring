<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>

<body>
	<header class="header">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
	</header>
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<tiles:insertAttribute name="sidebar" />
				</div>
				<div class="col-lg-9">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
	</section>
	<footer class="footer spad">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>

