<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Error</title>

    <!-- Bootstrap 5 CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">

    <div class="row justify-content-center align-items-center"
         style="min-height: 100vh;">

        <div class="col-md-6 col-lg-5">

            <div class="card shadow-sm">

                <div class="card-body text-center p-5">

                    <h2 class="fw-bold mb-3">
                        Something went wrong
                    </h2>

                    <div class="alert alert-danger mb-4" role="alert">

                        <strong>Error:</strong>

                        <div class="mt-2">
                            ${error}
                        </div>

                    </div>

                    <a href="${pageContext.request.contextPath}/login"
                       class="btn btn-primary">

                        Back to Login

                    </a>

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
```
