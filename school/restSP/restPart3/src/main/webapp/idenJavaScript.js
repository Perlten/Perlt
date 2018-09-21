
function fetchData(){
    var amount = document.getElementById("amount").value;
    var id = document.getElementById("startId").value;
    
    fetch("api/data/"+amount+"/"+ id)
            .then(function (response){
                return response.json();
    }).then(function (json){
       var tableHtml = "";
        for (var person of json) {
            tableHtml += "<tr>";
            tableHtml += "<td>" + person.id + "</td>";
            tableHtml += "<td> <input class=" + person.id + "class type=\"text\" value=" + person.firstName + " /> </td>";
            tableHtml += "<td> <input class=" + person.id + "class type=\"text\" value=" + person.lastName + " /> </td>";
            tableHtml += "<td> <input class=" + person.id + "class type=\"number\" value=" + person.age + " /> </td>";
            tableHtml += "</tr>";
        }
        document.getElementById("tableBody").innerHTML = tableHtml;
    });
}

document.getElementById("submit").addEventListener("click", fetchData);

