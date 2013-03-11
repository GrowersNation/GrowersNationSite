<#-- @ftlvariable name="" type="org.growersnation.site.views.PrivateFreemarkerView" -->
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
                                                              title="Private Data">Your profile</a></h1>
        </div>
        <div class="entry">
            <h3>Personal details</h3>
            <ul>
                <li><strong>Email address: </strong><p>${model.user.emailAddress!"Unknown"}</p></li>
                <li><strong>First name: </strong><p>${model.user.firstName!"Unknown"}</p></li>
                <li><strong>Last name: </strong><p>${model.user.lastName!"Unknown"}</p></li>
            </ul>

        </div>
        <div class="endline"></div>
    </div>

</div>
<!-- content end -->

<#include "../includes/common/cdn-scripts.ftl">

</body>
</html>