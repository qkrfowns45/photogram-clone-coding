<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container2">
		<c:forEach var="users" items="${users}">
			<div class = "profile-box">
				<div class = "profile-item">
					<div class = "profile-imagebox">
						<img class="profile-image" src="/upload/${dto.user.profileImageUrl}"
							onerror="this.src='/images/person.jpeg'" id="userProfileImage" />
					</div>
						<div class = "profile-detail">
							<div class="profile-detail-item">
								user : 
								<h4>${users.username}</h4>
							</div>
							<div class="profile-detail-item">
								email : 
								<h4> ${users.email}</h4>
							</div>
							<div class="profile-detail-item">
								bio : 
								<h4> ${users.bio}</h4>
							</div>
						</div>
					<div class="profile-subscribe">
						<button class="cta blue" onclick="toggleSubscribe(${u.id},this)">구독하기</button>
					</div>
				</div>
			</div>
		</c:forEach>
	</section>
</main>
</body>
</html>
