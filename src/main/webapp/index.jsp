<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>     
<script type="text/javascript">
	$(function() { //生成验证码       
		$('#kaptchaImage')
				.click(
						function() {
							$(this).hide().attr(
									'src',
									'code/captcha-image?'
											+ Math.floor(Math.random() * 100))
									.fadeIn();
						});
	});

	window.onbeforeunload = function() {
		//关闭窗口时自动退出  
		if (event.clientX > 360 && event.clientY < 0 || event.altKey) {
			alert(parent.document.location);
		}
	};
	function changeCode() { //刷新
		$('#kaptchaImage').hide().attr('src',
				'code/captcha-image?' + Math.floor(Math.random() * 100))
				.fadeIn();
		event.cancelBubble = true;
	}
	function changeVerifyCode() {
		var verifyCodeValue = $("#kaptcha").val();
		if (verifyCodeValue.replace(/\s/g, "") == "") {
			alert("请输入验证码");
		} else {
			//异步检查验证码是否输入正确
			var verifyUrl = "code/captcha-code?"+ Math.floor(Math.random() * 100);
			$.ajax({
				type : "POST",
				url : verifyUrl,
				data : {
					"verifyCode" : verifyCodeValue
				},
				//contentType: "application/json",  
				dataType : "json",
				success : function(data) {
					if (data.data == 'true') {
						$("#tip").attr("src","images/yes.png");
					} else {
						$("#tip").attr("src","images/no.png");
					}
				},
				error : function(e) {
					alert(e);
				}
			});
		}
	}
</script>
</head>
<body>
	<form action="greeting" method="POST" name="form">
		<table>
			<tr>
				<td>Names:</td>
				<td><input type="text" name="username" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="45px">
				<td valign="top">验证码 :</td>
				<td valign="top"><input name="j_code" type="text" id="kaptcha"
					maxlength="4" class="form-control" onblur="changeVerifyCode()" /></td>
				<td valign="top"><a href="#" onclick="changeCode()"> <img
						src="code/captcha-image" id="kaptchaImage"
						style="margin-bottom: -3px" alt="换一张" /></a><img id="tip" src="" /></td>
			</tr>
		</table>
		<br> <br> <input type="submit" value="submit" />
	</form>
</body>
</html>
