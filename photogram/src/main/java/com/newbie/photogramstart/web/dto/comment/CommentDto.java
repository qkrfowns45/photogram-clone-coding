package com.newbie.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

//NotNull = Null값 체크
//@NotBlank = 빈값이거나 null 체크 그리고 빈 공백
//@NotEmpty = 빈값이거나 null 체크
@Data
public class CommentDto {
	@NotBlank//빈값이거나 null 체크 그리고 빈 공백
	private String content;
	
	@NotNull //빈값이거나 null 체크
	private Integer imageId;

}
