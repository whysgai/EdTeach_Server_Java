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
let $usernameFld, $firstNameFld

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
    const firstname = $firstNameFld.val()

    const newUser = {
        username: username,
        fName: firstname,
        lName: "TBD",
        role: "TDB"
    }
    users.push(newUser)
    renderUsers(users)
}

const init = () => {
    tbody = $("tbody")
    template = $("tr.wbdv-template")
    $createBtn = $(".wbdv-create").click(createUser)
    $usernameFld = $("#usernameFld")
    $firstNameFld = $("#firstNameFld")

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