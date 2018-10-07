var rootURL = "http://localhost:8084/ChatProgram/api/"

function REST(URL, callback, options) {
    fetch(URL, options)
        .then(errorCheck)
        .then(data => callback(data))
        .catch(errorHandler)
}

function errorCheck(res) {
    if (res.ok) {
        return res.json()
    } else {
        return Promise.reject({
            httpError: res.status,
            fullError: res.json()
        })
    }
}

function errorHandler(err) {
    if (err.httpError) {    
        err.fullError.then(errjson => {
            console.log(errjson)
        })
    } else {
        console.log("Network Error " + err)
    }
}

function makeOpts(method, body) {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json"
        },
    };
    if (body) {
        var Jsonbody = JSON.stringify(body)
        opts.body = Jsonbody;
    }
    return opts;
}


