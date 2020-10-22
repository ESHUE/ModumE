<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="StringUtils" class="com.amolrang.modume.utils.StringUtils"/>

<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
<form name="f" action="/loginAction" method="POST">
        <table>
            <tbody>
                <tr>
                    <td>User:</td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><input name="submit" type="submit" value="Login"></td>
                </tr>
                <input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}">
            </tbody>
        </table>
    </form>
</body>
</html>