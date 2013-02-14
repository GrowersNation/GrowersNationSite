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
                                                                  title="Login with OpenID">Login with OpenID</a></h1>
            </div>
            <div class="entry">
                <a href="/private/home">Access 456 private info (protected with OpenId) available to public</a>

                <a href="/private/admin">Access private info (protected with OpenId) available to Administrators</a>

            </div>
            <div class="endline"></div>
        </div>

    <#include "../includes/common/social.ftl">

    </div>
    <!-- content end -->
</div>

<#include "../includes/common/footer.ftl">

<#include "../includes/common/cdn-scripts.ftl">

</body>
</html>