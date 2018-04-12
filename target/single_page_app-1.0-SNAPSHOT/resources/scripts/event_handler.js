$(document).ready(function ($) {

    loadSkillList();

    $('.list-group-item').on('click', function () {
        $('.glyphicon-chevron-right', this)
            .toggleClass('glyphicon-chevron-right')
            .toggleClass('glyphicon-chevron-down');
    });

    // $(".header").click(function () {
    //
    //     $header = $(this);
    //     //getting the next element
    //     $content = $header.next();
    //     //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
    //     $content.slideToggle(500, function () {
    //         //execute this after slideToggle is done
    //         //change text of header based on visibility of content div
    //
    //     });
    //
    // });



    $(".result_list").on('click', function () {
        alert("header clicked");
        var message = $(this).attr("class");
        alert("Onclick class\t" + message);
        var header = $(this);       // this - result_list

        var content = header.children();    // header
        message = content.attr("class");
        alert("Next class\t" + message);

        content.slideToggle(200, function () {

        });
    });

    // $(".header").click(function () {
    //
    //     var $header = $(this);
    //     //getting the next element
    //     var $content = $header.next();
    //     //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
    //     $content.slideToggle(100, function () {
    //         //execute this after slideToggle is done
    //         //change text of header based on visibility of content div
    //         $header.text(function () {
    //             //change text based on condition
    //             return $content.is(":visible") ? "Collapse" : "Expand";
    //         });
    //     });
    //
    // });

    $("#search-form").submit(function (event) {
        alert("Submited!");
        event.preventDefault();

        searchViaAjax();

    });

    $("#update-form").submit(function (event) {
        alert("Submited!");
        event.preventDefault();

        updateName();
    });

    $("#create-form").submit(function (event) {
        alert("Submited!");
        event.preventDefault();

        saveChild();
    });

    $("#delete-form").submit(function (event) {
        alert("Submited!");
        event.preventDefault();

        deleteNode();
    });

});

function appendDom(container, jsonData) {
    for (var i = 0; i < jsonData.length; i++) {
        var header = $("<div class='header list-group'></div>");
        var content = $("<div class='content list-group-item'></div>");
        var glyph = $("<i class='glyphicon glyphicon-chevron-right'></i>");
        var buttons = $("<span class=\"pull-right\">\n" +
            "                        <span class=\"btn btn-xs btn-default\"\n" +
            "                              onclick=\"alert('Action2 -> Update'); event.stopPropagation();\">\n" +
            "                            <span class=\"glyphicon glyphicon-cog\" aria-hidden=\"true\"></span>\n" +
            "                        </span>\n" +
            "                        <span class=\"btn btn-xs btn-default\"\n" +
            "                              onclick=\"alert('Action2 -> Play'); event.stopPropagation();\">\n" +
            "                            <span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>\n" +
            "                        </span>\n" +
            "                        <span class=\"btn btn-xs btn-default\"\n" +
            "                              onclick=\"alert('Action2 -> Delete'); event.stopPropagation();\">\n" +
            "                            <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
            "                        </span>\n" +
            "                    </span>");


        var nodeNameSpan = $("<span></span>");
        var nodeChildrenSpan = $("<span></span>");

        nodeNameSpan.text(jsonData[i].name);
        nodeChildrenSpan.text(jsonData[i].childrenAmount);

        header.append(glyph, nodeNameSpan, nodeChildrenSpan, buttons);

        if (jsonData[i].childrenAmount > 0) {
            appendDom(content, jsonData[i].componentList);
        }

        container.append(header, content);
    }
}

function deleteNode() {
    var composite = {};
    composite["currentName"] = $("#delete-name").val();

    alert("Composite: " + composite["currentName"]);

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/",
        data: JSON.stringify(composite),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            if (data === true) {
                alert("SUCCESS TRUE");
            } else {
                alert("SUCCESS FALSE");
            }
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
            display(e);
            appendDom($("#content"), data);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}

function loadSkillList() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/list/",
        async: true,
        success: function (data) {
            alert("SUCCESS");
            console.log("ALL DATA LOADED: ", data);
            display(data);
            appendDom($(".result_list"), data);
        },
        error: function (e) {
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE LOG");
        }
    });
}

function updateName() {
    var composite = {};
    composite["currentName"] = $("#current-name").val();
    composite["updatedName"] = $("#updated-name").val();

    alert("Composite: " + composite["currentName"] + " to " + composite["updatedName"]);

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
            } else {
                alert("SUCCESS FALSE");
            }
            console.log("SUCCESS LOG: ", data);
            display(data);
            loadSkillList();
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}

function saveChild() {
    var criteria = {};
    criteria["parentName"] = $("#parent-name").val();
    criteria["childName"] = $("#child-name").val();

    alert("Composite: " + criteria["parentName"] + " to " + criteria["childName"]);

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
            } else {
                alert("SUCCESS FALSE");
            }

            console.log("SUCCESS LOG: ", data);
            display(data);
            appendDom($("#content"), data);
        },
        error: function (e) {
            alert("ERROR");
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done: function (e) {
            alert("DONE");
            console.log("DONE LOG");
        }
    });
}


function searchViaAjax() {

    var search = {};
    search["currentName"] = $("#search-criteria").val();

    alert("Searched value: " + search["currentName"]);

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "http://localhost:8084/developer/skill/",
        data: JSON.stringify(search),
        dataType: 'json',
        async: true,
        timeout: 100000,
        success: function (data) {
            alert("SUccess");
            console.log("SUCCESS LOG: ", data);
            display(data);
            appendDom($("#content"), data);
        },
        error: function (e) {

            console.log("ERROR LOG: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE LOG");
        }
    });

}


function display(jsonData) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(jsonData, null, 4) + "</pre>";
    $('#feedback').html(json);
}

// function appendDom(container, jsonData) {
//     for (var i = 0; i < jsonData.length; i++) {
//         // if (jsonData[i].name === null) {
//         //     var wrapper = $("<div></div>");
//             var childWrapper = $("<div class='list-group' aria-expanded='false' data-role='collapsible'></div>");
//             var onChildRef = $("<a class='list-group-item collapsed' data-toggle='collapse' aria-expanded='false'></a>");
//             var glyph = $("<i class='glyphicon glyphicon-chevron-right'></i>");
//
//             var parentName = jsonData[i].name;
//
//             if (parentName === null) {
//                 parentName = jsonData[i].childrenAmount;
//             }
//
//             onChildRef.text(jsonData[i].name).attr('href', parentName).append(glyph);
//             childWrapper.attr('id', parentName);
//
//             // childWrapper.addClass('list-group');
//
//         // collapse' style='height: 0'
//
//             // var content = wrapper.append(onChildRef, childWrapper);
//
//             if (jsonData[i].childrenAmount > 0) {
//                 appendDom(childWrapper, jsonData[i].componentList);
//             }
//
//             container.append(onChildRef, childWrapper);
//         }
//     // }
//
//     // $('div[data-role=collapsible]').collapsible({refresh : true});
//     // $('div[data-role=collapsible]').collapsed({refresh : true});
//
//     // $(".list-group").addClass('collapse');
// }
