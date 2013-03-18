<#-- @ftlvariable name="" type="org.growersnation.site.views.PrivateFreemarkerView" -->
<!DOCTYPE html>
<html lang="en">
<head>
<#include "../../includes/common/head.ftl">
</head>

<body>
<#include "../../includes/common/header.ftl">
<!-- content start -->
<div id="content" class="clearfix">
    <div class="post">
        <div class="post_header_bg"><h1 class="post-title"><a href="http://www.growers-nation.org/"
                                                              title="Private Data">Your profile</a></h1>
        </div>
        <div class="entry">

            <form action="/soil" method="post" id="soil_form">
                <fieldset>
                    <legend>Enter soil data</legend>
                    <input id="soil_pH" name="soil_ph" size="10" type="text" value="5.5"/>
                    <input id="soil_submit" type="submit" value="Upload"/>
                </fieldset>
            </form>

        </div>
        <div class="endline"></div>
    </div>

</div>
<!-- content end -->

<#include "../../includes/common/cdn-scripts.ftl">

</body>
</html>