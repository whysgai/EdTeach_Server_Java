// import * as User from '../models/user.model.client'


(function () {
    let users = [
        {
            _id: '0000001',
            username: 'wonder',
            email: 'dprince@jla.org',
            firstname: 'Diana',
            lastname: 'Prince',
            role: 'Faculty'
        },
        {
            _id: '0000002',
            username: 'super',
            email: 'ckent@dailyplent.com',
            firstname: 'Clark',
            lastname: 'Kent',
            role: 'Faculty'
        },
        {
            _id: '0000003',
            username: 'bat',
            email: 'bwayne@batcave.net',
            firstname: 'Bruce',
            lastname: 'Wayne',
            role: 'Faculty'
        }
    ]

    let clone
    let selectedUser

    // Use $ in variable naming to indicate presence in DOM
    let $tbody, $template
    let $createBtn, $updateBtn, $searchBtn, $syncBtn
    let $searchFld, $usernameFld, $passwordFld, $emailFld, $firstNameFld, $lastNameFld, $roleFld

    const userService = new AdminUserServiceClient()

    // Deletes locally only, depricated
    const deleteUserFromTable = (event) => {
        const deleteBtn = $(event.currentTarget)
        deleteBtn.parents("tr.wbdv-template").remove()
    }

    const clearFields = () => {
        $searchFld.val("")
        $usernameFld.val("")
        $emailFld.val("")
        $passwordFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
        $roleFld.val("")

    }

    const renderUser = (user) => {
        $tbody.empty()
        clone = $template.clone()
        // swap wbdv-hidden for hide class
        clone.removeClass("wbdv-hidden")
        clone.find(".wbdv-username").html(user.username)
        clone.find(".wbdv-email").html(user.email)
        clone.find(".wbdv-first-name").html(user.firstname)
        clone.find(".wbdv-last-name").html(user.lastname)
        clone.find(".wbdv-role").html(user.role)
        // clone.find(".wbdv-remove").click(deleteUserFromTable)
        clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
        clone.find(".wbdv-edit").click(() => editUser(i))
        $tbody.append(clone)
    }

    const renderUsers = (users) => {
        $tbody.empty()
        let user
        for (let i = 0; i < users.length; i++) {
            user = users[i]
            clone = $template.clone()
            // swap wbdv-hidden for hide class
            clone.removeClass("wbdv-hidden")
            clone.find(".wbdv-username").html(user.username)
            clone.find(".wbdv-email").html(user.email)
            clone.find(".wbdv-first-name").html(user.firstname)
            clone.find(".wbdv-last-name").html(user.lastname)
            clone.find(".wbdv-role").html(user.role)
            // clone.find(".wbdv-remove").click(deleteUserFromTable)
            clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
            clone.find(".wbdv-edit").click(() => editUser(i))
            $tbody.append(clone)
        }
    }

    const refreshUsers = (event) => {
        userService.findAllUsers()
            .then((_users) => {
                console.log(_users)
                users = _users
                renderUsers(users)
            })
    }

    const removeUserFromArray = (_index) => {
        const user = users[_index]
        const userID = user._id
        userService.deleteUser(userID)
            .then(response => {
                users.splice(_index, 1)
                renderUsers(users)
            })
    }

    const findUserByID = (event) => {
        if (!$usernameFld.val() &&
            !$firstNameFld.val() &&
            !$lastNameFld.val()) {
            userService.findAllUsers()
                .then((_users) => {
                    users = _users
                    renderUsers(users)
                })
        } else {
            for (let i = 0; i < users.length; i++) {
                if ($usernameFld.val() == users[i].username ||
                    $emailFld.val() == users[i].email ||
                    $firstNameFld.val() == users[i].firstname ||
                    $lastNameFld.val() == users[i].lastname) {
                    userService.findUserById(users[i]._id)
                        .then(foundUser => {
                            renderUser(foundUser)
                        })
                }
            }
        }
    }

    const generateID = () => {
        const prefix = new Date().getFullYear()
        const suffix = Math.floor(Math.random() * (1000 - 0) + 0)
        const id = "" + prefix + suffix
        return id
    }

    const createUser = () => {
        const username = $usernameFld.val()
        const email = $emailFld.val()
        const firstname = $firstNameFld.val()
        const lastname = $lastNameFld.val()
        const role = $roleFld.val()

        // Can use same field for read/write depending on passed args

        const newUser = {
            username: username,
            email: email,
            password: 'PLACEHOLDER',
            firstname: firstname,
            lastname: lastname,
            role: role
        }

        userService.createUser(newUser)
            .then(actualNewUser => {
                users.push(actualNewUser)
                clearFields()
                renderUsers(users)
            })
    }

    const editUser = (index) => {
        selectedUser = users[index]
        $usernameFld.val(selectedUser.username)
        $emailFld.val(selectedUser.email)
        $passwordFld.val("")
        $firstNameFld.val(selectedUser.firstname)
        $lastNameFld.val(selectedUser.lastname)
        $roleFld.val(user.role)
    }

    const updateUser = (event) => {
        const updateUsername = $usernameFld.val()
        const updateEmail = $emailFld.val()
        // const password = $passwordFld.val()
        const updateFirstName = $firstNameFld.val()
        const updateLastName = $lastNameFld.val()
        const updateRole = $roleFld.val()
        const userID = selectedUser._id

        userService.updateUser(userID, {
            username: updateUsername,
            email: updateEmail,
            firstname: updateFirstName,
            lastname: updateLastName,
            role: updateRole
        })
            .then(response => {
                selectedUser.username = updateUsername
                selectedUser.email = updateEmail
                selectedUser.firstname = updateFirstName
                selectedUser.lastname = updateLastName
                selectedUser.role = updateRole
                clearFields()
                renderUsers(users)
            })
    }

    const init = () => {

        $tbody = $("tbody")
        $template = $("tr.wbdv-template")
        $searchFld = $("#userSearch")
        $syncBtn = $(".refresh").click(refreshUsers)
        $searchBtn = $(".wbdv-search").click(findUserByID)
        $createBtn = $(".wbdv-create").click(createUser)
        $updateBtn = $(".wbdv-update").click(updateUser)
        $usernameFld = $("#usernameFld")
        $emailFld = $("#emailFld")
        $passwordFld = $("#passwordFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")

        userService.findAllUsers()
            .then((_users) => {
                console.log(_users)
                users = _users
                renderUsers(users)
            })

        $("#userSearch").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $(".wbdv-tbody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    }
    $(init)

})();