function User(username, password, firstName, lastName, role) {
    this.username = username;
    this.password = password;
    this.firstname = firstName;
    this.lastname = lastName;
    this.role = role;

    // ...same for rest of properties…
    this.testFunc = testFunc;
    this.setUsername = setUsername;
    this.getUsername = getUsername;
    // ...same for rest of properties…

    function testFunc() {
        console.log("testFunc is workin'!")
    }
    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }
    // ...same for rest of properties…
}
