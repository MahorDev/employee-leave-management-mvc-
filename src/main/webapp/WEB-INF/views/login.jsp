<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Login</title>

    <!-- Bootstrap 5 CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">

    <div class="row justify-content-center align-items-center"
         style="min-height: 100vh;">

        <div class="col-md-5 col-lg-4">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h2 class="text-center fw-bold mb-2">
                        Employee Leave Management
                    </h2>

                    <h5 class="text-center text-muted mb-4">
                        Login
                    </h5>


                    <!-- Error Message -->

                    <c:if test="${not empty error}">

                        <div class="alert alert-danger"
                             role="alert">

                            ${error}

                        </div>

                    </c:if>


                    <!-- Login Form -->

                    <form
                            action="${pageContext.request.contextPath}/login"
                            method="post">


                        <!-- Employee ID -->

                        <div class="mb-3">

                            <label
                                    for="employeeId"
                                    class="form-label">

                                Employee ID

                            </label>

                            <input
                                    type="text"
                                    class="form-control"
                                    id="employeeId"
                                    name="employeeId"
                                    placeholder="Enter Employee ID"
                                    required>

                        </div>


                        <!-- Password -->

                        <div class="mb-4">

                            <label
                                    for="password"
                                    class="form-label">

                                Password

                            </label>

                            <input
                                    type="password"
                                    class="form-control"
                                    id="password"
                                    name="password"
                                    placeholder="Enter Password"
                                    required>

                        </div>


                        <!-- Login Button -->

                        <div class="d-grid">

                            <button
                                    type="submit"
                                    class="btn btn-primary">

                                Login

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</div>


<!-- Bootstrap 5 JS -->

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>

</html>