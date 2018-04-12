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
<h2 class="text-capitalize">hell</h2>

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
    <div class="container">
        <div class="row">
            <div id="feedback" class="col-lg-8"></div>
        </div>
    </div>
</div>

<div class="container">
    <div class="result_list">

    </div>

    <div class="just-padding">

        <div class="list-group list-group-root well">
            <div>
                <a href="#item-1" class="list-group-item" data-toggle="collapse">
                    <i class="glyphicon glyphicon-chevron-right"></i>Item 1
                </a>
                <div class="list-group collapse" id="item-1">

                    <a href="#item-1-1" class="list-group-item" data-toggle="collapse">
                        <i class="glyphicon glyphicon-chevron-right"></i>Item 1.1
                    </a>
                    <div class="list-group collapse" id="item-1-1">
                        <a href="#" class="list-group-item">Item 1.1.1</a>
                        <a href="#" class="list-group-item">Item 1.1.2</a>
                        <a href="#" class="list-group-item">Item 1.1.3</a>
                    </div>

                    <a href="#item-1-2" class="list-group-item" data-toggle="collapse">
                        <i class="glyphicon glyphicon-chevron-right"></i>Item 1.2
                        <span class="pull-right">
                        <span class="btn btn-xs btn-default"
                              onclick="alert('Action2 -> Update'); event.stopPropagation();">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        </span>
                        <span class="btn btn-xs btn-default"
                              onclick="alert('Action2 -> Play'); event.stopPropagation();">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </span>
                        <span class="btn btn-xs btn-default"
                              onclick="alert('Action2 -> Delete'); event.stopPropagation();">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </span>
                    </span>
                    </a>
                    <div class="list-group collapse" id="item-1-2">
                        <a href="#" class="list-group-item">Item 1.2.1</a>
                        <a href="#" class="list-group-item">Item 1.2.2</a>
                        <a href="#" class="list-group-item">Item 1.2.3</a>
                    </div>

                    <a href="#item-1-3" class="list-group-item" data-toggle="collapse">
                        <i class="glyphicon glyphicon-chevron-right"></i>Item 1.3
                    </a>
                    <div class="list-group collapse" id="item-1-3">
                        <a href="#" class="list-group-item">Item 1.3.1</a>
                        <a href="#" class="list-group-item">Item 1.3.2</a>
                        <a href="#" class="list-group-item">Item 1.3.3</a>
                    </div>

                </div>

                <div>
                    <a href="#item-2" class="list-group-item" data-toggle="collapse">
                        <i class="glyphicon glyphicon-chevron-right"></i>Item 2
                    </a>
                    <div class="list-group collapse" id="item-2">

                        <a href="#item-2-1" class="list-group-item" data-toggle="collapse">
                            <i class="glyphicon glyphicon-chevron-right"></i>Item 2.1
                        </a>
                        <div class="list-group collapse" id="item-2-1">
                            <a href="#" class="list-group-item">Item 2.1.1</a>
                            <a href="#" class="list-group-item">Item 2.1.2</a>
                            <a href="#" class="list-group-item">Item 2.1.3</a>
                        </div>

                        <a href="#item-2-2" class="list-group-item" data-toggle="collapse">
                            <i class="glyphicon glyphicon-chevron-right"></i>Item 2.2
                        </a>
                        <div class="list-group collapse" id="item-2-2">
                            <a href="#" class="list-group-item">Item 2.2.1</a>
                            <a href="#" class="list-group-item">Item 2.2.2</a>
                            <a href="#" class="list-group-item">Item 2.2.3</a>
                        </div>

                        <a href="#item-2-3" class="list-group-item" data-toggle="collapse">
                            <i class="glyphicon glyphicon-chevron-right"></i>Item 2.3
                        </a>
                        <div class="list-group collapse" id="item-2-3">
                            <a href="#" class="list-group-item">Item 2.3.1</a>
                            <a href="#" class="list-group-item">Item 2.3.2</a>
                            <a href="#" class="list-group-item">Item 2.3.3</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="header"><span>Expand</span>

    </div>
    <div class="content">
        <div class="header"><span>Expand</span>

        </div>
        <div class="content">

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/scripts/jQuery3.3.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/event_handler.js"></script>
</body>
</html>