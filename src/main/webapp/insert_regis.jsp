
<%@page import="java.sql.*"  import="java.security.MessageDigest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insert_regis</title>


</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost/major_db", "root", "tong1234");

		try {

			Statement stmt = conn2.createStatement();
			// 						stmtOne = conn2.prepareStatement(sqlAddNewMember, PreparedStatement.RETURN_GENERATED_KEYS);

			String user_name = request.getParameter("user_name");
			String user_pass = request.getParameter("user_pass");

			String password = request.getParameter("user_pass");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sbd = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sbd.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sbd.toString();

			// 			String user_full_name = request.getParameter("user_full_name");
			String name = request.getParameter("name");
			String lastname = request.getParameter("lastname");
			String pic = request.getParameter("pic");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			// 			String user_name = request.getParameter("user_name");
			String role_name = request.getParameter("role_name");
			role_name = "accounting";
			String full_name = request.getParameter("user_full_name");
			full_name = "accounting";
			String sqlQuery = "select user_name from member_users where user_name='" + user_name + "'";
			ResultSet rs = stmt.executeQuery(sqlQuery);
			int count = 0;

			while (rs.next()) {
				count++;
			}

			if (count > 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('ชื่อผู้ใช้งานซ่ำค่ะ!!');");
				out.println("location='frm_regis.jsp';");
				out.println("</script>");
			} else if (user_name != null) {

				if (!user_name.equals("")) {

					conn2.setAutoCommit(false);
					//st.executeUpdate("insert into usernameexist(username) values('"+username+"')");
					String sql = "INSERT INTO member_users(user_name,user_pass,user_full_name,name,lastname,pic,email,address,phone)"
							+ "value('" + user_name + "' , '" + password + "' ,'" + full_name + "' , '" + name
							+ "' ,'" + lastname + "' , '" + pic + "' , '" + email + "' , '" + address + "' ,'"
							+ phone + "'  )";

					String stmts = "INSERT INTO member_user_roles(user_name,role_name)" + "value('" + user_name
							+ "' , '" + role_name + "' )";

					if (stmt.executeUpdate(sql) != -1) {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('บันทึกข้อมูลเรียบร้อยค่ะ');");
						out.println("location='login.html';");
						out.println("</script>");
					} else {
						out.println("บักทึกข้อมูลไม่สำเร็จ");
					}

					if (stmt.executeUpdate(stmts) != -1) {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('บันทึกข้อมูลเรียบร้อยค่ะ');");
						out.println("location='login.html';");
						out.println("</script>");
					} else {
						out.println("บักทึกข้อมูลไม่สำเร็จ");
					}

				}
			}

		} catch (Exception e) {
			out.println("Error while register : " + e.getMessage());
		}
	%>

</body>
</html>
