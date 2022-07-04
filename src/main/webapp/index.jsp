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
                    <div class="col-sm-6 col-xl-6">
                        <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                            <h1 class="text-primary">Risk Ontology</h1>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Sale & Revenue End -->

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h3 class="mb-0">Idea</h3>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal">
                                RiskOntology was born with the idea of showing information relating to what may be the possible risks to be encountered during the Risk Management phase present in the Software development life cycle.
                                There are many conditions that lead us to encounter risks, they can derive from the type of project, the technologies used for implementation, the number of Stakeholders and their skills, and much more.
                                Not all Project Managers are able to manage and identify all the risks, what are the causes, the alarm bells to be taken into consideration and how to prevent and / or mitigate the risks.
                                To help with this, RiskOntology provides the information necessary to better understand Risks, causes and methodologies.
                            </p>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h3 class="mb-0">Motivation</h3>
                    </div>
                    <div class="table-responsive">
                        <div class="table text-start align-middle table-bordered table-hover mb-0">
                            <p style="overflow-wrap: normal">
                                The practice of Risk Management is one of the most delicate and important in the software development life cycle, but not all Project Managers are able to carry it out.
                                Particularly in Agile projects, where documentation is not given enough importance, a lot of information is lost, and past experiences are not passed down.
                                This lack of examples and information has led us to the realization of Risk Ontology, thanks to which Project Managers can inform themselves about good practices for good risk management and can inform themselves about other projects and conditions and risks encountered in them.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Recent Sales End -->


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