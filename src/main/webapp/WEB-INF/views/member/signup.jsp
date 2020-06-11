<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<html>
<head>
	<title>signup</title>
</head>
<body>

<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file ="../include/header.jsp" %>
		</div>
	</header>
	<nav id="nav">
		<div id="nav_box">
			<%@ include file ="../include/nav.jsp" %>
		</div>
	</nav>
	
	
	<section id="content">
		 <form role="form" method="post" autocomplete="off">
			  <div class="input_area">
			  	 <label for="userId">아이디</label>
				  <input type="email" id="userId" name="userId" placeholder="example@email.com" required="required" />
				  <a type ="button"  onClick="checkDupId()" >중복확인</a>    
				  <input type="hidden" id="checkDupId" name="checkDupId" value="0">
			  </div>
			  
			  <div class="input_area">
				   <label for="userPwd">패스워드</label>
				   <input type="password" id="userPwd" name="userPwd" required="required" />      
			  </div>
			  
			  <div class="input_area">
				   <label for="userName">닉네임</label>
				   <input type="text" id="userName" name="userName" placeholder="닉네임을 입력해주세요" required="required" />      
			  </div>
			  
			  <div class="input_area">
				   <label for="userPhone">연락처</label>
				   <input type="text" id="userPhone" name="userPhone" placeholder="연락처를 입력해주세요" required="required" />      
			  </div>
			  
			  <button type="submit" id="signup_btn" name="signup_btn" >회원가입</button>
			  
		 </form>   
	</section>
	
	
	
	<footer id="footer">
		<div id="footer_box">
			<%@ include file ="../include/footer.jsp" %>
		</div>
	</footer>

<script>

function checkDupId(){
	if($("#userId").val() == ""){
		alert("아이디를 입력해주세요");
		$("#userId").focus();
		return;
	}
	$.ajax({
		url : "/member/checkDupId",
		type : "GET",
		data : {
			userId : $("#userId").val()
		},
		dataType : "text",
		success : function(data){
			
			$("#userId").val(data);
			if($("#userId").val().length == 0){
				alert("아이디 중복입니다. 다시입력해주세요.");
				$("#userId").focus();
			}else{
				alert("사용가능한 아이딥니다.!");
				$("#checkDupId").val("1");
				$("#userPwd").focus();
			}
		}
	});
}
</script>
</div>
</body>
</html>
