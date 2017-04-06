<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-hans">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>课程</title>
  <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.css" rel="stylesheet">
  <link href="/style/video.css" rel="stylesheet" type="text/css" />
  <script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.js"></script>
<script type="text/javascript">
<!--//--><![CDATA[//><!--
// var _paq = _paq || [];(function(){var u=(("https:" == document.location.protocol) ? "" : "http://analytics.ninghao.net/");_paq.push(["setSiteId", "1"]);_paq.push(["setTrackerUrl", u+"piwik.php"]);_paq.push(["setDoNotTrack", 1]);_paq.push(["trackPageView"]);_paq.push(["setIgnoreClasses", ["no-tracking","colorbox"]]);_paq.push(["enableLinkTracking"]);var d=document,g=d.createElement("script"),s=d.getElementsByTagName("script")[0];g.type="text/javascript";g.defer=true;g.async=true;g.src=u+"piwik.js";s.parentNode.insertBefore(g,s);})();
//--><!]]>
</script>
<script type="text/javascript">
<!--//--><![CDATA[//><!--
// jQuery.extend(Drupal.settings, {"basePath":"\/","pathPrefix":"","ajaxPageState":{"theme":"ninghao","theme_token":"ak4zp1XOg9VVVBh8HpPIXCpoQJKqIa9-CpwDox0kCXc","js":{"sites\/all\/modules\/jquery_update\/replace\/jquery\/1.7\/jquery.min.js":1,"misc\/jquery.once.js":1,"misc\/drupal.js":1,"public:\/\/languages\/zh-hans_jdOVLGhxp3ay5J7N_RoVAMRXIqCbOCvEzAP-f5zpMlw.js":1,"misc\/autocomplete.js":1,"sites\/all\/modules\/search_api_autocomplete\/search_api_autocomplete.js":1,"sites\/all\/modules\/piwik\/piwik.js":1,"0":1,"sites\/all\/libraries\/placeholder\/jquery.placeholder.min.js":1,"sites\/all\/modules\/placeholder\/placeholder.js":1,"sites\/all\/themes\/ninghao\/js\/bootstrap.min.js":1,"sites\/all\/themes\/ninghao\/js\/headroom.min.js":1,"sites\/all\/themes\/ninghao\/js\/jQuery.headroom.min.js":1,"sites\/all\/themes\/ninghao\/js\/plyr.js":1,"sites\/all\/themes\/ninghao\/js\/jquery.touchSwipe.min.js":1,"sites\/all\/themes\/ninghao\/js\/jquery.vide.min.js":1,"sites\/all\/themes\/ninghao\/js\/jquery.mixitup.min.js":1,"sites\/all\/themes\/ninghao\/js\/scrollReveal.min.js":1,"sites\/all\/themes\/ninghao\/js\/ninghao.js":1},"css":{"modules\/system\/system.base.css":1,"modules\/system\/system.menus.css":1,"modules\/system\/system.messages.css":1,"modules\/system\/system.theme.css":1,"sites\/all\/modules\/ctools\/css\/ctools.css":1,"sites\/all\/themes\/ninghao\/css\/system.base.css":1,"sites\/all\/themes\/ninghao\/css\/system.menus.css":1,"sites\/all\/themes\/ninghao\/css\/system.messages.css":1,"sites\/all\/themes\/ninghao\/css\/system.theme.css":1,"sites\/all\/themes\/ninghao\/css\/ckeditor.css":1,"sites\/all\/themes\/ninghao\/css\/ctools.css":1,"sites\/all\/themes\/ninghao\/css\/bootstrap.min.css":1,"sites\/all\/themes\/ninghao\/css\/bootstrap-responsive.min.css":1,"sites\/all\/themes\/ninghao\/css\/base.css":1,"sites\/all\/themes\/ninghao\/css\/pages.css":1,"sites\/all\/themes\/ninghao\/css\/thumbnails.css":1,"sites\/all\/themes\/ninghao\/css\/animate.css":1,"sites\/all\/themes\/ninghao\/css\/plyr.css":1,"sites\/all\/themes\/ninghao\/css\/spin.css":1,"sites\/all\/themes\/ninghao\/css\/statistic.css":1,"sites\/all\/themes\/ninghao\/css\/item.css":1,"sites\/all\/themes\/ninghao\/css\/ninghao.css":1,"sites\/all\/themes\/ninghao\/css\/responsive.css":1,"sites\/all\/themes\/ninghao\/css\/css3.css":1,"sites\/all\/themes\/ninghao\/css\/print.css":1,"sites\/all\/themes\/ninghao\/css\/ie.css":1,"sites\/all\/themes\/ninghao\/css\/ie6.css":1}},"urlIsAjaxTrusted":{"\/search":true,"\/user\/login?destination=user\/register":true},"piwik":{"trackMailto":1}});
//--><!]]>
</script>
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<body class="html not-front not-logged-in no-sidebars page-user page-user-login section-user" >
  <header id="header" class="animated navbar-fixed-top slideInDown headroom--top">
    <div class="header-inner">
      <div class="container"></div>
        <nav class="navbar navbar-inverse">
          <a class="brand span2" href="" id="logo">
            <img src="" alt=""/>
          </a>
          <div class="nav-collapse">
            <ul class="nav" id="main-menu">
              <c:forEach items="${kechengList}" var="kechengList">
              	<li><a href=""><c:out value="${kechengList.kechengName}" /></a></li>
			  </c:forEach>
            </ul>
            <ul id="secondary-menu" class="nav pull-right">
              
              
              
              
              <li>
                <!-- <a href="">登录</a> -->
              </li>
            </ul>
          </div>
        </nav>
    </div>
  </header>

<div id="mainBody">
  <div id="container">
    <div id="content">
      <div id="menuTag">
      </div>

      <ul id="allCourse" class="thumbnails overlay basic">
        
        <c:forEach items="${kechengList}" var="kechengList">
          <li class="span4 mix" style="display: inline-block;">
          <div class="thumbnail">
            <div class="content">
              <a title="AFP" href="">
                <img typeof="foaf:Image" src="/images/showcase.jpg" width="362" height="161"></img>
                <div class="cheader">
                  <h3 class="title"><c:out value="${kechengList.kechengName}" /></h3>
                </div>
              </a>
            </div>

            <div class="meta">
              <span><c:out value="${kechengList.kechengCrdate}" /></span>
            </div>
          </div>
        </li>
	    </c:forEach>

      </ul>
    </div>
  </div>
</div>


<footer id="footer">
  <div class="container">
<section class="first last clearfix">
      <p class="pull-right">QQ：12345678</p>

      <!-- <p>© <a href="" rel="nofollow">金诺贝才课</a><span>|</span><a href="" rel="nofollow">隐私条款</a><span>|</span><a href="" rel="nofollow">服务条款</a><span>|</span><a href="" rel="nofollow">鲁ICP备12003619号-1</a><span></span></p>
 --></section>
</div>
</footer>
</body>
</html>
