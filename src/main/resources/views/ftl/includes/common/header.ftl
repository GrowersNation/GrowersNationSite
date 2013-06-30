<#-- @ftlvariable name="" type="org.growersnation.site.interfaces.rest.views.PublicFreemarkerView" -->
<!-- Add any common HTML here -->
<!-- wrapper start -->
<div id="wrapper">
    <div id="bottom_frame">
        <div id="top_frame">
            <div id="top_container">
                <!-- header start -->
                <div id="header">
                    <div id="blog_title"><a href="http://www.growers-nation.org">Growers&#039; Nation</a></div>
                    <h2>Planting the seeds locally for a more sustainable future</h2>

                    <div id="menu">
                        <ul class="mensy-menu">
                            <li class="menu_first"><a href="http://www.growers-nation.org">Home</a></li>
                            <li class="page_item page-item-56 current_page_ancestor current_page_parent"><a
                                    href="http://www.growers-nation.org/about/">About</a>
                                <ul class='children'>
                                    <li class="page_item page-item-198 current_page_item"><a
                                            href="http://www.growers-nation.org/">Background</a></li>
                                    <li class="page_item page-item-253"><a
                                            href="http://www.growers-nation.org/about/the-vision/">Our Vision</a></li>
                                    <li class="page_item page-item-241"><a
                                            href="http://www.growers-nation.org/about/media/">Media</a></li>
                                    <li class="page_item page-item-129"><a
                                            href="http://www.growers-nation.org/about/contact/">Contact</a></li>
                                </ul>
                            </li>
                            <li class="page_item page-item-162"><a href="http://www.growers-nation.org/participate/">Participate</a>
                                <ul class='children'>
                                    <li class="page_item page-item-168"><a
                                            href="http://www.growers-nation.org/participate/dataset/">Add to data
                                        set</a></li>
                                    <li class="page_item page-item-121"><a
                                            href="http://www.growers-nation.org/participate/surveys/">Surveys</a></li>
                                    <li class="page_item page-item-291"><a
                                            href="http://www.growers-nation.org/participate/volunteer/">Volunteer</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="page_item page-item-64"><a href="http://www.growers-nation.org/links/">Links</a>
                                <ul class='children'>
                                    <li class="page_item page-item-13"><a
                                            href="http://www.growers-nation.org/links/data/">Data</a>
                                        <ul class='children'>
                                            <li class="page_item page-item-23"><a
                                                    href="http://www.growers-nation.org/links/data/climate-and-weather/">Climate
                                                and Weather</a></li>
                                            <li class="page_item page-item-21"><a
                                                    href="http://www.growers-nation.org/links/data/mapping-resources/">Mapping
                                                Resources</a></li>
                                            <li class="page_item page-item-27"><a
                                                    href="http://www.growers-nation.org/links/data/fruitveggrain/">Fruit/Veg/Grain</a>
                                            </li>
                                            <li class="page_item page-item-25"><a
                                                    href="http://www.growers-nation.org/links/data/soil/">Soil</a></li>
                                            <li class="page_item page-item-29"><a
                                                    href="http://www.growers-nation.org/links/data/other/">Other</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="page_item page-item-187"><a
                                            href="http://www.growers-nation.org/links/related-apps/">Related apps</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="page_item page-item-48"><a href="http://www.growers-nation.org/the-future/">The
                                Future</a>
                                <ul class='children'>
                                    <li class="page_item page-item-282"><a
                                            href="http://www.growers-nation.org/the-future/outreach/">Outreach</a></li>
                                    <li class="page_item page-item-295"><a
                                            href="http://www.growers-nation.org/the-future/app-potential/">App
                                        potential</a></li>
                                </ul>
                            </li>
                            <li class="page_item page-item-53">
                            <#if model.user?? >
                              <a href="/private/home">Account</a>
                                <ul class='children'>
                                    <li class="page_item page-item-282"><a href="/openid/logout">Logout</a></li>
                                </ul>
                            <#else>
                              <a href="/openid/login">Login</a>
                            </#if>
                            </li>
                        </ul>

                    </div>
                    <div id="rss"><a href="http://www.growers-nation.org/feed/"><img
                            src="http://www.growers-nation.org/wp-content/themes/horticultural-life/images/spacer.gif"
                            alt="RSS"/></a></div>
                </div>
                <!-- header end -->
                <!-- container start -->
                <div id="container" class="clearfix">

                <#include "right-sidebar.ftl">
