$(document).ready(function ($) {

    findAll();

    $(document).on('click', 'div.header', function () {
        var header = $(this);
        var content = header.next();
        content.slideToggle(200);

        $('.glyphicon.nesting', this)
            .toggleClass('glyphicon-chevron-right')
            .toggleClass('glyphicon-chevron-down');
    });

    // $(".just-padding").on('click', 'div.header', function () {
    //     var header = $(this);
    //     var content = header.next();
    //     content.slideToggle(200);
    //
    //     $('.glyphicon.nesting', this)
    //         .toggleClass('glyphicon-chevron-right')
    //         .toggleClass('glyphicon-chevron-down');
    // });

    $("#search-form").submit(function (event) {
        if ($(".error").length > 0) {
            event.preventDefault();
            let e = $.event("keydown", {keyCode: 20});
            $("#search-criteria").trigger(e);
        } else {
            alert("Submited!");
            event.preventDefault();
            findNode();
        }
    });

    $('#modal-form').validate({
        rules: {
            updated: {
                minlength: 3,
                maxlength: 15,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });


});

function appendDom(container, jsonData, hasButton) {
    for (var i = 0; i < jsonData.length; i++) {


        var header = $("<div class='header list-group'></div>");
        var content = $("<div class='content list-group-item'></div>");
        var glyph = $("<i class='glyphicon glyphicon-chevron-right nesting'></i>");
        var buttons = $("<span class='pull-right'>\n" +
            "                        <span class='btn btn-xs btn-success'\n" +
            "                              onclick='renderUpdateDialog(this); event.stopPropagation();'>\n" +
            "                            <span class='glyphicon glyphicon-cog' aria-hidden='true'></span>\n" +
            "                        </span>\n" +
            "                        <span class='btn btn-xs btn-primary'\n" +
            "                              onclick=\"renderInsertDialog(this); event.stopPropagation();\">\n" +
            "                            <span class='glyphicon glyphicon-circle-arrow-down' aria-hidden='true'></span>\n" +
            "                        </span>\n" +
            "                        <span class='btn btn-xs btn-danger'\n" +
            "                              onclick='deleteNode(this); event.stopPropagation();'>\n" +
            "                            <span class='glyphicon glyphicon-trash' aria-hidden='true'></span>\n" +
            "                        </span>\n" +
            "                    </span>");

        var nodeNameSpan = $("<span class='identity'></span>");
        var nodeChildSpan = $("<span class='pull-left child-amount'></span>");

        var nodeName = jsonData[i].name;


        nodeNameSpan.text(nodeName).attr('name', nodeName);         // CHANGED ID TO NAME

        let childAmount = jsonData[i].childrenAmount;
        nodeChildSpan.text(childAmount);
        header.append(glyph, nodeNameSpan, nodeChildSpan);

        if (hasButton) {
            header.append(buttons);
        }

        if (childAmount > 0) {
            appendDom(content, jsonData[i].componentList, hasButton);
        }

        container.append(header, content);
    }


}

function deleteNode(context) {
    var composite = {};
    var root = $(context).parent().parent();
    var identifier = root.find(".identity").text();

    composite["currentName"] = identifier;

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/",
        data: JSON.stringify(composite),
        dataType: "JSON",
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("Server response", data);
            if (data === true) {
                var childAmount = parseInt(root.parent().prev().find(".child-amount").text(), 10) - 1;
                root.parent().prev().find(".child-amount").text(childAmount);
                removeElement(root);
                successAlert();
            } else {
                alert("SUCCESS FALSE");
            }
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}

function removeElement(element) {
    var next = $(element).next();
    if (next.hasClass("content")) {
        next.remove();
    }
    $(element).remove();
}

function clearElement() {
    $(".result_list").empty();
}

function findAll() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/list/",
        async: true,
        success: function (data) {
            if (data !== null) {
                alert("SUCCESS");
                console.log("ALL DATA LOADED: ", data);
                display(data);
                clearElement();
                let componentList = data[0].componentList;
                componentList.splice(0, 1);
                showData(componentList, true);
            } else {
                alert("Information not found");
            }
        },
        error: function (e) {
            console.log("ERROR LOG: ", e);
            handleError(e);
            display(e);
        },
        done: function (e) {
            console.log("DONE LOG");
        }
    });
}

function renderInsertDialog(context) {
    identifyDialog(context);
    $("#modal .modal-title").text("Create category");
    $("label[for='updated-name']").text("Child category:");
    $("label[for='current-name']").text("Parent category:");
    $("#modal .btn-primary").removeClass("updater").addClass("creator");
}

function identifyDialog(context) {
    $("#modal").modal();
    var parent = $(context).parent().parent();
    var identifier = parent.find(".identity").text();
    $("#modal .identified").text(identifier);
}

function renderUpdateDialog(context) {
    identifyDialog(context);
    $("#modal .modal-title").text("Update skill name");
    $("label[for='updated-name']").text("Updated name:");
    $("label[for='current-name']").text("Current name:");
    $("#modal .btn-primary").removeClass("creator").addClass("updater");
}

function renderErrorDialog(header, message) {
    $("#event-modal").modal();
    $("#event-modal .modal-title").text(header);
    $("#event-modal .modal-message").text(message);
}

function submitDialog() {
    var updated = $("#modal .updated").val();
    var current = $("#modal .identified").text();
    if ($("#modal .btn-primary").hasClass("creator")) {
        insertNode(current, updated);
    } else if ($("#modal .btn-primary").hasClass("updater")) {
        updateNode(current, updated);
    }
    $("#modal").modal("toggle");
}

function updateNode(current, updated) {

    var composite = {};
    composite["currentName"] = current;
    composite["updatedName"] = updated;

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/update",
        data: JSON.stringify(composite),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            if (data === true) {
                alert("SUCCESS TRUE");
                var selector = "span[name='" + current + "']";
                alert("Updated element: " + current + " to " + updated);
                $(selector).attr('name', updated).text(updated);
            } else {
                alert("SUCCESS FALSE");
                handleValidatorError(data);
            }
            console.log("SUCCESS LOG: ", data);
            display(data);
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
            handleError(e);
            display(e);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}

function addElement(parent, child) {
    alert("Parent id : " + parent);
    var parentSelector = "span[name='" + parent + "']";
    var root = $(parentSelector).parent();
    alert(root.attr("class"));
    var childAmount = parseInt(root.find(".child-amount").text(), 10) + 1;
    root.find(".child-amount").text(childAmount);

    var next = root.next();
    alert(next.attr("class"));
    var header = $("<div class='header list-group'></div>");
    var content = $("<div class='content list-group-item'></div>");
    var glyph = $("<i class='glyphicon glyphicon-chevron-right nesting'></i>");
    var buttons = $("<span class='pull-right'>\n" +
        "                        <span class='btn btn-xs btn-success'\n" +
        "                              onclick='renderUpdateDialog(this); event.stopPropagation();'>\n" +
        "                            <span class='glyphicon glyphicon-cog' aria-hidden='true'></span>\n" +
        "                        </span>\n" +
        "                        <span class='btn btn-xs btn-primary'\n" +
        "                              onclick=\"renderInsertDialog(this); event.stopPropagation();\">\n" +
        "                            <span class='glyphicon glyphicon-circle-arrow-down' aria-hidden='true'></span>\n" +
        "                        </span>\n" +
        "                        <span class='btn btn-xs btn-danger'\n" +
        "                              onclick='deleteNode(this); event.stopPropagation();'>\n" +
        "                            <span class='glyphicon glyphicon-trash' aria-hidden='true'></span>\n" +
        "                        </span>\n" +
        "                    </span>");

    var nodeNameSpan = $("<span class='identity'></span>");
    var nodeChildSpan = $("<span class='pull-left child-amount'></span>");

    nodeNameSpan.text(child).attr('name', child);       // CHANGED ID TO NAME
    nodeChildSpan.text(0);

    header.append(glyph, nodeNameSpan, nodeChildSpan, buttons);

    next.append(header, content);
}

function insertNode(parent, child) {

    var criteria = {};
    criteria["parentName"] = parent;
    criteria["childName"] = child;

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/add/",
        data: JSON.stringify(criteria),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            if (data === true) {
                alert("SUCCESS TRUE");
                addElement(parent, child);
            } else {
                alert("SUCCESS FALSE");
            }

            console.log("SUCCESS LOG: ", data);
            display(data);
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
            handleError(e);
            display(e);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}

function showData(data, hasButton) {
    appendDom($(".result_list"), data, hasButton);
}

function findNode() {
    var criteria = {};
    criteria["currentName"] = $("#search-criteria").val();

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/",
        data: JSON.stringify(criteria),
        dataType: "JSON",
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("Server response", data);
            clearElement();
            showData(data, false);
        },
        error: function (e) {
            alert("Error");
            console.log("ERROR LOG: ", e);
            display(e);
            handleError(e);
        },
        done: function (e) {
            handleValidatorError(e);
            console.log("DONE LOG ", e);
        }
    });
}


// TEMPORARY TO DELETE
function display(jsonData) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(jsonData, null, 4) + "</pre>";
    $('#feedback').html(json);
}

function handleError(errorData) {
    let responseText = errorData.responseText;

    let field;
    let message;
    try {
        let json = JSON.parse(responseText);
        field = "Error caused by: ";
        message;
        for (let i = 0; i < json["fieldErrors"].length; i++) {
            field += json["fieldErrors"][i]["field"];
            message = json["fieldErrors"][i]["message"];
        }
    } catch {
        field = "Error";
        message = responseText;
    }

    renderErrorDialog(field, message);
}