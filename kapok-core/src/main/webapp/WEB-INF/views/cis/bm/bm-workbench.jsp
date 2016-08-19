<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BM工作台</title>
	
    <link rel="shortcut icon" href="${ctx}/resources/images/logo_16.ico">
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome.min.css">
	<!--[if IE 7]>
	<link rel="stylesheet" href="${ctx}/resources/libs/${fontAwesome}/css/font-awesome-ie7.min.css">
	<![endif]-->
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/metro-blue/easyui.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/${jqueryEasyui}/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/gxwlui.css">
	<link rel="stylesheet" href="${ctx}/resources/css/page.css">
	<link rel="stylesheet" href="${ctx}/resources/css/jqchart/jquery.jqChart.css">
	<link rel="stylesheet" href="${ctx}/resources/css/jqchart/jquery.jqRangeSlider.css">
	<link rel="stylesheet" href="${ctx}/resources/css/jqchart/jquery-ui-1.10.4.css">
	<style type="text/css">
		.data-table {
			width:100%;
			font-size:14px;
			color:#555555;
			border-collapse:collapse;
		}
		.data-table th,.data-table td {
			height:40px;
 			border:1px solid #e1e1e1;
		}
		.data-table thead td{
			background-color:#3aa5e6;
			text-align:center;
			color:#ffffff;
		}
		.data-table tbody th {
			width:15%;
			background-color: #f3f3f3;
		}
		.data-table tbody td {
			padding-left:15px;
			width:85%;
		}
		.data-table td a{
			margin-right: 40px;
		}
		.data-table td a samp{
			color:#3aa5e6;
		}
		
		#chart{
		width: 100%;
		height: 200px;
		}
	</style>
</head>

<body>
<div class="g-layout">
	<div id="contentTable" style="padding:20px;display:none;">
		<table class="data-table" cellpadding="0" cellspacing="0">
		    <thead>
		        <tr>
		           <td colspan="2">基础数据一览表</td>
	            </tr>
		    </thead>
		    <tbody>
		    	<tr>
		    		<th>人口汇总</th>
		    		<td>
		    			<a href="#" icon="fa fa-renren" id="rkxx-all" tit="人口信息"  url="/householder/list">全部人口&nbsp;<samp id="rkzs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="nvrenxx-czrk" tit="人口信息-女性" url="/householder/list" options="q_sex_EQ=female">女性&nbsp;<samp id="femalezs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="nanrenxx-czrk" tit="人口信息-男性" url="/householder/list" options="q_sex_EQ=male">男性&nbsp;<samp id="malezs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="czrkxx-czrk" tit="人口信息-常住人口" url="/householder/list" options="q_gllx_EQ=4">常住人口&nbsp;<samp id="czrk">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="ldrkxx-czrk" tit="人口信息-流动人口" url="/householder/list" options="q_gllx_EQ=2">流动人口&nbsp;<samp id="ldrk">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="kgrkxx-czrk" tit="人口信息-空挂人口" url="/householder/list" options="q_gllx_EQ=1">空挂人口&nbsp;<samp id="kgrk">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="lingsanrkxx-czrk" tit="人口信息-零散人口" url="/householder/list" options="q_gllx_EQ=5">零散人口&nbsp;<samp id="lingsanrk">0</samp>&nbsp;人</a>
	    			</td>
		    	</tr>
		    	<tr>
		    		<th>人口汇总</th>
		    		<td>
						<a href="#" icon="fa fa-renren" id="ny-fny" tit="人口信息-农业与非农户" url="/householder/list" options="q_householderRelation_EQ=1">农业与非农户&nbsp;<samp id="hkzs">0</samp>&nbsp;户</a>
		    			<a href="#" icon="fa fa-renren" id="nyh-czrk" tit="人口信息-农业户" url="/householder/list" options="q_householdType_EQ=1&q_householderRelation_EQ=1">农业户&nbsp;<samp id="nonyehu">0</samp>&nbsp;户</a>
		    			<a href="#" icon="fa fa-renren" id="nyhxx-czrk" tit="人口信息-农业户人口" url="/householder/list" options="q_householdType_EQ=1">农业户人口&nbsp;<samp id="nyhzs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="fnyh-czrk" tit="人口信息-非农业户" url="/householder/list" options="q_householdType_EQ=2&q_householderRelation_EQ=1">非农业户&nbsp;<samp id="feinonyehu">0</samp>&nbsp;户</a>
		    			<a href="#" icon="fa fa-renren" id="fnyhxx-czrk" tit="人口信息-非农业户人口" url="/householder/list" options="q_householdType_EQ=2">非农业户人口&nbsp;<samp id="fnyhzs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="bksxx-czrk" tit="人口信息-本科生" url="/householder/list" options="q_eduLevel_EQ=benke">本科生&nbsp;<samp id="bkszs">0</samp>&nbsp;人</a>
	    			</td>
		    	</tr>
		    	<tr>
		    	   <th>老龄人口汇总</th>
		    	   <td>
					   <a href="#" icon="fa fa-renren" id="shaonianxx-czrk" tit="人口信息-18岁以下人口" url="/householder/list" options="q_age_LT=18">18岁以下人口&nbsp;<samp id="shaonianzs">0</samp>&nbsp;人</a>
		    	   	   <a href="#" icon="fa fa-odnoklassniki" id="lnr-all" tit="老年人信息" url="/oldpeople/list">老年人&nbsp;<samp id="lnr">0</samp>&nbsp;个</a>
		    	   	   <a href="#" icon="fa fa-renren" id="lsslnrxx-czrk" tit="人口信息-60岁以上人口" url="/householder/list" options="q_age_GTE=60">60岁以上老人&nbsp;<samp id="lssyslrzs">0</samp>&nbsp;人</a>
		    	   	   <a href="#" icon="fa fa-renren" id="qsslnrxx-czrk" tit="人口信息-70岁以上人口" url="/householder/list" options="q_age_GTE=70">70岁以上老人&nbsp;<samp id="qssyslrzs">0</samp>&nbsp;人</a>
		    		   <a href="#" icon="fa fa-renren" id="bsslnrxx-czrk" tit="人口信息-80岁以上人口" url="/householder/list" options="q_age_GTE=80">80岁以上老人&nbsp;<samp id="bssyslrzs">0</samp>&nbsp;人</a>
		    	   </td>   
		    	</tr>		    	
		    	<tr>
		    		<th>弱势群体汇总</th>
		    		<td>
		    		    <a href="#" icon="fa fa-odnoklassniki" id="gglnr-all" tit="老年人信息-孤寡老人" url="/oldpeople/list" options="q_jzfs_EQ=520">孤寡老人&nbsp;<samp id="gglrzs">0</samp>&nbsp;个</a>
		    		    <a href="#" icon="fa fa-odnoklassniki" id="kclnr-all" tit="老年人信息-空巢老人" url="/oldpeople/list" options="q_jzfs_EQ=522">空巢老人&nbsp;<samp id="kclr">0</samp>&nbsp;个</a>
		    		    <a href="#" icon="fa fa-wheelchair" id="cjr-all" tit="残疾人信息" url="/handicappedPeople/list">残疾人&nbsp;<samp id="cjr">0</samp>&nbsp;个</a>
		    		    <a href="#" icon="fa fa-child" id="guer-all" tit="孤儿信息" url="/orphan/list">孤儿&nbsp;<samp id="guer">0</samp>&nbsp;个</a>
		    		    <a href="#" icon="fa fa-child" id="lset-all" tit="留守儿童信息" url="/children/list">留守儿童&nbsp;<samp id="lset">0</samp>&nbsp;个</a>
		    		    <a href="#" icon="fa fa-renren" id="dbh-all" tit="低保户信息" url="/lowpeople/list">低保户&nbsp;<samp id="dbh">0</samp>&nbsp;个</a>
		    		</td>
		    	</tr>
 		    	<tr>
		    		<th>物业汇总</th>
		    		<td>
		    		   <a href="#" icon="fa fa-reddit-alien" id="cjr-all" tit="小区信息" url="/community/list">小区总数&nbsp;<samp id="xqzs">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-building" id="ldxx-all" tit="楼栋信息" url="/buildingmsg/list">楼栋总数&nbsp;<samp id="ldzs">0</samp>&nbsp;栋</a>
		    		   <a href="#" icon="fa fa-home" id="fwxx-all" tit="房屋信息" url="/house/list">房屋总数&nbsp;<samp id="fwzs">0</samp>&nbsp;间</a>
		    		   <a href="#" icon="fa fa-cutlery" id="mdxx-all" tit="门店信息" url="/shop/list">门店总数&nbsp;<samp id="mdzs">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-graduation-cap" id="xxxx-all" tit="学校信息" url="/school/list">学校总数&nbsp;<samp id="xxzs">0</samp>&nbsp;所</a>
		    		   <a href="#" icon="fa fa-hospital-o" id="yyxx-all" tit="医院信息" url="/hospital/list">医院总数&nbsp;<samp id="yyzs">0</samp>&nbsp;所</a>
		    		</td>		    		
		    	</tr>		    	
		    	<tr>
		    		<th>党建信息汇总</th>
		    		<td>
		    			<a href="#" icon="fa fa-renren" id="tyxx-czrk" tit="人口信息-团员信息" url="/householder/list" options="q_politicsStatus_EQ=2">团员&nbsp;<samp id="tyzs">0</samp>&nbsp;人</a>
		    			<a href="#" icon="fa fa-renren" id="dyxx-czrk" tit="人口信息-党员信息" url="/householder/list" options="q_politicsStatus_EQ=3">党员&nbsp;<samp id="dyzs">0</samp>&nbsp;人</a>
	    			</td>
		    	</tr>
		    	<tr>
		    		<th>辖区单位汇总</th>
		    		<td>
		    		   <a href="#" icon="fa fa-industry" id="qy-all" tit="单位信息" url="/units/list">所有企业&nbsp;<samp id="allqy">0</samp>&nbsp;家</a>
		    		   <a href="#" icon="fa fa-industry" id="wzqy-all" tit="单位信息-外资企业" url="/units/list" options="dwxz=foreign">外资企业&nbsp;<samp id="wzqy">0</samp>&nbsp;家</a>
		    		   <a href="#" icon="fa fa-industry" id="syqy-all" tit="单位信息-私营企业" url="/units/list" options="dwxz=private">私营企业&nbsp;<samp id="syqy">0</samp>&nbsp;家</a>
		    		   <a href="#" icon="fa fa-industry" id="jtqy-all" tit="单位信息-集体企业" url="/units/list" options="dwxz=collective">集体企业&nbsp;<samp id="jtqy">0</samp>&nbsp;家</a>
		    		   <a href="#" icon="fa fa-industry" id="xzjg-all" tit="单位信息-行政机关" url="/units/list" options="dwxz=administrative">行政机关&nbsp;<samp id="xzjg">0</samp>&nbsp;个</a>
		    		</td>		    	
		    	</tr>
		    	<tr>
		    		<th>综合治理汇总</th>
		    		<td>
		    		   <a href="#" icon="fa fa-users" id="xfdxx-all" tit="巡防队信息" url="/patrol/list">巡防队&nbsp;<samp id="xfdzs">0</samp>&nbsp;支</a>
		    		   <a href="#" icon="fa fa-fax" id="zbsxx-all" tit="值班室信息" url="/duryroom/list">值班室&nbsp;<samp id="zbszs">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-user-secret" id="sfsjxx-all" tit="司法事件管理" url="/judical/list">司法事件总数&nbsp;<samp id="sfsjzs">0</samp>&nbsp;</a>
		    		   <a href="#" icon="fa fa-exclamation-circle" id="afqkxx-all" tit="案发情况" url="/case/list">案发&nbsp;<samp id="afzs">0</samp>&nbsp;综</a>
		    		   <a href="#" icon="fa fa-video-camera" id="jfxx-all" tit="技防信息" url="/safety/list">摄像头&nbsp;<samp id="sxtzs">0</samp>&nbsp;个</a>
		    		</td>		    		
		    	</tr>
		    	<tr>
		    		<th>安检消防提醒</th>
		    		<td>
		    		   <a href="#" icon="fa fa-fire-extinguisher" id="xfxx-all" tit="消防信息" url="/fireControl/list">所有隐患&nbsp;<samp id="xfyhzs">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-fire-extinguisher" id="zsxftd-all" tit="阻塞消防通道隐患" url="/fireControl/list" options="congested=Y">阻塞消防通道隐患&nbsp;<samp id="CONGESTED">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-fire-extinguisher" id="wgyd-all" tit="违规用电隐患" url="/fireControl/list" options="overload=Y">违规用电隐患&nbsp;<samp id="OVERLOAD">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-fire-extinguisher" id="xfssbky-all" tit="消防设施不可用隐患" url="/fireControl/list" options="damage=Y">消防设施不可用隐患&nbsp;<samp id="DAMAGE">0</samp>&nbsp;个</a>
		    		   <a href="#" icon="fa fa-fire-extinguisher" id="jjtdzs-all" tit="紧急通道堵塞隐患" url="/fireControl/list" options="stairs=Y">紧急通道堵塞隐患&nbsp;<samp id="STAIRS">0</samp>&nbsp;个</a>
		    		</td>		    		
		    	</tr>	
    			    		    			    			    			    			    			    	
		    </tbody>
		</table>
	</div>
 	<div id="jqChart" style="width: 100%; height: 200px; padding: 0 20px 0 20px; margin-left: 20px;"></div>

</div>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/${jqueryEasyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/gxwl.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/base.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqchart/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqchart/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqchart/jquery.jqRangeSlider.min.js"></script>

<script type="text/javascript">
$(function(){
	$.messager.progress();
	$.ajax({
		type: "post",
		url: "${ctx}/count/getallcount",
		data: {
			meshIds: main.currentUserMesh.meshChildrenIds
		}
	}).done(function(result){
		initFlot(result.row);
		console.log(result.row);
		$.each(result.row, function(key, value){
			if($("#"+ key).size()){
				$("#"+ key).html(value ? value : 0);
			}
		});
		$('#contentTable').show();
		$.messager.progress("close");
	});
	
	$(".data-table td a").on("click", function(){
		var $samp = $(this);
		title = '<i class="'+ $samp.attr("icon") +'"></i>&nbsp;&nbsp;' + $samp.attr("tit");
		parent.addTab({
			id: $samp.attr("id"),
			title: title,
			url: "${ctx}/"+ $samp.attr("url") + "/?"+ $samp.attr("options")
		});
	});
	
});

function initFlot(row){
	
	 var background = {
             type: 'linearGradient',
             x0: 0,
             y0: 0,
             x1: 0,
             y1: 1,
             colorStops: [{ offset: 0, color: '#f3f3f3' },
                          { offset: 1, color: '#f3f3f3' }]
         };

	 	 var max = quzhen(row.rkzs);
         $('#jqChart').jqChart({
             //title: { text: 'Axis Settings' },
             border: { strokeStyle: '#e1e1e1' },
             background: background,
             animation: { duration: 1 },
             shadows: {
                 enabled: true
             },
             axes: [
                 {
                     type: 'linear',
                     location: 'left',
                     minimum: 5,
                     maximum: max,
                     interval: max / 5
                 }
             ],
             series: [
                 {
                     title: '人口 汇总',
                     type: 'column',
                     data: [['全部人口', row.rkzs], ['女性', row.femalezs], ['男性', row.malezs],
                            ['常住人口', row.czrk], ['流动人口', row.ldrk], ['挂空人口', row.kgrk],
                            ['农业户', row.nyhzs], ['非农业户', row.fnyhzs], ['本科生', row.bkszs]]
                 },
                 {
                     title: '各年龄段人口',
                     type: 'column',
                     data: [['十八岁以下人员', row.shaonianzs],['60岁以上人口', row.lssyslrzs], 
                            ['70岁以上人口', row.qssyslrzs], ['八十岁以上人口', row.bssyslrzs]]
                 },
                 {
                     title: '弱势群体',
                     type: 'column',
                     data: [['孤寡老人', row.gglrzs], ['空巢老人', row.kclr], ['残疾人', row.cjr],
                            ['孤儿', row.guer], ['留守儿童', row.lset], ['低保户', row.dbh]]
                 }
             ]
         });

}

function quzhen(num){
	var i = 100;
	for(;i < num;){
		i = i + 100;
	}	
	return i;
}
console.log();
</script>
</body>
</html>