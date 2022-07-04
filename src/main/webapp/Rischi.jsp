<%@ page import="model.dao.RiskOntologyDAO" %>
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
    RiskBean rischi = (RiskBean) request.getAttribute("descrizione");
    ArrayList<RiskBean> list = (ArrayList<RiskBean>) request.getAttribute("table");

    for(RiskBean r : list){if(r.getNome().equals(""))r.setNome("Estimation");}

    String check = (String) request.getAttribute("result");
    String sub = (String) request.getAttribute("sub");
    String obj = (String) request.getAttribute("obj");
    String pred = (String) request.getAttribute("pred");
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
                            <h1 class="text-primary">Risks</h1>
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
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=rischi.getNome()%></p>
                        </div>
                    </div>
                    <br>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h4 class="mb-0">Root cause</h4>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=rischi.getCausa()%></p>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h4 class="mb-0">Plan</h4>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=rischi.getTrigger()%></p>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">Prevention Plan</h6>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=rischi.getPrevenzione()%></p>
                        </div>
                    </div>

                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">Contingency Plan</h6>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal"><%=rischi.getMitigazione()%></p>
                        </div>
                    </div>

                </div>
            </div>
            <!-- Recent Sales End -->

            <!-- Risk Table -->
            <div class="container-fluid pt-4 px-4">
                <div class="col-12">
                <div class="bg-light rounded h-100 p-4">
                    <h6 class="mb-4">Risk Table</h6>
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


            <!-- Form -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-sm-12 col-xl-6">
                        <form action="Check" method="POST">
                            <div class="bg-light rounded h-100 p-4">
                                <h6 class="mb-4">Risk Check</h6>
                                <p style="overflow-wrap: normal">it is possible to insert a risk, a possible cause of origin or a possible warning about it, and find out if the combinations made are legitimate or not.</p>
                                <select class="form-select form-select-sm mb-3" aria-label=".form-select-sm example" name="sub">
                                    <option selected>None</option>
                                    <option value="Communication">Communication</option>
                                    <option value="Estimation">Estimation</option>
                                    <option value="Participation">Participation</option>
                                    <option value="Quality_management">Quality_management</option>
                                    <option value="Requirement">Requirement</option>
                                    <option value="Retardation">Retardation</option>
                                </select>
                                <select class="form-select form-select-sm mb-3" aria-label=".form-select-sm example" name="pred">
                                    <option selected>None</option>
                                    <option value="cause">Cause</option>
                                    <option value="discovered">Discovered</option>
                                </select>
                                <select class="form-select form-select-sm mb-3" aria-label=".form-select-sm example" name="obj">
                                    <option selected>None</option>
                                    <option value="Domain">Domain</option>
                                    <option value="Experience">Experience</option>
                                    <option value="interest">interest</option>
                                    <option value="Personality">Personality</option>
                                    <option value="Social_skills">Social_skills</option>
                                    <option value="Soft_skills">Soft_skills</option>
                                </select>
                                <button type="submit" class="btn btn-primary">Check</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- End Form-->


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