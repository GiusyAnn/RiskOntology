<%@ page import="model.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>RiskOntology</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<%
    ProjectBean project = (ProjectBean) request.getAttribute("project");
    ArrayList<ModSvilBean> tm = (ArrayList<ModSvilBean>) request.getAttribute("tm");
    ArrayList<RiskBean> list = (ArrayList<RiskBean>) request.getAttribute("table");

%>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <%@ include file="sidebar.jsp"%>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">

            <!-- Navbar Start -->
            <%@include file="navbar.jsp"%>
            <!-- Navbar End -->



            <!-- Sale & Revenue Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-sm-6 col-xl-8">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <h1 class="text-primary"><%=project.getNome()%></h1>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sale & Revenue End -->

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h2 class="mb-0">Description</h2>
                    </div>
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h4 class="mb-0"><%=project.getSoftware()%></h4>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=project.getDescriprion()%></p>
                        </div>
                    </div>
                    <br>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <form action="Artifacts" method="POST">
                            <button style="border: none; background: none"><h4 class="mb-0">Artifacts</h4></button>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <ul>
                                <%for(int i=0; i<project.getDocument().size(); i++){%>
                                <li>
                                    <%=project.getDocument().get(i)%>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <form action="ModelliSviluppo" method="POST">
                            <button  style="border: none; background: none"><h4 class="mb-0">Development's Model</h4></button>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=project.getModSviluppo()%></p>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h4 class="mb-0">Stakeholder</h4>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">Project Manager</h6>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=project.getPm()%></p>
                            <p style="overflow-wrap: normal"><%=tm.get(0).getDescrizione()%></p>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">Team Member : </h6>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <ul>
                                <%for(int i=1; i<tm.size(); i++){%>
                                <li>
                                    <%=tm.get(i).getNome()%>
                                </li>
                                <%}%>
                            </ul>
                            <p style="overflow-wrap: normal"><%=tm.get(1).getDescrizione()%></p>
                        </div>
                    </div>

                </div>
            </div>
            <!-- Recent Sales End -->

            <!-- Risk Table -->
            <div class="container-fluid pt-4 px-4">
                <div class="col-12">
                    <div class="bg-light rounded h-100 p-4">
                        <h6 class="mb-4">Risk Register <%=project.getNome()%></h6>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Root Cause</th>
                                    <th scope="col">Trigger</th>
                                    <th scope="col">Prevention Plan</th>
                                    <th scope="col">Contingency Plan</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%for(int i=1; i<list.size(); i++){ %>
                                <form action="Description" method="POST">
                                    <tr>
                                        <th scope="row" style="align-content: center"><%=i%></th>
                                        <td><button style="border: none; background: none" name="value" value="<%=list.get(i).getNome()%>"><%=list.get(i).getNome()%></button></td>
                                        <td><button style="border: none; background: none" name="value" value="<%=list.get(i).getCausa()%>"><%=list.get(i).getCausa()%></button></td>
                                        <td><button style="border: none; background: none" name="value" value="<%=list.get(i).getTrigger()%>"><%=list.get(i).getTrigger()%></button></td>
                                        <td><%=list.get(i).getPrevenzione()%></td>
                                        <td><%=list.get(i).getMitigazione()%></td>
                                    </tr>
                                </form>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Table -->

            <%@include file="footer.jsp"%>

        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>