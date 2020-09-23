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

const deleteUserTable = (event) => {
    const deleteBtn = $(event.currentTarget)
    // deleteBtn.parent().parent().parent().remove()
    deleteBtn.parents("tr.wbdv-template").remove()
    console.log("delete user from table")
}

const init = () => {
    tbody = $("tbody")
    template = $("tr.wbdv-template")
    for(let i=0; i<users.length; i++) {
        user = users[i]
        clone = template.clone()
        // swap wbdv-hidden for hide class
        clone.removeClass("wbdv-hidden")
        clone.find(".wbdv-username").html(user.username)
        clone.find(".wbdv-first-name").html(user.fName)
        clone.find(".wbdv-last-name").html(user.lName)
        clone.find(".wbdv-remove").click(deleteUserTable)
        tbody.append(clone)
    }
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