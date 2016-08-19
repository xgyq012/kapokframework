<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="nav-left">
	<ul>
		<li><a href="#baseForm" class="active">基础信息</a></li>
		<li><a href="#sbxx">社保信息</a></li>
		<li><a href="#ybxx">医保信息</a></li>
		<li><a href="#swxx">死亡信息</a></li>
		<li><a href="#lnxx">老年信息</a></li>
		<li><a href="#yfxx">优抚人员</a></li>
		<li><a href="#dbxx">低保人员</a></li>
		<li><a href="#wbxx">五保供养</a></li>
		<li><a href="#crxx">留守儿童</a></li>
		<li><a href="#grxx">孤儿信息</a></li>
		<li><a href="#cjxx">残疾人</a></li>
		<li><a href="#xsjjxx">刑释解教人员</a></li>
		<li><a href="#wfqsn">违法青少年</a></li>
		<li><a href="#xjry">从事邪教人员</a></li>
		<li><a href="#cqsfss">长期涉法涉诉人员</a></li>
		<li><a href="#sqjz">社区矫正对象</a></li>
		<li><a href="#fxry">服刑人员</a></li>
		<li><a href="#xdry">吸毒人员</a></li>
	</ul>
</div>
<div class="content-right">
	<form id="baseForm">
		<input id="householderId" name="householderId" type="hidden" value="${requestScope.householderId}">
		<input id="createrId" name="createrId" type="hidden">
		<input id="createTime" name="createTime" type="hidden">
		<fieldset>
			<legend>基础信息</legend>
    		<table class="g-form" cellpadding="0" cellspacing="0">
    			<tbody>
    				<tr>
	    				<td class="form-cell-1">
		            		<label class="form-label">所属网格</label>
		            		<input id="orgId" name="orgId">
	          			</td>
						<td class="form-cell-1" >
							<label class="form-label">户主名称</label>
							<input id="hzId" name="hzId">
						</td>
						<td class="form-cell-1" >
							<label class="form-label">与户主关系</label>
							<input id="familytree" dictCode="familytree" name="familytree"
								   class="easyui-combobox form-control"
								   style="width:${comboboxWidth};height:${comboboxHeight}px"
								   data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'" >
						</td>
	    				<td class="form-cell-1" rowspan="6">
	    					<label class="form-label">照片</label>
	    					<input id="photofileID" name="photofileID" type="hidden">
	     					<input id="docShowname" name="docShowname" type="hidden">
	     					<input id="photofileUrl" type="image" class="easyui-validatebox form-control"
	     						src="${ctx}/resources/images/base/1.png" onclick="return false;"
	     						style="height:${rowSpanHeight*6-gutterHeight}px">
	     						<div id="addPhoto"></div>
	    				</td>
	    				<%-- <td colspan="2" rowspan="5" align="center">
	     					<div style="width: 100%;text-align: center;">
		     					<img  id="photofileUrl" style="width:100px;height:100px" alt="" src="${ctx}/resources/images/base/1.png">
	     					</div>
	     					<div style="width: 100%;text-align: center;">
	     						<a onclick="uploadBtn.fileupload('click');" id="upfile"  href="#" 
	     							class="easyui-linkbutton toolbar" data-options="plain:true,iconCls:'icon-remove'">上传</a>
								<div id="addPhoto"></div>
	     					</div>
	     				</td> --%>
	    			</tr>
	    			<tr>
	    				<td class="form-cell-1">
	    					<label class="form-label">小区</label>
	     					<input id="comId" name="comId" type="hidden">
	     					<input id="communityName" name="communityName" class="easyui-validatebox form-control" readonly>
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">楼栋</label>
	     					<input id="buildId" name="buildId" type="hidden"/> 
	     					<input id="ldCode" name="ldCode" class="easyui-validatebox form-control" readonly>
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">房屋</label>
	     					<input id="houseId" name="houseId"   /> 
	     				</td>
	    			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">姓名</label>
	     					<input name="householderName" class="easyui-validatebox form-control" data-options="required:true">
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">是否户主</label>
	     					<input id="householderRelation" dictCode="HOUSEHOLDER" name="householderRelation"
								class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName',
									onChange:function (newValue,oldValue){
            							householderRelationChange(newValue);
									}
								">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">性别</label>
	    					<input  dictCode="Gender" name="sex" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">身份证号</label>
	     					<input onblur="addBirthDate(this);" name="cardCode" class="easyui-validatebox form-control" data-options="required:true" validType="checkIdCode">
	     				</td>
	     			
	     				<td class="form-cell-1">
	    					<label class="form-label">出生日期</label>
	     					<input id="birthDate" name="birthDate" class="easyui-datebox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
	     						data-options="required:true,editable:false">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">民族</label>
	     					<input dictCode="Nation"  name="nationality"
	     						class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     			</tr>
	     			<tr>
	     			 	<td class="form-cell-1">
							<label class="form-label">年龄</label>
							<input id="age" name="age" class="easyui-validatebox form-control" readonly>
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">健康状况</label>
	     					<input dictCode='healthStatus' name="healthStatus"
								class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">结婚时间</label>
	     					<input name="maritalData" class="easyui-datebox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
	     						data-options="editable:false">
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">婚姻状况</label>
	     					<input dictCode="maritalStatus" name="maritalStatus" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">职业</label>
	     					<input dictCode="JobType" name="jobName" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">节育措施</label>
	     					<input  dictCode="birthControl" name="zjxy" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     			</tr>
	     		 	<tr>
						<td class="form-cell-1">
	    					<label class="form-label">社会职务</label>
	     					<input dictCode="socialFunction" name="socialJob" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">工作单位</label>
	     					<input name="unit" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">职务</label>
	     					<input name="dutyName" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">特长</label>
							<input dictCode="SpecialityType" name="specialty" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">兵役状况</label>
	     					<input  dictCode="veteranStatus" name="veteranStatus" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">户籍类别</label>
	     					<input dictCode="residentType" name="householdType" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">户籍地</label>
	     					<input name="householdAddress" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">籍贯</label>
	     					<input name="nativePlace" class="easyui-validatebox form-control">
	     				</td>
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">身高</label>
	     					<input name="sgHeight" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">住房面积</label>
	     					<input name="housingArea" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">门牌号</label>
	     					<input name="doorplate" class="easyui-validatebox form-control">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">管理类型</label>
	     					<input dictCode="ManageType" name="gllx" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td> 
	     			</tr>
	     			<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">文化程度</label>
	     					<input dictCode="Education" name="eduLevel" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">血型</label>
	     					<input dictCode="bloodType" name="bloodType" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">志愿者</label>
	     					<input  dictCode="YesOrNo" name="isVolunteer" class="easyui-combobox form-control"
	     					style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	    					<label class="form-label">政治面貌</label>
	     					<input dictCode="politicsStatus" name="politicsStatus" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     			</tr>
					<tr>
	     				<td class="form-cell-1">
	    					<label class="form-label">宗教信仰</label>
	     					<input dictCode="Religion" name="religion" class="easyui-combobox form-control"
	     						style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	     				</td>
	     				<td class="form-cell-1">
	     					<label class="form-label">联系电话</label>
							<input name="callPhone" class="easyui-validatebox form-control">
	     				</td>  
	     				<td class="form-cell-2" colspan="2">
	    					<label class="form-label">现居地</label>
	     					<input name="dwellAddress" class="easyui-validatebox form-control">
	     				</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
	
	<!-- 社保信息 -->
    <form id="sbxx" infoName="sbxx"  action="${ctx}/socialinfo/save">
		<input name="socialId" type="hidden">
		<input name="householderId" type="hidden">
    	<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>社保信息</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="社保编号">社保编号</label>
							<input name="socialCode" class="easyui-validatebox form-control" data-options="required:true">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="退休日期">退休日期</label>
							<input  name="retirementDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="每月领取退休金金额">每月领取退休金金额</label>
							<input name="havePensionAmount" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="养老保险类型">养老保险类型</label>
							<input dictCode="EndowmentInsuranceType" name="endowInsuranceType"
								class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
						</td>
					</tr>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="参保日期">参保日期</label>
							<input name="participationDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="参保金额">参保金额</label>
							<input name="participationAmount"class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="领取养老金日期">领取养老金日期</label>
							<input  name="haveAgedDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="每月领取养老金金额">每月领取养老金金额</label>
							<input name="haveAgedAmount" class="easyui-validatebox form-control">
						</td>
					</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
			 		</tr>
			 	</tbody>
			</table>
		</fieldset>
	</form>

	<!-- 医保信息 -->
	<form id="ybxx" infoName="ybxx" action="${ctx}/healthinSuranceinfo/save">
		<input id="healthId" name="healthId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>医保信息</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="医保卡号">医保卡号</label>
							<input name="healthCode" class="easyui-validatebox form-control" data-options="required:true">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="缴纳医保类型">缴纳医保类型</label>
							<input dictCode="healthType" name="healthType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
	 					</td>
						<td class="form-cell-1">
							<label class="form-label" title="投保时间">投保日期</label>
							<input name="insureDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<%--<td class="form-cell-1">
							<label class="form-label" title="缴纳金额">缴纳金额</label>
							<input name="paymentAmount"class="easyui-validatebox form-control">
						</td>--%>
					</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
	
	<!-- 死亡信息 -->			
	<form  id="swxx" infoName="swxx" action="${ctx}/dieinfo/save">
		<input name="dieId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>死亡信息</legend>
	 		<table class="g-form" cellpadding="0" cellspacing="0">
	 			<tbody>
			 		<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="死亡时间">死亡时间</label>
							<input name="dieDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="required:true,editable:false">
						</td>
			 			<td class="form-cell-1">
							<label class="form-label" title="更新时间">更新时间</label>
							<input name="updateYearm" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="required:true,editable:false">
						</td>
			 			<td class="form-cell-1">
							<label class="form-label" title="死亡原因">死亡原因</label>
							<input name="dieReason" class="easyui-validatebox form-control">
						</td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
	 		</table>
		</fieldset>
	</form>

	<!-- 老年信息 -->
	<form id="lnxx" infoName="lnxx" action="${ctx}/oldpeople/save">
		<input name="householderId" type="hidden">
		<fieldset>
			<legend>老年信息</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>
			 		<tr class="lnxx">
		 				<td class="form-cell-1">
							<label class="form-label" title="经济状态">经济状态</label>
							<input dictCode="EconomicType" name="economic" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	required:true,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
							  <input name="opId" type="hidden">
						   	  <input name="createrId" type="hidden">
							  <input name="createTime" type="hidden">
 						</td>
 						<td class="form-cell-1">
							<label class="form-label" title="药物过敏史">药物过敏史</label>
							<input name="ywgms" class="easyui-validatebox form-control">
						</td>
					 	<td class="form-cell-1">
							<label class="form-label" title="居住方式">居住方式</label>
							<input dictCode="livingType" name="jzfs" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>		
						<td class="form-cell-1">
							<label class="form-label" title="服务需求">服务需求</label>
							<input dictCode="serviceType" name="fwxq" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
			 		</tr>
			 		<tr class="zvxx">
			 			<td class="form-cell-1">
							<label class="form-label" title="子女姓名"><span title="删除子女信息" class="zvxx_remove"><i class="fa fa-times" aria-hidden="true"></i></span>&nbsp;子女姓名</label>
							<input name="childrenName" class="easyui-validatebox form-control" required="required">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="工作单位">工作单位</label>
							<input name="gzdw" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="家庭详细住址">家庭详细住址</label>
							<input name="jtxxAddress" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="联系电话">联系电话</label>
							<input name="callPhone" required="required" class="easyui-validatebox form-control">
						</td>
			 		</tr>
			 		<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-2 f-button" colspan="2">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveLnxx(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
			 				<a onclick="otherInfo.addChildInfo(this)" class="easyui-linkbutton save g-button"><i class="fa fa-plus"></i>新增子女信息</a>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>

	<!-- 优抚人员 -->
	<form id="yfxx" infoName="yfxx" action="${ctx}/comfort/save">
		<input name="comfortId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>优抚人员</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>	
		 			<tr>
		 				<td class="form-cell-1">
							<label class="form-label" title="优抚类别">优抚类别</label>
							<input dictCode="comfortType" name="comfortType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
						<td class="form-cell-1">
							<label class="form-label" title="优抚金额">优抚金额</label>
							<input name="comfortAmount" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="工作状态">工作状态</label>
							<input name="workStatus" class="easyui-validatebox form-control">
						</td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
			 		</tr>
			 	</tbody>
	 		</table>
		</fieldset>
	</form>
	
	<!-- 低保人员 -->
	<form id="dbxx" infoName="dbxx" action="${ctx}/lowpeople/save">
		<input name="dbId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>低保人员</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>
	 				<tr>
	 					<td class="form-cell-1">
							<label class="form-label" title="低保类型">低保类型</label>
							<input dictCode="lowType" name="dbType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
						<td class="form-cell-1">
							<label class="form-label" title="家庭人口">家庭人口</label>
							<input name="familyNum" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="家庭月收入">家庭月收入</label>
							<input name="familyAmount" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="低保金额固定保障">低保金额固定保障</label>
							<input name="dbjegdb" class="easyui-validatebox form-control">
						</td>
			 		</tr>
			 		<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="保障人口">保障人口</label>
							<input name="bzrk" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="家庭月支出">家庭月支出</label>
							<input name="jtyzc" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="走访记录">走访记录</label>
							<input name="zfRecord" class="easyui-validatebox form-control">
						</td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
		 	</table>
		</fieldset>
	</form>
			
	<!-- 五保供养 -->		
	<form id="wbxx" infoName="wbxx" action="${ctx}/wbgy/save">
		<input name="wbId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>五保供养</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>
		 			<tr>
		 				<td class="form-cell-1">
								<label class="form-label" title="供养类别">供养类别</label>
								<input dictCode="gylbType" name="gylbType" class="easyui-combobox form-control"
									style="width:${comboboxWidth};height:${comboboxHeight}px"
									data-options="
								    	editable:false,
								    	panelHeight:'auto',
								    	valueField:'dictCode',
								    	textField:'dictName'">
 						</td>
						<td class="form-cell-1">
							<label class="form-label" title="现补贴标准">现补贴标准</label>
							<input name="xbtbz" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="监护人">监护人</label>
							<input name="jhr" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="监护人电话">监护人电话</label>
							<input name="jhrPhone" class="easyui-validatebox form-control">
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
	 		</table>
		</fieldset>
	</form>
	 
	<!-- 留守儿童 -->
	<form  id="crxx" infoName="crxx" action="${ctx}/children/save" >
		<input name="childrenId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
		<legend>留守儿童</legend>
	 	<table class="g-form" cellpadding="0" cellspacing="0">
	 		<tbody>
		 		<tr>
		 			<td class="form-cell-1">
						<label class="form-label" title="父亲">父亲</label>
						<input name="fatcherName" class="easyui-validatebox form-control">
					</td>
					<td class="form-cell-2" colspan="2">
						<label class="form-label" title="打工地">打工地</label>
						<input name="fatcherWorker" class="easyui-validatebox form-control">
					</td>
		 			<td class="form-cell-1">
						<label class="form-label" title="是否入学">是否入学</label>
						<input dictCode="YesOrNo" name="isSchool" class="easyui-combobox form-control"
							style="width:${comboboxWidth};height:${comboboxHeight}px"
							data-options="
						    	editable:false,
						    	panelHeight:'auto',
						    	valueField:'dictCode',
						    	textField:'dictName'">
 						</td>
 					</tr>
 					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="母亲">母亲</label>
							<input name="motherName" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-2" colspan="2">
							<label class="form-label" title="打工地">打工地</label>
							<input name="motherWorker" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
		 			</tr>
				</tbody>
		 	</table>
		</fieldset>
	</form>
			
	<!-- 孤儿信息 -->
	<form id="grxx" infoName="grxx" action="${ctx}/orphan/save">
		<input name="orphanId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>孤儿信息</legend>
	 		<table class="g-form" cellpadding="0" cellspacing="0">
	 			<tbody>
	 				<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="供养类别">供养类别</label>
							<input dictCode="gylbType" name="gylbType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
 						<td class="form-cell-1">
							<label class="form-label" title="现补贴标准">现补贴标准</label>
							<input name="xbtbz" class="easyui-validatebox form-control">
						</td>
					 	<td class="form-cell-1">
							<label class="form-label" title="监护人">监护人</label>
							<input name="jhr" class="easyui-validatebox form-control">
						</td>		
					 	<td class="form-cell-1">
							<label class="form-label" title="监护人电话">监护人电话</label>
							<input name="jhrPhone" class="easyui-validatebox form-control">
						</td>
	 				</tr>
	 				<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
	 			</tbody>
		 	</table>
		</fieldset>
	</form>

	<!-- 残疾人 -->
	<form id="cjxx" infoName="cjxx" action="${ctx}/handicappedPeople/save">
		<input name="hId" type="hidden">
		<input name="householderId" type="hidden">
   		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>残疾人</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
			 		<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="残疾类别">残疾类别</label>
							<input dictCode="impairmentType" name="cjrType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
 						</td>
 						<td class="form-cell-1">
							<label class="form-label" title="残疾等级">残疾等级</label>
							<input dictCode="disabilityLevel" name="cjrLevel" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
 						</td>
						<td class="form-cell-1">
							<label class="form-label" title="残疾人证号">残疾人证号</label>
							<input name="cjrCode" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="残疾部位">残疾部位</label>
							<input name="cjbw" class="easyui-validatebox form-control">
						</td>
			 		</tr>
			 		<tr>
			 			<td class="form-cell-4" colspan="4">
							<label class="form-label" title="残疾原因">残疾原因</label>
							<textarea name="cjyy" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
		 	</table>
		</fieldset>
	</form>
		
	<!-- 刑释解教人员 -->
	<form id="xsjjxx" infoName="xsjjxx"   action="${ctx}/xsjjry/save">
		<input name="xId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>刑释解教人员</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
			 		<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="是否农村籍">是否农村籍</label>
							<input dictCode="YesOrNo" name="isNcj" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
 						</td>
 						<td class="form-cell-1">
							<label class="form-label" title="原判刑罚及期限">原判刑罚及期限</label>
							<input name="ypxfjqx" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="释解日期">释解日期</label>
							<input name="xjrqDate" class="easyui-datebox form-control"
								data-options="editable:false"
								style="width:${comboboxWidth};height:${comboboxHeight}px">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="罪名">罪名</label>
							<input name="zm" class="easyui-validatebox form-control">
						</td>
			 		</tr>
			 		<tr>
			 			<td class="form-cell-1">
							<label class="form-label" title="现在工作状况">现在工作状况</label>
							<input name="gzzk" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-3" colspan="3">
							<label class="form-label" title="详细住址">详细住址</label>
							<input name="address" class="easyui-validatebox form-control">
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
		 	</table>
		</fieldset>
	</form>
		
	<!-- 违法青少年 -->
	<form id="wfqsn" infoName="wfqsn" action="${ctx}/wfjl/save">
		<input name="xId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>违法青少年</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
			 		<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="违法记录">违法记录</label>
							<textarea name="wfjl" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
		 	</table>
		</fieldset>
	</form>			
			
	<!-- 从事邪教人员 -->		
	<form id="xjry" infoName="xjry" action="${ctx}/csxjry/save">
		<input name="xjId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>从事邪教人员</legend>
		 	<table class="g-form" cellpadding="0" cellspacing="0">
		 		<tbody>
		 			<tr>
		 				<td class="form-cell-1">
							<label class="form-label" title="练功时间">练功时间</label>
							<input name="lgsjDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false" >
						</td>
						<td class="form-cell-3" colspan="3">
							<label class="form-label" title="转化情况">转化情况</label>
							<input name="zhqk" class="easyui-validatebox form-control">
						</td>
			 		</tr>
					<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="练功原因">练功原因</label>
							<textarea name="lgyy" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
			 		<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="受打击处理情况">受打击处理情况</label>
							<textarea name="sdjclqk" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
			 		<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="备注">备注</label>
							<textarea name="remark" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
			
	<!-- 长期涉法涉诉人员 -->
	<form id="cqsfss" infoName="cqsfss" action="${ctx}/cqsfssry/save">
		<input name="cfId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>长期涉法涉诉人员</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="涉法涉诉单位">涉法涉诉单位</label>
							<input name="sfssdwName" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="涉法涉诉人员">涉法涉诉人员</label>
							<input name="sfssryName" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="案件包保单位">案件包保单位</label>
							<input name="ajbbdw" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="包保人员">包保人员</label>
							<input name="bbryName" class="easyui-validatebox form-control">
						</td>
			 		</tr>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="涉法涉诉起始时间">涉法涉诉起始时间</label>
							<input name="sfssqsDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-3" colspan="3">
							<label class="form-label" title="涉法涉诉事由">涉法涉诉事由</label>
							<input name="sfssReason" class="easyui-validatebox form-control">
						</td>
					</tr>
					<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="现阶段处理情况">现阶段处理情况</label>
							<textarea name="xjdclqk" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
	 		</table>
		</fieldset>
	</form>
			
	<!-- 社区矫正对象 -->
	<form id="sqjz" infoName="sqjz" action="${ctx}/sqjzdx/save">
		<input name="jzId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>社区矫正对象</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="审判机关">审判机关</label>
							<input name="spjg" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="刑罚">刑罚</label>
							<input name="xf" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="单位或地址">单位或地址</label>
							<input name="address" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="生效日时期">生效日时期</label>
							<input name="sxrqDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
					</tr>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="判决日期">判决日期</label>
							<input name="pjrqDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="社区矫正起止时间">社区矫正起止时间</label>
							<input name="sqjzqzsjDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
					 	<td class="form-cell-1">
							<label class="form-label" title="判决罪名">判决罪名</label>
							<input name="pjzm" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="社区矫正类型">社区矫正类型</label>
							<input dictCode="CorrectionType" name="sqjzType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
 								    	editable:false,
 								    	panelHeight:'auto',
 								    	valueField:'dictCode',
 								    	textField:'dictName'">
 						</td>
					</tr>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="家庭监护人">家庭监护人</label>
							<input name="jtjhrName" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="社区监护人">社区监护人</label>
							<input name="sqjhrName" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
			
	<!-- 服刑人员 -->		
	<form id="fxry" infoName="fyry"  action="${ctx}/fxry/save">
		<input name="fxId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>服刑人员</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
		 			<tr>
				 		<td class="form-cell-1">
							<label class="form-label" title="审判机关">审判机关</label>
							<input name="spjg" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="罪名">罪名</label>
							<input name="zm" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="刑期">刑期</label>
							<input name="xq" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="服刑地址">服刑地址</label>
							<input name="fxdz" class="easyui-validatebox form-control">
						</td>
			 		</tr>
			 	 	<tr>
			 	 		<td class="form-cell-1">
							<label class="form-label" title="矫正期限">矫正期限</label>
							<input name="jzqx" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="类别">类别</label>
							<input dictCode="ImprisonType" name="lbType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
 						<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
			 		</tr>
				</tbody>		 		 
			</table>
		</fieldset>
	</form>
			
	<!-- 吸毒人员 -->	
	<form id="xdry" infoName="xdry" action="${ctx}/xdry/save">
		<input name="xdId" type="hidden">
		<input name="householderId" type="hidden">
		<input name="createrId" type="hidden">
		<input name="createTime" type="hidden">
		<fieldset>
			<legend>吸毒人员</legend>
			<table class="g-form" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="初吸时间">初吸时间</label>
							<input name="xdDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="毒品种类">毒品种类</label>
							<input dictCode="PoisonType" name="dpType" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
						<td class="form-cell-1">
							<label class="form-label" title="处理措施">处理措施</label>
							<input name="clcs" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="已纳入社区戒毒/社区康复">已纳入社区戒毒/社区康复</label>
							<input dictCode="YesOrNo" name="isRehabilitation" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
					</tr>
					<tr>
						<td class="form-cell-1">
							<label class="form-label" title="报到日期">报到日期</label>
							<input name="beginDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="结束日期">结束日期</label>
							<input name="endDate" class="easyui-datebox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="editable:false">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="法律文书文号">法律文书文号</label>
							<input name="flwswh" class="easyui-validatebox form-control">
						</td>
						<td class="form-cell-1">
							<label class="form-label" title="结束原因">结束原因</label>
							<input dictCode="EndReason" name="endReason" class="easyui-combobox form-control"
								style="width:${comboboxWidth};height:${comboboxHeight}px"
								data-options="
							    	editable:false,
							    	panelHeight:'auto',
							    	valueField:'dictCode',
							    	textField:'dictName'">
 						</td>
					</tr>
					<tr>
						<td class="form-cell-4" colspan="4">
							<label class="form-label" title="备注">备注</label>
							<textarea name="remark" class="easyui-validatebox form-control" rows="3"></textarea>
						</td>
			 		</tr>
					<tr>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1"></td>
			 			<td class="form-cell-1 f-button">
							<a onclick="otherInfo.delBtn(this)" class="easyui-linkbutton del g-button"><i class="fa fa-trash-o"></i>删除</a>
			 				<a onclick="otherInfo.editBtn(this)" class="easyui-linkbutton edit g-button"><i class="fa fa-edit"></i>编辑</a>
							<a onclick="otherInfo.saveBtn(this)" class="easyui-linkbutton save g-button"><i class="fa fa-floppy-o"></i>保存</a>
						</td>
					</tr>
				</tbody>		 		 
			</table>
		</fieldset>
	</form>
</div>		