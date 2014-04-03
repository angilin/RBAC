<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>RBAC系统</title>
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<!-- 参考extjs4.1.1中examples/layout/tabpaneltest.html -->
<style type="text/css">
p {
    margin:5px;
}
</style>
<script type="text/javascript" src="extjs/ext-all.js"></script>
<script type="text/javascript">
	
    Ext.require(['*']);

    Ext.onReady(function() {

        Ext.QuickTips.init();

        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

		//根节点还是只能设置1个
		var treejson = {id:"0",text:"菜单",expanded: true ,children:${sessionScope.com_user.menuJsonString}};
        
        var store = Ext.create("Ext.data.TreeStore", {				
	        model : "ctreemodel",
	        root : treejson
	    });
    	
        var treePanel = Ext.create('Ext.tree.Panel', {
	        id: 'tree',
	        width: 200,
	        autoScroll: true,
	        region: 'west',
	        store:  store
	    });

        var viewport = Ext.create('Ext.Viewport', {
            id: 'border-example',
            layout: 'border',
            items: [
            {
                region: 'north',
                contentEl: 'top',
                height: 50
            }, 
            treePanel,
            Ext.create('Ext.tab.Panel', {
                region: 'center', // a center region is ALWAYS required for border layout
                deferredRender: false,
                activeTab: 0,     // first tab initially active
                items: [{
                    contentEl: 'center1',
                    title: '欢迎',
                    //closable: true,
                    autoScroll: true
                }]
            })]
        });
                
        
        function showDate(){
       	 	var date = new Date();
	    	document.getElementById('currentTime').innerText=date.toLocaleString();
        }
        showDate();
        setInterval(showDate,1000);
        
    });

    </script>
</head>
<body>
    <!-- use class="x-hide-display" to prevent a brief flicker of the content -->
    <div id="top" class="x-hide-display">
        <div>
        	<span style="float:left;padding-left:50px;padding-top:5px;bold;font-size:20pt;">RBAC系统</span>
        	<span style="float:right;padding-right:50px;padding-top:15px;font-size:12pt;">
        		当前用户：<c:out value="${sessionScope.com_user.account.realname}"/>
        		&nbsp;&nbsp;&nbsp;
        		当前时间：<span id="currentTime"></span>
        		&nbsp;&nbsp;&nbsp;
        		<a href="logout.do">登出</a>
        	</span>
        </div>
    </div>
    <div id="west" class="x-hide-display">
    </div>
    <div id="center1" class="x-hide-display">
    菜单的json字符串：${sessionScope.com_user.menuJsonString}
    </div>
</body>
</html>



