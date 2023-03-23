<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container2">
		<article class="story-list" id="storyList">
			<div class="member-box">
				<div class="profile-left">
					<div class="profile-img-wrap story-border">
						<img class="profile-image" src="/images/person.jpeg" onerror="this.src='/images/person.jpeg'" id="userProfileImage">
					</div>
				</div>
				
				<div class="profile-right">
					<div class="profile-detail">
						<div>이름</div>
					</div>
					<div class="profile-detail">
						<div>이메일</div>
					</div>
					<div class="profile-detail">
						<div>소개</div>
					</div>
				</div>
			</div>
		</article>
	</section>
</main>
</body>
</html>
