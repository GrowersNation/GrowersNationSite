<#-- @ftlvariable name="" type="org.growersnation.site.views.PublicFreemarkerView" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
<head>
<#include "../includes/common/head.ftl">
</head>

<body>
<#include "../includes/common/header.ftl">

    <!-- content start -->
    <div id="content" class="clearfix">
        <div class="post">
            <div class="post_header_bg"><h1 class="post-title"><a href="http://www.growers-nation.org/"
                                                                  title="Growers' Nation Site">Growers' Nation Site</a></h1>
            </div>
            <div class="entry">
                <h3>OpenID demo</h3>
                <ul>
                    <li><a href="/private/home">Access your account info (protected with OpenID) available to public</a></li>
                    <li><a href="/private/admin">Access private info (protected with OpenID) available to Administrators</a></li>
                </ul>

                <h3>Soil data demo</h3>
                <ul>
                    <li><a href="/soildata?location=51.65,0.1">Look up soil data for lat/lng 51.65,0.1</a></li>
                </ul>
            </div>
            <div class="endline"></div>
        </div>

    <#include "../includes/common/social.ftl">

    </div>
    <!-- content end -->

<#include "../includes/common/footer.ftl">

<#include "../includes/common/cdn-scripts.ftl">

</body>
</html>
