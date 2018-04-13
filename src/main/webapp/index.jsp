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

<nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" onclick="findAll()">Developer skills</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <div class="col-sm-3 col-md-3">
            <form class="navbar-form navbar-right" role="search" id="search-form">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" id="search-criteria"
                           name="search-criteria">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="validator-message error"></div>
    </div>
</nav>

<div class="container">
    <div id="feedback" class="col-lg-8"></div>
</div>

<div class="container">
    <h2>Elements</h2>
    <div class="just-padding">
        <div class='header list-group'>
            <i class='glyphicon glyphicon-chevron-right nesting'></i>
            <span class='identity' name="Root">Root</span>
            <span class='pull-right'>
                <span class='btn btn-xs btn-primary' onclick="renderInsertDialog(this); event.stopPropagation();">
                    <span class='glyphicon glyphicon-circle-arrow-down' aria-hidden='true'></span>
                </span>
            </span>
        </div>
        <div class='content list-group-item'>
            <div class="result_list">
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modalLabel"></h4>
            </div>
            <div class="modal-body">
                <form role="form" id="modal-form">
                    <div class="form-group">
                        <label for="current-name" class="control-label"></label>
                        <span type="text" class="form-control identified" id="current-name"></span>
                    </div>
                    <div class="form-group">
                        <label for="updated-name" class="control-label"></label>
                        <input type="text" class="form-control updated" id="updated-name" name="updated">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitDialog(this)">Apply</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="event-modal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <p class="modal-message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<script src="${pageContext.request.contextPath}/resources/scripts/jQuery3.3.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/event_handler.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/search-validator.js"></script>
</body>
</html>