<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Project-Major-Web | Register</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/html/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/AdminLTE.min.css">
</head>
<body class="login-page" style="background-color: #bacab3;">
	<script language="javascript">
		function fncSubmit() {

			if (document.formRegis.txtPassword.value == "") {
				alert('Please input Password');
				document.formRegis.txtPassword.focus();
				return false;
			}

			if (document.formRegis.txtConPassword.value == "") {
				alert('Please input Confirm Password');
				document.formRegis.txtConPassword.focus();
				return false;
			}

			if (document.formRegis.txtPassword.value != document.formRegis.txtConPassword.value) {
				alert('Confirm Password Not Match');
				document.formRegis.txtConPassword.focus();
				return false;
			}

			document.form1.submit();
		}
	</script>
	<br>



	<form name="formRegis" action="insert_regis.jsp" method="post"
		onsubmit="return fncSubmit();">


		<!-- 			<p:messages showDetail="true" /> -->
		<div class="login-box">
			<div class="login-box-body"
				style="border: 2px solid; border-color: #0a4924;">
				<h2 style="text-align: center; size: 20px; color: red;">
					สมัครเป็นสมาชิก <br> <a href="index.jsf" style="color: red;">Major
						Arena</a>
				</h2>

				<br>

				<!-- 					<div class="panel-heading"> -->
				<!-- 						<h3 class="panel-title" style="color: red; text-align: center">:: -->
				<!-- 							ข้อมูลเข้าสู่ระบบ ::</h3> -->
				<!-- 					</div> -->

				<div class="panel-body">

					<div>
						<div>
							<label>ชื่อผู้เข้าใช้ระบบ : <span style="color: red;">*</span>
							</label>
						</div>
						<div class="form-group has-feedback">
							<input type="text" id="username" required autofocus
								name="user_name" class="form-control" value=""
								placeholder="กรุณากรอกชื่อผู้ใช้" /> <span
								class="fa fa-user form-control-feedback"></span>
						</div>
					</div>

					<div>

						<div>
							<label>รหัสผ่าน : <span style="color: red;">*</span>
							</label>
						</div>
						<div class="form-group has-feedback">

							<input type="password" class="form-control" name="user_pass"
								id="txtPassword" placeholder="กรอกรหัสผ่าน" required autofocus><span
								class="fa fa-lock form-control-feedback"></span>
						</div>
						<div>
							<label>ยืนยันรหัสผ่าน : <span style="color: red;">*</span>
							</label>
						</div>
						<div class="form-group has-feedback">
							<input type="password" class="form-control" name="user_pass"
								id="txtConPassword" placeholder="กรอกรหัสผ่านอีกครั้ง" required
								autofocus><span class="fa fa-lock form-control-feedback"></span>
						</div>
					</div>
					<div>
						<label>ชื่อ : <span style="color: red;">*</span></label>
					</div>
					<div class="form-group has-feedback">
						<input type="text" name="name" id="name" required autofocus
							class="form-control" value="" placeholder="กรุณากรอกชื่อ" />
					</div>

					<div>
						<div>
							<label>นามสกุล : <span style="color: red;">*</span></label>
						</div>
						<div class="form-group has-feedback">
							<input type="text" id="lastname" required autofocus
								name="lastname" class="form-control" value=""
								placeholder="กรุณากรอกนามสกุล" />

						</div>
					</div>
					<div>

						<label>รูปประจำตัว : </label>

						<div class="form-group has-feedback">
							<input type="file" pattern="/(\.|\/)(gif|jpe?g|png)$/" name="pic" name="filUpload" id="pic" value="" />
						</div>
					</div>

					<div>
						<div>
							<label>E-Mail : <span style="color: red;">*</span></label>
						</div>
						<div class="form-group has-feedback">
							<input required autofocus type="text" id="email" name="email"
								class="form-control" value="" placeholder="กรุณากรอก E-Mail"
								pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$" />


						</div>
					</div>
					<div>

						<label>ที่อยุ่ : <span style="color: red;"></span></label>

						<div class="form-group has-feedback">
							<input type="text" id="address" name="address"
								class="form-control" placeholder="กรุณากรอกที่อยู่">

						</div>
					</div>

					<div>
						<div>
							<label>เบอร์โทรศัพท์ : <span style="color: red;">*</span></label>
						</div>
						<div class="form-group has-feedback">
							<input required autofocus type="text" id="phone" name="phone"
								class="form-control" value=""
								placeholder="กรุณากรอกเบอร์โทรศัพท์" />
						</div>
					</div>

					<div class="row">
						<input name="submit" value="สมัครสมาชิก" type="submit"
							class="btn btn-block btn-success btn-nm" /> <a href="index.jsf"
							type="reset" class="btn btn-block btn-danger btn-nm">ผู้ที่มีรหัสผ่าน
							หรือ เข้าสู่ระบบ</a>

					</div>
				</div>
			</div>
		</div>
	</form>

	<script>
		$(document).ready(function() {
			$(".nav-tabs a").click(function() {
				$(this).tab('show');
			});
		});
	</script>




</body>
</html>
