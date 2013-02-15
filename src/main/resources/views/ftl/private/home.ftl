<#-- @ftlvariable name="" type="org.growersnation.site.views.PublicFreemarkerView" -->
<!DOCTYPE html>
<html lang="en">
<head>
<#include "../includes/common/head.ftl">
</head>

<body>
<#include "../includes/common/header.ftl">
<!-- content start -->
<div id="content" class="clearfix">
    <div class="post">
        <div class="post_header_bg"><h1 class="post-title"><a href="http://www.growers-nation.org/"
                                                              title="Private Data">Private Data</a></h1>
        </div>
        <div class="entry">

            <p>Congratulations! You authenticated through OpenId</p>
            <p>This can be seen by administrators and authenticated public</p>
            <p>Try to get to the <a href="/private/admin">admin page</a></p>
        </div>
        <div class="endline"></div>
    </div>

<#--<#include "../includes/common/social.ftl">-->

</div>
<!-- content end -->

<#include "../includes/common/cdn-scripts.ftl">

</body>
</html>