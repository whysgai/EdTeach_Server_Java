
function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/wcohen/users';
    var self = this;
    function findAllUsers() {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/wcohen/users')
            .then(response => response.json())
    }

    function createUser(user) {}
    function findUserById(userId) {}
    function updateUser(userId, user) {}
    function deleteUser(userId) {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/wcohen/users/' + userId, {
            method: 'DELETE'
        })

    }
}
