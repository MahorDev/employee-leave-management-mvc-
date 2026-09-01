<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>My Leave Requests</title>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body class="bg-light">

<div class="container py-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>

            <h1 class="fw-bold mb-1">
                My Leave Requests
            </h1>

            <p class="text-muted mb-0">
                View your submitted leave requests
            </p>

        </div>

        <a
                href="${pageContext.request.contextPath}/dashboard"
                class="btn btn-outline-secondary">

            Back to Dashboard

        </a>

    </div>


    <c:choose>

        <c:when test="${empty requests}">

            <div class="card shadow-sm">

                <div class="card-body text-center py-5">

                    <h4 class="mb-3">
                        No Leave Requests Found
                    </h4>

                    <p class="text-muted">
                        You have not submitted any leave requests yet.
                    </p>

                    <a
                            href="${pageContext.request.contextPath}/apply-leave"
                            class="btn btn-primary">

                        Apply Leave

                    </a>

                </div>

            </div>

        </c:when>

        <c:otherwise>

            <div class="card shadow-sm">

                <div class="card-body">

                    <div class="table-responsive">

                        <table
                                class="table table-hover table-bordered align-middle mb-0">

                            <thead class="table-dark">

                            <tr>

                                <th>Request ID</th>
                                <th>Leave Type</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Days</th>
                                <th>Reason</th>
                                <th>Status</th>
                                <th>Created Date</th>

                            </tr>

                            </thead>

                            <tbody>

                            <c:forEach
                                    var="request"
                                    items="${requests}">

                                <tr>

                                    <td>
                                        ${request.id}
                                    </td>

                                    <td>
                                        ${request.leaveType}
                                    </td>

                                    <td>
                                        ${request.fromDate}
                                    </td>

                                    <td>
                                        ${request.toDate}
                                    </td>

                                    <td>
                                        <span class="badge text-bg-secondary">
                                            ${request.numberOfDays}
                                        </span>
                                    </td>

                                    <td>
                                        ${request.reason}
                                    </td>

                                    <td>

                                        <c:choose>

                                            <c:when test="${request.status == 'APPROVED'}">
                                                <span class="badge text-bg-success">
                                                    ${request.status}
                                                </span>
                                            </c:when>

                                            <c:when test="${request.status == 'REJECTED'}">
                                                <span class="badge text-bg-danger">
                                                    ${request.status}
                                                </span>
                                            </c:when>

                                            <c:otherwise>
                                                <span class="badge text-bg-warning">
                                                    ${request.status}
                                                </span>
                                            </c:otherwise>

                                        </c:choose>

                                    </td>

                                    <td>
                                        ${request.createdDate}
                                    </td>

                                </tr>

                            </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

            <div class="mt-4">

                <a
                        href="${pageContext.request.contextPath}/apply-leave"
                        class="btn btn-primary">

                    Apply New Leave

                </a>

            </div>

        </c:otherwise>

    </c:choose>

</div>


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>

</html>

