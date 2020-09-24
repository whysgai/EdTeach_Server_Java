let users = [
    {
        username: 'wonder',
        fName: 'Diana',
        lName: 'Prince',
        role: 'Faculty'
    },
    {
        username: 'super',
        fName: 'Clark',
        lName: 'Kent',
        role: 'Faculty'
    },
    {
        username: 'bat',
        fName: 'Bruce',
        lName: 'Wayne',
        role: 'Faculty'
    }
]

let tbody
let template
let clone
// Use $ in variable naming to indicate presence in DOM
let $createBtn
let $updateBtn
let $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld
let userToEdit

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
        clone.find(".wbdv-first-name").html(user.fName)
        clone.find(".wbdv-last-name").html(user.lName)
        // clone.find(".wbdv-remove").click(deleteUserFromTable)
        clone.find(".wbdv-remove").click(() => removeUserFromArray(i))
        clone.find(".wbdv-edit").click(() => editUser(i))
        tbody.append(clone)
    }
}

const removeUserFromArray = (index) => {
    // console.log(index)
    users.splice(index, 1)
    renderUsers(users)
    console.log("remove user from array")
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
        username: username,
        fName: firstname,
        lName: lastname,
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
    $firstNameFld.val(userToEdit.fName)
    $lastNameFld.val(userToEdit.lName)
    $roleFld.val(user.role)

}

const updateUser = (event) => {
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
        username: username,
        fName: firstname,
        lName: lastname,
        role: role
    }
    users.push(newUser)
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

    renderUsers(users)
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