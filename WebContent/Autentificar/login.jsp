<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="i"%>

<!doctype html>

<html lang="en" class="no-js">

<head>

	<title>Saldo.mx</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,700,600,300' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/magnific-popup.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen"/>
	<link rel="stylesheet" href="css/jquery.bxslider.css" type="text/css" media="screen">

    <!-- REVOLUTION BANNER CSS SETTINGS -->
    <link rel="stylesheet" type="text/css" href="css/fullwidth.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/settings.css" media="screen" />

	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
	<link rel="stylesheet" href="css/responsive.css" type="text/css" media="screen">


	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.migrate.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jquery.imagesloaded.min.js"></script>
  	<script type="text/javascript" src="js/jquery.isotope.min.js"></script>
	<script type="text/javascript" src="js/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.min.js"></script>
  	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="js/gmap3.min.js"></script>

     <!-- jQuery KenBurn Slider  -->
    <script type="text/javascript" src="js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	<!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script type="text/javascript">
function formSubmit()
{
document.getElementById("lg").submit();
}
</script> 
	
</head>
<body>

	<!-- Container
		    ================================================== -->
	<div id="container" class="skin4">

		<!-- Header
		    ================================================== -->
		<header class="clearfix">
			<!-- Static navbar -->
			<div class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><img alt="" src="images/skin4/logo.png"></a>
					</div>
					
				</div>
			</div>
		</header>
		<!-- End Header -->


		<!-- contact-section 
			================================================== -->
		<div class="section">
			<div id="contact" class="bg-color">
				
				<div class="container">
					<div class="row contact-info">
						<div class="col-md-6">
							<h2>Contact Info</h2>
							<ul>
								<li><span class="label address">address:</span> <span class="label-information">189 West Santa Clara St San Jose California 95113</span></li>
								<li><span class="label phone">Phone:</span> <span class="label-information">(52) 222 323 1688</span></li>
								<li><span class="label e-mail">e-Mail:</span> <span class="label-information">info@saldo.mx</span></li>
							</ul>
						</div>
						<div class="col-md-6">
							<h2>Send us a message</h2>&nbsp;
							<form id="lg" method="post" action="<c:url value="/j_spring_security_check" />">

								<div class="row">
									 <div class="col-md-6">
                                      <span class="label address">usuario:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                      <input type="text" name="j_username" placeholder="e-mail" />
                                      <span class="label address">password:</span>
                                      <input type="password" name="j_password" placeholder="password" />
                                      </div>
					<div class="col-md-6">
						<ul class="nav navbar-nav navbar-center">
							<li><a class="contact-nav" href="#contact" onclick="formSubmit()">Login</a></li>
						</ul>
					</div>
								
								</div>
							</form>						
						</div>
					</div>					
				</div>
			</div>
		</div>
		<!-- End contact -->

		<!-- Footer
		    ================================================== -->
		<footer class="bg-color">
			<div class="inner-footer">
				<div class="container">
					<div class="row">
						<div class="col-md-7">
							<p>&copy; 2013 saldo.mx</p>
						</div>
						<div class="col-md-5">
							<ul class="social-icons">
								<li><a class="facebook" href="#"></a></li>
								<li><a class="rss" href="#"></a></li>
								<li><a class="youtube" href="#"></a></li>
								<li><a class="twitter" href="#"></a></li>
								<li><a class="google" href="#"></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- End Footer -->

	</div>
	<!-- End Container -->
	
	<!-- Contents of project popup -->
	<div id="first-popup" class="mfp-hide white-popup">
		<div class="some-element">
			<div class="flexslider">
			    <ul class="slides">
					<li>
						<img alt="" src="upload/single-project/1.jpg" />
					</li>
					<li>
						<img alt="" src="upload/single-project/2.jpg" />
					</li>
					<li>
						<img alt="" src="upload/single-project/3.jpg" />
					</li>
				</ul>
			</div>
			<h2>Useful Seo Icons</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
			cillum dolore eu fugiat nulla pariatur.</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			quis nostrud exercitation ullamco laboris nisi</p>
		</div>
		<p class="last-row">You can view the project <a href="#"> here</a></p>
	</div>

	<!--
	##############################
	 - ACTIVATE THE BANNER HERE -
	##############################
	-->
	<script type="text/javascript">

		var tpj=jQuery;
		tpj.noConflict();

		tpj(document).ready(function() {

		if (tpj.fn.cssOriginal!=undefined)
			tpj.fn.css = tpj.fn.cssOriginal;

			var api = tpj('.fullwidthbanner').revolution(
				{
					delay:9000,
					startwidth:1170,
					startheight:500,

					onHoverStop:"on",						// Stop Banner Timet at Hover on Slide on/off

					thumbWidth:100,							// Thumb With and Height and Amount (only if navigation Tyope set to thumb !)
					thumbHeight:50,
					thumbAmount:3,

					hideThumbs:0,
					navigationType:"no",				// bullet, thumb, none
					navigationArrows:"solo",				// nexttobullets, solo (old name verticalcentered), none

					navigationStyle:"round",				// round,square,navbar,round-old,square-old,navbar-old, or any from the list in the docu (choose between 50+ different item), custom


					navigationHAlign:"center",				// Vertical Align top,center,bottom
					navigationVAlign:"bottom",					// Horizontal Align left,center,right
					navigationHOffset:30,
					navigationVOffset:-40,

					soloArrowLeftHalign:"left",
					soloArrowLeftValign:"center",
					soloArrowLeftHOffset:20,
					soloArrowLeftVOffset:0,

					soloArrowRightHalign:"right",
					soloArrowRightValign:"center",
					soloArrowRightHOffset:20,
					soloArrowRightVOffset:0,

					touchenabled:"on",						// Enable Swipe Function : on/off


					stopAtSlide:-1,							// Stop Timer if Slide "x" has been Reached. If stopAfterLoops set to 0, then it stops already in the first Loop at slide X which defined. -1 means do not stop at any slide. stopAfterLoops has no sinn in this case.
					stopAfterLoops:-1,						// Stop Timer if All slides has been played "x" times. IT will stop at THe slide which is defined via stopAtSlide:x, if set to -1 slide never stop automatic

					hideCaptionAtLimit:0,					// It Defines if a caption should be shown under a Screen Resolution ( Basod on The Width of Browser)
					hideAllCaptionAtLilmit:0,				// Hide all The Captions if Width of Browser is less then this value
					hideSliderAtLimit:0,					// Hide the whole slider, and stop also functions if Width of Browser is less than this value


					fullWidth:"on",

					shadow:1								//0 = no Shadow, 1,2,3 = 3 Different Art of Shadows -  (No Shadow in Fullwidth Version !)

				});


				// TO HIDE THE ARROWS SEPERATLY FROM THE BULLETS, SOME TRICK HERE:
				// YOU CAN REMOVE IT FROM HERE TILL THE END OF THIS SECTION IF YOU DONT NEED THIS !
					api.bind("revolution.slide.onloaded",function (e) {


						jQuery('.tparrows').each(function() {
							var arrows=jQuery(this);

							var timer = setInterval(function() {

								if (arrows.css('opacity') == 1 && !jQuery('.tp-simpleresponsive').hasClass("mouseisover"))
								  arrows.fadeOut(300);
							},3000);
						})

						jQuery('.tp-simpleresponsive, .tparrows').hover(function() {
							jQuery('.tp-simpleresponsive').addClass("mouseisover");
							jQuery('body').find('.tparrows').each(function() {
								jQuery(this).fadeIn(300);
							});
						}, function() {
							if (!jQuery(this).hasClass("tparrows"))
								jQuery('.tp-simpleresponsive').removeClass("mouseisover");
						})
					});
				// END OF THE SECTION, HIDE MY ARROWS SEPERATLY FROM THE BULLETS
			});
	</script>
</body>
</html>