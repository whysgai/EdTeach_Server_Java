(function () {
    let users = [
        {
            userid: '0000001',
            username: 'wonder',
            firstname: 'Diana',
            lastname: 'Prince',
            role: 'Faculty'
        },
        {
            userid: '0000002',
            username: 'super',
            firstname: 'Clark',
            lastname: 'Kent',
            role: 'Faculty'
        },
        {
            userid: '0000003',
            username: 'bat',
            firstname: 'Bruce',
            lastname: 'Wayne',
            role: 'Faculty'
        }
    ]

    let tbody
    let template
    let clone
    let selectedUser

// Use $ in variable naming to indicate presence in DOM
    let $createBtn
    let $updateBtn
    let $searchBtn
    let $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld
    const userService = new AdminUserServiceClient()

    const deleteUserFromTable = (event) => {
        const deleteBtn = $(event.currentTarget)
        // deleteBtn.parent().parent().parent().remove()
        deleteBtn.parents("tr.wbdv-template").remove()
        console.log("delete user from table")
    }

    const renderUser = (user) => {
        tbody.empty()
        clone = template.clone()
        // swap wbdv-hidden for hide class
        clone.removeClass("wbdv-hidden")
        clone.find(".wbdv-username").html(user.username)
        clone.find(".wbdv-first-name").html(user.firstname)
        clone.find(".wbdv-last-name").html(user.lastname)
        clone.find(".wbdv-role").html(user.role)
        // clone.find(".wbdv-remove").click(deleteUserFromTable)
        clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
        clone.find(".wbdv-edit").click(() => editUser(i))
        tbody.append(clone)
    }

    const renderUsers = (users) => {
        tbody.empty()
        for (let i = 0; i < users.length; i++) {
            user = users[i]
            clone = template.clone()
            // swap wbdv-hidden for hide class
            clone.removeClass("wbdv-hidden")
            clone.find(".wbdv-username").html(user.username)
            clone.find(".wbdv-first-name").html(user.firstname)
            clone.find(".wbdv-last-name").html(user.lastname)
            clone.find(".wbdv-role").html(user.role)
            // clone.find(".wbdv-remove").click(deleteUserFromTable)
            clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
            clone.find(".wbdv-edit").click(() => editUser(i))
            tbody.append(clone)
        }
    }

    const removeUserFromArray = (_index) => {
        //console.log(index)
        const user = users[_index]
        const userID = user._id
        userService.deleteUser(userID)
            .then(response => {
                users.splice(_index, 1)
                renderUsers(users)
                console.log("remove user from array")
            })
    }

    const findUserByID = (event) => {
        for (let i = 0; i < users.length; i++) {
            if ($usernameFld.val() == users[i].username ||
                $firstNameFld.val() == users[i].firstname ||
                $lastNameFld.val() == users[i].lastname) {
                console.log("Match found:")
                console.log(users[i].username)
                console.log(users[i]._id)
                userService.findUserById(users[i]._id)
                    .then(foundUser => {
                        console.log(foundUser)
                        renderUser(foundUser)
                    })
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
        console.log("Create user!")
        const username = $usernameFld.val()
        // const password = $passwordFld.val()
        const firstname = $firstNameFld.val()
        const lastname = $lastNameFld.val()
        const role = $roleFld.val()

        // Can use same field for read/write depending on passed args
        $usernameFld.val("")
        $passwordFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
        $roleFld.val("")

        const newUser = {
            // userid: generateID(),
            username: username,
            firstname: firstname,
            lastname: lastname,
            role: role
        }

        userService.createUser(newUser)
            .then(actualNewUser => {
                users.push(actualNewUser)
                renderUsers(users)
            })
    }

    const editUser = (index) => {
        console.log("Edit user!")

        selectedUser = users[index]

        console.log(selectedUser.username)

        $usernameFld.val(selectedUser.username)
        $passwordFld.val("")
        $firstNameFld.val(selectedUser.firstname)
        $lastNameFld.val(selectedUser.lastname)
        $roleFld.val(user.role)

    }

    const updateUser = (event) => {
        console.log("Edit user!")
        const updateUsername = $usernameFld.val()
        // const password = $passwordFld.val()
        const updateFirstName = $firstNameFld.val()
        const updateLastName = $lastNameFld.val()
        const updateRole = $roleFld.val()
        const userID = selectedUser._id

        userService.updateUser(userID, {
            username: updateUsername,
            firstname: updateFirstName,
            lastname: updateLastName,
            role: updateRole
        })
            .then(response => {
                selectedUser.username = updateUsername
                selectedUser.firstname = updateFirstName
                selectedUser.lastname = updateLastName
                selectedUser.role = updateRole
                renderUsers(users)
            })

        // Can use same field for read/write depending on passed args
        $usernameFld.val("")
        $passwordFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
        $roleFld.val("")


    }

    const init = () => {

        tbody = $("tbody")
        template = $("tr.wbdv-template")
        $searchBtn = $(".wbdv-search").click(findUserByID)
        $createBtn = $(".wbdv-create").click(createUser)
        $updateBtn = $(".wbdv-update").click(updateUser)
        $usernameFld = $("#usernameFld")
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
    }
    $(init)

// <tr class="wbdv-template wbdv-user wbdv-hidden">
//     <td class="wbdv-username">wonderwoman</td>
// <td>&nbsp;</td>
// <td class="wbdv-first-name">Diana</td>
// <td class="wbdv-last-name">Prince</td>
// <td class="wbdv-role">FACULTY</td>
// <td class="wbdv-actions">
//     <span class="float-right">
//         <i class="fa-2x fa fa-times wbdv-remove"></i>
//         <i class="fa-2x fa fa-pencil wbdv-edit"></i>
//     </span>
// </td>
// </tr>
})();