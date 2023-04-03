<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container2">
		<c:forEach var="users" items="${users}">
			<div class = "profile-box">
				<div class = "profile-item">
					<div class = "profile-imagebox">
						<img class="profile-image" src="/upload/${users.user.profileImageUrl}"
							onerror="this.src='/images/person.jpeg'" id="userProfileImage" />
					</div>
						<div class = "profile-detail">
							<div class="profile-detail-item">
								<p>user : ${users.user.username}</p>
							</div>
							<div class="profile-detail-item">
								<p>email : ${users.user.email}</p>
							</div>
							<div class="profile-detail-item">
								<p>bio : ${users.user.bio}</p>
							</div>
						</div>
					<c:choose>
						<c:when test="${users.pageOwnerState}">
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${users.subscribeState}">
									<button class="cta blue" onclick="toggleSubscribe(${users.user.id},this)">구독취소</button>
								</c:when>
								<c:otherwise>
									<button class="cta" onclick="toggleSubscribe(${users.user.id},this)">구독하기</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</section>
</main>

<script src="/js/search.js"></script>
<%@ include file="../layout/footer.jsp"%>
