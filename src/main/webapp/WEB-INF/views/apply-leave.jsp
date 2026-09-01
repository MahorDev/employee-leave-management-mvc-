<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Apply Leave</title>

    <!-- Bootstrap 5 CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body class="bg-light">

<div class="container py-5">

    <div class="row justify-content-center">

        <div class="col-md-7 col-lg-6">

            <div class="card shadow-sm">


                <!-- Header -->

                <div class="card-header bg-primary text-white">

                    <h4 class="mb-0">
                        Apply Leave
                    </h4>

                </div>


                <!-- Form Body -->

                <div class="card-body p-4">


                    <!-- Error Message -->

                    <c:if test="${not empty error}">

                        <div class="alert alert-danger"
                             role="alert">

                            ${error}

                        </div>

                    </c:if>


                    <!-- Leave Form -->

                    <form
                            action="${pageContext.request.contextPath}/apply-leave"
                            method="post">


                        <!-- Leave Type -->

                        <div class="mb-3">

                            <label
                                    for="leaveType"
                                    class="form-label">

                                Leave Type

                            </label>

                            <select
                                    name="leaveType"
                                    id="leaveType"
                                    class="form-select"
                                    required>

                                <option value="">
                                    Select Leave Type
                                </option>

                                <option value="CASUAL">
                                    Casual
                                </option>

                                <option value="SICK">
                                    Sick
                                </option>

                                <option value="EARNED">
                                    Earned
                                </option>

                            </select>

                        </div>


                        <!-- From Date -->

                        <div class="mb-3">

                            <label
                                    for="fromDate"
                                    class="form-label">

                                From Date

                            </label>

                            <input
                                    type="date"
                                    name="fromDate"
                                    id="fromDate"
                                    class="form-control"
                                    required>

                        </div>


                        <!-- To Date -->

                        <div class="mb-3">

                            <label
                                    for="toDate"
                                    class="form-label">

                                To Date

                            </label>

                            <input
                                    type="date"
                                    name="toDate"
                                    id="toDate"
                                    class="form-control"
                                    required>

                        </div>


                        <!-- Reason -->

                        <div class="mb-4">

                            <label
                                    for="reason"
                                    class="form-label">

                                Reason

                            </label>

                            <textarea
                                    name="reason"
                                    id="reason"
                                    class="form-control"
                                    rows="5"
                                    placeholder="Enter reason for leave"
                                    required></textarea>

                        </div>


                        <!-- Buttons -->

                        <div class="d-flex gap-2">

                            <button
                                    type="submit"
                                    class="btn btn-primary">

                                Submit Leave

                            </button>


                            <a
                                    href="${pageContext.request.contextPath}/dashboard"
                                    class="btn btn-outline-secondary">

                                Back to Dashboard

                            </a>

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