<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container2">
		<div class = "profile-box">
			<div class = "profile-item">
				<div class = "profile-imagebox">
					<img class="profile-image" src="/upload/${dto.user.profileImageUrl}"
						onerror="this.src='/images/person.jpeg'" id="userProfileImage" />
				</div>
				<div clss = "profile-detail">
					<div class="profile-detail-item">
						user : 
					</div>
					<div class="profile-detail-item">
						email : 
					</div>
					<div class="profile-detail-item">
						bio : 
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
</body>
</html>
