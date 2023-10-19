<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript">
	function check() {
		window.history.back();
	}
</script>

<style>
    body {
      min-height: 100vh;

      background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
      background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
    }

    .input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
  </style>
</head>

<body>
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">회원정보</h4>
        <form class="validation-form" novalidate>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="name">ID</label>
              <input type="text" class="form-control" id="username" placeholder="" value="${users.user.username}" readonly="readonly">
            </div>
            <div class="col-md-6 mb-3">
              <label for="전화번호">Phone</label>
              <input type="text" class="form-control" id="tel" placeholder="" value="${users.user.tel}" readonly="readonly">
            </div>
          </div>
          <div class="mb-3">
            <label for="address">활동지역</label>
            <input type="text" class="form-control" id="address" value="${users.user.userSigungu}"  readonly="readonly">
          </div>
          <hr class="mb-4">
          <div class="mb-4"></div>
        </form>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="button" onclick="check()">확인</button>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2023 team02</p>
    </footer>
  </div>
<!--   <script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script> -->
</body>

</html>