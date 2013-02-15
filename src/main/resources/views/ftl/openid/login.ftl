<#-- @ftlvariable name="" type="org.growersnation.site.views.PublicFreemarkerView" -->
<!DOCTYPE html>
<html lang="en">
<head>
<#include "../includes/common/head.ftl">

    <!-- Simple OpenID Selector -->
    <link type="text/css" rel="stylesheet" href="/jquery/plugins/openid-selector/css/openid.css"/>

</head>

<body>
<#include "../includes/common/header.ftl">
<html>
<body>
<!-- content start -->
<div id="content" class="clearfix">
    <div class="post">
        <div class="post_header_bg"><h1 class="post-title"><a href="http://www.growers-nation.org/"
                                                              title="Sign-in with OpenID">Sign-in with OpenID</a></h1>
        </div>
        <div class="entry">

            <form action="/openid" method="post" id="openid_form">
                <fieldset>
                    <legend>Sign-in or Create New Account</legend>
                    <div id="openid_choice">
                        <p>Please click your account provider:</p>

                        <div id="openid_btns"></div>
                        <div id="my_btns"></div>
                    </div>
                    <div id="openid_input_area">
                        <input id="openid_identifier" size="100" name="openid_identifier" type="text" value="http://"/>
                        <input id="openid_submit" type="submit" value="Sign-In"/>
                    </div>
                    <noscript>
                        <p>OpenID is service that allows you to sign-in to many different websites using a single
                            identity. Find out <a href="http://openid.net/what/">more about OpenID</a> and <a
                                    href="http://openid.net/get/">how to get an OpenID enabled account</a>.</p>
                    </noscript>
                </fieldset>
            </form>

        </div>
        <div class="endline"></div>
    </div>

<#--<#include "../includes/common/social.ftl">-->

</div>
<!-- content end -->

<#include "../includes/common/footer.ftl">

<#include "../includes/common/cdn-scripts.ftl">

<!-- Page specific scripts -->
<script type="text/javascript" src="/jquery/plugins/openid-selector/js/openid-jquery.js"></script>
<script type="text/javascript" src="/jquery/plugins/openid-selector/js/openid-en.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        openid.init('identifier');
    });
</script>

</body>
</html>