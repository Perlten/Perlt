makeOptions = (method, body) => {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json",
            'Accept': 'application/json',
        }
    }
    if (body) {
        opts.body = JSON.stringify(body);
    }
    return opts;
}

const url = "https://perlt.net/chat/api/";

class LoginFacade {

    async verifyLogin(email, password) {

        const loginUser = {
            email,
            password
        }
        const opts = makeOptions("POST", loginUser);
        const res = await fetch(`${url}/user/login`, opts);
        const user = await res.json();

        if (!user.id) {
            return null;
        }
        return user;
    }



}


export default new LoginFacade();