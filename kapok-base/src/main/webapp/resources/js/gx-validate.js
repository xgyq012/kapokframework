
//验证电话
$.extend($.fn.validatebox.defaults.rules, {
phoneRex: {
  validator: function(value){
  var rex=/^1[3-8]+\d{9}$/;
  //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
  //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
  //电话号码：7-8位数字： \d{7,8
  //分机号：一般都是3位数字： \d{3,}
   //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
  var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
  if(rex.test(value)||rex2.test(value))
  {
    // alert('t'+value);
    return true;
  }else
  {
   //alert('false '+value);
     return false;
  }
    
  },
  message: '请输入正确电话或手机格式'
}
});

var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}   

function isCardID(sId){   
    var iSum=0 ;  
    var info="" ;  
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
    sId=sId.replace(/x$/i,"a");   
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
    if(iSum%11!=1) return "你输入的身份证号非法";   
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")   
}   
  
//验证身份证号码  
$.extend($.fn.validatebox.defaults.rules, {     
    idcared: {     
        validator: function(value,param){    
            var flag= isCardID(value);  
            return flag==true?true:false;    
        },     
        message: '不是有效的身份证号码'    
    }     
});  


//只能输入数字
$.extend($.fn.validatebox.defaults.rules, {     
	onlyNumber: {     
        validator: function(value,param){    
        	return value.match(/\D/) == null;
        },     
        message: '请输入整数'    
    }     
}); 
