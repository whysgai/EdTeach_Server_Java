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
let userToEdit

// Use $ in variable naming to indicate presence in DOM
let $createBtn
let $updateBtn
let $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld
const userService = new AdminUserServiceClient()

const deleteUserFromTable = (event) => {
    const deleteBtn = $(event.currentTarget)
    // deleteBtn.parent().parent().parent().remove()
    deleteBtn.parents("tr.wbdv-template").remove()
    console.log("delete user from table")
}

const renderUsers = (users) => {
    tbody.empty()
    for(let i=0; i<users.length; i++) {
        user = users[i]
        clone = template.clone()
        // swap wbdv-hidden for hide class
        clone.removeClass("wbdv-hidden")
        clone.find(".wbdv-username").html(user.username)
        clone.find(".wbdv-first-name").html(user.firstname)
        clone.find(".wbdv-last-name").html(user.lastname)
        // clone.find(".wbdv-remove").click(deleteUserFromTable)
        clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
        clone.find(".wbdv-edit").click(() => editUser(i))
        tbody.append(clone)
    }
}

const removeUserFromArray = (_index) => {
    // console.log(index)
    const user = users[_index]
    const userID = users.userid
    userService.deleteUser(userID)
        .then(response => {
            users.splice(_index, 1)
            renderUsers(users)
            console.log("remove user from array")
        })
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
        userid: generateID(),
        username: username,
        firstname: firstname,
        lastname: lastname,
        role: role
    }
    users.push(newUser)
    renderUsers(users)
}

const editUser = (index) => {
    console.log("Edit user!")

    userToEdit = users[index]

    console.log(userToEdit.username)

    $usernameFld.val(userToEdit.username)
    $passwordFld.val("")
    $firstNameFld.val(userToEdit.firstname)
    $lastNameFld.val(userToEdit.lastname)
    $roleFld.val(user.role)

}

const updateUser = (event) => {
    console.log("Edit user!")
    userToEdit.username = $usernameFld.val()
    // const password = $passwordFld.val()
    userToEdit.fName = $firstNameFld.val()
    userToEdit.lName = $lastNameFld.val()
    userToEdit.role = $roleFld.val()

    // Can use same field for read/write depending on passed args
    $usernameFld.val("")
    $passwordFld.val("")
    $firstNameFld.val("")
    $lastNameFld.val("")
    $roleFld.val("")

    renderUsers(users)
}

const init = () => {

    tbody = $("tbody")
    template = $("tr.wbdv-template")
    $createBtn = $(".wbdv-create").click(createUser)
    $updateBtn = $(".wbdv-update").click(updateUser)
    $usernameFld = $("#usernameFld")
    $passwordFld = $("#passwordFld")
    $firstNameFld = $("#firstNameFld")
    $lastNameFld = $("#lastNameFld")
    $roleFld = $("#roleFld")

    //renderUsers(users)

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