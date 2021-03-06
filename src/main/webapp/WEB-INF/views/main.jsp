<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>dog-soa通用接口平台</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/compiled/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/ui-elements.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <style>
    	html,body{ 
    		width:100%; 
    		height:100%;
    		overflow: hidden;
    	}  
    </style>
    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="brand" href="index.html"><img src="img/logo.png" /></a>

            <ul class="nav pull-right"> 
            	<!--                	
                <li class="hidden-phone">
                    <input class="search" type="text" />
                </li>
                -->           
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone icon-cog" data-toggle="dropdown">
                        	功能选择
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="soa/monitor.html" target="mainIframe">服务监控</a></li>
                        <li><a href="soa/clientList.html" target="mainIframe">客户端列表</a></li>
                        <li><a href="soa/serverList.html" target="mainIframe">服务端列表</a></li>
                        <li><a href="soa/createServer.html" target="mainIframe">添加服务</a></li>
                    </ul>
                </li>
                <!--
                <li class="settings hidden-phone">
                    <a href="personal-info.html" role="button">
                        <i class="icon-cog"></i>
                    </a>
                </li>
                <li class="settings hidden-phone">
                    <a href="signin.html" role="button">
                        <i class="icon-share-alt"></i>
                    </a>
                </li>
                -->
            </ul>            
        </div>
    </div>
    <!-- end navbar -->

	<iframe name="mainIframe" id="mainIframe" src="soa/serverList.html" width="100%" height="100%"></iframe>

	<!-- scripts -->
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    <script src="js/bootstrap.datepicker.js"></script>
    <script src="js/soa/main.js"></script>
</body>
</html>