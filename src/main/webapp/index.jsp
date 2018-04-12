<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Developer skills</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/style.css"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <form class="form-row" id="search-form">

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="search-criteria" placeholder="Search">
                </div>

                <button type="submit" id="btn-search" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col-lg-3">
            <form class="form-row" id="update-form">

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="current-name" placeholder="Current name">
                </div>

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="updated-name" placeholder="Updated name">
                </div>

                <button type="submit" id="btn-update" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <div class="col-lg-3">
            <form class="form-row" id="create-form">

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="parent-name" placeholder="Parent name">
                </div>

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="child-name" placeholder="Child name">
                </div>

                <button type="submit" id="btn-create" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <div class="col-lg-3">
            <form class="form-row" id="delete-form">

                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">@</div>
                    <input type="text" class="form-control" id="delete-name" placeholder="Node name">
                </div>

                <button type="submit" id="btn-delete" class="btn btn-primary">Submit</button>
            </form>
        </div>


    </div>


    <div id="feedback" class="col-lg-8"></div>

</div>

<div class="container">
    <h2>Elements</h2>
    <div class="just-padding">
        <div class="result_list">
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/resources/scripts/jQuery3.3.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/event_handler.js"></script>
</body>
</html>