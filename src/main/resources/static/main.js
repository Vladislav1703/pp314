const linkGetAllUsers = "http://localhost:8080/api/users";
const linkGetUser = "http://localhost:8080/api/user/:id";
const linkDeleteUser = "http://localhost:8080/api/delete/";
const linkUpdateUser = "http://localhost:8080/api/update/user";
const linkNewUser = "http://localhost:8080/api/new/user";

let userFormNew = {
    name: "testing",
    lastName: "1",
    age: 1,
    email: "1",
    password: "1",
    roles: []
}

let userForm = {
    id: 2,
    name: "testing",
    lastName: "1",
    age: 1,
    email: "1",
    password: "1",
    roles: [
    ]
}

allUsers();

async function allUsers() {
    console.log("Executing request!");

    try {
        const response = await fetch(linkGetAllUsers);
        const users = await response.json();
        console.log(users);
        if (users.length > 0) {
            let temp = "";
            users.forEach((u) => {
                temp += `<tr><td>${u.id}</td>`;
                temp += `<td>${u.name}</td>`;
                temp += `<td>${u.lastName}</td>`;
                temp += `<td>${u.age}</td>`;
                temp += `<td>${u.email}</td>`;
                temp += `<td>${u.stringRoles}</td>`;
                temp += `<td><button onclick=updateUserForm(${u.id}) type='button' class='btn btn-info'>Edit</button></td>`;
                temp += `<td><button onclick=deletedUser(${u.id}) type='button' class='btn btn-danger'>Delete</button></td>`;
            });
            document.getElementById("allUsers").innerHTML = temp;
        }
    } catch (e) {
        console.error(e);
    }
}

async function updateUserForm(id) {
    $(".editUser #exampleModal").modal();

    let user = await (await fetch(linkGetUser.replace(':id', id))).json();

    $('#id').val(user.id);
    $('#edit-firstname').val(user.name);
    $('#edit-lastname').val(user.lastName);
    $('#edit-age').val(user.age);
    $('#edit-email').val(user.email);
    $('#edit-password').val(user.password);

    upbtn.onclick = function () {
        userForm.id = id;
        userForm.name = $('#edit-firstname').val();
        userForm.lastName = $('#edit-lastname').val();
        userForm.age = $('#edit-age').val();
        userForm.email = $('#edit-email').val();
        userForm.password = $('#edit-password').val();
        const checked = []
        $('.edit-role:checked').each(function() {
            checked.push($(this).val());
        });
        userForm.roles = checked;
        console.log('userForm.roles', userForm.roles)
        updateUser();
    }
}

async function updateUser() {
    try {
        await fetch(linkUpdateUser, {
            method: "PATCH",
            body: JSON.stringify(userForm),
            headers: {
                "Content-Type": "application/json"
            }
        });
    } catch (e) {
        console.error(e);
    }
}

async function newUser() {
    try {
        await fetch(linkNewUser, {
            method: "POST",
            body: JSON.stringify(userFormNew),
            headers: {
                "Content-Type": "application/json"
            }
        });
    } catch (e) {
        console.error(e);
    }
}

function deletedUser(id) {
    fetch(linkDeleteUser + id, {
        method: "DELETE",
    }).then(() => allUsers());
}

newbtn.onclick = function () {
    userFormNew.name = $('#new-firstname').val();
    userFormNew.lastName = $('#new-lastname').val();
    userFormNew.age = $('#new-age').val();
    userFormNew.email = $('#new-email').val();
    userFormNew.password = $('#new-password').val();
    const checked = []
    $('.new-role:checked').each(function() {
        checked.push($(this).val());
    });
    userFormNew.roles = checked;
    newUser().then(allUsers);
}