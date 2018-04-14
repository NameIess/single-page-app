$(document).ready(function ($) {

    findAll();

    $(document).on('click', 'div.header', function () {
        let header = $(this);
        let content = header.next();
        content.slideToggle(200);

        $('.glyphicon.nesting', this)
            .toggleClass('glyphicon-chevron-right')
            .toggleClass('glyphicon-chevron-down');
    });

    $("#search-form").submit(function (event) {
        if ($(".error").length > 0) {
            event.preventDefault();
        } else {
            event.preventDefault();
            findNode();
        }
    });
});



function deleteNode(context) {
    let transfer = {};
    let root = $(context).parent().parent();
    let identifier = root.find(".identity").text();
    transfer["currentName"] = identifier;

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/",
        data: JSON.stringify(transfer),
        dataType: "JSON",
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("Server response on deleteNode():\t", data);
            if (data === true) {
                let childAmount = parseInt(root.parent().prev().find(".child-amount").text(), 10) - 1;
                root.parent().prev().find(".child-amount").text(childAmount);
                removeElement(root);
            }
        },
        error: function (e) {
            console.log("Error within deleteNode():\t", e);
        },
        done: function (e) {
            console.log("deleteNode() has done:\t" + e);
        }
    });
}

function removeElement(root) {
    let child = $(root).next();
    if (child.hasClass("content")) {
        child.remove();
    }

    $(root).remove();
}

function clearElementContent() {
    $(".result_list").empty();
}

function findAll() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/list/",
        async: true,
        success: function (data) {
            console.log("Server response on findAll():\t", data);
            if (data !== null) {
                clearElementContent();
                let componentList = data[0].componentList;
                componentList.splice(0, 1);
                showData(componentList, true);
            }
        },
        error: function (e) {
            console.log("Error within findAll():\t", e);
            handleError(e);
        },
        done: function (e) {
            console.log("findAll() has done:\t" + e);
        }
    });
}

function renderInsertDialog(context) {
    identifyDialog(context);
    let modal_window = $("#modal");
    modal_window.find(".modal-title").text("Create category");
    modal_window.find(".btn-primary").removeClass("updater").addClass("creator");
    $("label[for='updated-name']").text("Child category:");
    $("label[for='current-name']").text("Parent category:");
}

function identifyDialog(context) {
    let modal_window = $("#modal");
    modal_window.modal();
    let parent = $(context).parent().parent();
    let identifier = parent.find(".identity").text();
    modal_window.find(".identified").text(identifier);
}

function renderUpdateDialog(context) {
    identifyDialog(context);
    let modal_win = $("#modal");
    modal_win.find(".modal-title").text("Update skill name");
    modal_win.find(".btn-primary").removeClass("creator").addClass("updater");
    $("label[for='updated-name']").text("Updated name:");
    $("label[for='current-name']").text("Current name:");
}

function renderErrorDialog(title, message) {
    let alert_modal_win = $("#event-modal");
    alert_modal_win.modal();
    alert_modal_win.find(".modal-title").text(title);
    alert_modal_win.find(".modal-message").text(message);
}

function submitDialog() {
    let modal_win = $("#modal");
    let updated = modal_win.find(".updated").val();
    let current = modal_win.find(".identified").text();

    if (modal_win.find(".btn-primary").hasClass("creator")) {
        insertNode(current, updated);
    } else if (modal_win.find(".btn-primary").hasClass("updater")) {
        updateNode(current, updated);
    }
    modal_win.modal("toggle");
}

function updateNode(previous, updated) {
    let transfer = {};
    transfer["currentName"] = previous;
    transfer["updatedName"] = updated;

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/update",
        data: JSON.stringify(transfer),
        dataType: "JSON",
        async: true,
        timeout: 100000,
        success: function (data) {
            console.log("Server response on updateNode():\t", data);
            if (data === true) {
                let identifier = "span[name='" + previous + "']";
                $(identifier).attr('name', updated).text(updated);
            }
        },
        error: function (e) {
            console.log("Error within updateNode():\t", e);
            handleError(e);
        },
        done: function (e) {
            console.log("updateNode() has done:\t" + e);
        }
    });
}

function fillContentDom(container, jsonData, hasButton) {
    for (let i = 0; i < jsonData.length; i++) {

        let header = $("<div class='header list-group'></div>");
        let filling = $("<div class='content list-group-item'></div>");
        let glyph = $("<i class='glyphicon glyphicon-chevron-right nesting'></i>");
        let buttons = $("<span class='pull-right'>\n" +
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
        let rootNameSpan = $("<span class='identity'></span>");
        let rootChildAmount = $("<span class='pull-left child-amount'></span>");

        let rootName = jsonData[i].name;
        rootNameSpan.text(rootName).attr('name', rootName);

        let childAmount = jsonData[i].childrenAmount;
        rootChildAmount.text(childAmount);
        header.append(glyph, rootNameSpan, rootChildAmount);

        if (hasButton) {
            header.append(buttons);
        } else {

        }

        if (childAmount > 0) {
            fillContentDom(filling, jsonData[i].componentList, hasButton);
        }

        container.append(header, filling);
    }
}

function addElement(parent, child) {
    let container = "span[name='" + parent + "']";
    let root = $(container).parent();
    let childAmount = parseInt(root.find(".child-amount").text(), 10) + 1;
    root.find(".child-amount").text(childAmount);
    let filling = root.next();

    let header = $("<div class='header list-group'></div>");
    let content = $("<div class='content list-group-item'></div>");
    let glyph = $("<i class='glyphicon glyphicon-chevron-right nesting'></i>");
    let buttons = $("<span class='pull-right'>\n" +
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

    let nodeNameSpan = $("<span class='identity'></span>");
    let nodeChildSpan = $("<span class='pull-left child-amount'></span>");

    nodeNameSpan.text(child).attr('name', child);
    nodeChildSpan.text(0);

    header.append(glyph, nodeNameSpan, nodeChildSpan, buttons);

    filling.append(header, content);
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
            console.log("Server response on insertNode():\t", data);
            if (data === true) {
                addElement(parent, child);
            }
        },
        error: function (e) {
            console.log("Error within updateNode():\t", e);
            handleError(e);
        },
        done: function (e) {
            console.log("insertNode() has done:\t" + e);
        }
    });
}

function showData(data, hasButton) {
    fillContentDom($(".result_list"), data, hasButton);
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
            console.log("Server response on findNode():\t", data);
            clearElementContent();
            showData(data, false);
        },
        error: function (e) {
            console.log("Error within findNode():\t", e);
            handleError(e);
        },
        done: function (e) {
            console.log("insertNode() has done:\t" + e);
        }
    });
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