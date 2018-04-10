$(document).ready(function($) {

    loadSkillList();

    $("#search-form").submit(function(event) {

        alert("Submited!");

        // Disble the search button
        enableSearchButton(false);

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        searchViaAjax();

    });

    $("#update-form").submit(function(event) {

        alert("Submited!");

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        updateName();

    });

    $("#create-form").submit(function(event) {

        alert("Submited!");

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        saveChild();

    });

    $("#delete-form").submit(function (event) {

        alert("Submited!");

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        deleteNode();

    });

});

function deleteNode() {
    var composite = {};
    composite["currentName"] = $("#delete-name").val();

    alert("Composite: " + composite["currentName"]);

    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        url : "http://localhost:8084/developer/skill/",
        data : JSON.stringify(composite),
        dataType : 'json',
        async: true,
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS LOG: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE LOG");
            enableSearchButton(true);
        }
    });
}

function loadSkillList() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "http://localhost:8084/developer/skill/list/",
        async : true,
        success : function (data) {
            console.log("ALL DATA LOADED: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE LOG");
            enableSearchButton(true);
        }
    });
}

function updateName() {
    var composite = {};
    composite["currentName"] = $("#current-name").val();
    composite["updatedName"] = $("#updated-name").val();

    alert("Composite: " + composite["currentName"] + " to " + composite["updatedName"]);

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "http://localhost:8084/developer/skill/update",
        data : JSON.stringify(composite),
        dataType : 'json',
        async: true,
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS LOG: ", data);
            display(data);
            loadSkillList();
        },
        error : function(e) {
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE LOG");
            enableSearchButton(true);
        }
    });
}

function saveChild() {
    var criteria = {};
    criteria["parentName"] = $("#parent-name").val();
    criteria["childName"] = $("#child-name").val();

    alert("Composite: " + criteria["parentName"] + " to " + criteria["childName"]);

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "http://localhost:8084/developer/skill/add/",
        data : JSON.stringify(criteria),
        dataType : 'json',
        async: true,
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS LOG: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR LOG: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE LOG");
            enableSearchButton(true);
        }
    });
}


function searchViaAjax() {

    var search = {};
    search["currentName"] = $("#search-criteria").val();

    alert("Searched value: " + search["currentName"]);

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "http://localhost:8084/developer/skill/",
        data : JSON.stringify(search),
        dataType : 'json',
        async: true,
        timeout : 100000,
        success : function(data) {
            alert("SUccess");
            console.log("SUCCESS LOG: ", data);
            display(data);
        },
        error : function(e) {

            console.log("ERROR LOG: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE LOG");
            enableSearchButton(true);
        }
    });

}

function enableSearchButton(flag) {
    $("#btn-search").prop("disabled", flag);
}

function display(jsonData) {
    // for (var i = 0; i <jsonData.length; i++) {
    //     var $divParent  = $("<div></div>");
    //     $divParent.text(jsonData[i].label).attr('name',jsonData[i].id);
    //     if (jsonData[i].children) {
    //         appendDom($divParent, jsonData[i].children);
    //     }
    //     container.append($divParent);
    // }
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(jsonData, null, 4) + "</pre>";
    $('#feedback').html(json);
}