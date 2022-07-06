<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 쓰기</title>
</head>
<body>
<form action="write.do" method="post">
	<p>
						제목:<br />
						<input type="text" name="title" value="1">
						<c:if test="${errors.title}">제목을 입력하세요.</c:if>
					</p>
					
					<p>
						카테고리:<br />
						<textarea name="category" rows="5" cols="30" value="1">${param.category}</textarea>
					</p>
					
					<p>
						주소:<br />
						<textarea name="address" rows="5" cols="30" value="1">${param.address}</textarea>
					</p>
					
					<p>
						넓이:<br />
						<textarea name="area" rows="5" cols="30" value="1">${param.area}</textarea>
					</p>
					
					<p>
						시작일:<br />
						<textarea name="startDate" rows="5" cols="30" value="2022-02-02">${param.startDate}</textarea>
					</p>
					
					<p>
						마감일:<br />
						<textarea name="endDate" rows="5" cols="30" value="2022-02-02">${param.endDate}</textarea>
					</p>
					
					<p>
						예산:<br />
						<textarea name="budget" rows="5" cols="30" value="1">${param.budget}</textarea>
					</p>
					
					<p>
						분야:<br />
						<textarea name="part" rows="5" cols="30" value="1">${param.part}</textarea>
					</p>
					
					<p>
						요구사항:<br />
						<textarea name="require" rows="5" cols="30" value="1">${param.require}</textarea>
					</p>
					<p>
						이미지:<br />
						<textarea name="image" rows="5" cols="30" value="1">${param.image}</textarea>
					</p>
					
					
					<input type="submit" value="새 글 등록">
</form>
</body>
</html>