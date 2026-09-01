<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>${appName}</title>

    <!-- Bootstrap 5 CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body class="bg-light">

<!-- Navigation Bar -->

<nav class="navbar navbar-dark bg-primary shadow-sm">

    <div class="container">

        <span class="navbar-brand mb-0 h1">
            ${appName}
        </span>

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-outline-light btn-sm">
            Logout
        </a>

    </div>

</nav>


<div class="container py-5">

    <!-- Application Information -->

    <div class="mb-4">

        <h1 class="fw-bold">
            Dashboard
        </h1>

        <p class="text-muted mb-0">
            Application Version: ${appVersion}
        </p>

    </div>


    <!-- Employee Information -->

    <div class="card shadow-sm mb-4">

        <div class="card-header bg-white">

            <h5 class="mb-0">
                Employee Information
            </h5>

        </div>

        <div class="card-body">

            <div class="row">

                <div class="col-md-4 mb-3">

                    <small class="text-muted">
                        Employee ID
                    </small>

                    <h5>
                        ${employee.employeeId}
                    </h5>

                </div>


                <div class="col-md-4 mb-3">

                    <small class="text-muted">
                        Employee Name
                    </small>

                    <h5>
                        ${employee.name}
                    </h5>

                </div>


                <div class="col-md-4 mb-3">

                    <small class="text-muted">
                        Department
                    </small>

                    <h5>
                        ${employee.department}
                    </h5>

                </div>

            </div>

        </div>

    </div>


    <!-- Leave Summary -->

    <div class="row g-4 mb-4">

        <!-- Leave Balance -->

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Available Leave Balance
                    </h6>

                    <h2 class="fw-bold text-primary">
                        ${employee.leaveBalance}
                    </h2>

                </div>

            </div>

        </div>


        <!-- Pending Requests -->

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Pending Requests
                    </h6>

                    <h2 class="fw-bold text-warning">
                        ${pendingCount}
                    </h2>

                </div>

            </div>

        </div>


        <!-- Approved Requests -->

        <div class="col-md-4">

            <div class="card shadow-sm h-100">

                <div class="card-body">

                    <h6 class="text-muted">
                        Approved Requests
                    </h6>

                    <h2 class="fw-bold text-success">
                        ${approvedCount}
                    </h2>

                </div>

            </div>

        </div>

    </div>


    <!-- Dashboard Preference -->

    <div class="card shadow-sm mb-4">

        <div class="card-body">

            <h5 class="card-title">
                Dashboard Preference
            </h5>

            <p class="text-muted">
                Choose how you want to view your dashboard.
            </p>


            <form
                    action="${pageContext.request.contextPath}/preference"
                    method="post"
                    class="row g-3 align-items-end">

                <div class="col-md-6">

                    <label for="view"
                           class="form-label">
                        View
                    </label>

                    <select
                            name="view"
                            id="view"
                            class="form-select">

                        <option
                                value="summary"
                                ${viewPreference == 'summary' ? 'selected' : ''}>
                            Summary
                        </option>

                        <option
                                value="detailed"
                                ${viewPreference == 'detailed' ? 'selected' : ''}>
                            Detailed
                        </option>

                    </select>

                </div>


                <div class="col-md-auto">

                    <button
                            type="submit"
                            class="btn btn-primary">

                        Save Preference

                    </button>

                </div>

            </form>

        </div>

    </div>


    <!-- Detailed View -->

    <c:if test="${viewPreference == 'detailed'}">

        <div class="card shadow-sm border-info mb-4">

            <div class="card-header bg-info-subtle">

                <h5 class="mb-0">
                    Detailed View
                </h5>

            </div>

            <div class="card-body">

                <p>
                    This preference is stored in a
                    non-sensitive browser cookie.
                </p>

                <div class="row">

                    <div class="col-md-6">

                        <div class="alert alert-warning mb-0">

                            <strong>Pending Requests:</strong>
                            ${pendingCount}

                        </div>

                    </div>

                    <div class="col-md-6">

                        <div class="alert alert-success mb-0">

                            <strong>Approved Requests:</strong>
                            ${approvedCount}

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </c:if>


    <!-- Dashboard Actions -->

    <div class="d-flex gap-2 flex-wrap">

        <a href="${pageContext.request.contextPath}/apply-leave"
           class="btn btn-primary">

            Apply Leave

        </a>


        <a href="${pageContext.request.contextPath}/my-leaves"
           class="btn btn-outline-primary">

            My Leave Requests

        </a>


        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-outline-danger">

            Logout

        </a>

    </div>

</div>


<!-- Bootstrap 5 JS -->

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>

</html>
```
